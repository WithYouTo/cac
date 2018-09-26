package com.qcap.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/** 
 * @ClassName: RedisUtil 
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年4月9日 下午10:24:08  
 */
@Component
@SuppressWarnings({ "unchecked", "rawtypes"})
public class RedisUtil {
	
	@Autowired
    private StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
    private RedisTemplate redisTemplate;
	
	/**
	 * redis存放list
	 * @Title: setList 
	 * @Description: TODO
	 * @param key
	 * @param mapList
	 * @return
	 * @return: Long
	 */
	public Long setList(String key,List<?> list) {
		return redisTemplate.opsForList().rightPush(key, list);
	}
	
	/**
	 * redis取值list
	 * @Title: setList 
	 * @Description: TODO
	 * @param key
	 * @param mapList
	 * @return
	 * @return: Long
	 */
	public List<?> getList(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}
	
	/**
	 * Redis的散列可以让用户将多个键值对存储到一个Redis键里面
	 * @Title: setMap 
	 * @Description: TODO
	 * @param key
	 * @param resultMap
	 * @return: void
	 */
	public void setMap(String key,Map resultMap){
		redisTemplate.opsForHash().putAll(key, resultMap);
	}
	
	/**
	 * 取出Map
	 * @Title: getMap 
	 * @Description: TODO
	 * @param key
	 * @return
	 * @return: Map
	 */
	public Map getMap(String key){
		return redisTemplate.opsForHash().entries(key);
	}
	
	
	/**
	 * 设置key的过期时间
	 * @Title: setExpire 
	 * @Description: TODO
	 * @param key
	 * @param timeout
	 * @param unit
	 * @return: void
	 */
	public void setExpire(String key,long timeout, TimeUnit unit){
		redisTemplate.expire(key, timeout, unit);
	}
	
	
	/** 
	 * Get the value of key.
	 * @Title: get
	 * @param key  must not be null.
	 * @return: String
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}
	
	/** 
	 * Set the value for key.The default timeout is 7 hours
	 * @Title: set
	 * @param key
	 * @param value
	 * @return: void
	 */
	public void set(String key,String value) {
		 set(key,value,7 * 60);
	}
	
	
	/**  
	 * Set the value for key with Not expired
	 * @Title: set 
	 * @Description: TODO
	 * @param key
	 * @param value
	 * @return: void
	 */
	public void setNotExpired(String key,String value) {
		 stringRedisTemplate.opsForValue().set(key, value);
	}
	
	
	
	/** 
	 * Set the value and expiration timeout for key.
	 * @Title: set
	 * @param key  key must not be null
	 * @param value
	 * @param timeout The default unit is minute
	 * @return: void
	 */
	public void set(String key,String value,long timeout) {
		set(key, value, timeout,TimeUnit.MINUTES);
	}
	
	
	
	
	
	/** 
	 * Set the value and expiration timeout for key.
	 * @Title: set
	 * @param key  key must not be null
	 * @param value
	 * @param timeout
	 * @param unit  must not be null.
	 * @return: void
	 */
	public void set(String key,String value,long timeout, TimeUnit unit) {
		 stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}
	
	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}
	public void delete(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
	}
	
	public String getUserId(String token) {
    	Map data = this.getMap(token);//去除redis中的原始数据
        String userId = TenpayUtil.toString(data.get("userId"));
        return userId;
    }
}
