var wpApp = angular.module('wpApp', []);

/*
 * Background image directive
 */
wpApp.directive('ngBgImage', function() {
    return function(scope, element, attrs) {
        if(attrs.ngBgImage) {
            element.css({
                'background-image': 'url(' + attrs.ngBgImage +')'
            });
        }
    };
});

/*
 * Drag to scroll directive
 */
wpApp.directive('ngDragScroll', function() {
	return function(scope, element, attrs){
		var clicking = false;
	    var y = 0;
	    var x = 0;
	    var top = 0;
	    var left = 0;
	    
		angular.element(document).on('mouseup.dragscroll', function(e) {
			clicking = false;
		});
		
		element.on('mousedown.dragscroll', function(e) {
			e.preventDefault();
			clicking = true;
		    y = e.pageY;
		    x = e.pageX;
            top = $(element).scrollTop();
            left = $(element).scrollLeft();
		});
		
		element.on('mousemove.dragscroll', function(e) {
			if(clicking) {
			    $(element).scrollTop(top - e.pageY + y); 
			    $(element).scrollLeft(left - e.pageX + x);
			}
		});
	}
});

/*
 * Perfect scrollbar wrapper directive
 */
wpApp.directive('ngPerfectScroll', function($timeout) {
	return {
        scope: { watch: '=' },
        link: function(scope, element, attrs) {
    		if($.fn.perfectScrollbar) {
    			$(element).perfectScrollbar({
    		        "scrollYMarginOffset": 20,
    		        "scrollXMarginOffset": 20
    		    });
    			scope.$watch('watch', function() {
    				$timeout(function(){
    					$(element).perfectScrollbar('update');
    				}, 0);
    			});
    		}
    	}
	}
});

/*
 * Smooth scroll directive
 */
wpApp.directive('ngSmoothScroll', function() {
	return function(scope, element, attrs) {
		var target = $(attrs.ngSmoothScroll);
		if(target.length) {			
			element
				.on('click', function() {
					$('body').animate({
				        scrollTop: target.offset().top - ($(element).height()+30)
				    }, 400);
				})
				.data('target', target);
		}
	}
});

/*
 * Sticky scroll directive
 */
wpApp.directive('ngStickyScroll', function($compile) {
	return function(scope, element, attrs) {
		
		$(element).data('clone', $(element).clone(true).removeAttr('data-ng-sticky-scroll').addClass('sticky-scroll').hide());
		$(element).after($(element).data('clone'));
		$compile($(element).data('clone'))(scope);
		
		$(document).on('scroll.stickyscroll load.stickyscroll resize.stickyscroll', function() {
			var rect = element[0].getBoundingClientRect();
			if(	rect.top >= 0 &&
				rect.left >= 0
			) {
				// element is in viewport
				$(element).data('clone').hide();
				
			} else {
				// element is not in viewport
				$(element).data('clone').css({'width': $(element).width()+'px'}).show();
			}
		});
	}
});

/*
 * Timestamper directive
 */
