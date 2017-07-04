package com.project.schedule.shade.impl;



import java.util.List;

import org.apache.log4j.Logger;

import com.project.schedule.shade.SSCGameShade;
import com.project.spider.model.SSCRepo;

public abstract class AbstractSSCGameShade implements SSCGameShade{
	
	protected static Logger logger = Logger.getLogger(AbstractSSCGameShade.class);
	
	protected int getEqualGroupNum(SSCRepo sscRepo){
		int groupNum = 0;
		List<Integer> digitList = sscRepo.getDigitList();
		for (int i = 0; i < digitList.size(); i++) {
			for(int j=i+1; j <digitList.size(); j++){
				if(digitList.get(i) == digitList.get(j)){
					logger.info("FiveStars: is not win prize and the digit (offset/num) " + i+"/" + digitList.get(i) + " vs " + j+"/" + digitList.get(j));
					groupNum ++;
				}
			}
		}
		return groupNum;
	}
	
}
