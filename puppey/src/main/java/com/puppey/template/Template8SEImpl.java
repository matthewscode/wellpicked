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

@Service("template8SE")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class Template8SEImpl implements Template8SE {

    @Autowired
    private TournamentDao tournamentDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void createTournament(Tournament tournament) {
        List<Matchup> matchups = new ArrayList<Matchup>();

        // round 1
        matchups.add(new Matchup("R1M1", tournament));
        matchups.add(new Matchup("R1M2", tournament));
        matchups.add(new Matchup("R1M3", tournament));
        matchups.add(new Matchup("R1M4", tournament));

        // round 2
        matchups.add(new Matchup("R2M1", tournament));
        matchups.add(new Matchup("R2M2", tournament));

        // grand final
        matchups.add(new Matchup("GF", tournament));

        for (Matchup matchup : matchups) {
            tournamentDao.addMatchup(matchup);
        }

        List<Matchup> detailedMatchups = tournamentDao.getMatchupsByTournamentId(tournament.getTournamentId());
        // get matchups by tournament
        Matchup matchupR1M1 = null;
        Matchup matchupR1M2 = null;
        Matchup matchupR1M3 = null;
        Matchup matchupR1M4 = null;
        Matchup matchupR2M1 = null;
        Matchup matchupR2M2 = null;
        Matchup matchupGF = null;

        for (Matchup matchup : detailedMatchups) {// edit list
            if (matchup.getMatchupType() == "R1M1") {
                matchupR1M1 = matchup;
            }
            if (matchup.getMatchupType() == "R1M2") {
                matchupR1M2 = matchup;
            }
            if (matchup.getMatchupType() == "R1M3") {
                matchupR1M3 = matchup;
            }
            if (matchup.getMatchupType() == "R1M4") {
                matchupR1M4 = matchup;
            }
            if (matchup.getMatchupType() == "R2M1") {
                matchupR2M1 = matchup;
            }
            if (matchup.getMatchupType() == "R2M2") {
                matchupR2M2 = matchup;
            }
            if (matchup.getMatchupType() == "GF") {
                matchupGF = matchup;
            }
        }

        // round 1 details
        matchupR1M1.setWinnerNextMatchup(matchupR2M1.getMatchupId());
        matchupR1M1.setWinnerNextTeam(1);
        matchupR1M1.setWeight(1);
        tournamentDao.updateMatchup(matchupR1M1);

        matchupR1M2.setWinnerNextMatchup(matchupR2M1.getMatchupId());
        matchupR1M2.setWinnerNextTeam(2);
        matchupR1M2.setWeight(1);
        tournamentDao.updateMatchup(matchupR1M2);

        matchupR1M3.setWinnerNextMatchup(matchupR2M2.getMatchupId());
        matchupR1M3.setWinnerNextTeam(1);
        matchupR1M3.setWeight(1);
        tournamentDao.updateMatchup(matchupR1M3);

        matchupR1M4.setWinnerNextMatchup(matchupR2M2.getMatchupId());
        matchupR1M4.setWinnerNextTeam(2);
        matchupR1M4.setWeight(1);
        tournamentDao.updateMatchup(matchupR1M4);

        // round 2 details
        matchupR2M1.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupR2M1.setWinnerNextTeam(1);
        matchupR2M1.setWeight(2);
        tournamentDao.updateMatchup(matchupR2M1);

        matchupR2M2.setWinnerNextMatchup(matchupGF.getMatchupId());
        matchupR2M2.setWinnerNextTeam(2);
        matchupR2M2.setWeight(2);
        tournamentDao.updateMatchup(matchupR2M2);
        
        //grand finals
        matchupGF.setWeight(4);
        tournamentDao.updateMatchup(matchupGF);

    }

}
