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

@Service("template4U4LDE")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class Template4U4LDEImpl implements TournamentTemplate {

    @Autowired
    private TournamentDao tournamentDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createTournament(Tournament tournament) {
        List<Matchup> matchups = new ArrayList<Matchup>();

        // upper round 1
        matchups.add(new Matchup("UR1M1", tournament));
        matchups.add(new Matchup("UR1M2", tournament));

        // upper round 2
        matchups.add(new Matchup("UR2M1", tournament));

        // ************
        // lower round 1
        matchups.add(new Matchup("LR1M1", tournament));
        matchups.add(new Matchup("LR1M2", tournament));

        // lower round 2
        matchups.add(new Matchup("LR2M1", tournament));
        matchups.add(new Matchup("LR2M2", tournament));

        // lower round 3
        matchups.add(new Matchup("LR3M1", tournament));

        // lower round 4
        matchups.add(new Matchup("LR4M1", tournament));

        // grand finals
        matchups.add(new Matchup("GF", tournament));

        // add to db
        for (Matchup matchup : matchups) {
            tournamentDao.addMatchup(matchup);
        }

        List<Matchup> detailedMatchups = tournamentDao.getMatchupsByTournamentId(tournament.getTournamentId());
        // get matchups by tournament
        Matchup matchupUR1M1 = null;
        Matchup matchupUR1M2 = null;
        Matchup matchupUR2M1 = null;
        Matchup matchupLR1M1 = null;
        Matchup matchupLR1M2 = null;
        Matchup matchupLR2M1 = null;
        Matchup matchupLR2M2 = null;
        Matchup matchupLR3M1 = null;
        Matchup matchupLR4M1 = null;
        Matchup matchupGF = null;

        for (Matchup matchup : detailedMatchups) {// edit list
            if (matchup.getMatchupType() == "UR1M1") {
                matchupUR1M1 = matchup;
            }
            if (matchup.getMatchupType() == "UR1M2") {
                matchupUR1M2 = matchup;
            }
            if (matchup.getMatchupType() == "UR2M1") {
                matchupUR2M1 = matchup;
            }
            if (matchup.getMatchupType() == "LR1M1") {
                matchupLR1M1 = matchup;
            }
            if (matchup.getMatchupType() == "LR1M2") {
                matchupLR1M2 = matchup;
            }
            if (matchup.getMatchupType() == "LR2M1") {
                matchupLR2M1 = matchup;
            }
            if (matchup.getMatchupType() == "LR2M2") {
                matchupLR2M2 = matchup;
            }
            if (matchup.getMatchupType() == "LR3M1") {
                matchupLR3M1 = matchup;
            }
            if (matchup.getMatchupType() == "LR4M1") {
                matchupLR4M1 = matchup;
            }
            if (matchup.getMatchupType() == "GF") {
                matchupGF = matchup;
            }
        }

        // upper round 1 details
        matchupUR1M1.setWinnerNextMatchup(matchupUR2M1.getMatchupId());
        matchupUR1M1.setWinnerNextTeam(1);
        matchupUR1M1.setLoserNextMatchup(matchupLR2M1.getMatchupId());
        matchupUR1M1.setLoserNextTeam(1);
        matchupUR1M1.setWeight(4);

        matchupUR1M2.setWinnerNextMatchup(matchupUR2M1.getMatchupId());
        matchupUR1M2.setWinnerNextTeam(2);
        matchupUR1M2.setLoserNextMatchup(matchupLR2M2.getMatchupId());
        matchupUR1M2.setLoserNextTeam(1);
        matchupUR1M2.setWeight(4);

        // upper round 2 details
        matchupUR2M1.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupUR2M1.setWinnerNextTeam(1);
        matchupUR2M1.setLoserNextMatchup(matchupLR4M1.getMatchupId());
        matchupUR2M1.setLoserNextTeam(1);
        matchupUR2M1.setWeight(16);

        // lower round 1 details
        matchupLR1M1.setWinnerNextMatchup(matchupLR2M1.getMatchupId());
        matchupLR1M1.setWinnerNextTeam(2);
        matchupLR1M1.setWeight(2);

        matchupLR1M2.setWinnerNextMatchup(matchupLR2M2.getMatchupId());
        matchupLR1M2.setWinnerNextTeam(2);
        matchupLR1M2.setWeight(2);

        // lower round 2 details
        matchupLR2M1.setWinnerNextMatchup(matchupLR3M1.getMatchupId());
        matchupLR2M1.setWinnerNextTeam(1);
        matchupLR2M1.setWeight(4);

        matchupLR2M2.setWinnerNextMatchup(matchupLR3M1.getMatchupId());
        matchupLR2M2.setWinnerNextTeam(2);
        matchupLR2M2.setWeight(4);

        // lower round 3 details
        matchupLR3M1.setWinnerNextMatchup(matchupLR4M1.getMatchupId());
        matchupLR3M1.setWinnerNextTeam(2);
        matchupLR3M1.setWeight(8);

        // lower round 4 details
        matchupLR4M1.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupLR4M1.setWinnerNextTeam(2);
        matchupLR4M1.setWeight(16);
        
        matchupGF.setWeight(32);
    }

}
