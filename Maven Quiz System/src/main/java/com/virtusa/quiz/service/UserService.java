package com.virtusa.quiz.service;



import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.User;

public interface UserService {

	 int createUser(User data) throws UserDataException;

	 int updateUser(User u) throws UserDataException;

	 Optional<User> getUserById(int id) throws UserDataException;

	 List<User> fetchUser() throws UserDataException;

}
