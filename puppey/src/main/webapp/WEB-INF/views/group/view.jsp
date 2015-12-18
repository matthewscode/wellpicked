<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
    <spring:url value="/admin/tournament/add" var="adminTournamentAddUrl" htmlEscape="true"/>
    <spring:url value="/resources/images/items/" var="itemImageUrl" htmlEscape="true" />

<div class="information" data-ng-bg-image="<c:url value="/resources/images/tournaments/${group.tournamentList[0].tournamentSlug}.jpg"/>">
        <div class="wrapper">
        <h1 class="name">${group.groupName}</h1>
        <div class="separator"></div>
            <h1 class="name"><a href="${tournamentUrl}">${group.tournamentList[0].tournamentName}</a></h1>
            <div class="separator"></div>
            <br/><br/>
                <h4>${group.groupDescription}<br/><br/>
                creator <a data-ng-href="<c:url value="${profilePath}${group.user.userId}" />">${group.user.username}</a>
            </h4>
        </div>
    </div>


<a href="${group.groupId}/join">+ Join</a>
<c:forEach items="${group.groupPrizes}" var="prize" varStatus="index">
<strong>${prize.place} </strong><a href="${prize.item.steamUrl}"><img src="${itemImageUrl}${prize.item.slug}.png" width="100"></a>
</c:forEach>
<c:if test="${not empty createBracket}">
<strong><font color="red">${createBracket}</font></strong><br/>
</c:if>
<c:if test="${not empty fillBracket}">
<strong><font color="red">${fillBracket}</font></strong><br/>
	<c:if test="${not empty userPredictions}">
		<c:forEach items="${userPredictions}" var="item" varStatus="index">
		${item.tournamentPredictionName}
		<form  action="../pool/${group.groupId}/addPrediction" method="POST">
            <input type="hidden" name="tpid" value="${item.tournamentPredictionId}"/>
            <input type="Submit" value="Submit"/>
        </form><br/>
		</c:forEach>
	</c:if>
</c:if>
<br/><br/>

<table>

<tr><td width="200">Members</td><td width="200">Prediction</td></tr>
<c:forEach items="${usersAndPredictions}" var="item" varStatus="index">
	<tr>
	<td>${item.key.username}</td><td>${item.value}</td>
	</tr>
</c:forEach>
</table>
</tiles:putAttribute>
</tiles:insertDefinition>