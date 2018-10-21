package com.qcap.core.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.dao.TbCommonConfigMapper;
import com.qcap.core.entity.TbCommonConfig;
import com.qcap.core.service.ITbCommonConfigService;
import com.qcap.core.utils.AppUtils;
import com.qcap.core.utils.RedisUtil;

import cn.hutool.core.util.StrUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author nyj
 * @since 2018-08-01
 */
@Service
public class TbCommonConfigServiceImpl implements ITbCommonConfigService {
	@Resource
	private TbCommonConfigMapper tbCommonConfigMapper;

	@Resource
	private RedisUtil redisUtil;

	@Override
	public void getCommonConfigList(IPage<TbCommonConfig> page, TbCommonConfig commonConfig) {
		QueryWrapper<TbCommonConfig> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(commonConfig.getKeyName())) {
			wrapper.eq("key_name", commonConfig.getKeyName());
		}
		if (StringUtils.isNotEmpty(commonConfig.getType())) {
			wrapper.eq("type", commonConfig.getType());
		}
		tbCommonConfigMapper.selectPage(page, wrapper);
	}

	@Override
	public List<Map<String, String>> selectTypes() {
		List<Map<String, String>> lsRecord = new ArrayList<Map<String, String>>();
		List<String> ls = tbCommonConfigMapper.selectDistinctType();
		if (CollectionUtils.isNotEmpty(ls)) {
			for (String type : ls) {
				Map<String, String> map = new HashMap<>();
				map.put("type", type);
				lsRecord.add(map);
			}
		}

		return lsRecord;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void insertItem(TbCommonConfig commonConfig) throws Exception {
		String keyName = commonConfig.getKeyName();
		TbCommonConfig config = tbCommonConfigMapper
				.selectOne(new QueryWrapper<TbCommonConfig>().eq("key_name", keyName));
		// TbCommonConfig config = tbCommonConfigMapper
		// .selectOne(new
		// QueryWrapper<TbCommonConfig>().lambda().eq(TbCommonConfig::getKeyName,
		// keyName));
		if (config != null) {
			throw new Exception("所选类型的Key已经存在");
		}
		tbCommonConfigMapper.insert(commonConfig);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateItem(TbCommonConfig commonConfig) {
		tbCommonConfigMapper.updateById(commonConfig);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void batchDeleteCommonConfig(Collection<String> ids) {
		Collection<TbCommonConfig> cols = tbCommonConfigMapper.selectBatchIds(ids);
		for (TbCommonConfig del : cols) {
			String key = del.getType() + StrUtil.COLON + del.getKeyName();
			redisUtil.delete(AppUtils.getApplicationName() + StrUtil.COLON + key);
			tbCommonConfigMapper.deleteById(del.getId());
		}
	}

	@Override
	public void initialConfigCache() {
		Collection<TbCommonConfig> cols = tbCommonConfigMapper.selectList(new QueryWrapper<>());
		for (TbCommonConfig tcc : cols) {
			String key = tcc.getType() + StrUtil.COLON + tcc.getKeyName();
			redisUtil.setNotExpired(AppUtils.getApplicationName() + StrUtil.COLON + key, tcc.getKeyValue());
		}
	}
}
