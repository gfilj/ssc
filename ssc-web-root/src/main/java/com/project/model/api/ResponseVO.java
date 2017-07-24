package com.project.model.api;

public class ResponseVO {

	private Object data;
	private Object page;
	private int isSuccess;
	private String type;
	private String Msg;
	
	public Object getPage() {
		return page;
	}

	public void setPage(Object page) {
		this.page = page;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
