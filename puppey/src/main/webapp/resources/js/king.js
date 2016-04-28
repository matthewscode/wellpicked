
//wpApp.config(function($routeProvider) {
//    $routeProvider

//        .when('/', {
//            templateUrl : 'WEB-INF/views/tournament.jsp',
//            controller  : 'mainCtrl'
//        })

//        .when('/tournament/:tournamentSlug', {
//            templateUrl : 'pages/tournament.jsp',
//            controller  : 'tournamentCtrl'
//        })

//});
wpApp.controller('adminCtrl', ['$scope', '$http', function($scope, $http) {
	$scope.newsPostUrl = 'api/news';
	$scope.news = {};
	$scope.newsMessage = '';
	$scope.postNews = function(){
		var data = $scope.news;
		$http.post($scope.newsPostUrl, data)
		.success(function(data) {
				if(data){
					$scope.newsMessage = 'article submitted';
				}
			})
			.error(function(){
				console.log('error didnt set it');
			})
	}
	
}]);