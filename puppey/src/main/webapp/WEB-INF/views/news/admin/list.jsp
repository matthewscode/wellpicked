<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/admin/news/edit/" var="newsEditPath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - News" />
<tiles:putAttribute name="pageId" value="admin news list" />
<tiles:putAttribute name="body">
    
    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="title">News</h3>
            </div>
            
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-10">Name</div>
                        <div class="tile-2 text-center">Date</div>
                    </div>
                </div>
                <c:forEach items="${newsList}" var="item" varStatus="status">
                    <div class="list-item">
                        <div class="tiles">
                            <div class="tile-10">
                                ${item.newsTitle}
                            </div>
                            <div class="tile-2 text-center">
                                ${item.creation}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>