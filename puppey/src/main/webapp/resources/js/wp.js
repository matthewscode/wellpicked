var wpApp = angular.module('wpApp', []);

wpApp.controller('mainCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.tournamentUrl;
	$scope.tournamentTeamUrl;
	$scope.matchupListUrl = 'api/tournament/matchup/list/'
	$scope.selectedTournament = {};
	$scope.init = function(url, tournamentUrl) {
		$http.get(url)
			.success(function(data) {
				$scope.ctrl = 'news';
				$scope.selectedTournament.id = data[0].tournamentId;
				$scope.selectedTournament.slug = data[0].tournamentSlug;
				$scope.tournamentUrl = tournamentUrl;
				$scope.tData = data;
			})
			.error(function(){
				console.log('didnt init 4 tournaments');
			})
	};
	
	$scope.getTournament = function(id) {
		$http.get($scope.tournamentUrl + id)
		.success(function(data) {
				$scope.ctrl = 'tournament';
				$scope.selectedTournament.id = data.tournamentId;
				$scope.selectedTournament.name = data.tournamentName;
				$scope.selectedTournament.slug = data.tournamentSlug;
				$scope.selectedTournament.desc = data.tournamentDesc;
				$scope.selectedTournament.template = '4SE';
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

wpApp.controller('tournamentCtrl', ['$scope', '$http', function($scope, $http) {
	
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
