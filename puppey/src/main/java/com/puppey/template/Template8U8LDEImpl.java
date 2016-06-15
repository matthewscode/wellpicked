package com.puppey.template;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.puppey.dao.TournamentDao;
import com.puppey.domain.Matchup;
import com.puppey.domain.Tournament;

@Service("template8U8LDE")
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class Template8U8LDEImpl implements Template8U8LDE {
    
    @Autowired
    private TournamentDao tournamentDao;

    @Override
    @Transactional(propagation=Propagation.REQUIRED, readOnly=false)
    public void createTournament(Tournament tournament) {
    	
    	/**
         * NOTE:
         * changed it to losers switching obvious places after the first upper bracket matches (after the frankfurt major)
         */
    	
        /**
         * NOTE:
         * losers of upper bracket round 2 switch obvious match numbers in the lower bracket
         * UR2M1 loser goes to LR4M2(instead of LR4M1)
         * this is to not let teams who may have faced each other in the upper bracket meet again in the lower
         * in ti3 dk and ig would have faced each other again
         */
        
        List<Matchup> matchups = new ArrayList<Matchup>();
        
        //upper round 1
        matchups.add(new Matchup("UR1M1", tournament)); 
        matchups.add(new Matchup("UR1M2", tournament));
        matchups.add(new Matchup("UR1M3", tournament)); 
        matchups.add(new Matchup("UR1M4", tournament));
        
        //upper round 2
        matchups.add(new Matchup("UR2M1", tournament));
        matchups.add(new Matchup("UR2M2", tournament));
        
        //upper round 3
        matchups.add(new Matchup("UR3M1", tournament));
        
        //************
        //lower round 1
        matchups.add(new Matchup("LR1M1", tournament));   
        matchups.add(new Matchup("LR1M2", tournament));
        matchups.add(new Matchup("LR1M3", tournament));   
        matchups.add(new Matchup("LR1M4", tournament));
        
        //lower round 2
        matchups.add(new Matchup("LR2M1", tournament));   
        matchups.add(new Matchup("LR2M2", tournament));
        matchups.add(new Matchup("LR2M3", tournament));   
        matchups.add(new Matchup("LR2M4", tournament));
        
        //lower round 3
        matchups.add(new Matchup("LR3M1", tournament));
        matchups.add(new Matchup("LR3M2", tournament));
        
        //lower round 4
        matchups.add(new Matchup("LR4M1", tournament));
        matchups.add(new Matchup("LR4M2", tournament));
        
        //lower round 5
        matchups.add(new Matchup("LR5M1", tournament));
        
        //lower round 6
        matchups.add(new Matchup("LR6M1", tournament));
        
        //grand finals
        matchups.add(new Matchup("GF", tournament));
        
       //add to db
       for(Matchup matchup : matchups){
           tournamentDao.addMatchup(matchup);
       }
        
        
        
        List<Matchup> detailedMatchups = tournamentDao.getMatchupsByTournamentId(tournament.getTournamentId());
        //get matchups by tournament
        //upper
        Matchup matchupUR1M1 = null;
        Matchup matchupUR1M2 = null;
        Matchup matchupUR1M3 = null;
        Matchup matchupUR1M4 = null;
        
        Matchup matchupUR2M1 = null;
        Matchup matchupUR2M2 = null;
        
        Matchup matchupUR3M1 = null;
        //lower
        Matchup matchupLR1M1 = null;
        Matchup matchupLR1M2 = null;
        Matchup matchupLR1M3 = null;
        Matchup matchupLR1M4 = null;
        
        Matchup matchupLR2M1 = null;
        Matchup matchupLR2M2 = null;
        Matchup matchupLR2M3 = null;
        Matchup matchupLR2M4 = null;
        
        Matchup matchupLR3M1 = null;
        Matchup matchupLR3M2 = null;
        
        Matchup matchupLR4M1 = null;
        Matchup matchupLR4M2 = null;
        
        Matchup matchupLR5M1 = null;
        
        Matchup matchupLR6M1 = null;
        
        Matchup matchupGF = null;
        
        
        for(Matchup matchup : detailedMatchups){
            //ur1
            if(matchup.getMatchupType() == "UR1M1"){
                matchupUR1M1 = matchup;
            }
            if(matchup.getMatchupType() == "UR1M2"){
                matchupUR1M2 = matchup;
            }
            if(matchup.getMatchupType() == "UR1M3"){
                matchupUR1M3 = matchup;
            }
            if(matchup.getMatchupType() == "UR1M4"){
                matchupUR1M4 = matchup;
            }
            
            //ur2
            if(matchup.getMatchupType() == "UR2M1"){
                matchupUR2M1 = matchup;
            }
            if(matchup.getMatchupType() == "UR2M2"){
                matchupUR2M2 = matchup;
            }
            
            //ur3
            if(matchup.getMatchupType() == "UR3M1"){
                matchupUR3M1 = matchup;
            }
            
            //lr1
            if(matchup.getMatchupType() == "LR1M1"){
                matchupLR1M1 = matchup;
            }
            if(matchup.getMatchupType() == "LR1M2"){
                matchupLR1M2 = matchup;
            }
            if(matchup.getMatchupType() == "LR1M3"){
                matchupLR1M3 = matchup;
            }
            if(matchup.getMatchupType() == "LR1M4"){
                matchupLR1M4 = matchup;
            }
            
            //lr2
            if(matchup.getMatchupType() == "LR2M1"){
                matchupLR2M1 = matchup;
            }
            if(matchup.getMatchupType() == "LR2M2"){
                matchupLR2M2 = matchup;
            }
            if(matchup.getMatchupType() == "LR2M3"){
                matchupLR2M3 = matchup;
            }
            if(matchup.getMatchupType() == "LR2M4"){
                matchupLR2M4 = matchup;
            }
            
            //lr3
            if(matchup.getMatchupType() == "LR3M1"){
                matchupLR3M1 = matchup;
            }
            if(matchup.getMatchupType() == "LR3M2"){
                matchupLR3M2 = matchup;
            }
            
            //lr4
            if(matchup.getMatchupType() == "LR4M1"){
                matchupLR4M1 = matchup;
            }
            if(matchup.getMatchupType() == "LR4M2"){
                matchupLR4M2 = matchup;
            }
            
            //lr5
            if(matchup.getMatchupType() == "LR5M1"){
                matchupLR5M1 = matchup;
            }
            
            //lr6
            if(matchup.getMatchupType() == "LR6M1"){
                matchupLR6M1 = matchup;
            }
            
            if(matchup.getMatchupType() == "GF"){
                matchupGF = matchup;
            }
        }
        
        //upper round 1 details
        matchupUR1M1.setWinnerNextMatchup(matchupUR2M1.getMatchupId());
        matchupUR1M1.setWinnerNextTeam(1);
        matchupUR1M1.setLoserNextMatchup(matchupLR2M1.getMatchupId());
        matchupUR1M1.setLoserNextTeam(1);
        matchupUR1M1.setWeight(2);
        tournamentDao.updateMatchup(matchupUR1M1);
        
        matchupUR1M2.setWinnerNextMatchup(matchupUR2M1.getMatchupId());
        matchupUR1M2.setWinnerNextTeam(2);
        matchupUR1M2.setLoserNextMatchup(matchupLR2M2.getMatchupId());
        matchupUR1M2.setLoserNextTeam(1);
        matchupUR1M2.setWeight(2);
        tournamentDao.updateMatchup(matchupUR1M2);
        
        matchupUR1M3.setWinnerNextMatchup(matchupUR2M2.getMatchupId());
        matchupUR1M3.setWinnerNextTeam(1);
        matchupUR1M3.setLoserNextMatchup(matchupLR2M3.getMatchupId());
        matchupUR1M3.setLoserNextTeam(1);
        matchupUR1M3.setWeight(2);
        tournamentDao.updateMatchup(matchupUR1M3);
        
        matchupUR1M4.setWinnerNextMatchup(matchupUR2M2.getMatchupId());
        matchupUR1M4.setWinnerNextTeam(2);
        matchupUR1M4.setLoserNextMatchup(matchupLR2M4.getMatchupId());
        matchupUR1M4.setLoserNextTeam(1);
        matchupUR1M4.setWeight(2);
        tournamentDao.updateMatchup(matchupUR1M4);
        
        //upper round 2 details
        matchupUR2M1.setWinnerNextMatchup(matchupUR3M1.getMatchupId());
        matchupUR2M1.setWinnerNextTeam(1);
        matchupUR2M1.setLoserNextMatchup(matchupLR4M2.getMatchupId());
        matchupUR2M1.setLoserNextTeam(1);
        matchupUR2M1.setWeight(4);
        tournamentDao.updateMatchup(matchupUR2M1);
        
        matchupUR2M2.setWinnerNextMatchup(matchupUR3M1.getMatchupId());
        matchupUR2M2.setWinnerNextTeam(2);
        matchupUR2M2.setLoserNextMatchup(matchupLR4M1.getMatchupId());
        matchupUR2M2.setLoserNextTeam(1);
        matchupUR2M2.setWeight(4);
        tournamentDao.updateMatchup(matchupUR2M2);
        
        //upper round 3 details
        matchupUR3M1.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupUR3M1.setWinnerNextTeam(1);
        matchupUR3M1.setLoserNextMatchup(matchupLR6M1.getMatchupId());
        matchupUR3M1.setLoserNextTeam(1);
        matchupUR3M1.setWeight(8);
        tournamentDao.updateMatchup(matchupUR3M1);
        
        
        //lower round 1 details
        matchupLR1M1.setWinnerNextMatchup(matchupLR2M1.getMatchupId());
        matchupLR1M1.setWinnerNextTeam(2);
        matchupLR1M1.setWeight(1);
        tournamentDao.updateMatchup(matchupLR1M1);
        
        matchupLR1M2.setWinnerNextMatchup(matchupLR2M2.getMatchupId());
        matchupLR1M2.setWinnerNextTeam(2);
        matchupLR1M2.setWeight(1);
        tournamentDao.updateMatchup(matchupLR1M2);
        
        matchupLR1M3.setWinnerNextMatchup(matchupLR2M3.getMatchupId());
        matchupLR1M3.setWinnerNextTeam(2);
        matchupLR1M3.setWeight(1);
        tournamentDao.updateMatchup(matchupLR1M3);
        
        matchupLR1M4.setWinnerNextMatchup(matchupLR2M4.getMatchupId());
        matchupLR1M4.setWinnerNextTeam(2);
        matchupLR1M4.setWeight(1);
        tournamentDao.updateMatchup(matchupLR1M4);
        
        //lower round 2 details
        matchupLR2M1.setWinnerNextMatchup(matchupLR3M1.getMatchupId());
        matchupLR2M1.setWinnerNextTeam(1);
        matchupLR2M1.setWeight(1);
        tournamentDao.updateMatchup(matchupLR2M1);
        
        matchupLR2M2.setWinnerNextMatchup(matchupLR3M1.getMatchupId());
        matchupLR2M2.setWinnerNextTeam(2);
        matchupLR2M2.setWeight(1);
        tournamentDao.updateMatchup(matchupLR2M2);
        
        matchupLR2M3.setWinnerNextMatchup(matchupLR3M2.getMatchupId());
        matchupLR2M3.setWinnerNextTeam(1);
        matchupLR2M3.setWeight(1);
        tournamentDao.updateMatchup(matchupLR2M3);
        
        matchupLR2M4.setWinnerNextMatchup(matchupLR3M2.getMatchupId());
        matchupLR2M4.setWinnerNextTeam(2);
        matchupLR2M4.setWeight(1);
        tournamentDao.updateMatchup(matchupLR2M4);
        
        //lower round 3 details
        matchupLR3M1.setWinnerNextMatchup(matchupLR4M1.getMatchupId());
        matchupLR3M1.setWinnerNextTeam(2);
        matchupLR3M1.setWeight(2);
        tournamentDao.updateMatchup(matchupLR3M1);
        
        matchupLR3M2.setWinnerNextMatchup(matchupLR4M2.getMatchupId());
        matchupLR3M2.setWinnerNextTeam(2);
        matchupLR3M2.setWeight(2);
        tournamentDao.updateMatchup(matchupLR3M2);
        
        //lower round 4 details
        matchupLR4M1.setWinnerNextMatchup(matchupLR5M1.getMatchupId());
        matchupLR4M1.setWinnerNextTeam(1);
        matchupLR4M1.setWeight(2);
        tournamentDao.updateMatchup(matchupLR4M1);
        
        matchupLR4M2.setWinnerNextMatchup(matchupLR5M1.getMatchupId());
        matchupLR4M2.setWinnerNextTeam(2);
        matchupLR4M2.setWeight(2);
        tournamentDao.updateMatchup(matchupLR4M2);
       
        //lower round 5 details
        matchupLR5M1.setWinnerNextMatchup(matchupLR6M1.getMatchupId());
        matchupLR5M1.setWinnerNextTeam(2);
        matchupLR5M1.setWeight(4);
        tournamentDao.updateMatchup(matchupLR5M1);
        
        //lower round 6 details
        matchupLR6M1.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupLR6M1.setWinnerNextTeam(2);
        matchupLR6M1.setWeight(4);
        tournamentDao.updateMatchup(matchupLR6M1);

        //grand finals
        matchupGF.setWeight(9);
        tournamentDao.updateMatchup(matchupGF);
    }

}
