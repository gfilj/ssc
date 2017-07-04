package com.project.spider.model.list.impl;

import java.util.List;

import com.project.spider.factory.impl.TJSSCRepoFactory;
import com.project.spider.model.list.SSCRepoOriginalList;
import com.project.spider.model.type.SSCTypeEnum;
import com.project.spider.pipeline.SSCPageModelPipeline;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

/**
 * 重庆六合彩
 * 
 * @author luchangqing
 *
 */
@TargetUrl("http://11xuan5.cjcp.com.cn/guangdong/kaijiang/")
public class GD11TO5RepoOriginalList implements SSCRepoOriginalList{
	@ExtractBy("//table[@class='kjjg_table']/tbody/tr/td/regex('\\d{10}')")
	private List<String> issueList;
	@ExtractBy("//table[@class='kjjg_table']/tbody/tr/td/div/div/regex('\\d{2}')")
	private List<String> numList;
	
	private SSCTypeEnum type = SSCTypeEnum.GD11TO5;
	
	public SSCTypeEnum getType() {
		return type;
	}

	public void setType(SSCTypeEnum type) {
		this.type = type;
	}
	public List<String> getIssueList() {
		return issueList;
	}

	public void setIssueList(List<String> issueList) {
		this.issueList = issueList;
	}

	public List<String> getNumList() {
		return numList;
	}

	public void setNumList(List<String> numList) {
		this.numList = numList;
	}
	
	public static void main(String[] args) {
		SSCPageModelPipeline sscPageModelPipeline = new SSCPageModelPipeline();
		sscPageModelPipeline.setSscRepoFactory(new TJSSCRepoFactory());
		 OOSpider.create(Site.me(), sscPageModelPipeline, GD11TO5RepoOriginalList.class)
	        .addUrl("http://11xuan5.cjcp.com.cn/guangdong/kaijiang/")
	        .thread(1)
	        .run();
    }

	
}
