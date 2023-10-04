package com.virtusa.quiz.controller;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.exception.InvalidDataException;
import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Questions;
import com.virtusa.quiz.service.QuestionService;
import com.virtusa.quiz.service.ScoreService;
import com.virtusa.quiz.util.AppContext;


public class QuizController extends BaseController {

	public static final String ADDING = "Question Added Successfully,Hooray!";
	public static final String UPDATED = "Question Updated Successfully,Hooray!";
	public static final String DELETED = "Question Deleted Successfully,Hooray!  ";
	public static final String QUESTION = "Question ";

	List<Questions> rs;
	QuestionService qs = QuestionService.getInstance();
	
	Questions q = AppContext.getInstance().getBean("Questions");

	public void viewAllQues() throws UserDataException {

		rs = qs.fetchQues();

		int qn=0;
		for (Questions qt : rs) {
			log.info(DESIGN);
			log.info("Question Id : " + qt.getId()+"\n");
			log.info(QUESTION +(++qn)+" : "+ qt.getQuestion()+"\n");
			log.info("(A) " + qt.getOption1());
			log.info("(B) " + qt.getOption2());
			log.info("(C) " + qt.getOption3());
			log.info("(D) " + qt.getOption4()+"\n");
			log.info("Answer : " + qt.getAnswer().toUpperCase() + "\n");
		}
	}

	public void addQues() throws InvalidDataException, UserDataException {
		
		
		
		log.info("Please Add Question ");
		sc.nextLine();
		String ques = sc.nextLine();
		q.setQuestion(ques);
		log.info("Please Add Option A ");
		q.setOption1(sc.nextLine());
		log.info("Please Add Option B ");
		q.setOption2(sc.nextLine());
		log.info("Please Add Option C");
		q.setOption3(sc.nextLine());
		log.info("Please Add Option D ");
		q.setOption4(sc.nextLine());
		log.info("Please Add Correct Answer ");
		q.setAnswer(sc.nextLine());

		//Method Reference of Instance Method
		//QuestionServiceAdd qsa=qs::addQ

		if (qs.addQ(q) != 0) {
			log.info(ADDING);
		}

	}

	public void updateQues() throws UserDataException {
		log.info("Enter Question Id ");
		int id = sc.nextInt();
		rs = qs.fetchQues();

		//Using Stream to find Question by Id
		
		Optional<Questions> oq = rs.stream().filter(e -> e.getId() == id).findFirst();
		if (oq.isPresent()) {
			q = oq.get();
			log.info(QUESTION + q.getQuestion()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setQuestion(sc.nextLine());
			}

			log.info("Option A : " + q.getOption1()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setOption1(sc.nextLine());
			}
			log.info("Option B : " + q.getOption2()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setOption2(sc.nextLine());
			}
			log.info("Option C : " + q.getOption3()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setOption3(sc.nextLine());
			}
			log.info("Option D : " + q.getOption4()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setOption4(sc.nextLine());
			}
			log.info("Answer : " + q.getAnswer()+"\n");
			if (updation()) {
				log.info(UPDATING);
				sc.nextLine();
				q.setAnswer(sc.nextLine());
			}
			if (qs.updateQues(q) != 0) {
				log.info(UPDATED);
			}
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

	public void startQuiz(String email) throws UserDataException {
		rs = qs.fetchQues();
		int score = 0;
		int count=1;
		log.info("Quiz Starting");
		for (int i = 5; i > 0; i--) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();

			}
			log.info(String.format("%d...", i));

		}
		log.info("Go\n\n");
		// Calculates Time
		Instant start = Instant.now();
		for (Questions qt : rs) {
			log.info(DESIGN);
			log.info(QUESTION +(count)+" : "+ qt.getQuestion()+"\n");
			log.info("(A)" + qt.getOption1());
			log.info("(B)" + qt.getOption2());
			log.info("(C)" + qt.getOption3());
			log.info("(D)" + qt.getOption4()+"\n");
			log.info(CHOOSE);
			count++;
			String ch = sc.next();
			if (ch.equalsIgnoreCase(qt.getAnswer()))
				score++;

		}
		Instant end = Instant.now();
		Duration timeElapsed = Duration.between(start, end);
		int tm = (int) timeElapsed.getSeconds();

		DecimalFormat f = new DecimalFormat("#0.00");
		double total=(100.0/(--count))*score;
		log.info(DESIGN);
		log.info("You Scored " + score+"/"+count+" ,Total Percentage "+f.format(total) + " , Total Time Taken : " + tm + " Seconds\n");
		ScoreService ss=ScoreService.getInstance();
		ss.updateScore(total, tm, email);

	}


	public void deleteQues() throws UserDataException {
		log.info("Enter Id of Question You want to Delete");

		if (qs.delQ(sc.nextInt()) != 0) {
			log.info(DESIGN);
			log.info(DELETED);
		}
		else {
			log.info("Wrong ID");
		}

	}

}
