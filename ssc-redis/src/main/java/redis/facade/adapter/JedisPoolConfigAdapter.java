package redis.facade.adapter;

import redis.bean.PoolConfig;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolConfigAdapter {

	private static final int MAX_TOTAL = 100;
	private static final int MAX_IDLE = 30;
	private static final long MAX_WAIT = 2000;
	private static final boolean TEST_ON_BORROW = true;
	
	public static final JedisPoolConfig DEFAULT_POOL_CONFIG = new JedisPoolConfig();

	static {
          DEFAULT_POOL_CONFIG.setMaxTotal(MAX_TOTAL);
          DEFAULT_POOL_CONFIG.setMaxIdle(MAX_IDLE);
          DEFAULT_POOL_CONFIG.setMaxWaitMillis(MAX_WAIT);
          DEFAULT_POOL_CONFIG.setTestOnBorrow(TEST_ON_BORROW);
	}
	
	public static JedisPoolConfig toJedisPoolConfig(PoolConfig poolConfig) {
		
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(poolConfig.getMaxTotal());
		jedisPoolConfig.setMaxIdle(poolConfig.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(poolConfig.getMaxWaitMillions());
		jedisPoolConfig.setTestOnBorrow(poolConfig.isTestOnBorrow());
		
		return jedisPoolConfig;
	}
}
