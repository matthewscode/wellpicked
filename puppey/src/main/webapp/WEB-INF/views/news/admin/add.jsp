<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/teams" var="teamsUrl" htmlEscape="true"/>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Add Team" />
<tiles:putAttribute name="pageId" value="admin team add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${teamsUrl}">Teams</a></span>
                    <span class="breadcrumb-item">Create Team</span>
                </h3>
            </div>
        
            <c:if test="${not empty success}">${success}</c:if>
            
    		<form:form modelAttribute="newNews" class="add-team-form" enctype="multipart/form-data">
                <div class="tile-6">
                    <div class="form-input">
                        <label for="teamName">
                            Name
                            <span class="help-block error">
                                <c:if test="${not empty newsExists}">${newsExists}</c:if>
                            </span>
                        </label>
                        <form:input id="newsTitle" path="newsTitle" type="text" placeholder="news title" />
                    </div>
                    
                    
                    <div class="form-input">
                        <label for="teamBio">
                            Bio
                            <span class="help-block error">
                            </span>
                        </label>
                        <form:textarea id="newsText" path="newsText" placeholder="American Psychos was founded by Patrick Bateman in the 90's. Nicknamed \"Yuppies\", this team is known for brutally slaughtering their opponents."/>
                    </div>
                </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add Team</button>
                    </div>
                </div>
    		</form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>