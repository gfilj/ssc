package com.project.schedule.ssctype;

public enum GameType {
	FiveStars(0, 4), FourStars(1, 4), Before3Stars(0, 2), Middle3Stars(1, 3), After3Stars(2, 4), TwoStars(3,
			4), LocationBile(0, 4);
	private int start;
	private int end;

	private GameType(int start, int end) {
		this.start = start;
		this.end = end;
	}

}
