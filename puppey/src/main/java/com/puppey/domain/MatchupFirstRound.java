package com.puppey.domain;

import java.util.ArrayList;
import java.util.List;

public class MatchupFirstRound {

    private List<Matchup> firstRoundMatchups = new ArrayList<>();

    public MatchupFirstRound(List<Matchup> firstRoundMatchups) {
        this.firstRoundMatchups = firstRoundMatchups;
    }

    public List<Matchup> getFirstRoundMatchups() {
        return firstRoundMatchups;
    }

    public void setFirstRoundMatchups(List<Matchup> firstRoundMatchups) {
        this.firstRoundMatchups = firstRoundMatchups;
    }

}