wpApp.directive('ngTimestamper', ['$window', function($window) {
	if(!$window.moment) return;
	
	return {
	    restrict: 'AE',
	    scope: {
	    	timestamp: '='
	    },
	    link: function(scope, element, attrs) {
	    	var date = scope.timestamp > 0 ? moment.unix(scope.timestamp).utc() : moment().utc();
	    	var timestampEl = element.find('input');
	    	var monthEl = element.find('.timestamper-month'),
	    		dayEl = element.find('.timestamper-day'),
	    		yearEl = element.find('.timestamper-year'),
	    		hourEl = element.find('.timestamper-hour'),
	    		minuteEl = element.find('.timestamper-minute');
	    	
	    	_.each(moment.months(), function(element, index){
	    		monthEl.append('<option value="'+index+'">'+element+'</option>');
	    	});

    		_.each(_.range(0, date.daysInMonth()), function(element){
    			dayEl.append('<option value="'+(element+1)+'">'+(element+1)+'</option>');
	    	});

	    	_.each(_.range(date.year()-3, date.year()+4), function(element){
	    		yearEl.append('<option value="'+element+'">'+element+'</option>');
	    	});
	    	
	    	_.each(_.range(0, 24), function(element){
	    		hourEl.append('<option value="'+element+'">'+element+'</option>');
	    	});
	    	
	    	_.each(_.range(0, 60), function(element){
	    		minuteEl.append('<option value="'+element+'">'+element+'</option>');
	    	});
	    	
	    	monthEl.on('change', function(e){
	    		date.month(parseInt(monthEl.val()));
	    		timestampEl.val(date.unix());
	    		dayEl.children(':not([disabled])').remove();
	    		_.each(_.range(0, date.daysInMonth()), function(element){
	    			dayEl.append('<option value="'+(element+1)+'">'+(element+1)+'</option>');
		    	});
	    	}).val(date.month()).trigger('change');
	    	
	    	dayEl.on('change', function(e){
	    		date.date(parseInt(dayEl.val()));
	    		timestampEl.val(date.unix());
	    	}).val(date.date());

	    	yearEl.on('change', function(e){
	    		date.year(parseInt(yearEl.val()));
	    		timestampEl.val(date.unix());
	    	}).val(date.year());
	    	
	    	hourEl.on('change', function(e){
	    		date.hour(parseInt(hourEl.val()));
	    		timestampEl.val(date.unix());
	    	}).val(date.hour());
	    	
	    	minuteEl.on('change', function(e){
	    		date.minute(parseInt(minuteEl.val()));
	    		timestampEl.val(date.unix());
	    	}).val(date.minute());
	    }
	}
}]);

/*
 * Api controller
 * Retrieves whatever URL you want
 */
wpApp.controller('ApiController', ['$scope', '$http', function($scope, $http) {
	
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
		
		$scope.options = options || {};
		
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = processData(data);
				if($scope.options.callback && typeof $scope.options.callback === 'function') {
					$scope.options.callback.apply(null, [$scope.data]);
				}
				console.log($scope.data);
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.data = {error: true};
		    });
	}
}]);

/*
 * Comment controller
 * Retrieves and allows posting of comments
 */
wpApp.controller('CommentController', ['$scope', '$http', function($scope, $http) {
	$scope.newComment = {};
	
	$scope.init = function(url, options) {
		var url = url || $scope.getUrl || false;
		var options = options || {};
		
		if(!url) {
			return;
		} else {
			$scope.getUrl = url;
		}
		
		$scope.loading = true;

		if(options.objectName) {
			$scope.newComment.objectName = options.objectName;
		}
		
		if(options.objectId) {
			$scope.newComment.objectId = options.objectId;
		}
		
		$http.get(url)
			.success(function(data, status, headers, config) {
				$scope.data = _.sortBy(data, 'creation').reverse();
				$scope.loading = false;
				$scope.newComment.comment = '';
				console.log($scope.data);
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.data = {error: true};
				$scope.loading = false;
		    });
	}
	
	$scope.submit = function(url) {
		$scope.loading = true;
		
		$http.post(url, $scope.newComment)
			.success(function(data, status, headers, config) {
				$scope.init();
		    })
		    .error(function(data, status, headers, config) {
				$scope.loading = false;
		    	$scope.data = {error: true};
		    });
	}
}]);

/*
 * Tournament controller
 * Retrieves and displays a tournament
 */
