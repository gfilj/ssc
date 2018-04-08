package redis.facade;

public enum RedisPoolConfigType {

	SIMPLE(1),
	SHARD(2),
	SENTINEL(3);
	
	private int type;
	
	private RedisPoolConfigType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
}
