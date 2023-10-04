package com.virtusa.quiz.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.virtusa.quiz.exception.InvalidDataException;
import com.virtusa.quiz.exception.UserDataException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BaseController {

	public static final String CHOOSE = "Please Choose Correct Option : ";
	public static final String EMAIL = "Please Enter Email ID : ";
	public static final String PASSWORD = "Please Enter Your Password : ";
	public static final String LOGOUT = "You Logged Out Successfully. \n";
	public static final String UPDATING = "Please write Updated data : ";
	public static final String ASKUP = "Would You like to Update this (Y/N)?";
	public static final String BYE = " Thank You!! Have a good day.";
	public static final String VALIDEMAIL = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
	public static final String VALIDPHONE ="[6-9]\\d{9}"; 
	public static final String TRYAGAIN ="Wrong Credential, Please Try Again!!\n";
	public static final String DESIGN ="==============================================================================================================\n";

	public static final Logger log = LogManager.getLogger(BaseController.class);

	static final Scanner sc = new Scanner(System.in);

	public void welcomeQuiz() throws UserDataException {
		boolean c = true;
		while (c) {
			log.info(DESIGN);
			log.info("~~~WELCOME TO ONLINE QUIZ~~~\n");
			log.info("1. Register User");
			log.info("2. User Login");
			log.info("3. Admin Login");
			log.info("4. View Score Board");
			log.info("5. Exit\n");
			log.info(DESIGN);
			log.info(CHOOSE);
			
			
			
			
			UserController uc = new UserController();
			AdminController ac = new AdminController();
			ScoreController scc=new ScoreController();
			try {
				
				int i = sc.nextInt();
				
			switch (i) {
			case 1:

				uc.registerUser();
				break;
			case 2:
				uc.userLogin();
				break;
			case 3:
				ac.adminLogin();
				break;
			case 4:
				scc.scView();
				break;
			case 5:
				c = false;
				exit();
				break;
			default:
				break;

			}
			}
			catch(InvalidDataException e) {
			log.info(e);	
			}
			catch(InputMismatchException e) {
				log.info(e.toString()+" Wrong Input");
				sc.nextLine();
			}
			
			
			
		}

	}

	public void exit() {
		log.info(DESIGN);
		log.info(BYE);
		log.info(DESIGN);

	}
}
