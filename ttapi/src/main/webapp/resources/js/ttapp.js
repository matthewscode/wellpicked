var ttApp = angular.module('ttApp', []);

ttApp.directive('ckEditor', [function () {
    return {
        require: '?ngModel',
        link: function ($scope, elm, attr, ngModel) {
        	   var config = {
                       toolbar:[[ 'Bold', 'Italic', 'Underline', 'Strike', 'TextColor', 'FontSize', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight' ]],
                       width: '100%',
                       height: '32vh'
                   };
            var ck = CKEDITOR.replace(elm[0], config);

            ck.on('change', function () {
                $scope.$apply(function () {
                    ngModel.$setViewValue(ck.getData());
                });
            });

            ngModel.$render = function (value) {
                ck.setData(ngModel.$modelValue);
            };
        }
    };
}])

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
		
	$scope.init = function(url, options) {
		if(!url) {
			return;
		}
		$scope.dataUrl = url;
		
		$scope.options = options || {};
		$scope.options.start = $scope.options.start || 0;
		$scope.options.amount = $scope.options.amount || 50;
		
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
	};
	
	$scope.loadMore = function(url) {
		if(!url) {
			return;
		}
		url = url;
		
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = $scope.data.concat(processData(data));
				$scope.options.start += $scope.options.amount;
			})
		    .error(function(data, status, headers, config) {
	    		 console.log('lol u errored')
		    });
		
	};
	
}]);

ttApp.controller('FileController', ['$scope', '$http', function($scope, $http) {
	$scope.showDetail = false;
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

