<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Setting URL vars --%>
<c:url value="/bracket/${selectedTournament.tournamentSlug}/new" var="createBracketUrl" />
<c:url value="/bracket/" var="bracketPath" />
<c:url value="/team/" var="teamPath" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="view tournament" /> 
<tiles:putAttribute name="pageTitle" value="${selectedTournament.tournamentName}" /> 
<tiles:putAttribute name="bodyStructure" value="fluid" />
<tiles:putAttribute name="body">

    <%-- Check if tournament image exists --%>
    <c:import url="/resources/images/tournaments/${selectedTournament.tournamentSlug}.jpg" var="tournamentImage"/>

    <div class="container" data-url="<c:url value="/api/tournament/${selectedTournament.tournamentId}" />" data-ng-controller="TournamentController" data-ng-cloak="true">
    
        <!-- Tournament header -->
        <div class="wrapper">
            <div class="loader" data-ng-hide="tournament"></div>
            <div id="information" class="information" data-ng-show="tournament" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : selectedTournament.tournamentSlug}.jpg"/>">
                <div class="wrapper">
                    <h1 class="name">{{tournament.tournamentName}}</h1>
                    <h3 class="date">{{tournament.tournamentStart*1000 | date:'MMMM dd, yyyy'}} - {{tournament.tournamentEnd*1000 | date:'MMMM dd, yyyy'}}</h3>
                    <div class="status" data-ng-show="tournament.tournamentStart <= now && tournament.tournamentEnd > now">In Progress</div>
                    <div class="separator"></div>
                    <div class="description">{{tournament.tournamentDescription}}</div>
                </div>
                <div class="teams" data-ng-show="teams">
                    <div class="team" data-ng-repeat="team in teams" data-ng-class="{eliminated: team.eliminated}">
                        <span class="logo">
                            <a href="${teamPath}{{team.teamSlug}}"><img data-ng-src="<c:url value="/resources/images/teams/logos/{{team.teamSlug}}.png"/>" alt="{{team.teamName}}"></a>
                        </span>
                        <span class="name">{{team.teamName}}</span>
                    </div>
                </div>
                <div class="sticky-navigation" data-ng-sticky-scroll="true">
                    <a data-ng-smooth-scroll="#information">Info
                    </a><a data-ng-smooth-scroll="#results">Results
                    </a><a data-ng-smooth-scroll="#brackets">Brackets
                    </a><a data-ng-smooth-scroll="#pools">Pools
                    </a><a data-ng-smooth-scroll="#discussion">Discussion
                    </a>
                </div>
            </div>
            
            <!-- Tournament results (bracket) -->
            <div id="results" class="bracket-container" data-ng-show="matchups" data-ng-drag-scroll="true">
                <jsp:include page="templates/${selectedTournament.template.templateName}.jsp" />
            </div>
        </div>
        
        <!-- Tournament user brackets -->
        <div id="brackets" class="brackets-container">
            <div class="wrapper tiles">
                <c:choose>
                    <c:when test="${empty predictionClosed}">
                        <div class="top-brackets tile-4">
                            <h3 class="title text-center">Top Brackets</h3>
                            <p class="text-center">Tournament has not begun scoring.</p>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="top-brackets tile-4" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${selectedTournament.tournamentId}/predictions/top/10" />')" data-ng-cloak="true">
                            <div class="loader" data-ng-hide="data"></div>
                            <h3 class="title text-center">Top Brackets</h3>
                            <p class="text-center" data-ng-show="!data.length">No brackets found.</p>
                            <ol data-ng-show="data">
                                <li data-ng-repeat="prediction in data">
                                    <a data-ng-href="${bracketPath}{{prediction.tournamentPredictionId}}">{{prediction.tournamentPredictionName ? prediction.tournamentPredictionName : 'Prediction #' + prediction.tournamentPredictionId}}</a>
                                    <span class="score">{{prediction.tournamentPredictionScore}}</span>
                                </li>
                            </ol>
                        </div>
                    </c:otherwise>
                </c:choose>
                
                <div class="latest-brackets tile-4" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${selectedTournament.tournamentId}/predictions/latest/10" />')" data-ng-cloak="true">
                    <div class="loader" data-ng-hide="data"></div>
                    <h3 class="title text-center">Latest Brackets</h3>
                    <p class="text-center" data-ng-show="!data.length">No brackets found.</p>
                    <ul data-ng-show="data">
                        <li data-ng-repeat="prediction in data">
                            <a data-ng-href="${bracketPath}{{prediction.tournamentPredictionId}}">{{prediction.tournamentPredictionName ? prediction.tournamentPredictionName : 'Prediction #' + prediction.tournamentPredictionId}}</a>
                        </li>
                    </ul>
                </div>
                
                <c:choose>
                    <c:when test="${pageContext.request.userPrincipal.name != null}">
                        <div class="user-brackets tile-4" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/tournament/${selectedTournament.tournamentId}/predictions/user/${currentUser.userId}" />')" data-ng-cloak="true">
                            <div class="loader" data-ng-hide="data"></div>
                            <h3 class="title text-center">Your Brackets</h3>
                            <p class="text-center" data-ng-show="!data.length">No brackets.</p>
                            <ul data-ng-show="data">
                                <li data-ng-repeat="prediction in data">
                                    <a data-ng-href="${bracketPath}{{prediction.tournamentPredictionId}}">{{prediction.tournamentPredictionName ? prediction.tournamentPredictionName : 'Prediction #' + prediction.tournamentPredictionId}}</a>
                                </li>
                            </ul>
                            <c:if test="${empty predictionClosed}">
                                <div class="text-center">
                                    <a class="button over-dark-image" href="${createBracketUrl}">New bracket</a>
                                </div>
                            </c:if>
                        </div>
                    </c:when>    
                    <c:otherwise>
                        <div class="user-brackets tile-4">
                            <h3 class="title text-center">Your Brackets</h3>
                            <p class="text-center">Login/sign up to access your brackets.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
        <!-- Tournament discussion and pools -->
        <div class="discussion-pools tiles">
            <div id="pools" class="pools-container tile-4">
                <div class="loader" data-ng-hide="tournament"></div>
                <h2 class="title text-center">Pools</h2>
                <p class="text-center" data-ng-show="!tournament.groups.length">No pools.</p>
                <p class="text-center" data-ng-show="tournament.groups.length">Some pools. Need to do this view.</p>
            </div>
	        <jsp:include page="../site/components/_comments.jsp">
                <jsp:param name="elId" value="discussion" />
                <jsp:param name="elClass" value="tile-8" />
                <jsp:param name="objectName" value="tournament" />
                <jsp:param name="objectId" value="${selectedTournament.tournamentId}" />
	        </jsp:include>
        </div>
        
        <div class="footer tile-3h">
            <div class="wrapper background" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : selectedTournament.tournamentSlug}.jpg"/>"></div>
        </div>
    </div>
    
</tiles:putAttribute>
</tiles:insertDefinition>