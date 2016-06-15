var wpApp = angular.module('wpApp', ['ngRoute']);
wpApp.config(function($routeProvider) {
    $routeProvider

//        .when('/', {
//            templateUrl : 'WEB-INF/views/tournament.jsp',
//            controller  : 'mainCtrl'
//        })

        .when('/tournament/:tournamentSlug', {
            templateUrl : 'pages/tournament.jsp',
            controller  : 'tournamentCtrl'
        })
        .when('/stream/:streamTag', {
            templateUrl : 'pages/stream.jsp',
            controller  : 'streamCtrl'
        })
        
        .when('/', {
            templateUrl : 'pages/news.jsp',
            controller  : 'newsCtrl'
        })
        
        .when('/profile', {
            templateUrl : 'pages/profile.jsp',
            controller  : 'profileCtrl'
        })
        .when('/bracket/create/:tournamentSlug', {
            templateUrl : 'pages/bracket-create.jsp',
            controller  : 'bracketCtrl'
        })
                .when('/bracket/view/:tournamentSlug/:tpid', {
            templateUrl : 'pages/bracket-view.jsp',
            controller  : 'bracketCtrl'
        })

});
wpApp.controller('mainCtrl', ['$scope', '$http', '$location', function($scope, $http, $location) {
	$scope.latestTournamentsUrl = 'api/tournament/list/latest/4';
	$scope.matchupListUrl = 'api/tournament/matchup/list/'
	$scope.streamUrl = 'api/streams/live';
	$scope.homeTournament = {};
	$scope.username = 'Guest';
	$scope.userId = '0';
	$scope.userAvatar;
	$scope.init = function() {
		$http.get('api/user/current')
			.success(function(data) {
					$scope.userId = data.userId;
					$scope.username = data.username;
					if(angular.isDefined(data.userAvatar) && data.userAvatar.length  > 0){
					$scope.userAvatar = data.userAvatar;
					}else{
						$scope.userAvatar = 'default';
					}
				})
		$http.get($scope.latestTournamentsUrl)
			.success(function(data) {
				$scope.homeTournament.slug = data[0].tournamentSlug;
				$scope.tData = data;
			})
			.error(function(){
				console.log('didnt init 4 tournaments');
			})
		$http.get($scope.streamUrl)
			.success(function(data){
				$scope.dStream = data;
			})
			.error(function(){
				console.log('didnt init streams');
			})
	};
	
	$scope.go = function(path){
		 $location.path( path );
	}
	
	
}]);

wpApp.controller('newsCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.selectedNewsId = 0;
	$scope.latestNewsUrl = 'api/news/list/latest/4';
	$scope.init = function() {
		$http.get($scope.latestNewsUrl)
			.success(function(data) {
				$scope.dNews = data;
				$scope.selectedNewsId = data[0].newsId;;
			})
			.error(function(){
				console.log('didnt init 4 news');
			})
	};
	
	
}]);

wpApp.controller('tournamentCtrl', ['$scope', '$routeParams', '$http', function($scope, $routeParams, $http) {
	$scope.selectedTournament = {};
	$scope.tournamentUrl = 'api/tournament/';
	$scope.tournamentTeamUrl = 'api/tournament/team/list/';
	$scope.init = function(){
		$scope.getTournament();
	}
	$scope.getTournament = function() {
		$http.get($scope.tournamentUrl + $routeParams.tournamentSlug)
		.success(function(data) {
				$scope.selectedTournament.id = data.tournamentId;
				$scope.selectedTournament.name = data.tournamentName;
				$scope.selectedTournament.slug = data.tournamentSlug;
				$scope.selectedTournament.desc = data.tournamentDesc;
				$scope.selectedTournament.template = data.template;
				$scope.getTournamentTeams();
				$scope.getTournamentMatchups();
			})
			.error(function(){
				console.log('error didnt set it');
			})
	}
	$scope.getTournamentTeams = function() {
		$http.get($scope.tournamentTeamUrl + $scope.selectedTournament.id)
		.success(function(data) {
			$scope.selectedTournament.teamList = data;
			})
			.error(function(){
				console.log('error in getting teams');
			})
	}
	
	$scope.getTournamentMatchups = function(){
		$http.get($scope.matchupListUrl + $scope.selectedTournament.id)
		.success(function(data) {
			$scope.selectedTournament.matchupList = data;
			})
			.error(function(){
				console.log('error in getting matchups');
			})
	}
}]);

