<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<c:url value="/team/favorite/" var="teamFavoriteUrl" />
<c:url value="/stream/" var="streamPath" />
<c:url value="/bracket/" var="bracketPath" /> 
<c:url value="/resources/images/teams/logos/" var="teamLogoPath" />
<c:url value="/api/team/${team.teamId}/favorite" var="favoriteTeamUrl" />
<c:url value="/api/team/${team.teamId}/unfavorite"
	var="unfavoriteTeamUrl" />

<%-- Check if user has team in favorites --%>
<c:forEach items="${userData.favoriteTeams}" var="favTeam">
	<c:if test="${favTeam.teamId == team.teamId}">
		<c:set var="hasInFavorites" value="true" />
	</c:if>
</c:forEach>

<tiles:insertDefinition name="main">
	<tiles:putAttribute name="pageId" value="view groups" />
	<tiles:putAttribute name="pageTitle" value="Brackets" />
	<tiles:putAttribute name="bodyStructure" value="fluid" />
	<tiles:putAttribute name="body">


		<div class="container" ng-controller="bracketListCtrl" data-ng-init="init('<c:url value="/api/tournament/list/latest/4" />', '<c:url value="/api/predictions/list/latest/20/" />');">
			<div class="bracket-list-tournament-countainer">
				<a class="bracket-list-tournament-entry" ng-repeat="entry in data"  style="background-image: url(<c:url value="/resources/images/tournaments/{{ entry.tournamentSlug }}.jpg" />);"  ng-click="getBrackets('<c:url value="/api/predictions/list/latest/20/" />' + entry.tournamentId);"></a>
			</div>
			<div class="bracket-list-bar">
				<div class="bracket-bar-user">
				</div>
				<div class="bracket-bar-name">
					Bracket Name
				</div>
				<div class="bracket-bar-score">
					Score
				</div>
			</div>
			<div class="bracket-list-container">
				<div href="<c:url value="/bracket/{{ entry.tournamentPredictionId }}" />" class="bracket-list-entry" ng-repeat="entry in bData">
					
					<div class="bracket-list-entry-text">
						<a href="<c:url value="profile/{{ entry.userId }}" />" class="bracket-list-entry-img" style="background-image: url(<c:url value="/resources/images/achievements/{{ entry.userAvatar }}.png" />)"></a>
						 {{ entry.username }} 
					 </div>
					<div class="bracket-list-entry-name"> {{ entry.tournamentPredictionName }}</div>
					<div class="bracket-list-entry-name"> {{ entry.score }}</div>
				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>