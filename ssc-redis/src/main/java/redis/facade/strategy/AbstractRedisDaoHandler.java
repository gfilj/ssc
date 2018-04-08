package redis.facade.strategy;

import java.util.HashMap;
import java.util.Map;

import redis.RedisDao;

public class AbstractRedisDaoHandler {

	private static final Map<String,RedisDao> cacheRedisDaoMap = new HashMap<>();

	public final void putCache(String fullNameKey, RedisDao redisDao) {
		cacheRedisDaoMap.put(fullNameKey, redisDao);
	}
	public final RedisDao getCache(String fullNameKey) {
		return cacheRedisDaoMap.get(fullNameKey);
	}
	
}
