package com.puppey.achievement;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import com.puppey.service.TournamentPredictionAchievements;


@DependsOn("tournamentPredictionAchievements")
@Component
public class TournamentPredictionAchievementConsumer implements Runnable {


     TournamentPredictionAchievements tournamentPredictionAchievements;
    
    //default required for some strange reason
    public TournamentPredictionAchievementConsumer(){}
    
    public TournamentPredictionAchievementConsumer(TournamentPredictionAchievements tournamentPredictionAchievements){
        this.tournamentPredictionAchievements = tournamentPredictionAchievements;
    }
    @Override
    public void run() {
       while(true){
            
            try {
                Thread.sleep(200);
            	if(AchievementQueue.getTournamentPredictionAchievementQueue().size() > 0){    
            	    tournamentPredictionAchievements.allChecks(AchievementQueue.getTournamentPredictionAchievementQueue().take());
            	}
            	
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

}
