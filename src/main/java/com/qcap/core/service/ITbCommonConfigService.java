package com.qcap.core.service;

import java.util.Collection;
import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qcap.core.entity.TbCommonConfig;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 魔都架构师nyj
 * @since 2018-08-01
 */
public interface ITbCommonConfigService {
	/**
	 * 分页查询配置表
	 *
	 * @param page
	 *            分页对象
	 * @param commonConfig
	 *            参数对象
	 */
	void getCommonConfigList(IPage<TbCommonConfig> page, TbCommonConfig commonConfig);

	/**
	 * 查询types
	 *
	 * @return List
	 */
	List<String> selectTypes();

	/**
	 * 插入记录
	 *
	 * @param commonConfig
	 *            对象
	 * @throws Exception
	 *             检查型异常
	 */
	void insertItem(TbCommonConfig commonConfig) throws Exception;

	/**
	 * 修改记录
	 *
	 * @param commonConfig
	 *            修改的对象
	 */
	void updateItem(TbCommonConfig commonConfig);

	/**
	 * 批量删除
	 *
	 * @param ids
	 *            id集合
	 */
	void batchDeleteCommonConfig(Collection<String> ids);

	/**
	 * 初始化redis缓存
	 */
	void initialConfigCache() throws Exception;
}
