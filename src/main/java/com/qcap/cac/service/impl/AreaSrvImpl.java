package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dto.AreaDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.tools.EntityTools;
import com.qcap.cac.tools.ToolUtil;
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
        if(StringUtils.isEmpty(areaDto.getSuperAreaCode())){
            throw new  RuntimeException("选中节点的区域编码为空");
        }

        if(StringUtils.isEmpty(areaDto.getAreaName())){
            throw new  RuntimeException("区域名称为空");
        }

        if(StringUtils.isEmpty(areaDto.getLevel())){
            throw new  RuntimeException("当前选择节点的层级为空");
        }

        //项目编码(查询父级的项目编码)
        String programCode = this.areaMapper.selectProgramCodeByAreaCode(areaDto.getSuperAreaCode());

        //不同的父级，可以有相同的区域名称
        QueryWrapper<TbArea> wrapper = new QueryWrapper<>();
        wrapper.eq("PROGRAM_CODE",programCode);
        wrapper.eq("SUPER_AREA_CODE",areaDto.getSuperAreaCode());
        wrapper.eq("AREA_NAME",areaDto.getAreaName());
        if(areaMapper.selectCount(wrapper) > 0){
            throw new  RuntimeException("区域名称已经存在");
        }

        TbArea area = new TbArea();
        BeanUtils.copyProperties(areaDto,area);
        area.setAreaId(UUIDUtils.getUUID());
        area.setProgramCode(programCode);

        //子级区域编码
        String areaCode = initAreaCode(areaDto);
        area.setAreaCode(areaCode);

        //子级层级
        area.setLevel(Integer.toString(ToolUtil.toInt(areaDto.getLevel()) + 1));

        //子级排序
        Integer seqNo = this.areaMapper.selectMaxSeqNo(area.getSuperAreaCode());
        area.setSeqNo(Integer.toString(seqNo));


        area = EntityTools.setCreateEmpAndTime(area);
        this.areaMapper.insert(area);
        return area;
    }

    @Override
    public TbArea updateArea(TbArea area) {

        if(StringUtils.isEmpty(area.getAreaId())){
            throw new  RuntimeException("修改区域的主键为空");
        }

        //项目编码(查询父级的项目编码)
        String programCode = this.areaMapper.selectProgramCodeByAreaCode(area.getAreaCode());

        QueryWrapper<TbArea> wrapper = new QueryWrapper<>();
        //不同的父级，可以有相同的区域名称
        wrapper.eq("PROGRAM_CODE",programCode);
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
     * 查询子级编码
     * @param areaDto
     * @return
     */
    private String initAreaCode(AreaDto areaDto){
        String superAreaCode = areaDto.getSuperAreaCode();
        //查询子级的后缀
        Integer suffix = this.areaMapper.selectAreaCodeSuffix(superAreaCode);
        suffix = suffix == -1 ? 100 : suffix + 1;
        String areaCode = superAreaCode + suffix;
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
