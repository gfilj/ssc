package com.project.spider.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project.spider.factory.SSCRepoFactory;
import com.project.spider.factory.impl.TJSSCRepoFactory;
import com.project.spider.model.list.impl.CQSSCRepoOriginalList;
import com.project.spider.model.list.impl.GD11TO5RepoOriginalList;
import com.project.spider.model.list.impl.TJSSCRepoOriginalList;
import com.project.spider.pipeline.SSCPageModelPipeline;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;

@Component
public class SSCScheduleTask {


	@Autowired
	@Qualifier("TJSSCRepoFactory")
	private SSCRepoFactory tjSSCRepoFactory;
	

	@Autowired
	private SSCPageModelPipeline sscPageModelPipeline;
	
	private Site site = Site.me().setTimeOut(60000);
	/**
	 * 抓取重庆时时彩
	 */
	public void grabCQSSC() {
		OOSpider.create(site, sscPageModelPipeline, CQSSCRepoOriginalList.class)
				.addUrl("http://shishicai.cjcp.com.cn/chongqing/kaijiang/").thread(1).setExitWhenComplete(true).run();
	}

	/**
	 * 抓取天津时时彩
	 */
	public void grabTJSSC() {
		OOSpider.create(site, sscPageModelPipeline, TJSSCRepoOriginalList.class)
				.addUrl("http://shishicai.cjcp.com.cn/tianjin/kaijiang/").thread(1).setExitWhenComplete(true).run();
	}

	/**
	 * 抓取广东11选5
	 */
	public void grabGD11TO5() {
		
		OOSpider.create(site, sscPageModelPipeline, GD11TO5RepoOriginalList.class)
				.addUrl("http://11xuan5.cjcp.com.cn/guangdong/kaijiang/").thread(1).setExitWhenComplete(true).run();
	}

	/**
	 * 每5分钟执行的任务
	 */
	@Scheduled(fixedRate = 300000)
	public void per5MinuteTask() {
		sscPageModelPipeline.setSscRepoFactory(tjSSCRepoFactory);
		grabCQSSC();
		grabTJSSC();
		grabGD11TO5();
	}
}
