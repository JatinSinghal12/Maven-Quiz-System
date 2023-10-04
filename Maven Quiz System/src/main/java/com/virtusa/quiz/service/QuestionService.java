package com.virtusa.quiz.service;

import java.util.List;

import com.virtusa.quiz.dao.QuizDao;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Questions;

public class QuestionService implements QuestionServiceAdd {

	static QuestionService qs = new QuestionService();

	private QuestionService() {

	}

	public static QuestionService getInstance() {
		if (qs == null) {
			qs = new QuestionService();
		}

		return qs;
	}

	QuizDao qd = QuizDao.getInstance();

	@Override
	public int addQ(Questions q) throws UserDataException {

		return qd.setQues(q);

	}

	public List<Questions> fetchQues() throws UserDataException {

		return qd.getQues();
	}

	public int updateQues(Questions q) throws UserDataException {

		return qd.updateQues(q);

	}

	public int delQ(int id) throws UserDataException {

		return qd.delQues(id);
	}

}
