package redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Tuple;

public interface RedisDao {

	//-------------------------------------KEYS--------------------------------------

	public long expired(String key, int seconds);
	
	/**
	 * 判断key是否已经存在redis中
	 */
	public boolean exists(String key);


	//-------------------------------------String--------------------------------------
	/**
	 * 根据key,删除记录
	 */
	public void del(String key);

	/**
	 * 删除
	 * @param key
	 */
	public void del(byte[] key);

	/**
	 * 插入string
	 */
	public void set(String key, String value);

	/**
	 * 序列化的对象
	 * @param key
	 * @param value
	 */
	public void set(byte[] key, byte[] value);

	/**
	 * 插入string，可设置超时时间
	 */
	public void setEx(String key, String value, int seconds);

	/**
	 * 设置过期时间
	 */
	public void setEx(byte[] key, byte[] value, int seconds);

	/**
	 * 如存在则不设置该值
	 * @return 
	 */
	public Long setNx(String key, String value);

	/**
	 * 获取String,根据key
	 */
	public String get(String key);
	
	/**
	 * 获取key对应的对象
	 * @param key
	 * @return
	 */
	public byte[] get(byte[] key);
	
	/**
	 * 获取key对应的对象，并且set新值
	 * @param key
	 * @return
	 */
	public String getset(String key, String value);

	/**
	 * 设置记录的有效期
	 */
	public void setExpire(String key, int time);

	/**
	 * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用
	 * @param key
	 * @param number
	 * @return
	 */
	public long incrBy(String key, long number);

	/**
	 * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用
	 * @param key
	 * @param number
	 * @return
	 */
	public long decrBy(String key, long number);

	//-------------------------------------Hash--------------------------------------
	public long hset(String key, String field, String value);

	public String hmset(String key, Map<String, String> map);

	public long hsetnx(String key, String field, String value);

	public String hget(String key, String fields);

	public Long hdel(String key, String... field);

	public Long hdel(String key);

	public boolean hexists(String key, String field);

	public long hincrby(String key, String field, long value);

	public Set<String> hkeys(String key);

	public long hlen(String key);

	public Map<String, String> hgetAll(String key);

	/**
	  * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null
	  * @param String key
	  * @param String... fieids  存储位置
	  * @return List<String>
	 **/
	public List<String> hmget(String key, String... fieids);

	//-------------------------------------LIST--------------------------------------
	public byte[] lindex(byte[] key, int i);

	public String lindex(String key, int i);

	public long llen(byte[] key);

	public long llen(String key);

	public byte[] lpop(byte[] key);

	public String lpop(String key);

	public Long lpush(byte[] key, byte[] value);

	public Long lpush(String key, String value);

	public Long rpush(String key, String value);

	public List<String> lrange(String key, int start, int end);
	
	public Long lrem(String key, long count, String value);

	//-------------------------------------SortSet--------------------------------------
	/**
	 * 记录用户在某个榜单下的得分
	 */
	public void zadd(String key, double score, String account);

	public Long zcard(String key);

	public Double zincrby(String key, String member, double score);

	/**
	 * 获取用户在某个榜单下的排名
	 */
	public Long zrevrank(String key, String account);

	public Set<String> zrevrange(String key, int offset, int count);

	public Double zscore(String key, String member);

	public Long zrank(String key, String member);

	public Set<String> zrange(String key, long start, long end);

	public Long zrem(String key, String member);

	public Long zremrangeByScore(String key, double start, double end);

	public Long zremrangeByRank(String key, int offset, int count);

	public Set<Tuple> zrevrangeByOffsetWithScores(String key, int offset, int count);

	public Set<Tuple> zrevrangeWithScore(String key, int startIdx, int endIdx);

	//-------------------------------------Set--------------------------------------
	/**
	 * 获取Set中的一个元素并移除
	 */
	public String spop(String key);

	/**
	 * 向Set中添加一个元素
	 */
	public Long sadd(String key, String[] member);

	public Long sadd(String key, String member);

	/**
	 * 从Set中移除一个元素
	 */
	public Long srem(String key, String member);

	/**
	 * 返回名称为key的set的所有元素 
	 */
	public Set<String> smembers(String key);
	
}
