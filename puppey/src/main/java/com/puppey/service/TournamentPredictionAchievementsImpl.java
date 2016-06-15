package com.puppey.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.achievement.AchievementQueue;
import com.puppey.domain.Achievement;
import com.puppey.domain.Tournament;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.User;

@DependsOn("achievementService")
@Service("tournamentPredictionAchievements")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TournamentPredictionAchievementsImpl implements TournamentPredictionAchievements{
    
    @Autowired
    private TournamentPredictionService tournamentPredictionService;
    @Autowired
    private AchievementService achievementService;
    @Autowired
    private UserService userService;
    
    private static final int FIRST_BRACKET_ID = 4;
    private static final int SECOND_BRACKET_ID = 6;
    
    ;

    @Override
    @Transactional
    public void allChecks(int tpId){
//        TournamentPrediction tp = tournamentPredictionService.getTournamentPredictionById(tpId);
//        User user = tp.getUser();
//        Tournament tournament = tp.getTournament();
//        List<TournamentPrediction> userTournamentPredictions;
//        userTournamentPredictions = tournamentPredictionService.getTournamentPredictionsByUser(user);
//        
//        if(tournament.getTournamentStart() > (System.currentTimeMillis() / 1000)){
//        user = addBracket(user, userTournamentPredictions);
//        user = addSecondBracket(user, userTournamentPredictions);
//        }
//        
//        if(tournament.getTournamentStart() <= (System.currentTimeMillis() / 1000)){
//            int matchupSize = tournament.getMatchups().size();
//            if (matchupSize == 8){
//                user = perfectEight(tp);
//            }
//        }
//	
    }
	


    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addBracket(User user, List<TournamentPrediction> tournamentPredictions){
        for(Achievement achievement : user.getAchievements()){
            if(achievement.getAchievementId() == FIRST_BRACKET_ID){
                return user;
            }
        }
        if(tournamentPredictions.size() > 0){
            Achievement achievement = achievementService.getAchievementById(FIRST_BRACKET_ID);
            user.getAchievements().add(achievement);
        }
        return user;
    }
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User addSecondBracket(User user, List<TournamentPrediction> tournamentPredictions){
        for(Achievement achievement : user.getAchievements()){
            if(achievement.getAchievementId() == SECOND_BRACKET_ID){
                return user;
            }
        }
        
        List<Integer> tournamentIdList = new ArrayList<>();
        for(TournamentPrediction userTP : tournamentPredictions){
        	int tournamentId = userTP.getTournament().getTournamentId();
        	if(tournamentIdList.contains(userTP.getTournament().getTournamentId())){
        	    Achievement achievement = achievementService.getAchievementById(SECOND_BRACKET_ID);
        	    user.getAchievements().add(achievement);
        	    return user;
        	}
        	tournamentIdList.add(tournamentId);
        }
        return user;
    }
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public User perfectEight(TournamentPrediction tp) {
        // TODO Auto-generated method stub
        return null;
    }
}
