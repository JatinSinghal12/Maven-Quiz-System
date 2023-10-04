package com.virtusa.quiz.service;

import com.virtusa.quiz.exception.UserDataException;

public interface LoginService<T> {

	// Functional Interface
	T login(String email) throws UserDataException;

}
