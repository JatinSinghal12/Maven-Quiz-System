package com.virtusa.quiz.service;

import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.dao.ScoreDao;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.ScoreBoard;

public class ScoreService {

	static ScoreService ss = new ScoreService();

	private ScoreService() {

	}

	public static ScoreService getInstance() {
		if (ss == null) {
			ss = new ScoreService();
		}

		return ss;
	}

	ScoreDao sd = ScoreDao.getInstance();
	
	public Optional<List<ScoreBoard>> fetchScore() throws UserDataException {
		
		return sd.scoreViewAll();

	}
	public Optional<ScoreBoard> scoreById(int id) throws UserDataException
	{
		return sd.getScoreById(id);
		
	}

	public void updateScore(double total, int tm, String email) throws UserDataException {

		sd.updateScore(total, tm, email);

	}

}
