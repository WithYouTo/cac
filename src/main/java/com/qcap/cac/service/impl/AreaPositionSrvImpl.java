package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.dao.AreaPositionMapper;
import com.qcap.cac.dao.PubCodeMapper;
import com.qcap.cac.dto.AreaPositionDto;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.entity.TbAreaPosition;
import com.qcap.cac.service.AreaPositionSrv;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.tools.UUIDUtils;
import org.apache.commons.collections.CollectionUtils;
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
public class AreaPositionSrvImpl extends ServiceImpl<AreaPositionMapper, TbAreaPosition> implements AreaPositionSrv {

    @Resource
    private  AreaPositionMapper areaPositionMapper;

    @Resource
    private  AreaMapper areaMapper;


    @Override
    public List<Map> initTree() {
        return areaMapper.initTree();
    }

    @Override
    public List<TbAreaPosition> getAreaPositionList(AreaPositionDto areaPositionDto) {
       QueryWrapper<TbAreaPosition> wrapper = new QueryWrapper<>();

       if(StringUtils.isNotEmpty(areaPositionDto.getPositionName())){
           wrapper.like("POSITION_NAME","%" + areaPositionDto.getPositionName() + "%");
       }

        if(StringUtils.isNotEmpty(areaPositionDto.getAreaName())){
            wrapper.like("AREA_NAME","%" + areaPositionDto.getAreaName() + "%");
        }

        return this.areaPositionMapper.selectList(wrapper);
    }

    @Override
    public Integer insertAreaPosition(TbAreaPosition areaPosition) {
        areaPosition.setPositionCode(UUIDUtils.getPositionCode());
        areaPosition.setPositionId(UUIDUtils.getUUID());
        areaPosition.setCreateDate(new Date());
        areaPosition.setCreateEmp("SYS");
        return this.areaPositionMapper.insert(areaPosition);
    }

    @Override
    public Integer updateAreaPosition(TbAreaPosition areaPosition) {
        return this.areaPositionMapper.updateById(areaPosition);
    }

    @Override
    public Integer deleteAreaPosition(String positionId) {
        return this.areaPositionMapper.deleteById(positionId);
    }
}
