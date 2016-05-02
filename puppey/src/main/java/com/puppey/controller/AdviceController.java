package com.puppey.controller;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.service.StreamServiceImpl;
import com.puppey.service.TournamentService;
import com.puppey.service.UserService;
import com.puppey.util.SiteUser;

@ControllerAdvice(assignableTypes = {AchievementController.class, AdminController.class, GroupController.class, HomeController.class,
		TeamController.class, TournamentController.class,
		TournamentPredictionController.class, UserController.class })

@RequestMapping
public class AdviceController {

	@Autowired
	private UserService userService;
	@Autowired
	private TournamentService tournamentService;
	
	@ModelAttribute("userData")
	public User getUserData(){
	    if((SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null)){
	        return null;
	    }
		
		if(!(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equals("anonymousUser"))){
		SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userService.getUserWithFavoriteTeamsById(su.getUserId());
		
		}
		return null;
	}
	   @ModelAttribute("latestTournament")
	    public Tournament getLatestTournamentId(){
//		   if(tournamentService.getCurrentAndUpcomingTournaments().size() > 0){
//	        return tournamentService.getCurrentAndUpcomingTournaments().get(0);
//		   }else{
			   return null;
//		   }
	    }
	   
//	   @ModelAttribute("daysLeft")
//	   public Long getDaysLeft(@ModelAttribute("latestTournament") Tournament tournament){
//	       Long start = (long)tournament.getTournamentStart();
//	       Long today = (System.currentTimeMillis() / 1000);
//	       Long difference = (start - today)/((60 * 60 * 24));
//	       if(difference <= 0){
//	           return (long) 0;
//	       }
//	       return difference;
//	   }


	    
}
