package com.puppey.dto;

public class TournamentDto {
    
    private int tournamentId;
    private String tournamentName;
    private String tournamentSlug;
    private int tournamentStart;
    private int tournamentEnd;
    
    public TournamentDto(int tournamentId, String tournamentName, String tournamentSlug){
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.tournamentSlug = tournamentSlug;
    }
    
    public TournamentDto(int tournamentId, String tournamentName, String tournamentSlug, int tournamentStart, int tournamentEnd){
        this.tournamentId = tournamentId;
        this.tournamentName = tournamentName;
        this.tournamentSlug = tournamentSlug;
        this.tournamentStart = tournamentStart;
        this.tournamentEnd = tournamentEnd;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(int tournamentId) {
        this.tournamentId = tournamentId;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentSlug() {
        return tournamentSlug;
    }

    public void setTournamentSlug(String tournamentSlug) {
        this.tournamentSlug = tournamentSlug;
    }

	public int getTournamentStart() {
		return tournamentStart;
	}

	public void setTournamentStart(int tournamentStart) {
		this.tournamentStart = tournamentStart;
	}

	public int getTournamentEnd() {
		return tournamentEnd;
	}

	public void setTournamentEnd(int tournamentEnd) {
		this.tournamentEnd = tournamentEnd;
	}
    
    
}
