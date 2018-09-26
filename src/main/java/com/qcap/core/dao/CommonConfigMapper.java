package com.qcap.core.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.qcap.core.model.TbCommonConfig;


@Repository
public interface CommonConfigMapper {
	
	/**
	 * 分页查询配置信息
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public List<Map<String,Object>> selectConfigByPage(Map map) throws Exception;
	
	/**
	 * 新增配置信息
	 * @param cc
	 * @throws Exception
	 */
	public void insert(TbCommonConfig cc) throws Exception;
	
	
	
	/**
	 * 查出所有类型信息
	 * @Title: selectTypes 
	 * @Description: TODO
	 * @return
	 * @return: List<Map>
	 */
	public List<Map> selectTypes();
	
	/**
	 * 检验key是否已经存在
	 * @Title: testExistKey 
	 * @Description: TODO
	 * @param map
	 * @return
	 * @return: String
	 */
	public String testExistKey(Map map);
	
	/**
	 * 更新配置信息
	 * @Title: updateConfig 
	 * @Description: TODO
	 * @param config
	 * @return: void
	 */
	public void updateConfig(TbCommonConfig config);
	
	/**
	 * 批量删除
	 * @Title: batchDelete 
	 * @Description: TODO
	 * @param array
	 * @return: void
	 */
	public void batchDelete(String[] array);
	
	/**
	 * 查出要删除的条目
	 * @Title: getDeleteItems 
	 * @Description: TODO
	 * @param array
	 * @return
	 * @return: Object
	 */
	public List<Map> getDeleteItems(String[] array);
	
	

}
