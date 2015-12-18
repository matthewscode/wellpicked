<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/achievements" var="achievementsUrl" htmlEscape="true"/>
    
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Edit Achievement" />
<tiles:putAttribute name="pageId" value="admin team add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${achievementsUrl}">Achievements</a></span>
                    <span class="breadcrumb-item">${achievement.achievementName}</span>
                </h3>
            </div>
            
            <c:if test="${not empty success}">${success}</c:if>
            
            <form:form modelAttribute="achievement" class="edit-team-form" enctype="multipart/form-data">
                <div class="tile-6">
                    <div class="form-input">
                        <label for="achievementName">
                            Name
                            <span class="help-block error">
                                <form:errors path="achievementName" />
                            </span>
                        </label>
                        <form:input id="achievementName" path="achievementName" type="text" placeholder="#1 Bjs" />
                        <label for="reward">Reward</label>
                        <form:errors path="reward" />
                        <form:input id="reward" path="reward" type="text" />
                         <label for="description">Description</label>
                        <form:errors path="description" />
                        <form:input id="description" path="description" type="text" placeholder="Description" />
                    </div>
                </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Update Achievement</button>
                    </div>
                </div>
            </form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>