package com.qcap.core.service.impl;

import java.util.Arrays;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.dao.TbLoginLogMapper;
import com.qcap.core.entity.TbLoginLog;
import com.qcap.core.service.ITbLoginLogService;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author PH
 * @since 2018-08-01
 */
@Service
public class TbLoginLogServiceImpl implements ITbLoginLogService {
	@Resource
	private TbLoginLogMapper tbLoginLogMapper;

	@Override
	public void getLoginLogList(IPage<TbLoginLog> page, Map<String, String> parameters) {
		String startTime = parameters.get("startTime");
		String endTime = parameters.get("endTime");
		String logName = parameters.get("logName");
		QueryWrapper<TbLoginLog> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(startTime)) {
			wrapper.lambda().ge(TbLoginLog::getCreateTime, startTime);
		}
		if (StringUtils.isNotEmpty(endTime)) {
			wrapper.lambda().le(TbLoginLog::getCreateTime, endTime);
		}
		if (StringUtils.isNotEmpty(logName)) {
			wrapper.lambda().like(TbLoginLog::getLogName, logName + "%");
		}
		tbLoginLogMapper.selectPage(page, wrapper);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertItem(TbLoginLog tbLoginLog) {
		tbLoginLogMapper.insert(tbLoginLog);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchDeleteLoginLog(String ids) {
		tbLoginLogMapper.deleteBatchIds(Arrays.asList(ids.split(",")));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteLoginLogAll() {
		tbLoginLogMapper.delete(new UpdateWrapper<>());
	}
}
