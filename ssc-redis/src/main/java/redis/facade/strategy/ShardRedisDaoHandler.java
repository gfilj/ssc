package redis.facade.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.bean.PoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

import redis.RedisDao;
import redis.dao.ShardRedisDao;
import redis.facade.adapter.JedisPoolConfigAdapter;
import redis.util.Constant;
import redis.util.PropertiesManager;


public class ShardRedisDaoHandler extends AbstractRedisDaoHandler implements RedisDaoStrategy{

	private static final Logger logger = Logger.getLogger(ShardRedisDaoHandler.class);
	private static Lock lock = new ReentrantLock();
	
	@Override
	public RedisDao getRedisDao(String id) {
		String fullName = Constant.SHARD_PREFIX + id;
		PoolConfig poolConfig = PropertiesManager.getPoolConfig(fullName);
		
		RedisDao redisDao = null;
        lock.lock();
        try {
        	redisDao = super.getCache(fullName);
        	if (redisDao != null) {
        		return redisDao;
    		}
        	
        	JedisShardInfo shradInfo = null;
        	List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
        	for (int i = 0; i < poolConfig.getHostPort().size(); i++) {
        		shradInfo = new JedisShardInfo(poolConfig.getHostPort().get(i).getHost(), 
        				poolConfig.getHostPort().get(i).getPort(),
        				poolConfig.getTimeOut());
        		if (!StringUtils.isBlank(poolConfig.getPassword())) {
        			shradInfo.setPassword(poolConfig.getPassword());
        		}
        		shards.add(shradInfo);
        	}
        	
    		ShardedJedisPool shardJedisPool = new ShardedJedisPool(
    				JedisPoolConfigAdapter.toJedisPoolConfig(poolConfig),
    				shards);
    		
    		redisDao = new ShardRedisDao(shardJedisPool);
    		if (redisDao != null) {
                super.putCache(fullName, redisDao);
            }
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("获取shard redis pool出错",e);
        	throw new RuntimeException("获取shard redis pool出错",e);
        } finally {
            lock.unlock();
        }

        return redisDao;
	}

}
