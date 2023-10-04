package com.virtusa.quiz.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.exception.InvalidDataException;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Admin;
import com.virtusa.quiz.model.ScoreBoard;
import com.virtusa.quiz.model.User;
import com.virtusa.quiz.service.AdminLoginImpl;
import com.virtusa.quiz.service.ScoreService;
import com.virtusa.quiz.service.UserServiceImpl;

public class AdminController extends BaseController {

	public void adminLogin(){

		
		log.info(DESIGN);
		log.info("Enter Admin Login Details\n");
		log.info(EMAIL);
		sc.nextLine();
		String email = sc.nextLine().toLowerCase();
		AdminLoginImpl lu = new AdminLoginImpl();
		try {
		Optional<Admin> oa = lu.login(email);
		if (oa.isPresent()) {
			
			Admin a = oa.get();
			String pass = a.getPassword();
			log.info(PASSWORD);
			String password = sc.nextLine();
			if (!pass.equals(password)) {
				throw new InvalidDataException(TRYAGAIN);
			} else {
				adminHandler();

			}
		}

		else {
			throw new InvalidDataException(TRYAGAIN);
		}
		}
		catch(UserDataException e) {
			log.info(e);
		}

	}

	private void adminHandler() throws UserDataException {
		
		log.info(DESIGN);
		log.info("Welcome Admin !!\n\n");
		log.info("1. View All Questions");
		log.info("2. Add Questions");
		log.info("3. Delete Question");
		log.info("4. Update Question");
		log.info("5. View User Details");
		log.info("6. View Score Board");
		log.info("7. Logout\n");
		log.info(DESIGN);
		log.info(CHOOSE);
		int i = sc.nextInt();
		QuizController qc = new QuizController();
		ScoreController sc=new ScoreController();

		switch (i) {
		case 1:
			qc.viewAllQues();
			adminHandler();
			break;
		case 2:
			qc.addQues();
			adminHandler();
			break;
		case 3:
			qc.deleteQues();
			adminHandler();
			break;
		case 4:
			qc.updateQues();
			adminHandler();
			break;
		case 5:
			viewUserDetails();
			adminHandler();
			break;
		case 6:
			sc.scView();
			adminHandler();
			break;
		case 7:
			log.info(DESIGN);
			log.info(LOGOUT);
			break;
		default:
			log.info(CHOOSE);
			adminHandler();
			break;

		}
	}
	public void viewUserDetails() throws UserDataException {
		UserServiceImpl us=UserServiceImpl.getInstance();
		ScoreService ss=ScoreService.getInstance();
		List<User> ul=us.fetchUser();
		
		SpaceController spc=l->{
			int j=l/8;
			int k=l%8;
			StringBuilder sb=new StringBuilder();
			if(j>1)
				return sb.toString();
			else if(j==1){
				while((k--)!=0) {
					sb.append(" ");
				}
				return sb.toString();
			}
			else {
				return "\t";
			}
			
		};
		if(!ul.isEmpty()) {
			Collections.sort(ul);
			int sNo = 0;
			log.info(DESIGN);
			log.info("+----------+----------+---------------+-----------------------+---------------+----------+------------------+");
			log.info("|   S.No   |  UserId  |      Name     |       Email Id        |   Phone No.   |  Score   | TimeTaken(in Sec)|");
			log.info("+----------+----------+---------------+-----------------------+---------------+----------+------------------+");
			for (User u : ul) {
				Optional<ScoreBoard> osb=ss.scoreById(u.getId());
				if(osb.isPresent()) {
					ScoreBoard sb=osb.get();
				log.info("\t"+(++sNo) +"\t" + u.getId() + "\t" + u.getName() + spc.space(u.getName().length()) +"\t" + u.getEmailId() +spc.space(u.getEmailId().length())+ "\t"
						+ u.getPhoneNo()+ "\t   " + sb.getScore() + "\t\t" + sb.getTimeTaken());
				}
				else {
					break;
				}
			}
			log.info("\n\n");
		}
		else {
			log.info("No Data Available\n");
		}
	}
}
