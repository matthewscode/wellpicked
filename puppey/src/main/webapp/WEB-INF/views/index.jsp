<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,700'
	rel='stylesheet' type='text/css'>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WPGG</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.0/less.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/wpgg.css" />">
</head>
<body data-ng-app="wpApp">
	<div id="main-container" data-ng-controller="mainCtrl"
		data-ng-init="init();">

		<!-- NAVIGATION -->
		<div id="nav-container">
			<div class="nav-home-container" style="width: 5%;">
			<a href="#/" class="nav-link-entry">WP.GG</a>
			</div>
			<div class="nav-tournament-container"
				ng-click="showTeams=false;showTournaments=!showTournaments" ng-mouseenter="navtournaments = 'navtournamentsshown'" ng-mouseleave="navtournaments = 'navtournamentshidden'">
				
				<a href="" class="nav-link-entry">Tournaments<i
					class="icons">keyboard_arrow_down</i></a>
				<div class= "navtournamentshidden" ng-class="navtournaments">
					<a class="sub-menu-link" href="#/tournament/{{ entry.tournamentSlug }}"
						ng-click="getTournament(entry.tournamentId);"
						ng-repeat="entry in tData">{{ entry.tournamentName }}</a>
				</div>
			</div>
			<div class="nav-team-container"
				ng-click="showTournaments = false; showTeams = !showTeams;">
				<a href="" class="nav-link-entry"
					ng-class="{'nav-selected' : showTeams}">Teams<i class="icons">keyboard_arrow_down</i></a>
				<div class="nav-link-menu" ng-class="{'nav-selected' : showTeams}">
					<a class="sub-menu-link" href="">teams</a>
				</div>
			</div>
			 
		</div>
		

		<!-- MAIN -->
		<div class="main">
			<div class="main-left">
				<!-- routing to different pages goes here -->
				<div style="height: 100%; box-sizing: border-box;" ng-view></div>
			</div>
			<div class="main-right">
				<div class="right-profile" ng-click="go('profile')" style="background-image: url(resources/images/avatars/{{ userAvatar }}.png);" ng-cloak>
				<div class="steam-id-container" ng-show="userId == '0'">
				<form action="login/openid" method="post">
	              <input name="openid_identifier" type="hidden" value="http://steamcommunity.com/openid" />
	              <input type="image" src="<c:url value="/resources/images/pages/login--sits.png" />" alt="Sign In Through Steam"/>
	             </form>
				</div>
				</div>
				<div class="right-stream-list"><div class="box-inner">
				<div class="stream-entry-even head">Live Now!</div>
					<div ng-class-odd="'stream-entry-odd'" ng-class-even="'stream-entry-even'" ng-repeat="stream in dStream">
					<a href="#/stream/{{ stream.name }}">{{ stream.name }}</a> : {{ stream.team }}
					</div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.js"></script>
	<script src="<c:url value="/resources/js/wp.js" />"></script>
</body>
</html>