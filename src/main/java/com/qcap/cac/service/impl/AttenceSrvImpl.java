package com.qcap.cac.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.cac.dao.AttenceMapper;
import com.qcap.cac.dto.AttenceSearchDto;
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
    public void listAttence(IPage<Map<String, Object>> page, AttenceSearchDto attenceSearchDto) {
        List<Map<String,Object>> list = this.attenceMapper.listAttence(page,attenceSearchDto);
        page.setRecords(list);
    }
}
