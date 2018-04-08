package redis.dao;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Pipeline;

import redis.ExecutorBatchService;


public abstract class AbstractRedisDao {

	public abstract String flushAll();
	public abstract Set<String> keys(String pattern);
	public abstract String watch(String key);
	public abstract String unwatch(String key);
	public abstract boolean lock(String key, int seconds);
	public abstract boolean releaseLock(String key);
	public abstract List<String> mget(String...keys);
	//--------------------------------------------------Pipeline -----------------------------
	public abstract void pipeline(ExecutorBatchService<Pipeline> executor);
}
