package redis.facade.strategy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.bean.PoolConfig;
import redis.clients.jedis.JedisPool;

import redis.RedisDao;
import redis.dao.SimpleRedisDao;
import redis.facade.adapter.JedisPoolConfigAdapter;
import redis.util.Constant;
import redis.util.PropertiesManager;

public class SimpleRedisDaoHandler extends AbstractRedisDaoHandler implements RedisDaoStrategy{

	private static final Logger logger = Logger.getLogger(SimpleRedisDaoHandler.class);
	private static Lock lock = new ReentrantLock();
	
	@Override
	public RedisDao getRedisDao(String id) {
		String fullName = Constant.SIMPLE_PREFIX + id;
		PoolConfig poolConfig = PropertiesManager.getPoolConfig(fullName);
		
		RedisDao redisDao = null;
		lock.lock();
        try {
        	redisDao = super.getCache(fullName);
        	if (redisDao != null) {
        		return redisDao;
    		}
        	
        	JedisPool pool = null;
        	if (StringUtils.isBlank(poolConfig.getPassword())) {
        		 pool = new JedisPool(JedisPoolConfigAdapter.toJedisPoolConfig(poolConfig),
                		poolConfig.getHostPort().get(0).getHost(), 
                		poolConfig.getHostPort().get(0).getPort(), 
                		poolConfig.getTimeOut());
        	} else {
        		 pool = new JedisPool(JedisPoolConfigAdapter.toJedisPoolConfig(poolConfig),
                		poolConfig.getHostPort().get(0).getHost(), 
                		poolConfig.getHostPort().get(0).getPort(), 
                		poolConfig.getTimeOut(),
                		poolConfig.getPassword());
        	}
        	
            if (pool != null) {
                pool.returnResource(pool.getResource()); 

                redisDao = new SimpleRedisDao(pool);
                if (redisDao != null) {
                    super.putCache(fullName, redisDao);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("获取redis pool出错",e);
        	throw new RuntimeException("获取redis pool出错",e);
        } finally {
            lock.unlock();
        }
		
		return redisDao;
	}

}
