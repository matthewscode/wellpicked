<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="new bracket" /> 
<tiles:putAttribute name="pageTitle" value="New bracket - ${userTournamentPrediction.tournament.tournamentName}" />
<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="body">

<%-- Setting URL vars --%>
<c:url value="/tournament/${userTournamentPrediction.tournament.tournamentSlug}" var="tournamentUrl" />
<c:url value="/bracket/" var="bracketPath" />

<%-- Check if tournament image exists --%>
<c:import url="/resources/images/tournaments/${userTournamentPrediction.tournament.tournamentSlug}.jpg" var="tournamentImage"/>

<div class="well container" data-url="<c:url value="/api/tournament/${userTournamentPrediction.tournament.tournamentId}" />"
     data-ng-controller="TournamentController" data-ng-init="init({newBracket: true, bracketPath: '${bracketPath}'})" data-ng-cloak="true">
    <div class="loader" data-ng-hide="tournament"></div>
    <div class="loader" data-ng-show="loading"></div>
    
    <!-- Tournament header -->
    <div class="information" data-ng-show="tournament" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : userTournamentPrediction.tournament.tournamentSlug}.jpg"/>">
        <div class="wrapper">
            <h1 class="name"><a href="${tournamentUrl}">{{tournament.tournamentName}}</a></h1>
            <h3 class="date">{{tournament.tournamentStart*1000 | date:'MMMM dd, yyyy'}} - {{tournament.tournamentEnd*1000 | date:'MMMM dd, yyyy'}}</h3>
            <div class="status" data-ng-show="tournament.tournamentStart <= now && tournament.tournamentEnd > now">In Progress</div>
            <div class="separator"></div>
            <div class="bracket-name">
                <input type="text" name="bracketName" data-ng-model="newBracket.tournamentPredictionName" autofocus="autofocus" placeholder="Awesome bracket name" required>
            </div>
        </div>
    </div>
    
    <!-- Tournament results (bracket) -->
    <div class="bracket-container" data-ng-show="matchups" data-ng-drag-scroll="true">
        <jsp:include page="../tournament/templates/${userTournamentPrediction.tournament.template.templateName}.jsp">
            <jsp:param name="isEditable" value="1" />
        </jsp:include>
    </div>
        
    <div class="submit-container text-center">
        <button type="button" data-ng-click="submitNewBracket('<c:url value="/api/predictions/save" />')">Create new bracket</button>
    </div>
</div>

</tiles:putAttribute>
</tiles:insertDefinition>