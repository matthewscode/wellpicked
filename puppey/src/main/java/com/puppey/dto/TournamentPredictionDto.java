package com.puppey.dto;

import java.util.List;

import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.User;

public class TournamentPredictionDto {

        private int tournamentPredictionId;
        private int tournamentPredictionScore;
        private int tournamentId;
        private User user;
        private int creation;
        private String tournamentPredictionName;
        private List<MatchupPrediction> matchupPredictionList;
        
        
        public TournamentPredictionDto(int tournamentPredictionId, int tournamentPredictionScore, int tournamentId, User user, int creation, String tournamentPredictionName){
            this.tournamentPredictionId = tournamentPredictionId;
            this.tournamentPredictionScore = tournamentPredictionScore;
            this.tournamentId = tournamentId;
            this.user = user;
            this.creation = creation;
            this.tournamentPredictionName = tournamentPredictionName;
        }
        
        
        
        public int getTournamentPredictionId() {
            return tournamentPredictionId;
        }
        public void setTournamentPredictionId(int tournamentPredictionId) {
            this.tournamentPredictionId = tournamentPredictionId;
        }
        public int getTournamentPredictionScore() {
            return tournamentPredictionScore;
        }
        public void setTournamentPredictionScore(int tournamentPredictionScore) {
            this.tournamentPredictionScore = tournamentPredictionScore;
        }
        public int getTournamentId() {
            return tournamentId;
        }
        public void setTournamentId(int tournamentId) {
            this.tournamentId = tournamentId;
        }
        public User getUser() {
            return user;
        }
        public void setUser(User user) {
            this.user = user;
        }
        public int getCreation() {
            return creation;
        }
        public String getTournamentPredictionName() {
            return tournamentPredictionName;
        }
        public void setTournamentPredictionName(String tournamentPredictionName) {
            this.tournamentPredictionName = tournamentPredictionName;
        }
        public List<MatchupPrediction> getMatchupPredictionList() {
            return matchupPredictionList;
        }
        public void setMatchupPredictionList(List<MatchupPrediction> matchupPredictionList) {
            this.matchupPredictionList = matchupPredictionList;
        }
        
        
}
