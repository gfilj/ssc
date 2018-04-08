package redis.facade.strategy;

import redis.RedisDao;

public interface RedisDaoStrategy {

	public RedisDao getRedisDao(String id);
}
