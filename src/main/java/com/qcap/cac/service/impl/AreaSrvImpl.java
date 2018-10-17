package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qcap.cac.dao.AreaMapper;
import com.qcap.cac.entity.TbArea;
import com.qcap.cac.service.AppTaskRestSrv;
import com.qcap.cac.service.AreaSrv;
import com.qcap.cac.service.IWarehouseEntryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AreaSrvImpl  extends ServiceImpl<AreaMapper, TbArea> implements AreaSrv {

    @Resource
    private  AreaMapper areaMapper;

    @Override
    public List<Map> initTree() {
        return areaMapper.initTree();
    }

    @Override
    public List<Map> getAreaList(String areaCode) {
        return areaMapper.selectAreaList(areaCode);
    }
}
