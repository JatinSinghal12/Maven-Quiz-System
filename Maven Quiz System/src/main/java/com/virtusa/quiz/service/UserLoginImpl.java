package com.virtusa.quiz.service;

import java.util.Optional;

import com.virtusa.quiz.dao.UserDao;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.User;

public class UserLoginImpl implements LoginService<Optional<User>> {

	static UserLoginImpl ul = new UserLoginImpl();

	private UserLoginImpl() {

	}

	public static UserLoginImpl getInstance() {
		if (ul == null) {
			ul = new UserLoginImpl();
		}

		return ul;
	}

	@Override
	public Optional<User> login(String email) throws UserDataException {

		UserDao ud = UserDao.getInstance();

		return ud.loginUser(email);
	}

}
