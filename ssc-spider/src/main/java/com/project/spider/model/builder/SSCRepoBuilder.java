package com.project.spider.model.builder;

import java.util.ArrayList;
import java.util.List;

import com.project.spider.model.SSCRepo;

public class SSCRepoBuilder {
	
	private SSCRepo sscRepo = new SSCRepo();
	
	public SSCRepoBuilder setIssue(String issue) {
		sscRepo.setIssue(issue);
		return this;
	}
	public SSCRepoBuilder setDigit5(int digit5) {
		sscRepo.setDigit5(digit5);
		return this;
	}
	public SSCRepoBuilder setDigit4(int digit4) {
		sscRepo.setDigit4(digit4);
		return this;
	}
	public SSCRepoBuilder setDigit3(int digit3) {
		sscRepo.setDigit3(digit3);
		return this;
	}
	public SSCRepoBuilder setDigit2(int digit2) {
		sscRepo.setDigit2(digit2);
		return this;
	}
	public SSCRepoBuilder setDigit1(int digit1) {
		sscRepo.setDigit1(digit1);
		return this;
	}
	public SSCRepoBuilder setType(int type) {
		sscRepo.setType(type);;
		return this;
	}
	public static SSCRepoBuilder getInstance(){
		return new SSCRepoBuilder();
	}
	
	public SSCRepo build(){
		//make up digistList
		List<Integer> digitList = new ArrayList<Integer>();
		digitList.add(sscRepo.getDigit5());
		digitList.add(sscRepo.getDigit4());
		digitList.add(sscRepo.getDigit3());
		digitList.add(sscRepo.getDigit2());
		digitList.add(sscRepo.getDigit1());
		sscRepo.setDigitList(digitList);
		return sscRepo;
	}
}
