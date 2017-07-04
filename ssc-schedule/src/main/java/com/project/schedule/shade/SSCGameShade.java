package com.project.schedule.shade;

import com.project.schedule.ssctype.ShadeType;
import com.project.spider.model.SSCRepo;

/**
 * 时时彩中奖形态
 * @author mac
 *
 */
public interface SSCGameShade {
	/**
	 * 是否为中奖形态
	 * @return
	 */
	boolean isWinShade(SSCRepo sscRepo, ShadeType shadeType);
}
