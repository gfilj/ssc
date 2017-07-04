package com.project.spider.model.type;

public enum SSCTypeEnum {
	
	TJSSC(0),
	CQSSC(1),
	GD11TO5(2);
	
	private int type;

	/**
	 * @param type
	 */
	private SSCTypeEnum(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}
	
	
}
