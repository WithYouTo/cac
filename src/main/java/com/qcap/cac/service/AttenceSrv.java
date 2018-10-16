package com.qcap.cac.service;

import com.qcap.cac.dto.AttenceSearchDto;

import java.util.List;
import java.util.Map;

/**
 *
 * @Description:
 * @author huangxiang
 * @date 2018/10/11 20:25
 */
public interface AttenceSrv {

    /**
     *
     * @Description: 获取考勤列表
     *
     *
     * @MethodName: listAttence
     * @Parameters: [attenceSearchDto]
     * @ReturnType: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     *
     * @author huangxiang
     * @date 2018/10/11 20:26
     */
    List<Map<String, Object>> listAttence(AttenceSearchDto attenceSearchDto);
}
