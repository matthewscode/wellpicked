var wpApp = angular.module('wpApp', []);

wpApp.controller('mainCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.tournamentUrl;
	$scope.tournamentSelected = false;
	$scope.init = function(url, tournamentUrl) {
		$http.get(url)
			.success(function(data) {
				$scope.selectedTournament = data[0].tournamentId;
				$scope.tournamentUrl = tournamentUrl;
				$scope.getTournament($scope.selectedTournament);
				$scope.data = data;
			})
			.error(function(){
				console.log('error');
			})
	};
	
	$scope.getTournament = function(tournamentId) {
		$scope.tournamentSelected = false;
		$http.get($scope.tournamentUrl + tournamentId)
		.success(function(data) {
				$scope.tournamentName = data.tournamentName;
				$scope.tournamentSlug = data.tournamentSlug;
				$scope.tournamentSelected = true;
			})
			.error(function(){
				console.log('error');
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
