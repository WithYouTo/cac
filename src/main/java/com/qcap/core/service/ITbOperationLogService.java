package com.qcap.core.service;

import java.util.Collection;
import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.TbOperationLog;

/**
 * <p>
 * 操作日志 服务类
 * </p>
 *
 * @author PH
 * @since 2018-08-02
 */
public interface ITbOperationLogService {
	void getOperationLogList(IPage<TbOperationLog> page, Map<String, String> params);

	void insertItem(TbOperationLog operationLog);

	void batchDeleteOperationLog(Collection<String> ids);

	void deleteOperationLogAll();

}
