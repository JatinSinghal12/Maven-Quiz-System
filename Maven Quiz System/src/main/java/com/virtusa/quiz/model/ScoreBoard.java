package com.virtusa.quiz.model;

public class ScoreBoard {

	private int uId;
	private double score;
	private int timeTaken;

	
	public ScoreBoard() {
		super();
		
	}

	public ScoreBoard(int uId, double d, int timeTaken) {
		super();
		this.uId = uId;
		this.score = d;
		this.timeTaken = timeTaken;
	}

	public int getId() {
		return uId;
	}

	public void setId(int uId) {
		this.uId = uId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getTimeTaken() {
		return timeTaken;
	}

	public void setTimeTaken(int timeTaken) {
		this.timeTaken = timeTaken;
	}
}
