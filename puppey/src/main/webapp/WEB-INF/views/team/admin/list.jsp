<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/tournament/" var="tournamentViewPath" htmlEscape="true" />
<spring:url value="/admin/team/edit/" var="teamEditPath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Teams" />
<tiles:putAttribute name="pageId" value="admin team list" />
<tiles:putAttribute name="body">
    
    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="title">Teams</h3>
            </div>
            
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-10">Name</div>
                        <div class="tile-2 text-center">Edit</div>
                    </div>
                </div>
                <c:forEach items="${Teams}" var="item" varStatus="status">
                    <div class="list-item">
                        <div class="tiles">
                            <div class="tile-10">
                                ${item.teamName}
                            </div>
                            <div class="tile-2 text-center">
                                <a href="${teamEditPath}${item.teamId}"><i class="icon">&#xE3C9;</i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>