package com.puppey.thread;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.puppey.service.TournamentPredictionAchievements;
import com.puppey.service.TournamentPredictionService;

@DependsOn("tournamentPredictionService")
@Component
public class ScoreThread implements Runnable{


	TournamentPredictionService tournamentPredictionService;
   
   //default required for some strange reason
   public ScoreThread(){}
   
   public ScoreThread(TournamentPredictionService tournamentPredictionService){
       this.tournamentPredictionService = tournamentPredictionService;
   }
	@Override
	public void run() {
		while(true){
            try {
                Thread.sleep(200);
                if(ThreadStarter.getTournamentScoreQueue().size() > 0){
                	int tournamentId = ThreadStarter.getTournamentScoreQueue().take();
                	tournamentPredictionService.updateTournamentPredictionScores(tournamentId);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
	}

}
