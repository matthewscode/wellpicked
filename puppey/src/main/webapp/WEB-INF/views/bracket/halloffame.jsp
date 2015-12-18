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



<tiles:insertDefinition name="main">
	<tiles:putAttribute name="pageId" value="view Hall" />
	<tiles:putAttribute name="pageTitle" value="Hall of Fame" />
	<tiles:putAttribute name="bodyStructure" value="fluid" />
	<tiles:putAttribute name="body">


		<div class="container" >
		<div class="sticky-navigation" style="width: auto;left: 90px; right: 300px; bottom: auto; position: fixed; z-index: 3;">
				<div style="width: 100%;">HALL OF FAME</div>
				
			</div>
			<div style="text-align: center;"><img src="<c:url value="/resources/images/achievements/blank.png" />"></div>
			<div style="height: 40px"></div>

	</div>


	</tiles:putAttribute>
</tiles:insertDefinition>