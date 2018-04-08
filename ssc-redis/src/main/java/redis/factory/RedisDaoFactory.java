package redis.factory;

import redis.dao.SentinelRedisDao;
import redis.dao.ShardRedisDao;
import redis.dao.SimpleRedisDao;
import redis.facade.RedisDaoFacade;
import redis.facade.RedisPoolConfigType;

public class RedisDaoFactory {
	
	public static SimpleRedisDao getSimpleRedisDao(String id) {
		return (SimpleRedisDao) RedisDaoFacade.getRedisDaoHandler(RedisPoolConfigType.SIMPLE).getRedisDao(id);
	}
	
	public static ShardRedisDao getShardRedisDao(String id) {
		return (ShardRedisDao) RedisDaoFacade.getRedisDaoHandler(RedisPoolConfigType.SHARD).getRedisDao(id);
	}
	
	public static SentinelRedisDao getSentinelRedisDao(String id) {
		return (SentinelRedisDao) RedisDaoFacade.getRedisDaoHandler(RedisPoolConfigType.SENTINEL).getRedisDao(id);
	}
}
