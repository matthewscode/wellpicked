package com.puppey.util;

public class Message {

    private boolean success;
    private int tournamentPredictionId;

    public Message(boolean success) {
        this.success = success;
    }
    public Message(int tournamentPredictionId){
    	this.tournamentPredictionId = tournamentPredictionId;
    	this.setSuccess(true);
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
	public int getTournamentPredictionId() {
		return tournamentPredictionId;
	}
	public void setTournamentPredictionId(int tournamentPredictionId) {
		this.tournamentPredictionId = tournamentPredictionId;
	}

}
