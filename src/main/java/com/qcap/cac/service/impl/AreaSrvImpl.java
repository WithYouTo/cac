package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dto.AreaDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.RedisTools;
import com.qcap.cac.tools.UUIDUtils;
import com.qcap.core.model.ZTreeNode;
import com.qcap.core.utils.AppUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

import static com.qcap.core.utils.AppUtils.buildZTreeNodeByRecursive;

@Service
@Transactional
public class AreaSrvImpl  extends ServiceImpl<AreaMapper, TbArea> implements AreaSrv {

    @Resource
    private  AreaMapper areaMapper;

    @Override
    public List<ZTreeNode> initTree() {
        Map<String,Object> paraMap = new HashMap();
        List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
        programCodes.removeAll(Collections.singleton(""));
        paraMap.put("programCodes",programCodes);
        List<ZTreeNode> list = areaMapper.initTree(paraMap);
        list.add(ZTreeNode.createParent());
        return buildZTreeNodeByRecursive(list, new ArrayList<>(), e -> Objects.equals("-1", e.getPid()));
        //return areaMapper.initTree(paraMap);
    }

    @Override
    public void getAreaList(IPage<Map<String, Object>> page, AreaDto areaDto) {
        Map<String,Object> paraMap = new HashMap();
        List<String>  programCodes = AppUtils.getLoginUserProjectCodes();
        programCodes.removeAll(Collections.singleton(""));
        paraMap.put("programCodes",programCodes);
        paraMap.put("areaCode",areaDto.getAreaCode());
        paraMap.put("areaName",areaDto.getAreaName());
        paraMap.put("areaType",areaDto.getAreaType());
        paraMap.put("buildingPurpose",areaDto.getBuildingPurpose());
        List<Map<String, Object>> list =  areaMapper.selectAreaList(page,paraMap);
        page.setRecords(list);
    }

    @Override
    public TbArea insertArea(AreaDto areaDto) {

        if(StringUtils.isEmpty(areaDto.getAreaName())){
            throw new  RuntimeException("区域名称为空");
        }

        if(StringUtils.isEmpty(areaDto.getLevel())){
            throw new  RuntimeException("当前选择节点的层级为空");
        }

        //不同的父级，可以有相同的区域名称
        QueryWrapper<TbArea> wrapper = new QueryWrapper<>();
        wrapper.eq("SUPER_AREA_CODE",areaDto.getSuperAreaCode());
        wrapper.eq("AREA_NAME",areaDto.getAreaName());
        if(areaMapper.selectCount(wrapper) > 0){
            throw new  RuntimeException("区域名称已经存在");
        }

        TbArea area = new TbArea();
        //选中父级层级
        Integer level = Integer.parseInt(areaDto.getLevel());
        //新增子级的区域编码
        String areaCode = initAreaCode(level);
        BeanUtils.copyProperties(areaDto,area);
        //项目编码(查询父级的项目编码)
        String programCode = this.areaMapper.selectProgramCodeByAreaCode(area.getSuperAreaCode());
        area.setProgramCode(programCode);

        String areaId = UUIDUtils.getUUID();
        area.setAreaId(areaId);
        area.setAreaCode(areaCode);
        area.setLevel(Integer.toString(level + 1));//子级
        //子级排序
        Integer seqNo = this.areaMapper.selectMaxSeqNo(area.getSuperAreaCode());
        area.setSeqNo(Integer.toString(seqNo));

        EntityTools.setCreateEmpAndTime(area);
        this.areaMapper.insert(area);

        area = this.areaMapper.selectById(areaId);
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
        area = this.areaMapper.selectById(area.getAreaId());
        return area;
    }


    /**
     * 树所在层级
     * @param level
     * @return
     */
    private String initAreaCode(Integer level){
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
        Integer max = this.areaMapper.selectMaxNum(level + 1);
        Integer suffix = max == -1 ? 100 : max + 1;
        String areaCode = sb.toString() + suffix;
        return areaCode;
    }


    @Override
    public void deleteArea(String areaId) {
        if(StringUtils.isEmpty(areaId)){
            throw new  RuntimeException("未选中记录");
        }
        TbArea area = this.areaMapper.selectById(areaId);
        if(null == area){
            throw new  RuntimeException("根据区域主键没有查询到信息");
        }
        //判断是否有子级，如果有则提示无法删除
        if(areaMapper.checkSubAreaByAreaCode(area.getAreaCode()) > 0){
            throw new  RuntimeException("系统中此区域包含子区域，无法删除");
        }
        //判断是否有计划包含该区域
        if(areaMapper.checkPlanExistAreaCode(area.getAreaCode()) > 0){
            throw new  RuntimeException("系统中已有计划包含该区域，无法删除");
        }
        //判断是否有岗位包含该区域
        if(areaMapper.checkPositionExistAreaCode(area.getAreaCode()) > 0){
            throw new  RuntimeException("系统中已有岗位包含该区域，无法删除");
        }
        this.areaMapper.deleteById(areaId);
    }
}
