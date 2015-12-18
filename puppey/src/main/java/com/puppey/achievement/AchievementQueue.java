package com.puppey.achievement;

import java.util.concurrent.LinkedBlockingQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.puppey.service.UserService;

@Service("achievementQueue")
public class AchievementQueue {
	
	@Autowired
	private UserService userService;
	
	private static LinkedBlockingQueue<Integer> userAchievementQueue = new LinkedBlockingQueue<Integer>();
	private static LinkedBlockingQueue<Integer> tournamentPredictionAchievementQueue = new LinkedBlockingQueue<Integer>();
	
	public void addToUserQueue(int userId){
		try {
			userAchievementQueue.put(userId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addToTournamentPredictionQueue(int userId){
		try {
			tournamentPredictionAchievementQueue.put(userId);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public static LinkedBlockingQueue<Integer> getUserAchievementQueue() {
		return userAchievementQueue;
	}

	public static LinkedBlockingQueue<Integer> getTournamentPredictionAchievementQueue() {
		return tournamentPredictionAchievementQueue;
	}
	
	
	
}
