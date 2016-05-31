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
	
	$scope.predictMatchup = function(matchup, teamNum){
		console.log('matchup team 1 = ' + matchup.team1Id);
		console.log('teamNum = ' +teamNum);
		for(var i = 0; i < $scope.selectedTournament.matchupList.length; i++){
			if($scope.selectedTournament.matchupList[i].matchupId == matchup.winnerNextMatchId){
				var team = {};
				console.log(matchup.team1Id);
				if(teamNum == 1){
					for(var j = 0; j < $scope.selectedTournament.teamList.length; j++){
						console.log($scope.selectedTournament.teamList[j].id + '<->' + matchup.team1Id);
						if($scope.selectedTournament.teamList[j].id == matchup.team1Id){
							console.log('testthis' + $scope.selectedTournament.teamList[j].id);
							team = $scope.selectedTournament.teamList[j];
							break;
							}
						}
				}
				console.log(team.slug);
				$scope.selectedTournament.matchupList[i].team1Slug = team.slug;
			}
			
		}
		
	}
	
	
}]);