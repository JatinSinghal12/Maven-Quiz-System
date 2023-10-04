package com.virtusa.quiz.service;


import java.util.Optional;

import com.virtusa.quiz.dao.AdminDao;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Admin;

public class AdminLoginImpl implements LoginService<Optional<Admin>> {

	@Override
	public Optional<Admin> login(String email) throws UserDataException {
		AdminDao ad = AdminDao.getInstance();

		return ad.adminLogin(email);
	}

}
