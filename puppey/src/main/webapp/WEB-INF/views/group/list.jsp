<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<c:url value="/team/favorite/" var="teamFavoriteUrl" />
<c:url value="/stream/" var="streamPath" />
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


		<div class="container">
			<div class="sticky-navigation" style="bottom: auto; position: fixed;">
				<div style="width: 30%;">Featured Pools</div>
				<div style="width: 40%;">Tournament</div>
			</div>
			<div style="height: 40px;"></div>
			<c:forEach items="${currentTournaments}" var="tournament">

				<%-- Check if team banner image exists --%>
				<c:import
					url="/resources/images/tournaments/${tournament.tournamentSlug}.jpg"
					var="tournamentBanner" />

				<!-- Pool header -->
				<div class="wrapper header">
					<div class="tile-6 streams-container"
						style="height: 200px; text-align: right;">
						<c:forEach items="${groupList}" var="group">
							<c:if test="${fn:contains(group.tournamentList, tournament)}">
        						<h2
							style="font-size: 16px;"><a style="color: #fff;" href="<c:url value="/pool/${group.groupId}"/>">${group.groupName}</a></h2>
							</c:if>

						</c:forEach>
					</div>
					<div class="tile-6 streams-container content"
						style="height: 200px;background-image: url(<c:url value="/resources/images/tournaments/${empty tournamentBanner ? 'default' : tournament.tournamentSlug}"/>.jpg); background-size: 100%;">

						<h2 class="title"
							style="text-align: center; line-height: 150px; text-shadow: 0 0 1px #000000, 0 0 1px #000000, 0 0 2px rgba(0, 0, 0, 0.75);">${tournament.tournamentName}</h2>
					</div>
				</div>
				<div style="height: 20px;">
					<div class="sticky-navigation"
						style="bottom: auto; background: rgba(231, 231, 231, 0.8); height: 20px; z-index: 2;"></div>
				</div>
			</c:forEach>
		</div>


	</tiles:putAttribute>
</tiles:insertDefinition>