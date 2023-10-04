package com.virtusa.quiz.controller;

import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;
import com.virtusa.quiz.exception.InvalidDataException;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.User;
import com.virtusa.quiz.service.UserLoginImpl;
import com.virtusa.quiz.service.UserServiceImpl;
import com.virtusa.quiz.util.AppContext;

public class UserController extends BaseController {

	User u = AppContext.getInstance().getBean("User");
	
	UserServiceImpl usc = UserServiceImpl.getInstance();

	public void registerUser() throws  UserDataException {

		log.info(DESIGN);
		log.info("Enter User Name");
		sc.nextLine();
		String name = sc.nextLine();
		log.info(EMAIL);
		String emailId;
		while (true) {
			emailId = sc.nextLine().toLowerCase();

			Pattern pat = Pattern.compile(VALIDEMAIL, Pattern.CASE_INSENSITIVE);

			if (!pat.matcher(emailId).matches()) {

				log.info("Invalid Email Id ,Please Enter Again!!");

			} else
				break;
		}

		String phoneNo;
		log.info("Enter Phone Number");
		while (true) {

			phoneNo = sc.nextLine();

			Pattern p = Pattern.compile(VALIDPHONE);

			if (!p.matcher(phoneNo).matches()) {
				log.info("Invalid Phone No. ,Please Enter Again!!");

			} else
				break;
		}
		log.info(PASSWORD);

		String password = sc.nextLine();

		// Encoding Password
		Base64.Encoder encoder = Base64.getEncoder();
		String enPass = encoder.encodeToString(password.getBytes());

		u = new User(name, emailId, phoneNo, enPass);

		int status = usc.createUser(u);
		if (status != 0)

			log.info("You are Registered Succesfully!!\n");
		else {
			log.info("Please Try Again");

		}
	}

	public void userLogin() {

		log.info(EMAIL);
		sc.nextLine();
		String email = sc.nextLine().toLowerCase();
		UserLoginImpl lu = UserLoginImpl.getInstance();
		try {
			Optional<User> ou = lu.login(email);
			if (ou.isPresent()) {
				u = ou.get();

				String pass = u.getPassword();

				log.info(PASSWORD);
				// Decoding Password
				Base64.Decoder decoder = Base64.getDecoder();
				String dPass = new String(decoder.decode(pass));

				String password = sc.nextLine();

				if (dPass.equals(password)) {

					userHandler();

				} else {
					throw new InvalidDataException(TRYAGAIN);
					//log.info(TRYAGAIN)

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

	private void userHandler() throws UserDataException {

		String name = u.getName();
		String[] nm = name.split("\\s");
		
		// Capitalizing first letter of Name
		StringBuilder capName = new StringBuilder();
		for (String i : nm) {
			capName.append(i.substring(0, 1).toUpperCase() + i.substring(1).toLowerCase() + " ");
		}

		log.info(DESIGN);
		log.info("Welcome " + capName.toString().trim() + " !!\n");
		log.info("1. View Profile");
		log.info("2. Update Profile");
		log.info("3. Start Quiz");
		log.info("4. Logout\n");
		log.info(DESIGN);
		log.info(CHOOSE);
		int j = sc.nextInt();
		QuizController qc = new QuizController();
		switch (j) {
		case 1:
			viewProfile();
			userHandler();
			break;
		case 2:
			updateUser();
			userHandler();
			break;
		case 3:
			qc.startQuiz(u.getEmailId());
			userHandler();
			break;
		case 4:
			log.info(DESIGN);
			log.info(LOGOUT);
			break;
		default:
			log.info(CHOOSE);
			userHandler();
			break;
		}

	}

	public void viewProfile() {
		log.info(DESIGN);
		log.info("Id :       " + u.getId());
		log.info("Name :     " + u.getName());
		log.info("Email Id : " + u.getEmailId());
		log.info("Phone No : " + u.getPhoneNo() + "\n");
		log.info(DESIGN);

	}

	public void updateUser() throws UserDataException  {

		log.info("Name : " + u.getName() + "\n");
		if (updation()) {
			log.info(UPDATING);
			sc.nextLine();
			u.setName(sc.nextLine());
		}

		log.info("Phone No : " + u.getPhoneNo() + "\n");
		if (updation()) {
			log.info(UPDATING);
			sc.nextLine();
			String phoneNo = sc.nextLine();
			Pattern p = Pattern.compile(VALIDPHONE);

			if (!p.matcher(phoneNo).matches()) {
				log.info("Invalid Phone No.!!\n");

			} else {
				u.setPhoneNo(phoneNo);
			}
		}

		String ePass = u.getPassword();
		log.info("Password " + ePass + "\n");
		if (updation()) {
			log.info("Enter Old Password");
			sc.nextLine();

			// Decoding Password
			Base64.Decoder decoder = Base64.getDecoder();
			String dPass = new String(decoder.decode(ePass));
			if (dPass.equals(sc.nextLine())) {
				log.info(UPDATING);
				String password = sc.nextLine();

				// Encoding Password
				Base64.Encoder encoder = Base64.getEncoder();
				String enPass = encoder.encodeToString(password.getBytes());
				u.setPassword(enPass);
			} else {
				log.info("You Entered Wrong Old Password\n");
			}
		}

		if (usc.updateUser(u) != 0) {
			log.info("User Updated Successfully !!\n");
		}
	}

	public boolean updation() {
		log.info(ASKUP);
		char a = sc.next().charAt(0);
		boolean r = false;
		if (a == 'y' || a == 'Y')
			r = true;
		return r;
	}



}
