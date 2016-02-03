var ttApp = angular.module('ttApp', []);

ttApp.controller('ApiController', ['$scope', '$http', function($scope, $http) {
	var processData = function(data) {
		// Sort
		if($scope.options.sort && data) {
			data = _.sortBy(data, $scope.options.sort);
			if($scope.options.sortDirection && $scope.options.sortDirection === 'descending') {
				$scope.options.reverse();
			}
		}		
		return data;
	};
	
	$scope.dataUrl;
	
	$scope.init = function(url, options) {
		if(!url) {
			return;
		}
		$scope.dataUrl = url;
		
		$scope.options = options || {};
		
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = processData(data);
				if($scope.options.callback && typeof $scope.options.callback === 'function') {
					$scope.options.callback.apply(null, [$scope.data]);
				}
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.data = {error: true};
		    });
	}
}]);

ttApp.controller('utilController', ['$scope', '$http', function($scope, $http) {
	
	$scope.submitImageChecksum = function(url){
		$http.get(url).success(function(){
			console.log('made it');
		});
		}
	$scope.submitFileTranslation = function(url, originUrl){
		var newUrl = url + encodeURIComponent(originUrl);
		$http.get(newUrl).success(function(){
			console.log('made it');
		});
		}
	
}]);

