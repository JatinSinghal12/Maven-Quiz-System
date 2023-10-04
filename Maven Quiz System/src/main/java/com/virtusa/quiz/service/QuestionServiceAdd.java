package com.virtusa.quiz.service;

import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Questions;

public interface QuestionServiceAdd {

	// Functional Interface
	public int addQ(Questions q) throws UserDataException;
}
