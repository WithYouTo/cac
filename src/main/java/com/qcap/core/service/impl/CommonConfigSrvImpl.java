package com.qcap.core.service.impl;

import java.util.List;
import java.util.Map;

import com.qcap.core.dao.CommonConfigMapper;
import com.qcap.core.model.TbCommonConfig;
import com.qcap.core.service.CommonConfigSrv;
import com.qcap.core.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qcap.core.utils.AppUtils;

/**
 * 
 * @ClassName: CommonConfigSrvImpl 
 * @Description: TODO
 * @author: 聂**
 * @date: 2018年5月21日 上午10:58:38
 */
@Service
@Transactional
public class CommonConfigSrvImpl implements CommonConfigSrv {

	@Autowired
	private CommonConfigMapper commonConfigMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
    
    private static final String split=":";
	/**
	 * @Title: insert 
	 * @param cc
	 * @throws Exception
	 * @return: void
	 */
	@Override
	public void insert(TbCommonConfig cc) throws Exception {
		this.commonConfigMapper.insert(cc);
	}
	
	/**
	 * @Title: selectAll
	 * @param mapParam
	 * @return
	 * @throws Exception
	 * @see CommonConfigSrv#selectAll(java.util.Map)
	 */
	
	@Override
	public List<Map<String, Object>> selectByPage(Map<String, Object> mapParam)
			throws Exception {
		return this.commonConfigMapper.selectConfigByPage(mapParam);
	}
	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> selectTypes() {
		return this.commonConfigMapper.selectTypes();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String ifExist(Map map) {
		return this.commonConfigMapper.testExistKey(map);
	}

	@Override
	public void updateConfig(TbCommonConfig config) {
		this.commonConfigMapper.updateConfig(config);
	}

	@Override
	public void batchDeleteByIds(String[] array) {
		deleteConfigCache(this.commonConfigMapper.getDeleteItems(array));
		this.commonConfigMapper.batchDelete(array);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void deleteConfigCache(List<Map> dels){
		for(Map<String,Object> map:dels){
			String key= map.get("type_")+split+map.get("key_");
			redisUtil.delete(AppUtils.getApplicationName()+split+key);
		}
	}
	
	@Override
	public void inititalConfigCache() throws Exception {
		List<Map<String, Object>> configList = this.commonConfigMapper.selectConfigByPage(null);
		
		for(Map<String,Object> map:configList){
			String key= map.get("type_")+split+map.get("key_");
			String value=map.get("value_").toString();
			redisUtil.setNotExpired(AppUtils.getApplicationName()+split+key, value);
		}
		
	}


}