wpApp.controller('TournamentController', ['$scope', '$attrs', '$http', '$timeout', '$window', function($scope, $attrs, $http, $timeout, $window) {
	if(!$attrs.url) return;

	$scope.now = Math.floor(new Date().getTime()/1000);
	$scope.tournament = null;
	$scope.teams = {};
	$scope.matchups = null;
	$scope.groups = null;
	$scope.error = false;
	$scope.loading = false;
	
	$scope.init = function(options) {
		if(options.newBracket) {
			$scope.newBracket = {
					tournament: {tournamentId: null},
					tournamentPredictionName: null,
					matchupPredictionList: []
			};
		}
		
		if(options.bracketPath) {
			$scope.bracketPath = options.bracketPath;
		}
		
		if(options.viewBracket) {
			$scope.viewBracket = options.viewBracket;
		}
		
		if(options.editBracket) {
			$scope.editBracket = options.editBracket;
		}
	};
	
	$scope.submitNewBracket = function(url) {
		if($scope.newBracket) {
			$scope.newBracket.tournamentPredictionName = ($scope.newBracket.tournamentPredictionName ? $scope.newBracket.tournamentPredictionName : 'New bracket ' + (Math.floor(Math.random() * 999)+1));
			$scope.newBracket.tournament.tournamentId = $scope.tournament.tournamentId;
			$scope.newBracket.matchupPredictionList = [];
			_.each($scope.matchups, function(matchup) {
				$scope.newBracket.matchupPredictionList.push({
					winner: (matchup.winnerId ? matchup.winnerId : 0),
					matchup: matchup.matchupId
				});
			});
			
			$scope.loading = true;

			$http.post(url, $scope.newBracket)
				.success(function(data, status, headers, config) {
			    	if(data.tournamentPredictionId && $scope.bracketPath) {
			    		$window.location = $scope.bracketPath + data.tournamentPredictionId;
			    	}
					$scope.loading = false;
			    })
			    .error(function(data, status, headers, config) {
					$scope.loading = false;
					$scope.error = true;
			    });
		}
	};
	
	$scope.submitEditBracket = function(url) {
		if($scope.editBracket) {
			var editedBracket = {};
			editedBracket.tournamentPredictionName = ($scope.prediction.tournamentPredictionName ? $scope.prediction.tournamentPredictionName : 'New bracket ' + (Math.floor(Math.random() * 999)+1));
			editedBracket.tournamentPredictionId = $scope.prediction.tournamentPredictionId;
			editedBracket.matchupPredictionList = [];
			_.each($scope.matchups, function(matchup) {
				editedBracket.matchupPredictionList.push({
					predictionId: matchup.predictionId,
					winner: (matchup.winnerId ? matchup.winnerId : 0),
					matchup: matchup.matchupId
				});
			});
			
			$scope.loading = true;
			
			$http.post(url, editedBracket)
			.success(function(data, status, headers, config) {
		    	if(data.tournamentPredictionId && $scope.bracketPath) {
		    		$window.location = $scope.bracketPath + data.tournamentPredictionId;
		    	}
				$scope.loading = false;
		    })
		    .error(function(data, status, headers, config) {
				$scope.loading = false;
				$scope.error = true;
		    });
		}
	};
	
	$scope.setWinner = function(matchup, team) {
		var winningTeam = (team === 1 ? 'team1' : (team === 2 ? 'team2' : null));
		var losingTeam = (team === 1 ? 'team2' : (team === 2 ? 'team1' : null));
		
		// Recursive function to unset matchups
		var unsetTeam = function(previousMatchup, nextMatchup) {
			var matchupNextTeam = (nextMatchup.matchupType === previousMatchup.winnerNextMatchup ? 'winnerNextTeam' : (nextMatchup.matchupType === previousMatchup.loserNextMatchup ? 'loserNextTeam' : false));
			
			if(matchupNextTeam) {
				nextMatchup.winnerId = false;
				if(previousMatchup[matchupNextTeam] === 1) {
					nextMatchup.team1 = null;
					if(nextMatchup.team2) {
						nextMatchup.team2.winner = false;
					}
				}
				else if(previousMatchup[matchupNextTeam] === 2) {
					nextMatchup.team2 = null;
					if(nextMatchup.team1) {
						nextMatchup.team1.winner = false;
					}
				}
			}
			
			if(nextMatchup.winnerNextMatchup && $scope.matchups[nextMatchup.winnerNextMatchup]) {
				unsetTeam(nextMatchup, $scope.matchups[nextMatchup.winnerNextMatchup]);
			}
			
			if(nextMatchup.loserNextMatchup && $scope.matchups[nextMatchup.loserNextMatchup]) {
				unsetTeam(nextMatchup, $scope.matchups[nextMatchup.loserNextMatchup]);
			}
		};
		
		if(winningTeam && matchup[winningTeam] && matchup.winnerId !== matchup[winningTeam].teamId) {
			// Unset winning team
			if(matchup.winnerId) {
				matchup.winnerId = 0;
				matchup.team1.winner = false;
				matchup.team2.winner = false;
			}
			
			// Set next matchup for winner
			if(matchup.winnerNextMatchup && $scope.matchups[matchup.winnerNextMatchup]) {
				unsetTeam(matchup, $scope.matchups[matchup.winnerNextMatchup]);
				
				if(matchup.winnerNextTeam === 1) {
					$scope.matchups[matchup.winnerNextMatchup].team1 = _.clone(matchup[winningTeam]);
				}
				else if(matchup.winnerNextTeam === 2) {
					$scope.matchups[matchup.winnerNextMatchup].team2 = _.clone(matchup[winningTeam]);
				}
			}
			
			// Set the next matchup for loser
			if(matchup.loserNextMatchup && $scope.matchups[matchup.loserNextMatchup]) {
				unsetTeam(matchup, $scope.matchups[matchup.loserNextMatchup]);
				
				if(matchup.loserNextTeam === 1) {
					$scope.matchups[matchup.loserNextMatchup].team1 = _.clone(matchup[losingTeam]);
				}
				else if(matchup.loserNextTeam === 2) {
					$scope.matchups[matchup.loserNextMatchup].team2 = _.clone(matchup[losingTeam]);
				}
			}
			
			// Set winning team
			matchup.winnerId = matchup[winningTeam].teamId;
			matchup[winningTeam].winner = true;
		}
	};
	
	var eliminateLosers = function() {
		switch($scope.tournament.template.templateName) {
			case "8SE":
				var eliminationTypes = ["R1M1", "R1M2", "R1M3", "R1M4", "R2M1", "R2M2", "GF"];
				break;
			case "4U0LDE":
				var eliminationTypes = ["LR1M1", "LR2M1", "GF"];
				break;
			case "4U4LDE":
				var eliminationTypes = ["LR1M1", "LR1M2", "LR2M1", "LR2M2", "LR3M1", "LR4M1", "GF"];
				break;
			case "8U8LDE":
				var eliminationTypes = ["LR1M1", "LR1M2", "LR1M3", "LR1M4", "LR2M1", "LR2M2",
				                        "LR2M3", "LR2M4", "LR3M1", "LR3M2", "LR4M1", "LR4M2",
				                        "LR5M1", "LR6M1", "GF"];
				break;
			default:
				break;
		}
		
		_.each(
			_.filter($scope.matchups, function(matchup) {
				return (_.indexOf(eliminationTypes, matchup.matchupType) >= 0);
			}),
			function(matchup) {
				if(matchup.team1 && matchup.team2 && matchup.winnerId) {
					if(matchup.team1.winner) {
						_.findWhere($scope.teams, {teamId: matchup.team2.teamId}).eliminated = true;
					}
					else if(matchup.team2.winner) {
						_.findWhere($scope.teams, {teamId: matchup.team1.teamId}).eliminated = true;
					}
				}
			}
		);
	};
	
	var parseBracket = function(tournamentData, bracketData) {
		switch(tournamentData.template.templateName) {
			case "8SE":
				var startingMatchups = ["R1M1", "R1M2", "R1M3", "R1M4"];
				var matchupOrder = ["R1M1", "R1M2", "R1M3", "R1M4", "R2M1", "R2M2", "GF"];
				break;
			case "4U0LDE":
				var startingMatchups = ["UR1M1", "UR1M2"];
				var matchupOrder = ["UR1M1", "UR1M2", "LR1M1", "UR2M1", "LR2M1", "GF"]
				break;
			case "4U4LDE":
				var startingMatchups = ["LR1M1", "LR1M2", "UR1M1", "UR1M2"];
				var matchupOrder = ["UR1M1", "UR1M2", "LR1M1", "LR1M2", "LR2M1", "LR2M2", "LR3M1", "UR2M1", "LR4M1", "GF"]
				break;
			case "8U8LDE":
				var startingMatchups = ["LR1M1", "LR1M2", "LR1M3", "LR1M4", "UR1M1", "UR1M2", "UR1M3", "UR1M4"];
				var matchupOrder = ["UR1M1", "UR1M2", "UR1M3", "UR1M4", "LR1M1", "LR1M2", "LR1M3", "LR1M4", 
				                    "LR2M1", "LR2M2", "LR2M3", "LR2M4", "LR3M1", "LR3M2",
				                    "UR2M1", "UR2M2", "LR4M1", "LR4M2", "LR5M1", "UR3M1", "LR6M1", "GF"]
				break;
			default:
				break;
		}
		
		var predictions = {};
		
		// Loop through each bracket matchup prediction
		_.each(bracketData.matchupPredictionList, function(prediction) {
			predictions[prediction.matchup] = prediction;
		});

		// Loop through each matchup
		_.each(tournamentData.matchups, function(matchup) {			
			// Set loser matchup to matchup object
			if(matchup.loserNextMatchup) {
				matchup.loserNextMatchup = _.findWhere(tournamentData.matchups, {matchupId: matchup.loserNextMatchup}).matchupType;
			}
			
			// Set winner matchup to matchup object
			if(matchup.winnerNextMatchup) {
				matchup.winnerNextMatchup = _.findWhere(tournamentData.matchups, {matchupId: matchup.winnerNextMatchup}).matchupType;
			}
			
			// Unset information
			matchup.winnerId = null;
			matchup.loserId = null;
			matchup.team1 = _.indexOf(startingMatchups, matchup.matchupType) < 0 ? null : matchup.team1;
			matchup.team2 = _.indexOf(startingMatchups, matchup.matchupType) < 0 ? null : matchup.team2;
			
			// Get unique teams
			if(matchup.team1 && matchup.team1.teamId && !$scope.teams[matchup.team1.teamId]) {
				$scope.teams[matchup.team1.teamId] = _.clone(matchup.team1);
			}
			if(matchup.team2 && matchup.team1.teamId && !$scope.teams[matchup.team2.teamId]) {
				$scope.teams[matchup.team2.teamId] = _.clone(matchup.team2);
			}
		});
		
		// Loop through matchups in order to set bracket information correctly
		_.each(matchupOrder, function(matchupType) {
			var matchup = _.findWhere(tournamentData.matchups, {matchupType: matchupType});
			var prediction = predictions[matchup.matchupId];
			
			if(prediction.predictionId) {
				matchup.predictionId = prediction.predictionId;
			}

			if(prediction.winner && matchup.team1 && prediction.winner === matchup.team1.teamId) {
				var winner = matchup.team1;
				var loser = matchup.team2;
			}
			else if(prediction.winner && matchup.team2 && prediction.winner === matchup.team2.teamId) {
				var winner = matchup.team2;
				var loser = matchup.team1;
			}
			
			if(winner && matchup.winnerNextMatchup) {
				var winnerNextMatchup = _.findWhere(tournamentData.matchups, {matchupType: matchup.winnerNextMatchup});
			}
			
			if(loser && matchup.loserNextMatchup) {
				var loserNextMatchup = _.findWhere(tournamentData.matchups, {matchupType: matchup.loserNextMatchup});
			}

			if(winner && matchup.winnerNextMatchup && matchup.winnerNextTeam === 1) {
				winnerNextMatchup.team1 = _.clone(winner);
			}
			else if(winner && matchup.winnerNextMatchup && matchup.winnerNextTeam === 2) {
				winnerNextMatchup.team2 = _.clone(winner);
			}
			
			if(winner && matchup.loserNextMatchup && matchup.loserNextTeam === 1) {
				loserNextMatchup.team1 = _.clone(loser);
			}
			else if(winner && matchup.loserNextMatchup && matchup.loserNextTeam === 2) {
				loserNextMatchup.team2 = _.clone(loser);
			}
			
			if(winner) {
				matchup.winnerId = winner.teamId;
				winner.winner = true;
			}
			
			// Pass matchups to scope
			$scope.matchups = $scope.matchups || {};
			$scope.matchups[matchup.matchupType] = matchup;
		});
		
		// Pass tournaments to scope
		$scope.tournament = tournamentData;
		
		// Check tournament status
		var tournamentStart = $scope.tournament.tournamentStart*1000;
		var tournamentEnd = $scope.tournament.tournamentEnd*1000;
		var now = Date.now();
		if(tournamentStart > now) {
			$scope.tournament.upcoming = true;
		}
		else if(tournamentStart <= now && tournamentEnd > now) {
			$scope.tournament.in_progress = true;
		}
		else {
			$scope.tournament.completed = true;
		}
		
		// Pass bracket to scope
		$scope.prediction = bracketData;

		console.log($scope);
	};
	
	var parseData = function(data) {
		
		// Loop through each matchup
		_.each(data.matchups, function(matchup) {
			
			// Unset NONE team
			if(matchup.team1 && !matchup.team1.teamId) {
				matchup.team1 = null;
			}
			if(matchup.team2 && !matchup.team2.teamId) {
				matchup.team2 = null;
			}
			
			// Get unique teams
			if(matchup.team1 && matchup.team1.teamId && !$scope.teams[matchup.team1.teamId]) {
				$scope.teams[matchup.team1.teamId] = _.clone(matchup.team1);
			}
			if(matchup.team2 && matchup.team1.teamId && !$scope.teams[matchup.team2.teamId]) {
				$scope.teams[matchup.team2.teamId] = _.clone(matchup.team2);
			}
			
			// Set loser matchup to matchup type
			if(matchup.loserNextMatchup) {
				matchup.loserNextMatchup = _.findWhere(data.matchups, {matchupId: matchup.loserNextMatchup}).matchupType;
			}
			
			// Set winner matchup to matchup type
			if(matchup.winnerNextMatchup) {
				matchup.winnerNextMatchup = _.findWhere(data.matchups, {matchupId: matchup.winnerNextMatchup}).matchupType;
			}
			
			// Otherwise for tournament view, flag winners normally
			if(matchup.winnerId && matchup.loserId) {
				if(matchup.winnerId === matchup.team1.teamId) {
					matchup.winner = matchup.team1;
					matchup.team1.winner = true;
				} else {
					matchup.winner = matchup.team2;
					matchup.team2.winner = true;
				}
			}
			
			// Pass matchups to scope
			$scope.matchups = $scope.matchups || {};
			$scope.matchups[matchup.matchupType] = matchup;
		});
		
		// Pass tournaments to scope
		$scope.tournament = data;
		
		// Check tournament status
		var tournamentStart = $scope.tournament.tournamentStart*1000;
		var tournamentEnd = $scope.tournament.tournamentEnd*1000;
		var now = Date.now();
		if(tournamentStart > now) {
			$scope.tournament.upcoming = true;
		}
		else if(tournamentStart <= now && tournamentEnd > now) {
			$scope.tournament.in_progress = true;
		}
		else {
			$scope.tournament.completed = true;
		}
		
		// Pass groups to scope
		$scope.groups = data.groups;
		
		// Sort teams by name
		$scope.teams = _.sortBy($scope.teams, 'teamName');
		
		// Otherwise eliminate losing teams
		eliminateLosers();
	};
	
	$scope.loading = true;
	
	$http.get($attrs.url)
		.success(function(data, status, headers, config) {
			
			// If user bracket, consolidate w/ tournament matchups
			if($scope.viewBracket || $scope.editBracket) {
				$http.get(($scope.viewBracket ? $scope.viewBracket : $scope.editBracket))
					.success(function(userBracketData, status, headers, config) {
				    	parseBracket(data, userBracketData);
						$scope.loading = false;
				    })
				    .error(function(data, status, headers, config) {
				    	$scope.error = true;
						$scope.loading = false;
				    });
			}
			
			// Otherwise parse as normal tournament
			else {
		    	parseData(data);
		    	$scope.loading = false;
			}
	    })
	    .error(function(data, status, headers, config) {
	    	$scope.error = true;
	    	$scope.loading = false;
	    });
}]);

