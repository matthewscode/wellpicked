package com.puppey.thread;

import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.puppey.achievement.TournamentPredictionAchievementConsumer;
import com.puppey.service.TeamService;
import com.puppey.service.TournamentPredictionAchievements;
import com.puppey.service.TournamentPredictionService;

@Component
@DependsOn({"teamService","tournamentPredictionAchievements", "tournamentPredictionService"})
public class ThreadStarter {
    
    @Autowired
    private TeamService teamService;
    @Autowired
    private TournamentPredictionAchievements tournamentPredictionAchievements;
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    
    private static LinkedBlockingQueue<Integer> tournamentScoreQueue = new LinkedBlockingQueue<Integer>();
    
    public static LinkedBlockingQueue<Integer> getTournamentScoreQueue() {
		return tournamentScoreQueue;
	}

	@PostConstruct
    private void startStreamThread(){
        System.out.println(tournamentPredictionAchievements);
        StreamThread streamThread = new StreamThread(teamService.getAllTeams());
        TournamentPredictionAchievementConsumer tpac = new TournamentPredictionAchievementConsumer(tournamentPredictionAchievements);
        ScoreThread scoreThread = new ScoreThread(tournamentPredictionService);
        new Thread(streamThread).start();
        new Thread(tpac).start();
        new Thread(scoreThread).start();
    }
    
    
}
