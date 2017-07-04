package com.project.schedule.ssctype;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 投注类别
 * @author mac
 *
 */
public enum CathecticType {
	ALL(0,1,2,3,4,5,6,7,8,9),
	BIG(5,6,7,8,9),
	SMALL(0,1,2,3,4),
	SINGLE(1,3,5,7,9),
	DOUBLE(0,2,4,6,8),
	CUSTOM(0,1,2,3);
	
	private Integer[] arr;

	private CathecticType(Integer... arr) {
		this.arr = arr;
	}

	public Integer[] getArr() {
		return arr;
	}
	/**
	 * 只允许自定义进行修改
	 * @param arr
	 */
	public void setArr(Integer... arr) {
		if(this==CUSTOM){
			this.arr = arr;
		}
	}
	
	public Set<Integer> getArrSet(){
		Set<Integer> arrSet = new HashSet<Integer>();
		arrSet.addAll(Arrays.asList(arr));
		return arrSet;
	}
	
	
	
	
	
}
