package com.project.schedule.shade.impl;

import com.project.schedule.shade.SSCGameShade;
import com.project.schedule.ssctype.ShadeType;
import com.project.spider.model.SSCRepo;

public class GroupStarsSSCGameShade extends AbstractSSCGameShade implements SSCGameShade{
	
	
	@Override
	public 	boolean isWinShade(SSCRepo sscRepo, ShadeType shadeType) {
		if(super.getEqualGroupNum(sscRepo)==shadeType.getEqualGroupNum()){
			return true;
		}
		return false;
	}
	
	

}
