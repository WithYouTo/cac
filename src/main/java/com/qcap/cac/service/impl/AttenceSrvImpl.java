package com.qcap.cac.service.impl;

import com.qcap.cac.dao.AttenceMapper;
import com.qcap.cac.dto.AttenceSearchParam;
import com.qcap.cac.service.AttenceSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description: TODO
 * @author huangxiang
 * @date 2018/10/11 20:27
 */
@Service
@Transactional
public class AttenceSrvImpl implements AttenceSrv {

    @Autowired
    private AttenceMapper attenceMapper;

    @Override
    public List<Map<String, Object>> listAttence(AttenceSearchParam attenceSearchParam) {
        return this.attenceMapper.listAttence(attenceSearchParam);
    }
}
