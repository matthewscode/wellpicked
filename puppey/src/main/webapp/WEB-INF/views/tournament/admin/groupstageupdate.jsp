<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/admin/tournaments" var="tournamentsUrl" htmlEscape="true"/>
<spring:url value="/admin/tournament/edit/" var="tournamentEditPath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Update Tournament" />
<tiles:putAttribute name="pageId" value="admin tournament update" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${tournamentsUrl}">Tournaments</a></span>
                    <span class="breadcrumb-item">${tournament.tournamentName}</span>
                </h3>
            </div>
            
            <form:form modelAttribute="tournament" class="update-tournament-form" enctype="multipart/form-data">
                <div class="tile-12 container list">
                    <div class="list-header">
                        <div class="tiles">
                            <div class="tile-1">Place</div>
                            <div class="tile-3 text-center">Team</div>
                        </div>
                    </div>
                    <c:forEach items="${tournament.matchups}" var="item" varStatus="status">
                        <div class="list-item">
                            <div class="tiles">
                                <div class="tile-1 round">${item.matchupType}</div>
                                <div class="tile-3 form-input no-margin small">
                                    <form:select path="matchups[${status.index}].winnerId"  items="${teamList}" itemValue="teamId" />
                                </div>
                                </div>
                            </div>
                    </c:forEach>
                </div>
                <div class="tile-6">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Update Tournament</button>
                    </div>
                </div>
                <div class="tile-6 text-right">
                    <div class="form-input form-submit no-margin">
                        <a class="button small secondary" href="${tournamentEditPath}${tournamentId}">Edit Tournament</a>
                    </div>
                </div>
            </form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>