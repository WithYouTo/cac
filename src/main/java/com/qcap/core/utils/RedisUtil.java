package com.qcap.core.utils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import cn.hutool.core.util.ObjectUtil;

/**
 * @ClassName: RedisUtil
 * @Description: TODO
 * @author: zengxin
 * @date: 2018年4月9日 下午10:24:08
 */
@Component
@SuppressWarnings({ "unchecked", "rawtypes" })
public class RedisUtil {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * redis存放list
	 * 
	 * @param key
	 *            键
	 * @param list
	 *            List集合
	 * @return Long
	 */
	public Long setList(String key, List<?> list) {
		return redisTemplate.opsForList().rightPush(key, list);
	}

	/**
	 * redis取值list
	 * 
	 * @param key
	 *            键
	 * @return java.util.List
	 */
	public List<?> getList(String key) {
		return redisTemplate.opsForList().range(key, 0, -1);
	}

	/**
	 * Redis的散列可以让用户将多个键值对存储到一个Redis键里面
	 * 
	 * @param key
	 *            键
	 * @param resultMap
	 *            结果Map
	 */
	public void setMap(String key, Map resultMap) {
		redisTemplate.opsForHash().putAll(key, resultMap);
	}

	/**
	 * 取出Map
	 * 
	 * @param key
	 *            键
	 * @return Map
	 */
	public Map getMap(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	/**
	 * 设置key的过期时间
	 * 
	 * @param key
	 *            键
	 * @param timeout
	 *            超时
	 * @param unit
	 *            timeUnit
	 */
	public void setExpire(String key, long timeout, TimeUnit unit) {
		redisTemplate.expire(key, timeout, unit);
	}

	/**
	 * Get the value of key.
	 * 
	 * @param key
	 *            must not be null.
	 * @return String
	 */
	public String get(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * Set the value for key.The default timeout is 7 hours
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void set(String key, String value) {
		set(key, value, 7 * 60);
	}

	/**
	 * Set the value for key with Not expired
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void setNotExpired(String key, String value) {
		stringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * Set the value and expiration timeout for key.
	 * 
	 * @param key
	 *            key must not be null
	 * @param value
	 *            值
	 * @param timeout
	 *            The default unit is minute
	 */
	public void set(String key, String value, long timeout) {
		set(key, value, timeout, TimeUnit.MINUTES);
	}

	/**
	 * Set the value and expiration timeout for key.
	 * 
	 * @param key
	 *            key must not be null
	 * @param value
	 *            值
	 * @param timeout
	 *            超时
	 * @param unit
	 *            must not be null.
	 */
	public void set(String key, String value, long timeout, TimeUnit unit) {
		stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
	}

	public void delete(String key) {
		stringRedisTemplate.delete(key);
	}

	public void delete(Collection<String> keys) {
		stringRedisTemplate.delete(keys);
	}

	public String getUserId(String token) {
		// 去除redis中的原始数据
		Map data = this.getMap(token);
		return ObjectUtil.toString(data.get("userId"));
	}

	public void set(String key, Object o) {
		stringRedisTemplate.opsForValue().set(key, JSON.toJSONString(o));
		;
	}

	public <T> T get(String key, Class<T> clazz) {
		String jsonString = stringRedisTemplate.opsForValue().get(key);
		return JSON.parseObject(jsonString, clazz);
	}
}
