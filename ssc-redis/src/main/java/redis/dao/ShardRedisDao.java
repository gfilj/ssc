package redis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.Tuple;

import redis.ExecutorBatchService;
import redis.RedisDao;

public class ShardRedisDao implements RedisDao{
	private static final Logger logger = Logger.getLogger(ShardRedisDao.class);
	
	private ShardedJedisPool shardedJedisPool;
	
	public ShardRedisDao(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

	@Override
	public long expired(String key, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.expired() , key:" + key, e);
			throw new RuntimeException("redis keys, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public boolean exists(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.exists() error, key: " + key + "  ", e);
			throw new RuntimeException("redis exists() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void del(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.del() , key:" + key, e);
			throw new RuntimeException("redis delKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void del(byte[] key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.del() , key:" + key, e);
			throw new RuntimeException("redis delKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void set(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.set() , key:" + key, e);
			throw new RuntimeException("redis set, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void set(byte[] key, byte[] value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.set() , key:" + key, e);
			throw new RuntimeException("redis set, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void setEx(String key, String value, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setEx() , key:" + key, e);
			throw new RuntimeException("redis setEx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public void setEx(byte[] key, byte[] value, int seconds) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setEx() error, key:" + key, e);
			throw new RuntimeException("redis setEx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long setNx(String key, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			Long n = jedis.setnx(key, value);
			return n;
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setnx error, key:" + key, e);
			throw new RuntimeException("redis setnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String get(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.getStringByKey() error, key :" + key, e);
			throw new RuntimeException("redis getStringByKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public byte[] get(byte[] key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.get() error, key :" + key, e);
			throw new RuntimeException("redis get, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String getset(String key, String val) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			String value = jedis.getSet(key, val);
			return value;
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.getSet() error, key :" + key, e);
			throw new RuntimeException("redis getSet, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void setExpire(String key, int time) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.expire(key, time);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setExpire() error, key:" + key, e);
			throw new RuntimeException("redis setExpire, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long incrBy(String key, long number) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.incrBy(key, number);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.incrBy() error, key:" + key, e);
			throw new RuntimeException("redis incrBy, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long decrBy(String key, long number) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.decrBy(key, number);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.decrBy() error, key:" + key, e);
			throw new RuntimeException("redis decrBy, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hset(String key, String field, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hset() error, key:" + key, e);
			throw new RuntimeException("redis hset, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String hmset(String key, Map<String, String> map) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hmset(key, map);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hmset() error, key:" + key, e);
			throw new RuntimeException("redis hmset, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hsetnx(String key, String fieid, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hsetnx(key, fieid, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String hget(String key, String field) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public Long hdel(String key, String... fields) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hdel(key, fields);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public Long hdel(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hdel(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public boolean hexists(String key, String fieid) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hexists(key, fieid);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hexists() error, key:" + key, e);
			throw new RuntimeException("redis hexists, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hincrby(String key, String field, long value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hincrby() error, key:" + key, e);
			throw new RuntimeException("redis hincrby, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hkeys(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hkeys() error, key:" + key, e);
			throw new RuntimeException("redis hkeys, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hlen(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hlen(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hlen() error, key:" + key, e);
			throw new RuntimeException("redis hlen, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hmget() error, key:" + key, e);
			throw new RuntimeException("redis hmget, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hgetAll() error, key:" + key, e);
			throw new RuntimeException("redis hgetAll, error key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public byte[] lindex(byte[] key, int i) {
		byte[] result = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			result = jedis.lindex(key, i);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lindex() error, key: " + key, e);
			throw new RuntimeException("redis lindex, error key = " + key + " index = " + i, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public String lindex(String key, int i) {
		String result = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			result = jedis.lindex(key, i);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lindex() error, key: " + key, e);
			throw new RuntimeException("redis lindex, error key = " + key + " index = " + i, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public long llen(byte[] key) {
		Long length = 0L;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			length = jedis.llen(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.llen() error, key: " + key, e);
			throw new RuntimeException("redis llen error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return length != null ? length : 0L;
	}

	@Override
	public long llen(String key) {
		Long length = 0L;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			length = jedis.llen(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.llen() error, key: " + key, e);
			throw new RuntimeException("redis llen error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return length != null ? length : 0L;
	}

	@Override
	public byte[] lpop(byte[] key) {
		byte[] result = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			result = jedis.lpop(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpop() error, key: " + key, e);
			throw new RuntimeException("redis lpop error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public String lpop(String key) {
		String result = null;
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			result = jedis.lpop(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpop() error, key: " + key, e);
			throw new RuntimeException("redis lpop error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public Long lpush(byte[] key, byte[] value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpush() error, key: " + key, e);
			throw new RuntimeException("redis lpush error, key = " + key + " value = " + value, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long lpush(String key, String val) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.lpush(key, val);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpush() error, key: " + key, e);
			throw new RuntimeException("redis lpush error, key = " + key + " value = " + val, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public List<String> lrange(String key, int start, int end) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lrange() error, key: " + key, e);
			throw new RuntimeException("redis lrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public Long lrem(String key, long count, String value) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.lrem(key, count, value);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lrem() error, key: " + key, e);
			throw new RuntimeException("redis lrem error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long rpush(String key, String val) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.rpush(key, val);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.rpush() error, key: " + key, e);
			throw new RuntimeException("redis rpush error, key = " + key + " value = " + val, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void zadd(String key, double score, String account) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.zadd(key, score, account);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zadd() error, key: " + key, e);
			throw new RuntimeException("redis zadd error, key = " + key + " value = " + account, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zcard(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zcard() error, key: " + key, e);
			throw new RuntimeException("redis zcard error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrevrank(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrevrank(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrank() error, key: " + key, e);
			throw new RuntimeException("redis zrevrank error, key = " + key + " member = " + member, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> zrevrange(String key, int offset, int count) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrevrange(key, offset, offset + count - 1);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrange() error, key: " + key, e);
			throw new RuntimeException("redis zrevrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Double zscore(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zscore() error, key: " + key, e);
			throw new RuntimeException("redis zscore error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Double zincrby(String key, String member, double score) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zincrby(key, score, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zincrby() error, key: " + key, e);
			throw new RuntimeException("redis zincrby error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrank(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrank(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrank error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrem(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrem error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zremrangeByScore(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zremrangeByScore() error, key: " + key, e);
			throw new RuntimeException("redis zremrangeByScore error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zremrangeByRank(String key, int offset, int count) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zremrangeByRank(key, offset, offset + count);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zremrangeByRank() error, key: " + key, e);
			throw new RuntimeException("redis zremrangeByRank error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<Tuple> zrevrangeByOffsetWithScores(String key, int offset, int count) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrevrangeWithScores(key, offset, offset + count - 1);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrangeByOffsetWithScores() error, key: " + key, e);
			throw new RuntimeException("redis zrevrangeWithScores error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<Tuple> zrevrangeWithScore(String key, int startIdx, int endIdx) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.zrevrangeWithScores(key, startIdx, endIdx);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrangeWithScore() error, key: " + key, e);
			throw new RuntimeException("redis zrevrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	//Set方法
	public String spop(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.spop(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.spop() error, key: " + key, e);
			throw new RuntimeException("redis spop error, key: = " + key, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public Long sadd(String key, String[] member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long sadd(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long srem(String key, String member) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.srem(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> smembers(String key) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.smembers() error, key: " + key + "  ", e);
			throw new RuntimeException("redis smembers() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}

	public void shardPipeline(ExecutorBatchService<ShardedJedisPipeline> executor) {
		ShardedJedis jedis = null;
		try {
			jedis = shardedJedisPool.getResource();
			ShardedJedisPipeline shardPipelined = jedis.pipelined();
			executor.execute(shardPipelined);
			shardPipelined.syncAndReturnAll();  
		} catch (Exception e) {
			if (jedis != null) {
				shardedJedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.pipeline() error", e);
			throw new RuntimeException("redis pipeline error", e);
		} finally {
			if (jedis != null) {
				shardedJedisPool.returnResource(jedis);
			}
		}
	}
	
	//set get
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}

}
