package com.qcap.core.service;

import java.util.List;
import java.util.Map;

import com.qcap.core.model.TbCommonConfig;

public interface CommonConfigSrv {
	
	
	/**
	 * 分页查询信息
	 * @param mapParam
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectByPage(Map<String, Object> mapParam) throws Exception;
	
	/**
	 * 添加信息
	 * @param mapParam
	 * @return
	 * @throws Exception
	 */	
	public void insert(TbCommonConfig cc)throws Exception;
	
	/**
	 * 查询所有类型信息
	 * @Title: selectTypes 
	 * @return: void
	 */
	public List<Map> selectTypes();
	
	/**
	 * key是否存在
	 * @Title: ifExist 
	 * @param map
	 * @return
	 * @return: boolean
	 */
	public String ifExist(Map map);
	
	/**
	 * 修改配置信息
	 * @Title: update 
	 * @param config
	 * @return: void
	 */
	public void updateConfig(TbCommonConfig config);
	
	/**
	 * 批量删除操作
	 * @Title: batchDeleteByIds 
	 * @param array
	 * @return: void
	 */
	public void batchDeleteByIds(String[] array);
	
	/**
	 * 初始化/刷新缓存
	 * @Title: inititalConfigCache 
	 * @throws Exception
	 * @return: void
	 */
	public void inititalConfigCache()  throws Exception;

	
}
