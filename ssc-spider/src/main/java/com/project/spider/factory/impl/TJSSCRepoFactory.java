package com.project.spider.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.spider.factory.SSCRepoFactory;
import com.project.spider.model.SSCRepo;
import com.project.spider.model.builder.SSCRepoBuilder;
import com.project.spider.model.list.SSCRepoOriginalList;
/**
 * 天津
 * 广东11选5
 * 重庆时时彩
 * @author mac
 *
 */
@Repository("TJSSCRepoFactory")
public class TJSSCRepoFactory implements SSCRepoFactory{
	public List<SSCRepo> getSSCRepoList(SSCRepoOriginalList sscRepoOriginalList){
		List<SSCRepo> sscRepoList = new ArrayList<SSCRepo>();
		//去掉标题
		List<String> issueList = sscRepoOriginalList.getIssueList();
		List<String> numList = sscRepoOriginalList.getNumList();
		for(int i = 0 ; i < issueList.size();i++){
			SSCRepoBuilder sscRepoBuilder = SSCRepoBuilder.getInstance();
			sscRepoBuilder.setIssue(issueList.get(i)).setType(sscRepoOriginalList.getType().getType());
			int j= i*5;
			sscRepoBuilder
				.setDigit5(Integer.parseInt(numList.get(j)))
				.setDigit4(Integer.parseInt(numList.get(j+1)))
				.setDigit3(Integer.parseInt(numList.get(j+2)))
				.setDigit2(Integer.parseInt(numList.get(j+3)))
				.setDigit1(Integer.parseInt(numList.get(j+4)));
			sscRepoList.add(sscRepoBuilder.build());
		}
		return sscRepoList;
	}
	
}
