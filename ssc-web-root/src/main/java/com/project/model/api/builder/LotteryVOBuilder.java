package com.project.model.api.builder;
/**
 * 
 * @author goforit
 *
 */

import java.util.LinkedList;
import java.util.List;

import com.project.model.api.BaseVO;
import com.project.model.api.CodeVO;
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

	public LotteryVOBuilder setResult() {
		CodeSimple codeSimple = new CodeSimple();
		codeSimple.setCn("重庆时时彩");
		codeSimple.setCode("CQSSC");
		codeSimple.setId(1);
		List<CodeSimple> recordList = new LinkedList<CodeSimple>();
		recordList.add(codeSimple);
		codeVO.setResult(recordList);
		return this;
	}
	public BaseVO build() {
		return codeVO;
	}
	
	/**
	 * 彩票项目列目列表
	 * @author goforit
	 *
	 */
	class CodeSimple{
		private String code;
		private int id;
		private String cn;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getCn() {
			return cn;
		}
		public void setCn(String cn) {
			this.cn = cn;
		}
		
	}
}
