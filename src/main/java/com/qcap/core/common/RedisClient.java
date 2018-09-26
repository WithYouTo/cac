package com.qcap.core.common;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;



public class RedisClient {

	    private Jedis jedis;//非切片额客户端连接
		private JedisPool jedisPool;//非切片连接池
	    private ShardedJedis shardedJedis;//切片额客户端连接
	    private ShardedJedisPool shardedJedisPool;//切片连接池
	    
	    public RedisClient() 
	    { 
	        initialPool(); 
	        initialShardedPool(); 
	        shardedJedis = shardedJedisPool.getResource(); 
	        jedis = jedisPool.getResource(); 
	        
	        
	    } 
	 
	    /**
	     * 初始化非切片池
	     */
	    private void initialPool() 
	    { 
	        // 池基本配置 
	        JedisPoolConfig config = new JedisPoolConfig(); 
	        config.setMaxIdle(8); 
	        config.setMinIdle(0); 
	        config.setMaxWaitMillis(-1); 
	        config.setTestOnBorrow(false); 
	        
	        jedisPool = new JedisPool(config,"192.168.185.17",6379);
	    }
	    
	    /** 
	     * 初始化切片池 
	     */ 
	    private void initialShardedPool() 
	    { 
	        // 池基本配置 
	        JedisPoolConfig config = new JedisPoolConfig(); 
	        config.setMaxIdle(8); 
	        config.setMinIdle(0); 
	        config.setMaxWaitMillis(-1);  
	        config.setTestOnBorrow(false); 
	        // slave链接 
	        List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>(); 
	        shards.add(new JedisShardInfo("192.168.185.17", 6379, "whxx123.")); 

	        // 构造池 
	        shardedJedisPool = new ShardedJedisPool(config, shards); 
	    } 


	    public Jedis getJedis() {
			return jedis;
		}

		public void setJedis(Jedis jedis) {
			this.jedis = jedis;
		}

		public ShardedJedisPool getShardedJedisPool() {
			return shardedJedisPool;
		}

		public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
			this.shardedJedisPool = shardedJedisPool;
		}
		
		
}
