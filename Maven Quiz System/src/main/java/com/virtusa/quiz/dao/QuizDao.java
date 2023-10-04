package com.virtusa.quiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.virtusa.quiz.controller.BaseController;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Questions;

public class QuizDao extends BaseDao {

	static QuizDao qd = new QuizDao();

	private QuizDao() {

	}

	public static QuizDao getInstance() {
		if (qd == null) {
			qd = new QuizDao();
		}

		return qd;
	}

	public List<Questions> getQues() throws UserDataException {
		ArrayList<Questions> ques = new ArrayList<>();
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("select * from questions;")) {
				ResultSet rs = ps.executeQuery();
				rs.next();
				do {
					Questions q = new Questions(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getInt(7));
					ques.add(q);
				} while (rs.next());
			}
		} 
		catch(UserDataException e) {
			BaseController.log.info(e);
		}catch (SQLException e) {
			//e.printStackTrace()
			throw new UserDataException(e.toString());

		}
		return ques;

	}

	public int setQues(Questions q) throws UserDataException {
		int status = 0;
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement(
					"insert into questions(ques,option1,option2,option3,option4,answer) values(?,?,?,?,?,?);")) {
				ps.setString(1, q.getQuestion());

				ps.setString(2, q.getOption1());
				ps.setString(3, q.getOption2());
				ps.setString(4, q.getOption3());
				ps.setString(5, q.getOption4());
				ps.setString(6, q.getAnswer());

				status = ps.executeUpdate();

			}
		}
		catch(UserDataException e) {
			BaseController.log.info(e);
		}

		catch (SQLException e) {
			//e.printStackTrace()
			

		}
		return status;

	}

	public int updateQues(Questions q) throws UserDataException {
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement(
					"update questions set ques=?,option1=?,option2=?,option3=?,option4=?,answer=? where id_ques=? ;")) {
				ps.setString(1, q.getQuestion());
				ps.setString(2, q.getOption1());
				ps.setString(3, q.getOption2());
				ps.setString(4, q.getOption3());
				ps.setString(5, q.getOption4());
				ps.setString(6, q.getAnswer());
				ps.setInt(7, q.getId());

				return ps.executeUpdate();

			}
		}

		catch (SQLException e) {
			throw new UserDataException(e.toString());

		}
	}

	public int delQues(int id) throws UserDataException {
		try {
			Connection con = getConnection();
			try (PreparedStatement ps = con.prepareStatement("Delete from questions where id_ques=?;")) {
				ps.setInt(1, id);
				return ps.executeUpdate();
			}
		} catch (SQLException e) {
			//e.printStackTrace()
			throw new UserDataException(e.toString());

		}
	}
}
