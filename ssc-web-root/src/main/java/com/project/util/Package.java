/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.project.util;

import com.project.property.SystemProperty;
import org.apache.commons.lang3.StringUtils;


/**
 * 类似于包的一些工具类
 */
public class Package {	
	private static String point =".";

	/**
	 *
	 * @param name 传入的名字
	 * @param key 要获取的指定值
	 * @param pointNum 允许出现的相同字符串的个数
	 * @return
	 */
	public static String getRealClassName(String name,String key, int pointNum){
		if(StringUtils.isBlank(name))return null;
		if(containNum(name,point)>pointNum)return name;

		return SystemProperty.getSystemProperty(key)+point+name;
	}

	public static String getRealClassName(String name,String key){
		return getRealClassName(name,key,0);
	}

	/**
	 * 返回字符串中包含几个相通的点
	 */
	public static int containNum(String resourceStr, String containStr){
		int count = 0;
		int index;
		while (( index = resourceStr.indexOf(containStr))!=-1){
			count++;
			resourceStr = resourceStr.substring(index+1);
		}
		return count;
	}


}
