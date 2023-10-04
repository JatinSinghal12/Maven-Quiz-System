package com.virtusa.quiz.model;

import java.util.Comparator;

//Class to compare Score and time
public class ScoreTimeCompare implements Comparator<ScoreBoard> {

	@Override
	public int compare(ScoreBoard o1, ScoreBoard o2) {
		if (o1.getScore() == o2.getScore()) {
			return o1.getTimeTaken() - o2.getTimeTaken();
		} else {
			return (int) (o2.getScore() - o1.getScore());
		}

	}

}