/*
 * Team controller
 */
wpApp.controller('TeamController', ['$scope', '$http', function($scope, $http) {
	
	$scope.favLoading = false;
	
	$scope.init = function(options) {
		if(options) {
			$scope = _.extend($scope, options);
		}
	};

	$scope.favoriteTeam = function(url) {
		if(!url || $scope.favLoading) return;
		
		$scope.favLoading = true;
		
		$http.get(url)
			.success(function(data, status, headers, config) {
		    	$scope.hasInFavorites = true;
		    	$scope.favLoading = false;
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.error = true;
		    	$scope.favLoading = false;
		    });
	};
	
	$scope.unfavoriteTeam = function(url) {
		if(!url || $scope.favLoading) return;
		
		$scope.favLoading = true;
		
		$http.get(url)
			.success(function(data, status, headers, config) {
		    	$scope.hasInFavorites = false;
		    	$scope.favLoading = false;
		    })
		    .error(function(data, status, headers, config) {
		    	$scope.error = true;
		    	$scope.favLoading = false;
		    });
	};
	
	$scope.getStreams = function(data) {
		$scope.streams = [];
		_.each(data.channels, function(channel){
			if(channel.channel.meta_game === 'Dota 2') {
				$scope.streams.push(channel.channel);
			}
		});
	};
	
	$scope.getTournaments = function(data) {
		
	};
}]);