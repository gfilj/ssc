package com.project.spider.model;

import java.io.Serializable;
import java.util.List;

/**
 * 重庆六合彩
 * @author luchangqing
 *
 */
public class SSCRepo implements Serializable{
	
	/**
	 * id
	 */
	private static final long serialVersionUID = -8528787109775676932L;
	
	private String issue;//期号
	private int digit5;//万位数字
	private int digit4;//百位数字
	private int digit3;//十位数字
	private int digit2;//个位数字
	private int digit1;//个位数字
	private List<Integer> digitList;//数字队列
	private int type;//彩票类型
	
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public int getDigit5() {
		return digit5;
	}
	public void setDigit5(int digit5) {
		this.digit5 = digit5;
	}
	public int getDigit4() {
		return digit4;
	}
	public void setDigit4(int digit4) {
		this.digit4 = digit4;
	}
	public int getDigit3() {
		return digit3;
	}
	public void setDigit3(int digit3) {
		this.digit3 = digit3;
	}
	public int getDigit2() {
		return digit2;
	}
	public void setDigit2(int digit2) {
		this.digit2 = digit2;
	}
	public int getDigit1() {
		return digit1;
	}
	public void setDigit1(int digit1) {
		this.digit1 = digit1;
	}
	public List<Integer> getDigitList() {
		return digitList;
	}
	public void setDigitList(List<Integer> digitList) {
		this.digitList = digitList;
	}
	@Override
	public String toString() {
		return "SSCRepo [issue=" + issue + ", digit5=" + digit5 + ", digit4=" + digit4 + ", digit3=" + digit3
				+ ", digit2=" + digit2 + ", digit1=" + digit1 + ", digitList=" + digitList + "]";
	}
	
	
	
}
