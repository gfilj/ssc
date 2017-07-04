package com.project.schedule.prize.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.schedule.prize.SSCWinPrize;
import com.project.schedule.shade.SSCGameShade;
import com.project.schedule.ssctype.CathecticType;
import com.project.schedule.ssctype.ShadeType;
import com.project.spider.model.SSCRepo;

public class DoubleSelectedSSCWinPrize extends AbstractSSCWinPrize implements SSCWinPrize{
	
	@Autowired
	private SSCGameShade sscGameShade;
	@Override
	public boolean isWinPrize(SSCRepo sscRepo, ShadeType shadeType, CathecticType cathecticType) {
		if(sscGameShade.isWinShade(sscRepo, shadeType)){
			if(isContainAllSSCRepoDigit(sscRepo, cathecticType)){
				return true;
			}
		}
		return false;
	}
}