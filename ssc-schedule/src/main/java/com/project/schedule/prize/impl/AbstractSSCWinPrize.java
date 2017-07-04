package com.project.schedule.prize.impl;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import com.project.schedule.prize.SSCWinPrize;
import com.project.schedule.shade.impl.AbstractSSCGameShade;
import com.project.schedule.ssctype.CathecticType;
import com.project.spider.model.SSCRepo;

public abstract class AbstractSSCWinPrize implements SSCWinPrize{
	protected static Logger logger = Logger.getLogger(AbstractSSCGameShade.class);
	/**
	 * 获取无重复数字结合
	 * @param sscRepo
	 * @return
	 */
	protected Set<Integer> getAllSSCRepoNoRepeatDigit(SSCRepo sscRepo){
		Set<Integer> digitSet = new HashSet<Integer>();
		digitSet.addAll(sscRepo.getDigitList());
		return digitSet;
	}
	/**
	 * 投注是否包含所有
	 * @param sscRepo
	 * @param cathecticType
	 * @return
	 */
	protected boolean isContainAllSSCRepoDigit(SSCRepo sscRepo, CathecticType cathecticType){
		if(cathecticType.getArrSet().containsAll(getAllSSCRepoNoRepeatDigit(sscRepo))){
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		Set<Integer> digitSet = new HashSet<Integer>();
		Set<Integer> digitSetSub = new HashSet<Integer>();
		digitSetSub.add(8);
		digitSetSub.add(2);
		digitSet.add(1);
		digitSet.add(2);
		digitSet.add(3);
		digitSet.add(4);
		digitSet.add(5);
		System.out.println(digitSet.containsAll(digitSetSub));
		
	}
}
