package com.puppey.dto;

public class MatchupDto {

    private int matchupId;
    private int date;
    private int winnerId;
    private int team1Id;
    private int team2Id;
    private String matchupType;
    private boolean showTeams1 = false;
    private boolean showTeams2 = false;
    private String team1Name;
    private String team2Name;
    private String team1Slug;
    private String team2Slug;
    private String team1Color;
    private String team2Color;
    private String winnerSlug;
    private int winnerNextMatchId;
    private int loserNextMatchId;
    private int winnerNextTeam;
    private int loserNextTeam;
    
    public MatchupDto(){
    	
    }
    
    public int getMatchupId() {
        return matchupId;
    }
    public void setMatchupId(int matchupId) {
        this.matchupId = matchupId;
    }
    public int getDate() {
        return date;
    }
    public void setDate(int date) {
        this.date = date;
    }
    public int getWinnerId() {
        return winnerId;
    }
    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }

	public int getTeam1Id() {
		return team1Id;
	}

	public void setTeam1Id(int team1Id) {
		this.team1Id = team1Id;
	}

	public int getTeam2Id() {
		return team2Id;
	}

	public void setTeam2Id(int team2Id) {
		this.team2Id = team2Id;
	}
	public String getMatchupType() {
		return matchupType;
	}
	public void setMatchupType(String matchupType) {
		this.matchupType = matchupType;
	}
	public boolean isShowTeams1() {
		return showTeams1;
	}
	public void setShowTeams1(boolean showTeams1) {
		this.showTeams1 = showTeams1;
	}
	public boolean isShowTeams2() {
		return showTeams2;
	}
	public void setShowTeams2(boolean showTeams2) {
		this.showTeams2 = showTeams2;
	}
	public String getTeam1Name() {
		return team1Name;
	}
	public void setTeam1Name(String team1Name) {
		this.team1Name = team1Name;
	}
	public String getTeam2Name() {
		return team2Name;
	}
	public void setTeam2Name(String team2Name) {
		this.team2Name = team2Name;
	}
	public String getTeam1Slug() {
		return team1Slug;
	}
	public void setTeam1Slug(String team1Slug) {
		this.team1Slug = team1Slug;
	}
	public String getTeam2Slug() {
		return team2Slug;
	}
	public void setTeam2Slug(String team2Slug) {
		this.team2Slug = team2Slug;
	}
	public String getWinnerSlug() {
		return winnerSlug;
	}
	public void setWinnerSlug(String winnerSlug) {
		this.winnerSlug = winnerSlug;
	}
	public int getWinnerNextMatchId() {
		return winnerNextMatchId;
	}
	public void setWinnerNextMatchId(int winnerNextMatchId) {
		this.winnerNextMatchId = winnerNextMatchId;
	}
	public int getLoserNextMatchId() {
		return loserNextMatchId;
	}
	public void setLoserNextMatchId(int loserNextMatchId) {
		this.loserNextMatchId = loserNextMatchId;
	}

	public String getTeam1Color() {
		return team1Color;
	}

	public void setTeam1Color(String team1Color) {
		this.team1Color = team1Color;
	}

	public String getTeam2Color() {
		return team2Color;
	}

	public void setTeam2Color(String team2Color) {
		this.team2Color = team2Color;
	}

	public int getWinnerNextTeam() {
		return winnerNextTeam;
	}

	public void setWinnerNextTeam(int winnerNextTeam) {
		this.winnerNextTeam = winnerNextTeam;
	}

	public int getLoserNextTeam() {
		return loserNextTeam;
	}

	public void setLoserNextTeam(int loserNextTeam) {
		this.loserNextTeam = loserNextTeam;
	}
	 

	
    
	
    
}
