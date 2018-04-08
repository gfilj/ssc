package redis.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.HostPort;
import redis.bean.PoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;



/**
 * 配置文件工具类
 */
public final class PropertiesManager {
    
    private static Logger logger = LoggerFactory.getLogger(PropertiesManager.class);
    
    private static final String HOST = ".host";
    private static final String MAX_TOTAL = ".maxTotal";
    private static final String MAX_IDLE = ".maxIdle";
    private static final String MAX_WAIT_MILLIONS = ".maxWaitMillions";
    private static final String TEST_ON_BORROW = ".testOnBorrow";
    private static final String TIME_OUT = ".timeOut";
    private static final String PASSWORD = ".password";
    private static final String MASTER_NAME = ".masterName";
    
    private static Properties props ;
    private static final Map<String,PoolConfig> configMap = new ConcurrentHashMap<>();
    
    private static final int DEFAULT_TIME_OUT = 2000;
    private static final String propertiesName = "redis-config.properties";
    
    static {
	    try {
	    	props = new Properties();
			props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName));
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private PropertiesManager() {
    	
    }
    
    private static void loadPoolConfig(final String fullName) {
    	PoolConfig poolConfig = new PoolConfig();
		
		if (StringUtils.isBlank(props.getProperty(fullName + HOST))) {
            throw new RuntimeException("加载 properties出错,host为空，格式：127.0.0.1:6379");
		}
		if (StringUtils.isBlank(props.getProperty(fullName + MAX_TOTAL))) {
            throw new RuntimeException("加载 properties出错,maxTotal为空");
		}
		if (StringUtils.isBlank(props.getProperty(fullName + MAX_IDLE))) {
            throw new RuntimeException("加载 properties出错,maxIdle为空");
		}
		if (StringUtils.isBlank(props.getProperty(fullName + MAX_WAIT_MILLIONS))) {
            throw new RuntimeException("加载 properties出错,maxWaitMillions为空");
		}
		if (StringUtils.isBlank(props.getProperty(fullName + TEST_ON_BORROW))) {
            throw new RuntimeException("加载 properties出错,testOnBorrow为空");
		}
		
		String host = props.getProperty(fullName + HOST);
		int maxTotal = Integer.valueOf(props.getProperty(fullName + MAX_TOTAL));
		int maxIdle = Integer.valueOf(props.getProperty(fullName + MAX_IDLE));
		long maxWaitMillions = Long.valueOf(props.getProperty(fullName + MAX_WAIT_MILLIONS));
		boolean testOnBorrow = Boolean.valueOf(props.getProperty(fullName + TEST_ON_BORROW));
		String password = props.getProperty(fullName + PASSWORD);
		String masterName = props.getProperty(fullName + MASTER_NAME);
		
		int timeOut = DEFAULT_TIME_OUT;
		if (!StringUtils.isBlank(props.getProperty(fullName + TIME_OUT))) {
			timeOut = Integer.parseInt(props.getProperty(fullName + TIME_OUT));
		}
		
		List<HostPort> hostPortList = new ArrayList<>();
		HostPort hostPort = null;
		String []hosts = host.split(";");
		for (int i = 0; i < hosts.length; i++) {
			String ip = hosts[i].split(":")[0];
			int port = Integer.valueOf(hosts[i].split(":")[1]);
			hostPort = new HostPort(ip,port);
			hostPortList.add(hostPort);
		}
		
		poolConfig.setHostPort(hostPortList);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxWaitMillions(maxWaitMillions);
		poolConfig.setTestOnBorrow(testOnBorrow);
		poolConfig.setTimeOut(timeOut);
		poolConfig.setPassword(password);
		poolConfig.setMasterName(masterName);
		
		configMap.put(fullName, poolConfig);
    }
    
    public synchronized static PoolConfig getPoolConfig(String fullName) {
    	if (!configMap.containsKey(fullName)) {
    		loadPoolConfig(fullName);
    	}
    	
    	return configMap.get(fullName);
    }
    
    public static void main(String[] args) {
		
    	PoolConfig poolConfig = PropertiesManager.getPoolConfig(Constant.SIMPLE_PREFIX + "user");
    	System.out.println(poolConfig.toString());
    	
//    	String a = "shard_user_01.port";
//    	String prefix = "shard_user";
//    	System.out.println(a.startsWith(prefix));
	}
}
