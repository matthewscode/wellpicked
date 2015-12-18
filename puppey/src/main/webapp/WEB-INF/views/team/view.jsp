<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Setting URL vars --%>
<c:url value="/team/favorite/" var="teamFavoriteUrl" />
<c:url value="/stream/" var="streamPath" />
<c:url value="/resources/images/teams/logos/" var="teamLogoPath" />
<c:url value="/api/team/${team.teamId}/favorite" var="favoriteTeamUrl" />
<c:url value="/api/team/${team.teamId}/unfavorite" var="unfavoriteTeamUrl" />

<%-- Check if user has team in favorites --%>
<c:forEach items="${userData.favoriteTeams}" var="favTeam">
    <c:if test="${favTeam.teamId == team.teamId}">
        <c:set var="hasInFavorites" value="true"/>
    </c:if>
</c:forEach>
    
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="view team" /> 
<tiles:putAttribute name="pageTitle" value="${team.teamName}" /> 
<tiles:putAttribute name="bodyStructure" value="fluid" />
<tiles:putAttribute name="body">

    <%-- Check if team logo image exists --%>
    <c:import url="/resources/images/teams/logos/${team.teamSlug}.png" var="teamLogo"/>
    
    <%-- Check if team banner image exists --%>
    <c:import url="/resources/images/teams/banners/${team.teamSlug}.jpg" var="teamBanner"/>

    <div class="container" data-ng-controller="TeamController"
         data-ng-init="init({hasInFavorites: ${empty hasInFavorites ? false : true}})">
    
        <!-- Team header -->
        <div class="wrapper header">
            <div class="information" data-ng-bg-image="<c:url value="/resources/images/teams/banners/${empty teamBanner ? 'default' : team.teamSlug}.jpg"/>">
                <div class="sticky-navigation" data-ng-sticky-scroll="true">
                    <a data-ng-smooth-scroll="#information">Info
                    </a><a data-ng-smooth-scroll="#streams">Streams
                    </a><a data-ng-smooth-scroll="#tournaments">Tournaments
                    </a><a data-ng-smooth-scroll="#discussion">Discussion
                    </a>
                </div>
            </div>
        </div>
        
        <!--  Team info -->
        <div id="information" class="information-container tiles table">
            <div class="tile-4 logo">
                <div class="tile-7"></div>
                <div class="tile-5">
                    <div class="loader" data-ng-show="favLoading"></div>
                    <img src="<c:url value="/resources/images/teams/logos/${empty teamLogo ? 'default' : team.teamSlug}.png"/>">
                    <div class="favorite">
                        <span class="add" data-ng-show="!hasInFavorites" data-ng-click="favoriteTeam('${favoriteTeamUrl}')">
                            <span class="tooltip">Add to favorites</span>
                        </span>
                        <span class="remove" data-ng-show="hasInFavorites" data-ng-click="unfavoriteTeam('${unfavoriteTeamUrl}')">
                            <span class="tooltip">Remove from favorites</span>
                        </span>
                    </div>
                </div>
            </div>
            <div class="tile-8">
                <div class="info tiles table">
                    <span class="label tile-3">Name</span>
                    <span class="value tile-9">${team.teamName}</span>
                </div>
                <div class="info tiles table">
                    <span class="label tile-3">Abbreviation</span>
                    <span class="value tile-9">${team.teamAbbr}</span>
                </div>
                <div class="info tiles table">
                    <span class="label tile-3">Region</span>
                    <span class="value tile-9">${team.region}</span>
                </div>
                <div class="info tiles table">
                    <span class="label tile-3">Bio</span>
                    <span class="value tile-9">${team.teamBio}</span>
                </div>
            </div>
        </div>
        
        <div class="tiles table">
            <!-- Team streams -->
            <div id="streams" class="tile-6 streams-container" data-ng-controller="ApiController"
                 data-ng-init="init('<c:url value="/api/streams/team/${team.teamId}"/>', {callback: getStreams})">
                <h2 class="title text-center">Streams</h2>
                <div class="loader" data-ng-hide="streams"></div>
                <p class="text-center" data-ng-hide="streams.channels">No live streams :(</p>
                <div class="streams tiles" data-ng-show="streams">
                    <div class="stream tile-6" data-ng-repeat="stream in streams">
                        <a data-ng-href="${streamPath}{{stream.name}}" title="{{stream.description}}">
                            <div class="image" data-ng-bg-image="{{stream.image.size50}}"></div>
                            <div class="name">{{stream.display_name}}</div>
                        </a>
                    </div>
                </div>
            </div>
            
            <!-- Team tournaments -->
            <div id="tournaments" class="tile-6 tournaments-container" data-ng-controller="ApiController"
                 data-ng-init="init('<c:url value="/api/tournament/team/${team.teamId}"/>', {callback: getTournaments})">
                <h2 class="title text-center">Tournaments</h2>
                <div class="loader" data-ng-hide="data"></div>
                <p class="text-center" data-ng-show="!data.length || data.error">No tournaments.</p>
                <div class="tournaments" data-ng-show="data.length && !data.error">
                    <div data-ng-repeat="tournament in data">
                        {{tournament.tournamentName}}
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Team discussion -->
        <jsp:include page="../site/components/_comments.jsp">
            <jsp:param name="elId" value="discussion" />
            <jsp:param name="objectName" value="team" />
            <jsp:param name="objectId" value="${team.teamId}" />
        </jsp:include>
    </div>

</tiles:putAttribute>
</tiles:insertDefinition>