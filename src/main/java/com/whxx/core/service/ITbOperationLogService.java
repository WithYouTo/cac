package com.whxx.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whxx.core.entity.TbOperationLog;

import java.util.Collection;
import java.util.Map;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author PH
 * @since 2018-08-02
 */
public interface ITbOperationLogService
{
    void getOperationLogList(IPage<TbOperationLog> page, Map<String, String> params);

    void insertItem(TbOperationLog operationLog);

    void batchDeleteOperationLog(Collection<String> ids);

    void deleteOperationLogAll();

}
