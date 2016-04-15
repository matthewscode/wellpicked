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
	<tiles:putAttribute name="pageTitle" value="Pools" />
	<tiles:putAttribute name="bodyStructure" value="fluid" />
	<tiles:putAttribute name="body">


		<div class="container" ng-controller="bracketListCtrl" data-ng-init="init('<c:url value="/api/tournament/list/latest/4" />'); tab = 1;">
			<div class="bracket-list-tournament-countainer">
				<div class="bracket-list-tournament-entry" style="background-image: url(<c:url value="/resources/images/tournaments/{{ tournamentOne.slug }}.jpg" />);" ng-click="tab = 1"></div>
				<div class="bracket-list-tournament-entry" style="background-image: url(<c:url value="/resources/images/tournaments/{{ tournamentTwo.slug }}.jpg" />);" ng-click="tab = 2"></div>
				<div class="bracket-list-tournament-entry" style="background-image: url(<c:url value="/resources/images/tournaments/{{ tournamentThree.slug }}.jpg" />);" ng-click="tab = 3"></div>
				<div class="bracket-list-tournament-entry" style="background-image: url(<c:url value="/resources/images/tournaments/{{ tournamentFour.slug }}.jpg" />);" ng-click="tab = 4"></div>
			</div>
			<div class="bracket-list-container" ng-show="tab == 1">
				<div href="<c:url value="/bracket/{{ entry.tournamentPredictionId }}" />"class="bracket-list-entry" ng-repeat="entry in tournamentOne.predictions">
				<a href="<c:url value="profile/{{ entry.userId }}" />" class="bracket-list-entry-img" style="background-image: url(<c:url value="/resources/images/achievements/{{ entry.userAvatar }}.png" />)"></a>
				<div class="bracket-list-entry-text"> {{ entry.username }} </div>
				<div class="bracket-list-entry-name"> {{ entry.tournamentPredictionName }}</div>
				</div>
			</div>
			<div class="bracket-list-container" ng-show="tab == 2">
				<div href="<c:url value="/bracket/{{ entry.tournamentPredictionId }}" />"class="bracket-list-entry" ng-repeat="entry in tournamentTwo.predictions">
				<a href="<c:url value="profile/{{ entry.userId }}" />" class="bracket-list-entry-img" style="background-image: url(<c:url value="/resources/images/achievements/{{ entry.userAvatar }}.png" />)"></a>
				<div class="bracket-list-entry-text"> {{ entry.username }} </div>
				<div class="bracket-list-entry-name"> {{ entry.tournamentPredictionName }}</div>
				</div>
			</div>
			<div class="bracket-list-container" ng-show="tab == 3">
				<div href="<c:url value="/bracket/{{ entry.tournamentPredictionId }}" />"class="bracket-list-entry" ng-repeat="entry in tournamentThree.predictions">
				<a href="<c:url value="profile/{{ entry.userId }}" />" class="bracket-list-entry-img" style="background-image: url(<c:url value="/resources/images/achievements/{{ entry.userAvatar }}.png" />)"></a>
				<div class="bracket-list-entry-text"> {{ entry.username }} </div>
				<div class="bracket-list-entry-name"> {{ entry.tournamentPredictionName }}</div>
				</div>
			</div>
			<div class="bracket-list-container" ng-show="tab == 4">
				<div href="<c:url value="/bracket/{{ entry.tournamentPredictionId }}" />"class="bracket-list-entry" ng-repeat="entry in tournamentFour.predictions">
				<a href="<c:url value="profile/{{ entry.userId }}" />" class="bracket-list-entry-img" style="background-image: url(<c:url value="/resources/images/achievements/{{ entry.userAvatar }}.png" />)"></a>
				<div class="bracket-list-entry-text"> {{ entry.username }} </div>
				<div class="bracket-list-entry-name"> {{ entry.tournamentPredictionName }}</div>
				</div>
			</div>
		</div>
 			<!-- tournament --> 
<%-- 			<c:if test="${not empty tournaments}"> --%>
<%-- 			<c:forEach var="tournament" items="${tournaments}" varStatus="loopStatus"> --%>
			

