package redis.facade;

import redis.facade.strategy.RedisDaoStrategy;
import redis.facade.strategy.SentinelRedisDaoHandler;
import redis.facade.strategy.ShardRedisDaoHandler;
import redis.facade.strategy.SimpleRedisDaoHandler;

import java.util.HashMap;
import java.util.Map;

public class RedisDaoFacade {

	private final static Map<Integer,RedisDaoStrategy> poolConfigMap = new HashMap<>();
	static {
		poolConfigMap.put(RedisPoolConfigType.SIMPLE.getType(), new SimpleRedisDaoHandler());
		poolConfigMap.put(RedisPoolConfigType.SHARD.getType(), new ShardRedisDaoHandler());
		poolConfigMap.put(RedisPoolConfigType.SENTINEL.getType(), new SentinelRedisDaoHandler());
	}
	
	public static RedisDaoStrategy getRedisDaoHandler (RedisPoolConfigType type) {
		if (poolConfigMap.containsKey(type.getType())) {
			return poolConfigMap.get(type.getType());
		}
		return new SimpleRedisDaoHandler();
	}
}
