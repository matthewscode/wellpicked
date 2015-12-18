<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/tournament/" var="tournamentPath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="upcoming tournaments" /> 
<tiles:putAttribute name="pageTitle" value="Tournaments" /> 

<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="body">
    
    <div class="upcomingTournamentsContainer">
        <c:forEach items="${currentTournaments}" var="item" varStatus="status">
        
            <%-- Check if tournament image exists --%>
            <c:set var="tournamentImage" value="" />
            <c:import url="/resources/images/tournaments/${item.tournamentSlug}.jpg" var="tournamentImage"/>
            
<a href="${tournamentPath}${item.tournamentSlug}">
            <div class="upcomingTournamentBox" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : item.tournamentSlug}.jpg"/>">
                    
                    </div>
                    </a>
        </c:forEach>
        <c:if test="${not empty oldTournaments}">
        <c:forEach items="${oldTournaments}" var="item" varStatus="status">
        <c:set var="tournamentImage" value="" />
            <c:import url="/resources/images/tournaments/${item.tournamentSlug}.jpg" var="tournamentImage"/>
            <a href="${tournamentPath}${item.tournamentSlug}">
            <div class="upcomingTournamentBox upcomingTournamentOldBox" style="z-index: ${5 - status.index*2}" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : item.tournamentSlug}.jpg"/>">
                    </div>
                    </a>
        </c:forEach>
        </c:if>
    </div>
</tiles:putAttribute>
</tiles:insertDefinition>