package share.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 配置文件工具类
 */
public final class PropertiesUtil {
    
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    
    private final static Integer MAX_TOTAL = 5;
    private final static Integer MAX_WAITMILLIS= 5;
    private static Integer MAX_IDLE = 5;
    private static Boolean TEST_ON_BORROW = false;
    private static Integer TIME_OUT = 2000;
    
    public static Map<String,Object> loadRedicConfigProperties(String propertiesName){
        Properties properties = new Properties();
        InputStream in = null;
        try{
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName);
            properties.load(in);
            
            String host = (String) properties.get("host");
            if (StringUtils.isEmpty(host)) {
            	throw new RuntimeException("加载 properties出错,host字段为空，格式：127.0.0.1:6379");
            }
    		String masterName = (String) properties.get("masterName");
    		if (StringUtils.isEmpty(masterName)) {
            	throw new RuntimeException("加载 properties出错,masterName字段为空");
            }
    		Integer maxTotal = Integer.valueOf((String) properties.get("maxTotal"));
    	    if (maxTotal == null) {
    	    	maxTotal = MAX_TOTAL;
    	    }
    	    Integer maxWaitMillis = Integer.valueOf((String) properties.get("maxWaitMillis"));
    		if (maxWaitMillis == null) {
    			 maxWaitMillis = MAX_WAITMILLIS;
     	    }
    		Integer maxIdle = Integer.valueOf((String) properties.get("maxIdle"));
    		if (maxIdle == null) {
    			maxIdle = MAX_IDLE;
    	    }
    		Boolean testOnBorrow = Boolean.valueOf((String) properties.get("testOnBorrow"));
    		if (testOnBorrow == null) {
    			testOnBorrow = TEST_ON_BORROW;
    		}
    		Integer timeout = Integer.valueOf((String) properties.get("timeout"));
    		if (timeout == null) {
    			timeout = TIME_OUT;
    		}
    		String password = (String) properties.get("password");
    		if (StringUtils.isEmpty(password)) {
    			throw new RuntimeException("加载 properties出错,password字段为空");
    		}
    		
    		Map<String,Object> result = new HashMap<>();
    		result.put("host", host);
    		result.put("masterName", masterName);
    		result.put("maxTotal", maxTotal);
    		result.put("maxWaitMillis", maxWaitMillis);
    		result.put("maxIdle", maxIdle);
    		result.put("testOnBorrow", testOnBorrow);
    		result.put("timeout", timeout);
    		result.put("password", password);
    		return result;
        }catch(Exception e){
            logger.error("加载 properties出错",e);
            throw new RuntimeException("加载 properties出错",e);
        }finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static List<String> loadWhiteListProperties(String propertiesName){
        Properties properties = new Properties();
        InputStream in = null;
        try{
        	
            in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesName);
            properties.load(in);
            String whiteList = (String) properties.get("WHITE_LIST_URL");
            if (StringUtils.isEmpty(whiteList)) {
            	return new ArrayList<>();
            }
    		
    		String[] wl = whiteList.split(";");
    		List<String> result = Arrays.asList(wl);
    		return result;
        }catch(Exception e){
            logger.error("加载 properties出错",e);
            throw new RuntimeException("加载 properties出错",e);
        }finally{
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void main(String[] args) {
    	List a = PropertiesUtil.loadWhiteListProperties("whitelist.properties");
    	for (int i = 0; i < a.size(); i++) {
    		System.out.println(a.get(i));
    	}
	}
}
