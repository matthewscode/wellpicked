<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/tournament/" var="tournamentPath" htmlEscape="true" />

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="pageId" value="completed tournaments" /> 
<tiles:putAttribute name="pageTitle" value="Completed Tournaments" /> 
<tiles:putAttribute name="body">
    
    <h2 class="page-title">Completed Tournaments</h2>
    
    <div class="grid tiles">
        <c:forEach items="${completedTournaments}" var="item" varStatus="status">
        
            <%-- Check if tournament image exists --%>
            <c:set var="tournamentImage" value="" />
            <c:import url="/resources/images/tournaments/${item.tournamentSlug}.jpg" var="tournamentImage"/>
            
            <a href="${tournamentPath}${item.tournamentSlug}">
            <div class="grid-item tournament linked image tile-6 tile-4h" data-ng-cloak="true">
                <div class="wrapper">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/tournaments/${empty tournamentImage ? 'default' : item.tournamentSlug}.jpg"/>"></div>
                    <div class="wrapper">
                        <div class="content">
                            <h2 class="title">${item.tournamentName}</h2>
                            <h4 class="subtitle">{{${item.tournamentStart}*1000 | date:'MMMM dd, yyyy'}} - {{${item.tournamentEnd}*1000 | date:'MMMM dd, yyyy'}}</h4>
                        </div>
                    </div>
                </div>
            </div>
            </a>
        </c:forEach>
    </div>
        
</tiles:putAttribute>
</tiles:insertDefinition>