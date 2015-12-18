<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/admin/achievement/edit/" var="achievementEditPath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Achievements" />
<tiles:putAttribute name="pageId" value="admin achievement list" />
<tiles:putAttribute name="body">
    
    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="title">Achievements</h3>
            </div>
            
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-10">Name</div>
                        <div class="tile-2 text-center">Edit</div>
                    </div>
                </div>
                <c:forEach items="${achievements}" var="item" varStatus="status">
                    <div class="list-item">
                        <div class="tiles">
                            <div class="tile-10">
                                ${item.achievementName}
                            </div>
                            <div class="tile-2 text-center">
                                <a href="${achievementEditPath}${item.achievementId}"><i class="icon">&#xE3C9;</i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>