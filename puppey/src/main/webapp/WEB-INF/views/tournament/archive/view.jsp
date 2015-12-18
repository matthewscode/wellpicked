<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="pageId" value="view tournament" /> 
    <tiles:putAttribute name="pageTitle" value="${selectedTournament.tournamentName}" /> 
    <tiles:putAttribute name="bodyStructure" value="fluid" /> 
    <tiles:putAttribute name="body">
    
    ${selectedTournament.tournamentName}
   <!--
        <div class="page-bg">
            <c:choose>
                <c:when test="${not empty selectedTournament.tournamentSlug}">
                    <img src="<c:url value="${selectedTournament.tournamentSlug}.png" />">
                </c:when>
                <c:otherwise>
                    <img src="<c:url value="/resources/images/placeholders/tournament-large.jpg" />">
                </c:otherwise>
            </c:choose>
        </div>
               
        <div class="bracket-container container">
            <a href="<c:url value="/tournaments" />" class="back"><i class="fa fa-arrow-left"></i> Back to tournaments</a>
        
            <div class="row information text-center">
                <c:if test="${empty selectedTournament.tournamentSlug}">
                    <div class="name">
                        <h2>${selectedTournament.tournamentSlug}</h2>
                    </div>
                </c:if>
                <div class="col-xs-12 col-md-10 col-md-offset-1 col-lg-8 col-lg-offset-2 description text-shadow">   
                    ${selectedTournament.tournamentDescription}
                </div>
            </div>
            
            <div class="row bracket round-{{matchups.round}}" data-ng-controller="BracketController">
                <script type="text/ng-template" id="render_matchups.html">
                <div class="teams">
                    <div class="team" data-ng-class="{winner:matchup.team_1.win}">{{matchup.team_1.name}}</div>
                    <div class="team" data-ng-class="{winner:matchup.team_2.win}">{{matchup.team_2.name}}</div>
                </div>
                <div class="matchups" data-ng-if="matchup.previous_matchups.length">
                    <div class="matchup round-{{matchup.round}}" data-ng-repeat="matchup in matchup.previous_matchups" data-ng-include="'render_matchups.html'">
                    </div>
                </div>
                </script>
                
                <div class="before-last matchup left round-{{matchups.previous_matchups[0].round}}">
                    <div class="teams">
                        <div class="team" data-ng-class="{winner:matchups.previous_matchups[0].team_1.win}">{{matchups.previous_matchups[0].team_1.name}}</div>
                        <div class="team" data-ng-class="{winner:matchups.previous_matchups[0].team_2.win}">{{matchups.previous_matchups[0].team_2.name}}</div>
                    </div>
                    <div class="matchups">
                        <div class="matchup round-{{matchup.round}}" data-ng-repeat="matchup in matchups.previous_matchups[0].previous_matchups" data-ng-include="'render_matchups.html'"></div>
                    </div>
                </div>
                
                <div class="last matchup">
                    <div class="team" data-ng-class="{winner:matchups.team_1.win}">{{matchups.team_1.name}}</div>
                    <div class="team winner trophy text-shadow">
                        <i class="fa fa-trophy"></i>&nbsp;&nbsp;<span class="name">{{matchups.winner.name}}</span>
                    </div>
                    <div class="team" data-ng-class="{winner:matchups.team_2.win}">{{matchups.team_2.name}}</div>
                </div>
                
                <div class="before-last matchup right round-{{matchups.previous_matchups[1].round}}">
                    <div class="teams">
                        <div class="team" data-ng-class="{winner:matchups.previous_matchups[1].team_1.win}">{{matchups.previous_matchups[1].team_1.name}}</div>
                        <div class="team" data-ng-class="{winner:matchups.previous_matchups[1].team_2.win}">{{matchups.previous_matchups[1].team_2.name}}</div>
                    </div>
                    <div class="matchups">
                        <div class="matchup round-{{matchup.round}}" data-ng-repeat="matchup in matchups.previous_matchups[1].previous_matchups" data-ng-include="'render_matchups.html'"></div>
                    </div>
                </div>
            </div>
            
            <c:choose>
                <c:when test="${empty predictionClosed}">
                    <div class="row text-center">
                        <a class="btn btn-lg btn-primary" href="<c:url value="/tournament/${selectedTournament.tournamentSlug}/pick" />">Create Your Bracket</a>
                    </div>
                </c:when>
            </c:choose>
        </div>
        
        <div class="container tournament-data">
            <div class="row">
            
            <c:choose>
                <c:when test="${empty predictionClosed}">
                    <div class="top col-xs-12 col-sm-6 col-md-5" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${selectedTournament.tournamentId}/predictions/top/10" />')">
                        <h3>Top brackets</h3>
                        <div data-ng-hide="data">
                            <i class="fa fa-cog fa-spin"></i> Loading
                        </div>
                        <div class="list-group" data-ng-show="data">
                            <a class="list-group-item"  data-ng-repeat="item in data" href="<c:url value="/bracket" />/{{item.tournamentPredictionId}}">
                                <i class="fa fa-chevron-right pull-right"></i>
                                <span class="badge" title="Points">{{item.tournamentPredictionScore}} <i class="fa fa-bolt"></i></span>
                                <span class="index">\#{{$index+1}}</span> {{item.user.username}}
                            </a>
                        </div>
                    </div>
                </c:when>
            </c:choose>
                
                <div class="latest col-xs-12 col-sm-6 col-md-5 ${empty predictionClosed ? 'col-md-offset-2' : ''}" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${selectedTournament.tournamentId}/predictions/latest/10" />')">
                    <h3>Latest brackets</h3>
                    <div data-ng-hide="data">
                        <i class="fa fa-cog fa-spin"></i> Loading
                    </div>
                    <div class="list-group" data-ng-show="data">
                        <a class="list-group-item" data-ng-repeat="item in data" href="<c:url value="/bracket" />/{{item.tournamentPredictionId}}">
                            <i class="fa fa-chevron-right pull-right"></i>
                            {{item.user.username}}
                        </a>
                    </div>
                </div>
            </div>
        </div>
        
        <script type="text/javascript">
        var WELLPICKED = WELLPICKED || {};
        WELLPICKED.tournament = {
            id: ${selectedTournament.tournamentId},
            name: "${selectedTournament.tournamentName}",
            start: ${selectedTournament.tournamentStart},
            end: ${selectedTournament.tournamentStart}
        };
        WELLPICKED.tournament.matchups = [
            <c:forEach items="${selectedTournament.matchups}" var="item" varStatus="status">
            {
                id: ${selectedTournament.matchups[status.index].matchupId},
                round: ${selectedTournament.matchups[status.index].round},
                next_matchup_id: ${selectedTournament.matchups[status.index].nextMatchup},
                team_1: {
                    id: ${empty selectedTournament.matchups[status.index].team1.teamId ? 'null' : selectedTournament.matchups[status.index].team1.teamId},
                    name: "${empty selectedTournament.matchups[status.index].team1.teamName ? 'null' : selectedTournament.matchups[status.index].team1.teamName}"
                },
                team_2: {
                    id: ${empty selectedTournament.matchups[status.index].team2.teamId ? 'null' : selectedTournament.matchups[status.index].team2.teamId},
                    name: "${empty selectedTournament.matchups[status.index].team2.teamName ? 'null' : selectedTournament.matchups[status.index].team2.teamName}"
                },
                winner: ${empty selectedTournament.matchups[status.index].winnerId ? 'null' : selectedTournament.matchups[status.index].winnerId}
            }<c:if test="${!status.last}">,</c:if>
            </c:forEach>
        ];
        </script>
          -->
    </tiles:putAttribute>
</tiles:insertDefinition>