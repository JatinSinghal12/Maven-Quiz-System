package com.virtusa.quiz.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.virtusa.quiz.exception.UserDataException;
import com.virtusa.quiz.model.ScoreBoard;
import com.virtusa.quiz.model.ScoreTimeCompare;
import com.virtusa.quiz.model.User;
import com.virtusa.quiz.service.ScoreService;
import com.virtusa.quiz.service.UserServiceImpl;


interface SpaceController {
	//Functional Interface
	String space(int l);

}

public class ScoreController extends BaseController {

	//Lambda Expression
	SpaceController sp=l->{
		int j=l/8;
		int k=l%8;
		StringBuilder s=new StringBuilder();
		if(j>1)
			return s.toString();
		else if(j==1){
			while((k--)!=0) {
				s.append(" ");
			}
			return s.toString();
		}
		else {
			return "\t";
		}
		
	};
	
	public void scView() {
		UserServiceImpl uss = UserServiceImpl.getInstance();
		ScoreService ss = ScoreService.getInstance();
		
		try {
			
		Optional<List<ScoreBoard>> fsb = ss.fetchScore();
		if(fsb.isPresent()) {
			ScoreTimeCompare tc=new ScoreTimeCompare();
			List<ScoreBoard> scb=fsb.get();
			Collections.sort(scb,tc);
			int rank = 0;
			log.info(DESIGN);
			log.info("+----------+----------+---------------+-------------------------+------------------+------------------+");
			log.info("|   Rank   |  UserId  |      Name     |        Email Id         |    Percentage    | TimeTaken(in Sec)|");
			log.info("+----------+----------+---------------+-------------------------+------------------+------------------+");
	
			for (ScoreBoard sb : scb) {
				Optional<User> uo=uss.getUserById(sb.getId());
				if(uo.isPresent()) {
					
					User u=uo.get();
					log.info("\t"+(++rank) + "\t" + sb.getId() + "\t" + u.getName() + sp.space(u.getName().length())+"\t" + u.getEmailId() + sp.space(u.getEmailId().length())+"\t\t"
							+ sb.getScore() + "\t\t" + sb.getTimeTaken());
					log.info(" -----------------------------------------------------------------------------------------------------");
				}
				else {
					break;
				}
			}
			log.info("\n\n");
		}
		else {
			log.info("No Data Available\n\n");
		}
		}
		catch(UserDataException e) {
			log.info(e);
		}
		
	}
}
