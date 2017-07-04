package com.project.spider.factory.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.spider.factory.SSCRepoFactory;
import com.project.spider.model.SSCRepo;
import com.project.spider.model.builder.SSCRepoBuilder;
import com.project.spider.model.list.SSCRepoOriginalList;
/**
 * old
 * 暂已废弃
 * @author mac
 *
 */
@Repository("CQSSCRepoFactory")
public class CQSSCRepoFactory implements SSCRepoFactory{
	public List<SSCRepo> getSSCRepoList(SSCRepoOriginalList sscRepoOriginalList){
		List<SSCRepo> sscRepoList = new ArrayList<SSCRepo>();
		//去掉标题
		List<String> issueList = sscRepoOriginalList.getIssueList();
		List<String> numList = sscRepoOriginalList.getNumList();
		for(int i = 1 ; i < issueList.size();i++){
			SSCRepoBuilder sscRepoBuilder = SSCRepoBuilder.getInstance();
			sscRepoBuilder.setIssue(issueList.get(i));
			String[] digitStrArr = numList.get(i).split("-");
			sscRepoBuilder.setDigit5(Integer.parseInt(digitStrArr[0]))
			.setDigit4(Integer.parseInt(digitStrArr[1]))
			.setDigit3(Integer.parseInt(digitStrArr[2]))
			.setDigit2(Integer.parseInt(digitStrArr[3]))
			.setDigit1(Integer.parseInt(digitStrArr[4]));
			sscRepoList.add(sscRepoBuilder.build());
		}
		return sscRepoList;
	}
}
