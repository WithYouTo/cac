package com.qcap.core.service;

import java.util.Map;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.TbLoginLog;

/**
 * <p>
 * 登录记录 服务类
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
public interface ITbLoginLogService {
	void getLoginLogList(IPage<TbLoginLog> page, Map<String, String> parameters);

	void insertItem(TbLoginLog tbLoginLog);

	void batchDeleteLoginLog(String ids);

	void deleteLoginLogAll();
}
