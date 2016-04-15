package com.puppey.dto;

import java.util.List;

import com.puppey.domain.MatchupPrediction;
import com.puppey.domain.User;

public class TournamentPredictionDto {

        private int tournamentPredictionId;
        private int tournamentPredictionScore;
        private int tournamentId;
        private String tournamentName;
        private String tournamentSlug;
        private int userId;
        private String username;
        private String userAvatar;
        private int creation;
        private String tournamentPredictionName;
        private int score;
        private List<MatchupPrediction> matchupPredictionList;
        
        
        public TournamentPredictionDto(int tournamentPredictionId, int tournamentPredictionScore, int tournamentId, User user, int creation, String tournamentPredictionName){
            this.tournamentPredictionId = tournamentPredictionId;
            this.tournamentPredictionScore = tournamentPredictionScore;
            this.tournamentId = tournamentId;
            this.creation = creation;
            this.tournamentPredictionName = tournamentPredictionName;
        }
        
        
        
        public TournamentPredictionDto() {
			
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
        public int getUserId() {
            return userId;
        }
        public void setUserId(int userId) {
            this.userId = userId;
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
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getUserAvatar() {
			return userAvatar;
		}
		public void setUserAvatar(String userAvatar) {
			this.userAvatar = userAvatar;
		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}

        
        
}
