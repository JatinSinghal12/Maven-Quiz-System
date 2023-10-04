package com.virtusa.quiz;

import org.apache.log4j.PropertyConfigurator;

import com.virtusa.quiz.controller.BaseController;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	PropertyConfigurator.configure("Properties/log4j.properties");
    	BaseController bc = new BaseController();
		bc.welcomeQuiz();
    }
}
