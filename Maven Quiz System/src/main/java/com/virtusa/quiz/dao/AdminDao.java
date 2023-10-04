package com.virtusa.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.virtusa.quiz.controller.BaseController;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Admin;

public class AdminDao extends BaseDao {

	static AdminDao ad = new AdminDao();

	private AdminDao() {

	}

	public static AdminDao getInstance() {
		if (ad == null) {
			ad = new AdminDao();
		}

		return ad;
	}

	public Optional<Admin> adminLogin(String email) throws UserDataException {
		Admin a ;

		try (Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from admin where emailId=lower(?);")) {
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			rs.next();
			a = new Admin(rs.getString(1), rs.getString(2));
			return Optional.ofNullable(a);
		}
		catch(UserDataException e) {
			BaseController.log.info(e);
		}

		catch (SQLException e) {
			//e.printStackTrace()
		}
		return Optional.empty();
		
	}

}