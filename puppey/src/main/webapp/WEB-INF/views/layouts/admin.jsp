<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%-- Current URL --%>
<c:set value="${requestScope['javax.servlet.forward.request_uri']}" var="currentUrl" />

<%-- Setting URL vars --%>
<spring:url value="/admin" var="homeUrl" htmlEscape="true" />
<spring:url value="/admin/tournament" var="tournamentUrlPattern" htmlEscape="true" />
<spring:url value="/admin/tournaments" var="tournamentsUrl" htmlEscape="true" />
<spring:url value="/admin/tournament/add" var="tournamentAddUrl" htmlEscape="true" />
<spring:url value="/admin/tournament/groupstage/add" var="groupstageAddUrl" htmlEscape="true" />
<spring:url value="/admin/team" var="teamUrlPattern" htmlEscape="true" />
<spring:url value="/admin/teams" var="teamsUrl" htmlEscape="true" />
<spring:url value="/admin/team/add" var="teamAddUrl" htmlEscape="true" />
<spring:url value="/admin/pool" var="groupUrlPattern" htmlEscape="true" />
<spring:url value="/admin/pools" var="groupsUrl" htmlEscape="true" />
<spring:url value="/admin/pool/add" var="groupAddUrl" htmlEscape="true" />
<spring:url value="/admin/achievement" var="achievementUrlPattern" htmlEscape="true" />
<spring:url value="/admin/achievements" var="achievementsUrl" htmlEscape="true" />
<spring:url value="/admin/achievement/add" var="achievementAddUrl" htmlEscape="true" />
<spring:url value="/admin/item" var="itemUrlPattern" htmlEscape="true" />
<spring:url value="/admin/item/add" var="itemAddUrl" htmlEscape="true" />
<spring:url value="/admin/items" var="itemUrl" htmlEscape="true" />
<spring:url value="/admin/news" var="newsUrlPattern" htmlEscape="true" />
<spring:url value="/admin/news/add" var="newsAddUrl" htmlEscape="true" />
<spring:url value="/admin/news" var="newsUrl" htmlEscape="true" />
<div class="tiles">
    <div class="tile-12 well">
        <div class="tile-2 navigation">
        
            <div class="nav-item ${currentUrl == homeUrl ? 'active' : ''}">
                <a href="${homeUrl}">Dashboard</a>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, tournamentUrlPattern) ? 'active' : ''}">
                <a href="${tournamentsUrl}">Tournaments</a>
                <div class="nav-item ${currentUrl == tournamentsUrl ? 'active' : ''}"><a href="${tournamentsUrl}">List</a></div>
                <div class="nav-item ${currentUrl == tournamentAddUrl ? 'active' : ''}"><a href="${tournamentAddUrl}">Create</a></div>
                <div class="nav-item ${currentUrl == groupstageAddUrl ? 'active' : ''}"><a href="${groupstageAddUrl}">Create GS</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, teamUrlPattern) ? 'active' : ''}">
                <a href="${teamsUrl}">Teams</a>
                <div class="nav-item ${currentUrl == teamsUrl ? 'active' : ''}"><a href="${teamsUrl}">List</a></div>
                <div class="nav-item ${currentUrl == teamAddUrl ? 'active' : ''}"><a href="${teamAddUrl}">Create</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, groupUrlPattern) ? 'active' : ''}">
                <a href="${groupsUrl}">Pools</a>
                <div class="nav-item ${currentUrl == groupsUrl ? 'active' : ''}"><a href="${groupsUrl}">List</a></div>
                <div class="nav-item ${currentUrl == groupAddUrl ? 'active' : ''}"><a href="${groupAddUrl}">Create</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, newsUrlPattern) ? 'active' : ''}">
                <a href="${newsUrl}">News</a>
                <div class="nav-item ${currentUrl == newsUrl ? 'active' : ''}"><a href="${newsUrl}">List</a></div>
                <div class="nav-item ${currentUrl == newsAddUrl ? 'active' : ''}"><a href="${newsAddUrl}">Create</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, achievementUrlPattern) ? 'active' : ''}">
                <a href="${achievementsUrl}">Achievements</a>
                <div class="nav-item ${currentUrl == achievementsUrl ? 'active' : ''}"><a href="${achievementsUrl}">List</a></div>
                <div class="nav-item ${currentUrl == achievementAddUrl ? 'active' : ''}"><a href="${achievementAddUrl}">Create</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item">
                <a href="">Users</a>
                <div class="nav-item"><a href="">List</a></div>
            </div>
            
            <div class="separator"></div>
            
            <div class="nav-item ${fn:contains(currentUrl, itemUrlPattern) ? 'active' : ''}">
                <a href="${itemUrl}">Items</a>
                <div class="nav-item ${currentUrl == itemUrl ? 'active' : ''}"><a href="${itemUrl}">List</a></div>
                <div class="nav-item ${currentUrl == itemAddUrl ? 'active' : ''}"><a href="${itemAddUrl}">Create</a></div>
            </div>
            
        </div>
        <div class="tile-10">
            <h2 class="text-right title">Administration</h2>
            <div class="separator"></div>
            <tiles:insertAttribute name="admin.body" ignore="true" />
        </div>
    </div>
</div>