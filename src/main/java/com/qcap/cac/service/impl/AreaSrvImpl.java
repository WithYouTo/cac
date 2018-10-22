package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.PubCodeMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AreaSrvImpl  extends ServiceImpl<AreaMapper, TbArea> implements AreaSrv {

    @Resource
    private  AreaMapper areaMapper;

    @Resource
    private PubCodeMapper pubCodeMapper;

    @Override
    public List<Map> initTree() {
        return areaMapper.initTree();
    }

    @Override
    public List<Map> getAreaList(String areaCode) {
        return areaMapper.selectAreaList(areaCode);
    }

    @Override
    public TbArea insertArea(TbArea area) {

        if(StringUtils.isEmpty(area.getAreaName())){
            throw new  RuntimeException("区域名称为空");
        }

        if(StringUtils.isEmpty(area.getLevel())){
            throw new  RuntimeException("选择树节点的层级为空");
        }

        QueryWrapper<TbArea> wrapper = new QueryWrapper<>();
        //不同的父级，可以有相同的区域名称
        wrapper.eq("SUPER_AREA_CODE",area.getSuperAreaCode());
        wrapper.eq("AREA_NAME",area.getAreaName());
        if(areaMapper.selectCount(wrapper) > 0){
            throw new  RuntimeException("区域名称已经存在");
        }

        //选中父级
        Integer level = Integer.parseInt(area.getLevel());
        String areaCode = initAreaCode(level);

        area.setAreaId(UUIDUtils.getUUID());
        area.setAreaCode(areaCode);
        area.setLevel(Integer.toString(level + 1));//子级
        //排序
        Integer seqNo = this.areaMapper.selectMaxSeqNo(area.getSuperAreaCode());
        area.setSeqNo(Integer.toString(seqNo));

        area.setFinalFlag("N");
        area.setCreateEmp("SYS");
        area.setCreateDate(new Date());
        this.areaMapper.insert(area);
        return area;
    }

    @Override
    public TbArea updateArea(TbArea area) {

        if(StringUtils.isEmpty(area.getAreaId())){
            throw new  RuntimeException("修改区域的主键为空");
        }


        QueryWrapper<TbArea> wrapper = new QueryWrapper<>();
        //不同的父级，可以有相同的区域名称
        wrapper.eq("SUPER_AREA_CODE",area.getSuperAreaCode());
        wrapper.eq("AREA_NAME",area.getAreaName());
        if(areaMapper.selectCount(wrapper) > 1){
            throw new  RuntimeException("区域名称已经存在");
        }

        this.areaMapper.updateById(area);
        return area;
    }


    /**
     * 树所在层级
     * @param level
     * @return
     */
    private String initAreaCode(Integer level){

        //查询通用代码档----区域编号前缀(100)
//        Map<String,String> param = new HashMap();
//        param.put("configCode","AREA_PREFIX");
//        List<Map> list = pubCodeMapper.selectConfigCodeApp(param);
//        if(CollectionUtils.isEmpty(list)){
//            throw new  RuntimeException("系统区域编码前缀未配置");
//        }
        //查询系统配置档----区域编号前缀(100)
        String prefix = RedisTools.getCommonConfig("CAC_AREA_PREFIX");
        if(StringUtils.isEmpty(prefix)){
            throw new  RuntimeException("系统区域编码前缀未配置");
        }

        StringBuffer sb  = new StringBuffer();
        //第一级100  第二级100100
        if(level > 0){
            for(int i = 0 ;i < level; i++){
                sb.append(prefix);
            }
        }

        //后缀编号
        Integer suffix = this.areaMapper.selectMaxNum(level + 1);
        String areaCode = sb.toString() + suffix;

        return areaCode;
    }
}
