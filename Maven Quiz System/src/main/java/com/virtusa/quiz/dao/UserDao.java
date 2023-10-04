package com.virtusa.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.User;

public class UserDao extends BaseDao {

	static UserDao ud = new UserDao();

	private UserDao() {

	}

	public static UserDao getInstance() {
		if (ud == null) {
			ud = new UserDao();
		}

		return ud;
	}

	public int registerUser(User u) throws UserDataException{
		int status = 0;
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con
					.prepareStatement("insert into user (emailId,password,name,phoneNo) values(?,?,?,?);")) {
				ps.setString(1, u.getEmailId());
				ps.setString(2, u.getPassword());
				ps.setString(3, u.getName());
				ps.setString(4, u.getPhoneNo());

				status = ps.executeUpdate();

			}
		}
		

		catch (SQLException e) {
			//e.printStackTrace()
			//throw new UserDataException(e.toString()+" Please Enter Correct Details")

		}

		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement(
					"insert into scorecard (user_id) select user.id from user where user.emailId =lower(?) ;")) {
				ps.setString(1, u.getEmailId());

				status = ps.executeUpdate();

			}
		}

		catch (SQLException e) {
		//	e.printStackTrace()
			//throw new UserDataException(e.toString())

		}
		return status;

	}

	public Optional<User> loginUser(String email) throws UserDataException  {
		User u;
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("select * from user where emailId=lower(?);")) {
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				rs.next();
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				return Optional.ofNullable(u);
			}
		} 
		catch (SQLException e) {
			//e.printStackTrace()

		}
		return Optional.empty();

	}
	public List<User> userViewAll() throws UserDataException  {

		ArrayList<User> sb = new ArrayList<>();
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("select id,name,emailId,phoneNo,password from user;")) {
				ResultSet rs = ps.executeQuery();
				rs.next();
				do {
					User dsb = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));
					sb.add(dsb);
				} while (rs.next());
			}
		} 
		catch (SQLException e) {
			//e.printStackTrace()

		}
		return sb;

	}

	public Optional<User> getUserById(int id) throws UserDataException  {
		User u;
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("select * from user where id=?;")) {
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				rs.next();
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				return Optional.of(u);
			}
		}
		catch (SQLException e) {
			//e.printStackTrace()
			

		}
		return Optional.empty();

	}


	
	public int updateUser(User u) throws UserDataException {

		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con
					.prepareStatement("update user set name=?,phoneNo=?,password=? where emailId=? ;")) {
				ps.setString(1, u.getName());
				ps.setString(2, u.getPhoneNo());
				ps.setString(3, u.getPassword());
				ps.setString(4, u.getEmailId());

				return ps.executeUpdate();

			}
			
		}
		
		catch (SQLException e) {
			//throw new UserDataException(e.toString())

		}
		return 0;
	}

}