wpApp.controller('teamListCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.northAmerica = [];
	$scope.europe = [];
	$scope.china = [];
	$scope.sea = [];
	$scope.init = function(url) {
		$http.get(url)
			.success(function(data) {
				for(i = 0; i < data.length; i++){
					if(data[i].teamRegion == 'NA'){
						$scope.northAmerica.push(data[i]);
						
					}else if(data[i].teamRegion == 'EU'){
						$scope.europe.push(data[i]);
					}else if(data[i].teamRegion == 'CN'){
						$scope.china.push(data[i]);
					}else if(data[i].teamRegion == 'SEA'){
						$scope.sea.push(data[i]);
					}
					
				}
				
			})
			.error(function(){
				console.log('error');
			})
	};
}]);
wpApp.controller('bracketListCtrl', ['$scope', '$http', function($scope, $http) {
	//get to showing 4 but only pulling one
	$scope.selectedTournament;
	$scope.init = function(url, bracketUrl) {
		$http.get(url)
			.success(function(data) {
				$scope.data = data;
				$scope.selectedTournament = data[0].tournamentId;
				$scope.getBrackets(bracketUrl + $scope.selectedTournament);
			})
			.error(function(){
				console.log('error on bracketListCtrl data init');
			})
	};
	$scope.getBrackets = function(url){
		$scope.loadIt = false;
		console.log($scope.selectedTournament);
		$http.get(url)
		.success(function(data) {
				$scope.bData = data;
				$scope.loadIt = true;
			})
			.error(function(){
				console.log('error on bracketListCtrl data init');
			})
	}
}]);
wpApp.controller('streamCtrl', ['$scope', '$routeParams', '$http', '$sce', function($scope, $routeParams, $http, $sce) {
	$scope.init = function() {
		console.log('test is it geting here');
		$scope.streamUrl = $sce.trustAsResourceUrl('http://www.twitch.tv/' + $routeParams.streamTag + '/embed');
	};
}]);
wpApp.controller('profileCtrl', ['$scope', '$routeParams', '$http', '$sce', function($scope, $routeParams, $http, $sce) {
	$scope.userAvatar;
	$scope.init = function() {
		$http.get('api/user/current')
		.success(function(data) {
				$scope.userId = data.userId;
				$scope.username = data.username;
				if(data.userAvatar.length  > 0){
				$scope.userAvatar = data.userAvatar;
				}else{
					$scope.userAvatar = 'default';
				}
			})
		$http.get('api/user/brackets/recent')
		.success(function(data) {
				$scope.bracketList = data;
			})
	};
}]);
wpApp.controller('bracketCtrl', ['$scope', '$routeParams', '$http', '$sce', '$filter', function($scope, $routeParams, $http, $sce, $filter) {
	$scope.selectedTournament = {};
	$scope.tournamentUrl = 'api/tournament/';
	$scope.tournamentTeamUrl = 'api/tournament/team/list/';
	$scope.allTeamsUrl = 'api/team/list/active/0';
	$scope.matchupPredictionListUrl = 'api/tournament-prediction/matchup-list/'
	$scope.init = function(){
		$scope.getTournament();
		
	}
	$scope.getTournament = function() {
		$http.get($scope.tournamentUrl + $routeParams.tournamentSlug)
		.success(function(data) {
				$scope.selectedTournament.id = data.tournamentId;
				$scope.selectedTournament.name = data.tournamentName;
				$scope.selectedTournament.slug = data.tournamentSlug;
				$scope.selectedTournament.desc = data.tournamentDesc;
				$scope.selectedTournament.template = data.template;
				$scope.getTournamentTeams();
				$scope.getTournamentMatchups();
			})
			.error(function(){
				console.log('error didnt set it');
			})
	}
	$scope.getTournamentTeams = function() {
		$http.get($scope.allTeamsUrl)
		.success(function(data) {
			$scope.selectedTournament.teamList = data;
			})
			.error(function(){
				console.log('error in getting teams');
			})
	}
	
	$scope.getTournamentMatchups = function(){
		$http.get($scope.matchupListUrl + $scope.selectedTournament.id)
		.success(function(data) {
			$scope.selectedTournament.matchupList = data;
			if($routeParams.tpid.length > 0){
				$scope.getTournamentPrediction();
			}
			})
			.error(function(){
				console.log('error in getting matchups');
			})
	}
	
	$scope.getTournamentPrediction = function(){
		$scope.tournamentPrediction = {};
		$http.get($scope.matchupPredictionListUrl + $routeParams.tpid)
		.success(function(data) {
			$scope.tournamentPrediction = data;
			for(var i = 0; i < data.matchupPredictionList.length; i++){
				if(data.matchupPredictionList[i].winner == data.matchupPredictionList[i].team1Id){
					$scope.predictMatchup(data.matchupPredictionList[i], 1);
				}else if(data.matchupPredictionList[i].winner == data.matchupPredictionList[i].team2Id){
					$scope.predictMatchup(data.matchupPredictionList[i], 2);
				}
			}
			})
			.error(function(){
				console.log('error in getting matchup predictions');
			})
	}
	
	$scope.predictMatchup = function(matchup, teamNum){
		var winnerNextMatchup;
		var loserNextMatchup;
		var winnerTeamNum = matchup.winnerNextTeam;
		var loserTeamNum = matchup.loserNextTeam;
		var team1 = $scope.findTeam(matchup.team1Id);
		var team2 = $scope.findTeam(matchup.team2Id);
		
		var teamWinner = {};
		var teamLoser = {};
		if(teamNum == 1){
			matchup.winnerId = team1.id;
			teamWinner = team1;
			teamLoser = team2;
		} else if(teamNum == 2){
			matchup.winnerId = team2.id;
			teamWinner = team2;
			teamLoser = team1;
		}
		for(var i = 0; i < $scope.selectedTournament.matchupList.length; i++){
			if($scope.selectedTournament.matchupList[i].matchupId == matchup.id){
				$scope.selectedTournament.matchupList[i].winner = matchup.winnerId;
			}
			if($scope.selectedTournament.matchupList[i].matchupId == matchup.winnerNextMatchId){
				winnerNextMatchup = $scope.selectedTournament.matchupList[i];
				if(teamNum == 1){
					if(winnerTeamNum == 1){
						$scope.selectedTournament.matchupList[i].team1Id = teamWinner.id;
						$scope.selectedTournament.matchupList[i].team1Slug = teamWinner.slug;
						$scope.selectedTournament.matchupList[i].team1Color = teamWinner.color;
						$scope.selectedTournament.matchupList[i].team1Name = teamWinner.name;
					} else if(winnerTeamNum == 2){
						$scope.selectedTournament.matchupList[i].team2Id = teamWinner.id;
						$scope.selectedTournament.matchupList[i].team2Slug = teamWinner.slug;
						$scope.selectedTournament.matchupList[i].team2Color = teamWinner.color;
						$scope.selectedTournament.matchupList[i].team2Name = teamWinner.name;
					}
	
				}else if(teamNum == 2){
					if(winnerTeamNum == 1){
						$scope.selectedTournament.matchupList[i].team1Id = teamWinner.id;
						$scope.selectedTournament.matchupList[i].team1Slug = teamWinner.slug;
						$scope.selectedTournament.matchupList[i].team1Color = teamWinner.color;
						$scope.selectedTournament.matchupList[i].team1Name = teamWinner.name;
					}else if(winnerTeamNum == 2){
						$scope.selectedTournament.matchupList[i].team2Id = teamWinner.id;
						$scope.selectedTournament.matchupList[i].team2Slug = teamWinner.slug;
						$scope.selectedTournament.matchupList[i].team2Color = teamWinner.color;
						$scope.selectedTournament.matchupList[i].team2Name = teamWinner.name;
					}
				}

				
			} 
			if($scope.selectedTournament.matchupList[i].matchupId == matchup.loserNextMatchId){
				if(loserTeamNum == 1){
					$scope.selectedTournament.matchupList[i].team1Id = teamLoser.id;
					$scope.selectedTournament.matchupList[i].team1Slug = teamLoser.slug;
					$scope.selectedTournament.matchupList[i].team1Color = teamLoser.color;
					$scope.selectedTournament.matchupList[i].team1Name = teamLoser.name;
				}else if(loserTeamNum == 2){
					$scope.selectedTournament.matchupList[i].team2Id = teamLoser.id;
					$scope.selectedTournament.matchupList[i].team2Slug = teamLoser.slug;
					$scope.selectedTournament.matchupList[i].team2Color = teamLoser.color;
					$scope.selectedTournament.matchupList[i].team2Name = teamLoser.name;
				}
			}
			
		}
	}
	
	$scope.submitTournamentPrediction = function(predictionName){
		if(predictionName.length > 30){
			predictionName = predictionName.substring(0,30);
		}
		$scope.matchupPredictionList = [];
		for(var i = 0; i < $scope.selectedTournament.matchupList.length; i++){
			var matchupPrediction = {};
			matchupPrediction.matchup = $scope.selectedTournament.matchupList[i].matchupId;
			matchupPrediction.winner = $scope.selectedTournament.matchupList[i].winnerId;
			$scope.matchupPredictionList.push(matchupPrediction);
		}
		$http.post('api/tournament-prediction/create/' + $scope.selectedTournament.id + '/' + predictionName, $scope.matchupPredictionList)
		.success(function() {
			console.log('sucessfully created tp');
			})
			.error(function(){
				console.log('DIDNT CREATE TP');
			})
		
	}
	$scope.findTeam = function(teamId){
		for(var j = 0; j < $scope.selectedTournament.teamList.length; j++){
			var team = {};
			if($scope.selectedTournament.teamList[j].id == teamId){
				team = $scope.selectedTournament.teamList[j];
				return team;
				}
			}
		return team;
	}
	
}]);