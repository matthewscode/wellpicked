package com.puppey.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.achievement.AchievementQueue;
import com.puppey.dao.TournamentPredictionDao;
import com.puppey.domain.Matchup;
import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.TournamentPrediction;
import com.puppey.domain.Tournament;
import com.puppey.domain.User;
import com.puppey.dto.TournamentPredictionDto;
import com.puppey.util.Message;
import com.puppey.util.SiteUser;

import org.apache.log4j.Logger;

@Service("tournamentPredictionService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TournamentPredictionServiceImpl implements TournamentPredictionService {

    @Autowired
    private TournamentPredictionDao tournamentPredictionDao;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private AchievementQueue achievementQueue;
    
    private static final Logger LOG = Logger.getLogger(TournamentPredictionServiceImpl.class);
    
   private static Deque<TournamentPrediction> popularTournamentPredictions = new LinkedList<>();
    
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMatchupPredictionsByTournamentId(int tid, User predictingUser) {
        // need matchups
        List<Matchup> matchupsToPredict = tournamentService.getMatchupsByTournamentId(tid);
        List<MatchupPrediction> matchupPredictionsByUser = getMatchupPredictionsByUser(predictingUser);
        List<Matchup> matchupsUserHasPredicted = new ArrayList<>();
        // populate list of matchups already made by user
        for (int i = 0; i < matchupPredictionsByUser.size(); i++) {
            matchupsUserHasPredicted.add(tournamentService.getMatchup(matchupPredictionsByUser.get(i).getMatchup()));
        }

        // subtract matchups that have been predicted
        for (int j = 0; j < matchupsToPredict.size(); j++) {
            if (matchupsUserHasPredicted.contains(matchupsToPredict.get(j))) {
                matchupsToPredict.remove(matchupsToPredict.get(j));
            }
        }
        // add new matchup predictions that do not already exist
        for (Matchup matchup : matchupsToPredict) {
            MatchupPrediction matchupPrediction = new MatchupPrediction();
            matchupPrediction.setMatchup(matchup.getMatchupId());
            tournamentPredictionDao.addMatchupPrediction(matchupPrediction);
        }
    }

    @Override
    @Transactional
    public List<MatchupPrediction> getMatchupPredictionsByUser(User predictingUser) {
        return tournamentPredictionDao.getMatchupPredictionsByUser(predictingUser);
    }

    @Override
    @Transactional
    public List<MatchupPrediction> getMatchupPredictionsByUserAndTournament(User predictingUser, Tournament tournament) {
        return tournamentPredictionDao.getMatchupPredictionsByUserAndTournament(predictingUser, tournament);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void addMatchupPredictionsInWrapper(TournamentPrediction matchupPredictionWrapper) {
        for (int i = 0; i < matchupPredictionWrapper.getMatchupPredictionList().size(); i++) {
            tournamentPredictionDao.addMatchupPrediction(matchupPredictionWrapper.getMatchupPredictionList().get(i));
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMatchup(MatchupPrediction matchupPrediction) {

        if (!(matchupPrediction.getWinner() == 0)) {
            tournamentPredictionDao.updateMatchupPrediction(matchupPrediction);
        }
    }

    @Override
    @Transactional
    public void updateMatchupPredictionsInWrapper(TournamentPrediction matchupPredictionWrapper) {

        for (int i = 0; i < matchupPredictionWrapper.getMatchupPredictionList().size(); i++) {
            updateMatchup(matchupPredictionWrapper.getMatchupPredictionList().get(i));

        }
    }

    @Override
    @Transactional
    public int calculateMatchupPredictionScore(MatchupPrediction matchupPrediction) {
        Matchup currentMatchup = tournamentService.getMatchup(matchupPrediction.getMatchup());
        if (matchupPrediction.getWinner() == currentMatchup.getWinnerId()
                && matchupPrediction.getWinner() != 0) {
            return tournamentService.getMatchupWeight(currentMatchup);
        }
        return 0;
    }

    @Override
    @Transactional
    public boolean userPredictionsForTournamentExists(User user, Tournament tournament) {
        return tournamentPredictionDao.userPredictionsForTournamentExists(user, tournament);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public TournamentPrediction addTournamentPrediction(TournamentPrediction tournamentPrediction) {
        tournamentPredictionDao.addTournamentPrediction(tournamentPrediction);
        //might need tweaking for matchup prediction achievements
        achievementQueue.addToTournamentPredictionQueue(tournamentPrediction.getTournamentPredictionId());
        return tournamentPrediction;
    }
    

    @Override
    @Transactional
    public TournamentPrediction getPredictionTournament(User user, Tournament tournament) {
        TournamentPrediction tournamentPrediction = tournamentPredictionDao.getTournamentPrediction(user, tournament);
        if (tournamentPrediction != null) {
            tournamentPrediction.getMatchupPredictionList().size();
        }
        return tournamentPrediction;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTournamentPrediction(TournamentPrediction tournamentPrediction) {
        tournamentPredictionDao.updateTournamentPrediction(tournamentPrediction);
        for (MatchupPrediction i : tournamentPrediction.getMatchupPredictionList()) {
            tournamentPredictionDao.updateMatchupPrediction(i);
        }

    }

    @Override
    @Transactional
    public List<MatchupPrediction> getMatchupPredictionsByEmail(String name) {
        User user = userService.getUserByEmail(name);
        return getMatchupPredictionsByUser(user);
    }

    @Override
    @Transactional
    public List<TournamentPrediction> getTopFivePredictionScores(Tournament tournament) {
        return tournamentPredictionDao.getTopFivePredictionScores(tournament);
    }

    @Override
    @Transactional
    public List<TournamentPrediction> getLastFivePredictions(Tournament tournament) {
        return tournamentPredictionDao.getLastFivePredictions(tournament);
    }

    @Override
    @Transactional
    public List<TournamentPredictionDto> getLatestPredictionsForTournament(int tournamentId, int amount) {
        Tournament tournament = tournamentService.getTournament(tournamentId);
        List<TournamentPrediction> tournamentPredictions = tournamentPredictionDao.getLatestPredictions(tournament, amount);
        List<TournamentPredictionDto> tournamentPredictionDtos = new ArrayList<>();
        for(TournamentPrediction tp : tournamentPredictions){
            tournamentPredictionDtos.add(new TournamentPredictionDto(tp.getTournamentPredictionId(), tp.getTournamentPredictionScore(), tp.getTournament().getTournamentId(), tp.getUser(), tp.getCreation(), tp.getTournamentPredictionName()));
        }
        return tournamentPredictionDtos;
    }

    @Override
    @Transactional
    public List<TournamentPrediction> getTopPredictionsForTournament(int tournamentId, int amount) {
        Tournament tournament = tournamentService.getTournament(tournamentId);
        return tournamentPredictionDao.getTopPredictions(tournament, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTournamentPredictionScores(Tournament tournament) {
        for (TournamentPrediction tournamentPrediction : getTournamentPredictionsByTournament(tournament)) {
            int score = 0;
            for (MatchupPrediction matchupPrediction : tournamentPrediction.getMatchupPredictionList()) {
                score = score + calculateMatchupPredictionScore(matchupPrediction);
            }
            tournamentPrediction.setTournamentPredictionScore(score);
        }

    }
    
    //for the queue
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateTournamentPredictionScores(int tournamentId) {
        for (TournamentPrediction tournamentPrediction : getTournamentPredictionsByTournament(tournamentService.getTournament(tournamentId))) {
            int score = 0;
            for (MatchupPrediction matchupPrediction : tournamentPrediction.getMatchupPredictionList()) {
                score = score + calculateMatchupPredictionScore(matchupPrediction);
            }
            tournamentPrediction.setTournamentPredictionScore(score);
        }

    }

    @Override
    @Transactional
    public List<TournamentPrediction> getTournamentPredictionsByTournament(Tournament tournament) {
        return tournamentPredictionDao.getTournamentPredictionsByTournament(tournament);

    }

    @Transactional
    @Override
    public List<TournamentPrediction> getTournamentPredictionsByUser(User user) {
        return tournamentPredictionDao.getTournamentPredictionsByUser(user);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void updateMatchupPrediction(MatchupPrediction matchupPrediction) {
        Tournament tournament = matchupPrediction.getTournamentPrediction().getTournament();
            if (!tournamentService.tournamentHasStarted(tournament)) {
                tournamentPredictionDao.updateMatchupPrediction(matchupPrediction);
            }
    }

    @Override
    @Transactional
    public MatchupPrediction getMatchpPredictionById(int matchupPredictionId) {
        return tournamentPredictionDao.getMatchupPredictionById(matchupPredictionId);
    }

    @Override
    @Transactional
    public TournamentPredictionDto getTournamentPredictionById(int tournamentPredictionId) {
        TournamentPrediction tournamentPrediction = tournamentPredictionDao
                .getTournamentPredictionById(tournamentPredictionId);
        tournamentPrediction.getMatchupPredictionList().size();
        TournamentPredictionDto tpdto = new TournamentPredictionDto();
        tpdto.setMatchupPredictionList(tournamentPrediction.getMatchupPredictionList());
        tpdto.setScore(tournamentPrediction.getTournamentPredictionScore());
        tpdto.setTournamentPredictionId(tournamentPrediction.getTournamentPredictionId());
        tpdto.setTournamentPredictionName(tournamentPrediction.getTournamentPredictionName());
        return tpdto;
    }
    
    @Override
    @Transactional
    public TournamentPrediction getTournamentPredictionByIdSolo(int tournamentPredictionId) {
        TournamentPrediction tournamentPrediction = tournamentPredictionDao
                .getTournamentPredictionById(tournamentPredictionId);
        return tournamentPrediction;
    }
    
    @Override
    @Transactional
    public List<TournamentPredictionDto> getTournamentPredictionDtoForTournamentByUser(
            int tournamentId, int userId) {
        Tournament tournament = tournamentService.getTournament(tournamentId);
        User user = userService.getUserById(userId);
        List<TournamentPrediction> tournamentPredictions =  tournamentPredictionDao.getTournamentPredictionsForTournamentByUser(tournament, user);
        List<TournamentPredictionDto> tournamentPredictionDtos = new ArrayList<>();
        for(TournamentPrediction tp : tournamentPredictions){
            tournamentPredictionDtos.add(new TournamentPredictionDto(tp.getTournamentPredictionId(), tp.getTournamentPredictionScore(), tp.getTournament().getTournamentId(), tp.getUser(), tp.getCreation(), tp.getTournamentPredictionName()));
        }
        return tournamentPredictionDtos;
    }

    @Override
    @Transactional
    public List<TournamentPrediction> getTournamentPredictionsByUserAndTournaments(User currentUser,
            List<Tournament> tournaments) {
        return tournamentPredictionDao.getTournamentPredictionsByUserAndTournaments(currentUser, tournaments);
    }

    @Override
    public boolean tournamentPredictionBelongsToUser(int userId, int userId2) {
        return userId == userId2;
    }

    @Override
    @Transactional
    public List<TournamentPredictionDto> getTournamentPredictionForTournamentByUser(
            int tournamentId, int userId) {
        Tournament tournament = tournamentService.getTournament(tournamentId);
        User user = userService.getUserById(userId);
        List<TournamentPrediction> tournamentPredictions =  tournamentPredictionDao.getTournamentPredictionsForTournamentByUser(tournament, user);
        List<TournamentPredictionDto> tournamentPredictionDtos = new ArrayList<>();
        for(TournamentPrediction tp : tournamentPredictions){
            tournamentPredictionDtos.add(new TournamentPredictionDto(tp.getTournamentPredictionId(), tp.getTournamentPredictionScore(), tp.getTournament().getTournamentId(), tp.getUser(), tp.getCreation(), tp.getTournamentPredictionName()));
        }
        return tournamentPredictionDtos;
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addMatchupPrediction(MatchupPrediction matchupPrediction) {
		tournamentPredictionDao.addMatchupPrediction(matchupPrediction);
	}

    @Override
    @Transactional
    public TournamentPredictionDto getTournamentPredictionDtoById(int id) {
        TournamentPrediction tp = tournamentPredictionDao
                .getTournamentPredictionById(id);
        tp.getMatchupPredictionList().size();
        TournamentPredictionDto tournamentPredictionDto = new TournamentPredictionDto(tp.getTournamentPredictionId(), tp.getTournamentPredictionScore(), tp.getTournament().getTournamentId(), tp.getUser(), tp.getCreation(), tp.getTournamentPredictionName());
        tournamentPredictionDto.setMatchupPredictionList(tp.getMatchupPredictionList());
        return tournamentPredictionDto;
    }

    @Override
    @Transactional
    public List<TournamentPrediction> getUserTournamentPredictionForTournament(User currentUser, Tournament tournament) {
        return tournamentPredictionDao.getTournamentPredictionsForTournamentByUser(tournament, currentUser);
    }

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int updateUserTournamentPrediction(TournamentPrediction jsonTP) {
		
//		TournamentPrediction oldTP = getTournamentPredictionById(jsonTP.getTournamentPredictionId());
//		SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//		if(oldTP.getUser().getUserId() != su.getUserId()){
//			return 0;
//		}
//
//        try {
//            for (MatchupPrediction jsonMP : jsonTP.getMatchupPredictionList()) {
//                MatchupPrediction matchupPrediction = getMatchpPredictionById(jsonMP
//                        .getPredictionId());
//                if(matchupPrediction.getTournamentPrediction().getTournamentPredictionId() == oldTP.getTournamentPredictionId()){
//                	matchupPrediction.setWinner(jsonMP.getWinner());
//                }else{
//                	throw new Exception();
//                }
//            }
//    		if(jsonTP.getTournamentPredictionName() != null){
//    			oldTP.setTournamentPredictionName(jsonTP.getTournamentPredictionName());
//    		}
//        } catch (Exception e) {
//            LOG.error("Unable to save, check log", e);
//            return 0;
//        }
//        return oldTP.getTournamentPredictionId();
		return 0;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public int createNewTournamentPrediction(TournamentPrediction jsonTP) {
		SiteUser su = (SiteUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User currentUser = userService.getUserById(su.getUserId());
		TournamentPrediction newTP = jsonTP;
		newTP.setUser(currentUser);
		addTournamentPrediction(newTP);
		 try {
	            for (MatchupPrediction jsonMP : jsonTP.getMatchupPredictionList()) {
	            	jsonMP.setTournamentPrediction(newTP);
	                addMatchupPrediction(jsonMP);
	            }
	        } catch (Exception e) {
	            LOG.error("Unable to save, check log", e);
	            return 0;
	        }
		 
		 return newTP.getTournamentPredictionId();
	}

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Message updateApiTournamentPrediction(TournamentPrediction jsonTP) {
        int tpResult = updateUserTournamentPrediction(jsonTP);
        if(tpResult > 0){
            return messageService.isSuccessful(true);
        }else{
            return messageService.isSuccessful(false);
        }
    }

    @Override
    public Message createApiTournamentPrediction(TournamentPrediction jsonTP) {
        int tpResult = createNewTournamentPrediction(jsonTP);
        if(tpResult > 0){
            return messageService.getTournamentPredictionId(tpResult);
        }else{
            return messageService.isSuccessful(false);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public List<TournamentPredictionDto> getPopularPredictionsForTournament(int tournamentId, int amount) {
        List<TournamentPrediction> tournamentPredictions = new ArrayList<>();
        for(TournamentPrediction tp : popularTournamentPredictions){
            if(tp.getTournament().getTournamentId() == tournamentId){
                tournamentPredictions.add(tp);
            }
        }

        List<TournamentPredictionDto> tournamentPredictionDtos = new ArrayList<>();
        for(TournamentPrediction tp : tournamentPredictions){
            tournamentPredictionDtos.add(new TournamentPredictionDto(tp.getTournamentPredictionId(), tp.getTournamentPredictionScore(), tp.getTournament().getTournamentId(), tp.getUser(), tp.getCreation(), tp.getTournamentPredictionName()));
        }
        if(tournamentPredictionDtos.size() < amount){
            for(TournamentPredictionDto newTp : getLatestPredictionsForTournament(tournamentId, Math.abs((tournamentPredictionDtos.size() - amount)))){
                tournamentPredictionDtos.add(newTp);
            }
        }
        
        return tournamentPredictionDtos;
    }
    
    public static void addToDeque(TournamentPrediction tp){
        if(popularTournamentPredictions.size() >= 50){
            popularTournamentPredictions.removeLast();
        }else if(popularTournamentPredictions.size() >= 1){
        for(TournamentPrediction oldTp : popularTournamentPredictions){
            if(oldTp.getTournamentPredictionId() == tp.getTournamentPredictionId()){
                popularTournamentPredictions.remove(oldTp);
            }
          }
        }
        
            popularTournamentPredictions.addFirst(tp);
        }
    

    public static Deque<TournamentPrediction> getPopularTournamentPredictions() {
        return popularTournamentPredictions;
    }

    public static void setPopularTournamentPredictions(Deque<TournamentPrediction> popularTournamentPredictions) {
        TournamentPredictionServiceImpl.popularTournamentPredictions = popularTournamentPredictions;
    }

	@Override
	@Transactional
	public List<TournamentPredictionDto> getLatestTournamentPredictions(int tournamentId, int numResults) {
		List<TournamentPredictionDto> tpdtoList = new ArrayList<>();
		Tournament tournament = tournamentService.getTournament(tournamentId);
		List<TournamentPrediction> tpList = tournamentPredictionDao.getTournamentPredictionsByScore(tournament, numResults);
		for(TournamentPrediction tp : tpList){
			TournamentPredictionDto tpd = new TournamentPredictionDto();
			tpd.setUserId(tp.getUser().getUserId());
			tpd.setUsername(tp.getUser().getUsername());
			tpd.setUserAvatar(tp.getUser().getAvatarName());
			tpd.setTournamentPredictionId(tp.getTournamentPredictionId());
			tpd.setTournamentPredictionName(tp.getTournamentPredictionName());
			tpd.setScore(tp.getTournamentPredictionScore());
			tpdtoList.add(tpd);
		}
		return tpdtoList;
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public boolean createTournamentPrediction(int tournamentId, User user, String predictionName, List<MatchupPrediction> matchupPredictionList) {
		TournamentPrediction tournamentPrediction = new TournamentPrediction();
		Tournament tournament = tournamentService.getTournament(tournamentId);
		tournamentPrediction.setTournament(tournament);
		tournamentPrediction.setUser(user);
		if(predictionName.length() < 1){
			predictionName = user.getUsername() + " - " + new Date().getTime();
		}
		tournamentPrediction.setTournamentPredictionName(predictionName);
		tournamentPredictionDao.addTournamentPrediction(tournamentPrediction);
		for(MatchupPrediction mp : matchupPredictionList){
			mp.setTournamentPrediction(tournamentPrediction);
			tournamentPredictionDao.addMatchupPrediction(mp);
			System.out.println(mp.getMatchup() + " ----" + mp.getWinner() + " - tp: " + mp.getTournamentPrediction().getTournamentPredictionId());
		}
		return true;
	}
    

}
