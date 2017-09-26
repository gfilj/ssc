
package com.project.property;

/**
 *
 */
public class SystemProperty {
	
	static{
		System.setProperty("ssc", "com.project.schedule.ssc");
	}
	
	public static String getSystemProperty(String key){
		return System.getProperty(key);
	}

}
