<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="view bracket" /> 
<tiles:putAttribute name="pageTitle" value="${userPrediction.tournamentPredictionName}" />
<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="body">

<%-- Setting URL vars --%>
<c:url value="/tournament/${userPrediction.tournament.tournamentSlug}" var="tournamentUrl" />
<c:url value="/bracket/${userPrediction.tournamentPredictionId}/edit" var="editUrl" />
<c:url value="/profile/${userPrediction.user.userId}" var="ownerUrl" />

<c:url value="/profile/" var="profilePath" />

<%-- Check if tournament image exists --%>
<c:import url="/resources/images/tournaments/${userPrediction.tournament.tournamentSlug}.jpg" var="tournamentImage"/>


<div class="well container" style="padding: 0px;" data-url="<c:url value="/api/tournament/${userPrediction.tournament.tournamentId}" />"
     data-ng-controller="TournamentController" data-ng-init="init({viewBracket: '<c:url value="/api/prediction/${userPrediction.tournamentPredictionId}" />'})" data-ng-cloak="true">
    <div class="loader" data-ng-hide="tournament"></div>
    <div class="loader" data-ng-show="loading"></div>
    
    <!-- Tournament header -->
    <div class="information" style="height: 200px; padding: 0px;" data-ng-show="tournament">

        <div class="wrapper" style="width: 100%">
                    <div style="z-index: 2;position: absolute; left: 0px;width: 20%; height: 200px;display: inline-block;background-image: url(<c:url value="/resources/images/achievements/{{prediction.user.avatarName}}.png"/>);background-size: cover;background-position: center; -webkit-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);-moz-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);">
            </div>
            <div style="padding: 2px 30px 2px 30px;display: inline-block; left: 20%;width: 60%; position: absolute; height: 100%; background: #1e4880; text-align: left; -webkit-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);-moz-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5); z-index: 1;">
            <h1 class="name" style="text-align: center">{{prediction.tournamentPredictionName}}</h1>
            <h4 class="name">Tournament: <span style="text-align: right"><a href="${tournamentUrl}">{{tournament.tournamentName}}</a></span></h4>
            <h5 class="name">Owner: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="${ownerUrl}">{{prediction.user.username}}</a></h5>
            <h5 class="name">Score: &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{prediction.tournamentPredictionScore}}</h5>
            <c:if test="${userData.userId == userPrediction.user.userId}">
            <a href="${editUrl}" class="button small over-dark-image" data-ng-if="tournament.upcoming">Edit Bracket</a>
            </c:if>

        </div>
        <div style="right: 0px; float: right; position absolute;width: 20%; height: 200px;display: inline-block;background-image: url(<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : userPrediction.tournament.tournamentSlug}.jpg"/>);background-size: cover;background-position: center; -webkit-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);-moz-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5); z-index: 1;position: absolute">
        
        </div>
        </div>
    </div>
    
    <!-- Tournament results (bracket) -->
    <div class="sticky-navigation" style="bottom: auto; background: rgba(231, 231, 231, 0.8); height: 20px; z-index: 2;"></div>
    <div class="bracket-container" style="-webkit-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);-moz-box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5);box-shadow: 0 0 10px 1px rgba(0, 0, 0, 0.5); z-index: 1;"data-ng-show="matchups" data-ng-drag-scroll="true">
        <jsp:include page="../tournament/templates/${userPrediction.tournament.template.templateName}.jsp" />
    </div>
    
    <!-- Bracket comments -->
    <jsp:include page="../site/components/_comments.jsp">
        <jsp:param name="objectName" value="tournamentPrediction" />
        <jsp:param name="objectId" value="${userPrediction.tournamentPredictionId}" />
        <jsp:param name="ngShow" value="tournament" />
    </jsp:include>
</div>

</tiles:putAttribute>
</tiles:insertDefinition>