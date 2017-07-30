package com.project.model.api.builder;
/**
 * 
 * @author goforit
 *
 */

import java.util.List;

import com.project.model.api.BaseVO;
import com.project.model.api.CodeVO;
import com.project.spider.model.Row;
/**
 * 撤单/当前的采种类型
 * @author goforit
 *
 */
public class LotteryVOBuilder {

	private CodeVO codeVO = new CodeVO();

	public static LotteryVOBuilder getInstance() {
		return new LotteryVOBuilder();
	}

	public LotteryVOBuilder setMsg(String msg)  {
		codeVO.setMsg(msg);
		return this;
	}

	public LotteryVOBuilder setCode(int code) {
		codeVO.setCode(code);
		return this;
	}

	public LotteryVOBuilder setResult(List<Row> lotteryList) {
		codeVO.setResult(lotteryList);
		return this;
	}
	public BaseVO build() {
		return codeVO;
	}
	
}
