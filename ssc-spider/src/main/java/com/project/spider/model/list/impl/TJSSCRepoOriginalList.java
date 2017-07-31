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
@TargetUrl("http://kj.cjcp.com.cn/gaopin/ssc/tj/")
public class TJSSCRepoOriginalList implements SSCRepoOriginalList{
	@ExtractBy("//table[@class='qgkj_table']/tbody/tr/td/regex('\\d{11}')")
	private List<String> issueList;
	@ExtractBy("//table[@class='qgkj_table']/tbody/tr/td/input/regex('\\d{1}')")
	private List<String> numList;
	
	private SSCTypeEnum type = SSCTypeEnum.TJSSC;
	
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
		 OOSpider.create(Site.me(), sscPageModelPipeline, TJSSCRepoOriginalList.class)
	        .addUrl("http://kj.cjcp.com.cn/gaopin/ssc/tj/")
	        .thread(1)
	        .run();
    }

	
}
