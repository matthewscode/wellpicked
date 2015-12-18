package com.puppey.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchupWrapper {

    private List<Matchup> matchupList = new ArrayList<Matchup>();

    public List<Matchup> getMatchupList() {
        return matchupList;
    }

    public void setMatchupList(List<Matchup> listOfMatchups) {
        this.matchupList = listOfMatchups;
    }

}
