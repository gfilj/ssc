package redis.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;
import redis.clients.jedis.Tuple;

import redis.ExecutorBatchService;
import redis.RedisDao;

public class SentinelRedisDao extends AbstractRedisDao implements RedisDao{
	private static final Logger logger = Logger.getLogger(AbstractRedisDao.class);
	
	private JedisSentinelPool jedisPool;
	
	public SentinelRedisDao() {

	}

	public SentinelRedisDao(JedisSentinelPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public List<String> mget(String...keys) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.mget(keys);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.mget()", e);
			throw new RuntimeException("redis mget, error ", e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public String flushAll() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.flushAll();
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.flushAll()", e);
			throw new RuntimeException("redis flushAll, error ", e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long expired(String key, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expire(key, seconds);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.expired() , key:" + key, e);
			throw new RuntimeException("redis keys, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> keys(String pattern) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.keys(pattern);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.keys() , key:" + pattern, e);
			throw new RuntimeException("redis keys, error key = " + pattern, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.exists() error, key: " + key + "  ", e);
			throw new RuntimeException("redis exists() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public String watch(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.watch(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.watch() error, key: " + key + "  ", e);
			throw new RuntimeException("redis watch() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public String unwatch(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.unwatch();
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.watch() error, key: " + key + "  ", e);
			throw new RuntimeException("redis watch() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public void del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.del() , key:" + key, e);
			throw new RuntimeException("redis delKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void del(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.del() , key:" + key, e);
			throw new RuntimeException("redis delKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.set() , key:" + key, e);
			throw new RuntimeException("redis set, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void set(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.set() , key:" + key, e);
			throw new RuntimeException("redis set, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void setEx(String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setEx() , key:" + key, e);
			throw new RuntimeException("redis setEx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public void setEx(byte[] key, byte[] value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setEx() error, key:" + key, e);
			throw new RuntimeException("redis setEx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long setNx(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Long n = jedis.setnx(key, value);
			return n;
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setnx error, key:" + key, e);
			throw new RuntimeException("redis setnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.getStringByKey() error, key :" + key, e);
			throw new RuntimeException("redis getStringByKey, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public byte[] get(byte[] key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.get(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.get() error, key :" + key, e);
			throw new RuntimeException("redis get, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String getset(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String value = jedis.getSet(key, val);
			return value;
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.getSet() error, key :" + key, e);
			throw new RuntimeException("redis getSet, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void setExpire(String key, int time) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.expire(key, time);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.setExpire() error, key:" + key, e);
			throw new RuntimeException("redis setExpire, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long incrBy(String key, long number) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(key, number);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.incrBy() error, key:" + key, e);
			throw new RuntimeException("redis incrBy, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long decrBy(String key, long number) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decrBy(key, number);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.decrBy() error, key:" + key, e);
			throw new RuntimeException("redis decrBy, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hset(String key, String field, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hset() error, key:" + key, e);
			throw new RuntimeException("redis hset, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String hmset(String key, Map<String, String> map) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmset(key, map);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hmset() error, key:" + key, e);
			throw new RuntimeException("redis hmset, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hsetnx(String key, String fieid, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hsetnx(key, fieid, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hget(key, field);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public Long hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(key, fields);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}

	}

	@Override
	public Long hdel(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hsetnx() error, key:" + key, e);
			throw new RuntimeException("redis hsetnx, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public boolean hexists(String key, String fieid) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hexists(key, fieid);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hexists() error, key:" + key, e);
			throw new RuntimeException("redis hexists, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hincrby(String key, String field, long value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hincrBy(key, field, value);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hincrby() error, key:" + key, e);
			throw new RuntimeException("redis hincrby, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> hkeys(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hkeys(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hkeys() error, key:" + key, e);
			throw new RuntimeException("redis hkeys, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public long hlen(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hlen(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hlen() error, key:" + key, e);
			throw new RuntimeException("redis hlen, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmget(key, fields);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hmget() error, key:" + key, e);
			throw new RuntimeException("redis hmget, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hgetAll(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.hgetAll() error, key:" + key, e);
			throw new RuntimeException("redis hgetAll, error key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public byte[] lindex(byte[] key, int i) {
		byte[] result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lindex(key, i);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lindex() error, key: " + key, e);
			throw new RuntimeException("redis lindex, error key = " + key + " index = " + i, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public String lindex(String key, int i) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lindex(key, i);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lindex() error, key: " + key, e);
			throw new RuntimeException("redis lindex, error key = " + key + " index = " + i, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public long llen(byte[] key) {
		Long length = 0L;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			length = jedis.llen(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.llen() error, key: " + key, e);
			throw new RuntimeException("redis llen error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return length != null ? length : 0L;
	}

	@Override
	public long llen(String key) {
		Long length = 0L;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			length = jedis.llen(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.llen() error, key: " + key, e);
			throw new RuntimeException("redis llen error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return length != null ? length : 0L;
	}

	@Override
	public byte[] lpop(byte[] key) {
		byte[] result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lpop(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpop() error, key: " + key, e);
			throw new RuntimeException("redis lpop error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public String lpop(String key) {
		String result = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.lpop(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpop() error, key: " + key, e);
			throw new RuntimeException("redis lpop error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return result;
	}

	@Override
	public Long lpush(byte[] key, byte[] value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lpush(key, value);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpush() error, key: " + key, e);
			throw new RuntimeException("redis lpush error, key = " + key + " value = " + value, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long lpush(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lpush(key, val);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lpush() error, key: " + key, e);
			throw new RuntimeException("redis lpush error, key = " + key + " value = " + val, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public List<String> lrange(String key, int start, int end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lrange() error, key: " + key, e);
			throw new RuntimeException("redis lrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	@Override
	public Long lrem(String key, long count, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrem(key, count, value);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lrem() error, key: " + key, e);
			throw new RuntimeException("redis lrem error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long rpush(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.rpush(key, val);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.rpush() error, key: " + key, e);
			throw new RuntimeException("redis rpush error, key = " + key + " value = " + val, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public void zadd(String key, double score, String account) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.zadd(key, score, account);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zadd() error, key: " + key, e);
			throw new RuntimeException("redis zadd error, key = " + key + " value = " + account, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zcard(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zcard(key);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zcard() error, key: " + key, e);
			throw new RuntimeException("redis zcard error, key = " + key, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrevrank(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrank(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				jedisPool.returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrank() error, key: " + key, e);
			throw new RuntimeException("redis zrevrank error, key = " + key + " member = " + member, e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> zrevrange(String key, int offset, int count) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrange(key, offset, offset + count - 1);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrange() error, key: " + key, e);
			throw new RuntimeException("redis zrevrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Double zscore(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zscore(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zscore() error, key: " + key, e);
			throw new RuntimeException("redis zscore error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Double zincrby(String key, String member, double score) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zincrby(key, score, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zincrby() error, key: " + key, e);
			throw new RuntimeException("redis zincrby error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrank(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrank(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrank error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> zrange(String key, long start, long end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrange(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long zrem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrem(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrank() error, key: " + key, e);
			throw new RuntimeException("redis zrem error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zremrangeByScore(key, start, end);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zremrangeByScore() error, key: " + key, e);
			throw new RuntimeException("redis zremrangeByScore error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long zremrangeByRank(String key, int offset, int count) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zremrangeByRank(key, offset, offset + count);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zremrangeByRank() error, key: " + key, e);
			throw new RuntimeException("redis zremrangeByRank error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Set<Tuple> zrevrangeByOffsetWithScores(String key, int offset, int count) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrangeWithScores(key, offset, offset + count - 1);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrangeByOffsetWithScores() error, key: " + key, e);
			throw new RuntimeException("redis zrevrangeWithScores error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Set<Tuple> zrevrangeWithScore(String key, int startIdx, int endIdx) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.zrevrangeWithScores(key, startIdx, endIdx);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.zrevrangeWithScore() error, key: " + key, e);
			throw new RuntimeException("redis zrevrange error, key = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	//Set方法
	public String spop(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.spop(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.spop() error, key: " + key, e);
			throw new RuntimeException("redis spop error, key: = " + key, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}

	}

	@Override
	public Long sadd(String key, String[] member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long sadd(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.sadd(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Long srem(String key, String member) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.srem(key, member);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.sadd() error, key: " + key + "  " + member, e);
			throw new RuntimeException("redis sadd error, key: = " + key + "  " + member, e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public Set<String> smembers(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.smembers(key);
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.smembers() error, key: " + key + "  ", e);
			throw new RuntimeException("redis smembers() error, key: = " + key + "  ", e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}

	@Override
	public void pipeline(ExecutorBatchService<Pipeline> executor) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Pipeline pipelined = jedis.pipelined();
			executor.execute(pipelined);
			pipelined.sync();
		} catch (Exception e) {
			if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.pipeline() error", e);
			throw new RuntimeException("redis pipeline error", e);
		} finally {
			if (jedis != null) {
				getJedisPool().returnResource(jedis);
			}
		}
	}
	
	@Override
	public boolean lock(String key, int seconds) {
		Jedis jedis = null;
        try {
        	jedis = jedisPool.getResource();
            
            if(jedis.exists(key)) {
                return false;
            }
            jedis.watch(key);
            Transaction t = jedis.multi();
            Response<Long> response = t.setnx(key, "0");
            t.expire(key, seconds);
            List<Object> list = t.exec();

            return list != null && response.get() > 0;
        } catch (Exception e) {
        	if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.lock() error", e);
			throw new RuntimeException("redis lock error", e);
        } finally {
            if (jedis != null) {
            	getJedisPool().returnResource(jedis);
            }
        }
    }
	
	@Override
	public boolean releaseLock(String key) {
		Jedis jedis = null;
        try {
        	jedis = jedisPool.getResource();
            return jedis.del(key) > 0;
        } catch (Exception e) {
        	if (jedis != null) {
				getJedisPool().returnBrokenResource(jedis);
			}
			logger.error("RedisDao.releaseLock() error", e);
			throw new RuntimeException("redis releaseLock error", e);
        } finally {
            if (jedis != null) {
            	getJedisPool().returnResource(jedis);
            }
        }
    }

	//set get
	public JedisSentinelPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisSentinelPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
