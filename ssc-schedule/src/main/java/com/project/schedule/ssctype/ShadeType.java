package com.project.schedule.ssctype;

public enum ShadeType {
	SSC5Group120(0), SSC5Group60(1), SSC5Group30(2), SSC5Group20(3), SSC5Group10(4), SSC5Group5(6),
	SSC4Group24(0), SSC4Group12(1), SSC4Group6(2),
	SSC3Group6(0), SSC3Group3(1),
	
	SSCLocationFDan(0);
	private int equalGroupNum;

	private ShadeType(int equalGroupNum) {
		this.equalGroupNum = equalGroupNum;
	}

	public int getEqualGroupNum() {
		return equalGroupNum;
	}
	
}
