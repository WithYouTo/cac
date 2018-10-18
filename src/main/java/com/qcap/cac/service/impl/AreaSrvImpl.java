package com.qcap.cac.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.PubCodeMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.service.IWarehouseEntryService;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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
        area.setSeqNo("1");
        area.setFinalFlag("N");
        area.setCreateEmp("SYS");
        area.setCreateDate(new Date());
        this.areaMapper.insert(area);
        return area;
    }


    /**
     * 树所在层级
     * @param level
     * @return
     */
    private String initAreaCode(Integer level){

        //查询通用代码档----区域编号前缀(100)
        Map<String,String> param = new HashMap();
        param.put("configCode","AREA_PREFIX");
        List<Map> list = pubCodeMapper.selectConfigCodeApp(param);
        if(CollectionUtils.isEmpty(list)){
            throw new  RuntimeException("系统区域编码前缀未配置");
        }

        StringBuffer sb  = new StringBuffer();
        //层级 100100100
        String prefix = list.get(0).get("text").toString();
        if(level > 0){ //第一级无需叠加
            for(int i = 0 ;i < level; i++){
                sb.append(prefix);
            }
        }

        //子级的后缀编号
        Integer suffix = this.areaMapper.selectMaxNum(level + 1);
        String areaCode = sb.toString() + suffix;

        return areaCode;
    }
}
