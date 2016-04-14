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
    
    	<a href="tournament/{{ entry.tournamentSlug }}" class="tournament-list-box" data-ng-repeat="entry in data" ng-mouseenter="hover = entry.id" ng-mouseleave="hover = 0" style="background-image: url(resources/images/tournaments/{{ entry.tournamentSlug }}.jpg)">
    		<div class="box-title-hidden" ng-class="{ 'box-title-shown' : hover == entry.id }" >{{ entry.tournamentName }}</div>
    	</a>
    </div>
</tiles:putAttribute>
</tiles:insertDefinition>