<!-- <div class="information"> -->
<!-- <div class="wrapper" style="overflow: hidden; height: 250px;"> -->
<%-- 	<div style="width: 40%; height: 250px; background: #000000; background-image: url(<c:url value="/resources/images/tournaments/${empty tournaments ? 'default' : tournament.tournamentSlug}.jpg"/>);text-align: center;background-repeat: no-repeat;background-size: cover;background-position: center;text-shadow: 0 0 1px #000000, 0 0 1px #000000, 0 0 2px rgba(0, 0, 0, 0.5); display: inline-block; float: left"><div class="wrapper"><h1 class="name">${tournament.tournamentName}</h1></div></div> --%>
<!--     <div style="display: inline-block; width: 60%; background: #285fa9; overflow: hidden; height: 250px;"> -->
    
<!--     	<div style="display: inline-block; width: 50%; height: 250px"> -->
<!--     		<div style="background: #e2e2e2; height: 250px" >			 -->
<%--  				<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${tournament.tournamentId}/predictions/popular/5" />')" > --%>
<!--                     <div class="loader" data-ng-hide="data"></div> -->
<!--                     <div data-ng-show="data"> -->
<!--                         <div data-ng-repeat="prediction in data" style="overflow: hidden; height: 50px; line-height: 0px;"> -->
<%--                             <div style="background-image: url(<c:url value="/resources/images/achievements/{{prediction.user.avatarName}}.png"/>);background-size: auto 50px; background-position: center; display: inline-block; height: 50px; width: 50px; line-height: 70px;">&nbsp; --%>
<!--                             </div> -->
<!--                             <div style="display: inline-block; position: relative;  height: 100%; line-height: 20.5px"> -->
<!--                             	<div class="comment-wrapper" style="display: block; padding-left: 3px;"> -->
<%--                 					<span class="comment ng-binding" style="display:block;"><a data-ng-href="${bracketPath}{{prediction.tournamentPredictionId}}"> {{prediction.tournamentPredictionName ? prediction.tournamentPredictionName : 'Prediction #' + prediction.tournamentPredictionId}}</a></span> --%>
<%-- 									<span class="user" style="color: #333333; font-size: 12px; display:block;"><a href="/user/${prediction.user.userid}"></a>{{prediction.user.username}}</span> --%>
<!--             					</div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--     		</div> -->
<!--             </div><div style="display: inline-block; width: 50%"> -->
<!--             <div style="background: #d9d9d9; height: 250px"> -->
<%-- 			<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${tournament.tournamentId}/predictions/latest/5" />')" > --%>
<!--             	<div class="loader" data-ng-hide="data"></div> -->
<!--                     <div data-ng-show="data"> -->
<!--                         <div data-ng-repeat="prediction in data" style="overflow: hidden; height: 50px; line-height: 0px;"> -->
<%--                             <div style="background-image: url(<c:url value="/resources/images/achievements/{{prediction.user.avatarName}}.png"/>);background-size: auto 50px; background-position: center; display: inline-block; height: 50px; width: 50px; line-height: 70px;">&nbsp; --%>
<!--                             </div> -->
<!--                             <div style="display: inline-block; position: relative;  height: 100%; line-height: 20.5px"> -->
<!--                             	<div class="comment-wrapper" style="display: block; padding-left: 3px;"> -->
<%--                 					<span class="comment ng-binding" style="display:block;"><a data-ng-href="${bracketPath}{{prediction.tournamentPredictionId}}"> {{prediction.tournamentPredictionName ? prediction.tournamentPredictionName : 'Prediction #' + prediction.tournamentPredictionId}}</a></span> --%>
<!-- 									<span class="user" style="color: #333333; font-size: 12px; display:block;">{{prediction.user.username}}</span> -->
<!--             					</div> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!-- 				</div> -->
<!--                 </div> -->
<!--     			</div> -->
<!-- 			</div> -->
<!-- 			</div>	 -->
<!-- 	<div style="height: 20px;"> -->
<!-- 		<div class="sticky-navigation" style="bottom: auto; background: #1e4880; height: 20px; z-index: 2;"></div> -->
<!-- 	</div> -->
<%-- 		</c:forEach> --%>
<%-- 		</c:if> --%>
<!-- 	</div> -->


	</tiles:putAttribute>
</tiles:insertDefinition>