<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Setting URL vars --%>
<c:url value="/team/" var="teamPath" />
    
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="teams" /> 
<tiles:putAttribute name="pageTitle" value="Teams" /> 
<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="body">

<div class="team-list-container" ng-controller="teamListCtrl" data-ng-init="init('<c:url value="/api/team/list/active/0" />')">
	<div class="region-box" style="background-image: url(<c:url value="/resources/images/teams/regions/na.jpg" />)">
	<table width="100%" height="100%">
		<tr>
		<td>
			<a href="<c:url value="/team/{{ entry.teamSlug }}" />" class="region-team-box" ng-repeat="entry in northAmerica" style="background-image: url(<c:url value="/resources/images/teams/logos/{{ entry.teamSlug }}.png" />)">
			
			</a>
		</td>
		</tr>
	</table>
	
	</div>
	<div class="region-box" style="background-image: url(<c:url value="/resources/images/teams/regions/eu.jpg" />)">
	<table width="100%" height="100%">
		<tr>
		<td>
			<a href="<c:url value="/team/{{ entry.teamSlug }}" />" class="region-team-box" ng-repeat="entry in europe" style="background-image: url(<c:url value="/resources/images/teams/logos/{{ entry.teamSlug }}.png" />)">
			
			</a>
		</td>
		</tr>
	</table>
	
	</div>
	<div class="region-box" style="background-image: url(<c:url value="/resources/images/teams/regions/cn.jpg" />)">
	<table width="100%" height="100%">
		<tr>
		<td>
			<a href="<c:url value="/team/{{ entry.teamSlug }}" />" class="region-team-box" ng-repeat="entry in china" style="background-image: url(<c:url value="/resources/images/teams/logos/{{ entry.teamSlug }}.png" />)">
			
			</a>
		</td>
		</tr>
	</table>
	
	</div>
	<div class="region-box" style="background-image: url(<c:url value="/resources/images/teams/regions/sea.jpg" />)">
	<table width="100%" height="100%">
		<tr>
		<td>
			<a href="<c:url value="/team/{{ entry.teamSlug }}" />" class="region-team-box" ng-repeat="entry in sea" style="background-image: url(<c:url value="/resources/images/teams/logos/{{ entry.teamSlug }}.png" />)">
			
			</a>
		</td>
		</tr>
	</table>
	
	</div>
	</div>

<%-- <spring:url value="/team/" var="teamUrl" htmlEscape="true" /> --%>

<%-- <c:set var="region" value="0" /> --%>
<!--     <div style="height: 100.00vh; overflow: hidden;"> -->
<%-- <c:forEach var="team" items="${teams}" varStatus="status"> --%>
    
<%--     Close region --%>
<%--     <c:if test="${team.region != region && status.index > 0}"> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<%--     </c:if> --%>
    
<%--     Open region --%>
<%--     <c:if test="${team.region != region}"> --%>
<!--         <div class="region-list-box"> -->
<!--             <div class="grid-item tile-24 tile-3h hover image" style="padding: 0px; max-height: 25.00vh"> -->
<!--                 <div class="wrapper"> -->
<%--                     <div class="background" data-ng-bg-image="<c:url value="/resources/images/teams/regions/${fn:toLowerCase(team.region)}.jpg"/>"></div> --%>
<!--                     <div class="wrapper"> -->
<!--                         <div class="content" style="padding: 0px"> -->
<!--                             <h2 class="title"> -->
<%--                                 <c:choose> --%>
<%--                                     <c:when test="${team.region == 'NA'}">North America</c:when> --%>
<%--                                     <c:when test="${team.region == 'SEA'}">South East Asia</c:when> --%>
<%--                                     <c:when test="${team.region == 'EU'}">Europe</c:when> --%>
<%--                                     <c:when test="${team.region == 'CN'}">China</c:when> --%>
<%--                                     <c:otherwise>${team.region}</c:otherwise> --%>
<%--                                 </c:choose> --%>
<!--                             </h2> -->
<!--                             <div class="teams"> -->
<%--     </c:if> --%>
<%--     Team logo --%>
<%--     <c:set var="teamImage" value="" /> --%>
<%--     <c:import url="/resources/images/teams/logos/${team.teamSlug}.png" var="teamImage"/> --%>
<!--     <div class="team"> -->
<!--         <div class="logo"> -->
<%--             <a href="${teamPath}${team.teamSlug}"><img src="<c:url value="/resources/images/teams/logos/${empty teamImage ? 'default' : team.teamSlug}.png"/>" /></a> --%>
<!--         </div> -->
<%--         <span class="name">${team.teamName}</span> --%>
<!--     </div> -->
    
<%--     Close region --%>
<%--     <c:if test="${status.last}"> --%>
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<%--     </c:if> --%>
    
<%--     <c:set var="region" value="${team.region}" /> --%>
<%-- </c:forEach> --%>
<!-- </div> -->

    
</tiles:putAttribute>
</tiles:insertDefinition>