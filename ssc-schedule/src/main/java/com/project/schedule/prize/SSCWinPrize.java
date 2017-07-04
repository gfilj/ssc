package com.project.schedule.prize;

import com.project.schedule.ssctype.CathecticType;
import com.project.schedule.ssctype.ShadeType;
import com.project.spider.model.SSCRepo;

/**
 * 时时彩中奖
 * @author mac
 *
 */
public interface SSCWinPrize {
	boolean isWinPrize(SSCRepo sscRepo, ShadeType shadeType, CathecticType cathecticType);
}
