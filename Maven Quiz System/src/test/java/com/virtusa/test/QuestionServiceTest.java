package com.virtusa.test;

import static org.junit.Assert.*;
import org.junit.Test;

import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.Questions;
import com.virtusa.quiz.service.QuestionService;

public class QuestionServiceTest {

	@Test
	public void testUpdateQues() throws  UserDataException{

		QuestionService qs = QuestionService.getInstance();
		assertEquals(1, qs.updateQues(new Questions("2*3=?", "5", "7", "6", "2", "C", 1)));
		
	}
	
	@Test
	public void testAddQ() {

		assertNotNull(new Questions());
		
	}

}
