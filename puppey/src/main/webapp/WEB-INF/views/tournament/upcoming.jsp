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
    
    <div class="upcoming-tournaments-container" ng-controller="tournamentListCtrl" data-ng-init="init('<c:url value="/api/tournament/list/latest/4" />'); hover = 0;">
    
    	<div class="list-tournament-countainer">
				<a class="list-tournament-entry" ng-repeat="entry in data" 
					style="background-image: url(<c:url value="/resources/images/tournaments/{{ entry.tournamentSlug }}.jpg" />);"  
					ng-click="getBrackets('<c:url value="/api/predictions/list/latest/20/" />' + entry.tournamentId); $parent.selectedTournament = entry.tournamentId;"
					ng-class="{'bracket-list-selected' : $parent.selectedTournament == entry.tournamentId}"></a>
			</div>
    </div>
</tiles:putAttribute>
</tiles:insertDefinition>