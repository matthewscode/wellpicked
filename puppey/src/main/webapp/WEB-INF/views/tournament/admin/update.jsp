<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/admin/tournaments" var="tournamentsUrl" htmlEscape="true"/>
<spring:url value="/admin/tournament/edit/" var="tournamentEditPath" htmlEscape="true" />
<c:set var="updateMatchupUrl" value="/admin/api/tournament/matchup/update" />
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Update Tournament" />
<tiles:putAttribute name="pageId" value="admin tournament update" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${tournamentsUrl}">Tournaments</a></span>
                    <span class="breadcrumb-item">${tournament.tournamentName}</span>
                </h3>
            </div>
        
        <div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/admin/api/tournament/matchups/${tournamentId}" />')">
          
          <div class="loader" data-ng-hide="data"></div>
          <div data-ng-show="data">
          <button style="width:10%; padding: 0; background: #143157"></button>
          <button style="width: 25%; background: #143157;" >Team 1</button>
          <button style="width: 25%; background: #143157;" >Team 2</button>
          <button style="width:10%; padding: 0; background: #143157">Winner</button>
          
              <div data-ng-repeat="matchup in data">
              <div ng-controller="TournamentUpdateController">
                  <button style="width: 10%; padding: 0;  background: #143157"><span class="logo">{{ matchup.matchupType }}</span></button>
                  <button style="width: 25%; padding: 5px;" ng-click="matchup.showTeams1 = !matchup.showTeams1"><img src="<c:url value="/resources/images/teams/logos/"/>{{ matchup.team1Slug }}.png" /></button>
                  <button style="width: 25%; padding: 5px;" ng-click="matchup.showTeams2 = !matchup.showTeams2"><span class="logo"><img src="<c:url value="/resources/images/teams/logos/{{ matchup.team2Slug }}.png" />" /></span></button>
                  <button style="width: 10%; padding: 5px;  background: #CCC" ng-click="showWinner = !showWinner"><div class="loader" data-ng-hide="data"></div><span class="logo"><img src="<c:url value="/resources/images/teams/logos/{{ matchup.winnerSlug }}.png" />" /></span></button>
					<div class="team-list-box" ng-show="matchup.showTeams1">
						<c:forEach var="team1" items="${teamList}">
							<div class="team-list-logo">
								<a href="" ng-click="updateMatchupTeam('<c:url value="${updateMatchupUrl}" />/' + matchup.matchupId +'/1/${team1.teamId}', matchup.matchupId, 1, '${team1.teamSlug}', ${team1.teamId})">
									<img src="<c:url value="/resources/images/teams/logos/${team1.teamSlug}.png" />" />
								</a>
							</div>
						</c:forEach>
					</div>
					<div class="team-list-box" ng-show="matchup.showTeams2">
						<c:forEach var="team2" items="${teamList}">
							<div class="team-list-logo">
								<a href="" ng-click="updateMatchupTeam('<c:url value="${updateMatchupUrl}" />/' + matchup.matchupId +'/2/${team2.teamId}', matchup.matchupId, 2, '${team2.teamSlug}', ${team2.teamId})">
									<img src="<c:url value="/resources/images/teams/logos/${team2.teamSlug}.png" />" />
								</a>
							</div>
						</c:forEach>
					</div>
					<div class="team-list-box" ng-show="showWinner">
					<div class="team-list-logo" style="text-align: right">
						<a href="" ng-click="updateMatchupTeam('<c:url value="${updateMatchupUrl}" />/' + matchup.matchupId +'/3/' + matchup.team1Id, matchup.team1Id, 3, matchup.team1Slug)">
							<img src="<c:url value="/resources/images/teams/logos/" />{{ matchup.team1Slug }}.png" />
						</a>
					</div>
					<div class="team-list-logo" style="text-align: right">
						<a href="" ng-click="updateMatchupTeam('<c:url value="${updateMatchupUrl}" />/' + matchup.matchupId +'/3/' + matchup.team2Id, matchup.team2Id, 3, matchup.team2Slug)">
							<img src="<c:url value="/resources/images/teams/logos/" />{{ matchup.team2Slug }}.png" />
						</a>
					</div>
					</div>
			</div>
            </div>
        </div>
        </div>
        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>