package com.virtusa.quiz.util;

import com.virtusa.quiz.model.Admin;
import com.virtusa.quiz.model.Questions;
import com.virtusa.quiz.model.ScoreBoard;
import com.virtusa.quiz.model.User;

public class AppContext {
	
	static AppContext appCt;
	
	private AppContext() {}
	
	public static AppContext getInstance() {
		if(appCt==null) {
			appCt= new AppContext();
		}
		return appCt;
			
	}
	
	//Factory Design Pattern
	
	public <T> T getBean(String beanName) {

        if (beanName.equalsIgnoreCase("Admin")){
            return (T) new Admin();
        } 
        else if (beanName.equalsIgnoreCase("Questions")){
            return (T) new Questions();
        }
        else if (beanName.equalsIgnoreCase("ScoreBoard")){
            return (T) new ScoreBoard();
        }
        else
        	return (T)new User();
    }
	
	

}
