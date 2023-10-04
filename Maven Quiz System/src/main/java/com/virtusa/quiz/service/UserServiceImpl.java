package com.virtusa.quiz.service;


import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.dao.UserDao;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.User;

public class UserServiceImpl implements UserService {

	static UserServiceImpl us = new UserServiceImpl();

	private UserServiceImpl() {

	}

	public static UserServiceImpl getInstance() {
		if (us == null) {
			us = new UserServiceImpl();
		}

		return us;
	}

	@Override
	public int createUser(User data) throws UserDataException {

		UserDao ud = UserDao.getInstance();
		return ud.registerUser(data);

	}

	@Override
	public int updateUser(User u) throws UserDataException {
		UserDao ud = UserDao.getInstance();
		return ud.updateUser(u);
	}
	@Override
	public List<User> fetchUser() throws UserDataException {
		UserDao ud=UserDao.getInstance();
		return ud.userViewAll();
	}
	
	@Override
	public Optional<User> getUserById(int id) throws UserDataException {
		UserDao ud=UserDao.getInstance();
		return ud.getUserById(id);
	}

	

	

}
