package com.virtusa.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.controller.BaseController;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.ScoreBoard;

public class ScoreDao extends BaseDao {

	static ScoreDao sd = new ScoreDao();

	private ScoreDao() {

	}

	public static ScoreDao getInstance() {
		if (sd == null) {
			sd = new ScoreDao();
		}

		return sd;
	}

	public Optional<List<ScoreBoard>> scoreViewAll() throws UserDataException {

		ArrayList<ScoreBoard> sb = new ArrayList<>();
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement(
					"select * from scorecard;")) {
				ResultSet rs = ps.executeQuery();
				rs.next();
				do {
					ScoreBoard dsb = new ScoreBoard(rs.getInt(1) ,rs.getDouble(2),rs.getInt(3));
					sb.add(dsb);
				} while (rs.next());
			}
		} 
		catch (SQLException e) {
			//throw new UserDataException(e)

		}
		return Optional.ofNullable(sb);

	}
	public Optional<ScoreBoard> getScoreById(int id) throws UserDataException {
		ScoreBoard sb;
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("select * from scorecard where user_id=?;")) {
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				rs.next();
				sb = new ScoreBoard(rs.getInt(1),rs.getDouble(2),rs.getInt(3) );
				return Optional.ofNullable(sb);
			}
		} 
		catch(UserDataException e) {
			BaseController.log.info(e);
		}catch (SQLException e) {
			//e.printStackTrace()
			//throw new UserDataException("No User With this ID")

		}
		return Optional.empty();

		
	}

	public void updateScore(double total, int time, String email) throws UserDataException {
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement(
					"update scorecard set score=round(?,2),timetaken=? where user_id=(select user.id from user where user.emailId=lower(?));")) {
				ps.setDouble(1, total);
				ps.setInt(2, time);
				ps.setString(3, email);
				ps.executeUpdate();
			}
		} 
		catch(UserDataException e) {
			BaseController.log.info(e);
		}
		catch (SQLException e) {
			//e.printStackTrace()
			throw new UserDataException(e.toString());

		}

	}

}
