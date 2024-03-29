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
		data-ng-init="init();"
		style="background-image: url(<c:url value="/resources/images/tournaments/" />{{ homeTournament.slug }}.jpg);">
		<!-- NAVIGATION -->
		<div id="nav-container">
			<div class="nav-link-container"
				ng-click="showTeams=false;showTournaments=!showTournaments">
				<a href="" class="nav-link-entry"
					ng-class="{'nav-selected' : showTournaments}">Tournaments<i
					class="icons">keyboard_arrow_down</i></a>
				<div class="nav-link-menu"
					ng-class="{'nav-selected' : showTournaments}">
					<a class="sub-menu-link"
						href="#/tournament/{{ entry.tournamentSlug }}"
						ng-click="getTournament(entry.tournamentId);"
						ng-repeat="entry in tData">{{ entry.tournamentName }}</a>
				</div>
			</div>
			<div class="nav-link-container"
				ng-click="showTournaments = false; showTeams = !showTeams;">
				<a href="" class="nav-link-entry"
					ng-class="{'nav-selected' : showTeams}">Teams<i class="icons">keyboard_arrow_down</i></a>
				<div class="nav-link-menu" ng-class="{'nav-selected' : showTeams}">
					<a class="sub-menu-link" href="">teams</a>
				</div>
			</div>
			<div class="steam-id-container" ng-show="userId == 0">
				<form action="login/openid" method="post">
					<input name="openid_identifier" type="hidden"
						value="http://steamcommunity.com/openid" /> <input type="image"
						src="<c:url value="/resources/images/pages/login--sits.png" />"
						alt="Sign In Through Steam" />
				</form>
			</div>

		</div>

		<!-- MAIN -->
		<div class="main" data-ng-controller="adminCtrl">
			News
			<form ng-submit="postNews()" class="form-admin">
				<input ng-model="news.newsTitle" /><br />
				<textarea ng-model="news.newsText"></textarea>
				<br />
				<button type="submit">submit news</button>
				<br /> {{ newsMessage }}
			</form>

		</div>

	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular-route.js"></script>
	<script src="<c:url value="/resources/js/wp.js" />"></script>
	<script src="<c:url value="/resources/js/king.js" />"></script>
</body>
</html>