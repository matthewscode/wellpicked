<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/tournament/" var="tournamentViewPath" htmlEscape="true" />
<spring:url value="/admin/tournament/edit/" var="tournamentEditPath" htmlEscape="true" />
<spring:url value="/admin/tournament/update/" var="tournamentUpdatePath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Tournaments" />
<tiles:putAttribute name="pageId" value="admin tournament list" />
<tiles:putAttribute name="body">
    
    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="title">Tournaments</h3>
            </div>
            
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-10">Name</div>
                        <div class="tile-1 text-center">Edit</div>
                        <div class="tile-1 text-center">Update</div>
                    </div>
                </div>
                <c:forEach items="${Tournaments}" var="item" varStatus="status">
                    <div class="list-item">
                        <div class="tiles">
                            <div class="tile-10">
                                <a href="${tournamentViewPath}${item.tournamentSlug}">${item.tournamentName}</a>
                            </div>
                            <div class="tile-1 text-center">
                                <a href="${tournamentEditPath}${item.tournamentId}"><i class="icon">&#xE3C9;</i></a>
                            </div>
                            <div class="tile-1 text-center">
                                <a href="${tournamentUpdatePath}${item.tournamentId}"><i class="icon">&#xE255;</i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>