package share.storage;


import org.springframework.stereotype.Component;
import redis.RedisDao;
import redis.factory.RedisDaoFactory;
import share.util.SessionIdGenerator;

/**
 * 自定义session 基类
 */
@Component
public class RedisSessionHandler implements StorageHandler{
    
	private static final long serialVersionUID = -3437702686249069252L;
	
	private String defaultPrefix = "prefix:";
    private int valueTTL = 86400;
    private RedisDao sessionRedisDao = RedisDaoFactory.getSimpleRedisDao("session");
    
    @Override
    public String createSessionId() {
    	 String sessionId = null;
         do {
         	 sessionId = appendSessionId(SessionIdGenerator.generateSessionId());
         } while (sessionRedisDao.exists(sessionId));
         
         this.setAttribute(sessionId, sessionId, "true");
         return sessionId;
    }

	@Override
	public boolean existsSessionId(String sessionId) {
		return sessionRedisDao.exists(sessionId);
	}

	@Override
	 public void setAttribute(String sessionId, String name, String value) {
        sessionRedisDao.hset(sessionId, name, value);
    }
	
	@Override
	public String getAttribute(String sessionId, String name) {
        return sessionRedisDao.hget(sessionId, name);
    }

	@Override
	public void removeAttribute(String sessionId, String name) {
        sessionRedisDao.hdel(sessionId, name);
    }
	
	@Override
    public void setMaxInactiveInterval(String sessionId, int seconds) {
        if(seconds<=0){
            seconds = valueTTL;
        }
        sessionRedisDao.expired(sessionId, seconds);
    }
  
    private String appendSessionId(String sessionId) {
		return defaultPrefix + sessionId;
	}

    //set get
	public RedisDao getSessionRedisDao() {
		return sessionRedisDao;
	}

	public void setSessionRedisDao(RedisDao sessionRedisDao) {
		this.sessionRedisDao = sessionRedisDao;
	}

	public int getValueTTL() {
        return valueTTL;
    }

    public void setValueTTL(int valueTTL) {
        this.valueTTL = valueTTL;
    }
    
	public String getDefaultPrefix() {
		return defaultPrefix;
	}

	public void setDefaultPrefix(String defaultPrefix) {
		this.defaultPrefix = defaultPrefix;
	}
    
}
