<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<%-- Setting URL vars --%>
<spring:url value="/admin/achievements" var="achievementUrl" htmlEscape="true"/>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Add Artist" />
<tiles:putAttribute name="pageId" value="admin artist add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${achievementUrl}">Artist</a></span>
                    <span class="breadcrumb-item">Add Artist</span>
                </h3>
            </div>
        

            <c:if test="${not empty success}">
            <p class="success">${success}</p>
            </c:if>
            <c:if test="${not empty slugExists}">
            <p class="error">${slugExists}</p>
            </c:if>
    		<form:form modelAttribute="artist" class="add-team-form" enctype="multipart/form-data">
                    <div class="form-input">
                        <label for="artistName">
                            Title
                            <span class="help-block error">
                                <form:errors path="artistName" />
                            </span>
                        </label>
                        <form:input path="artistName" type="text" placeholder="- Artist Name(not actual name)" />
                    </div>
                    <div class="form-input">
                    </div>
                    <div class="form-input">
                        <label>
                            External Link
                            <span class="help-block error">
                                <form:errors path="link" />
                            </span>
                        </label>
                        <form:input path="link" type="text" placeholder="http://teamliquid.net" />
                    </div>
                    <div class="form-input">
                        <label for="region">
                            Class
                            <span class="help-block error">
                                <form:errors path="newsClass" />
                            </span>
                        </label>
                        <form:input path="newsClass" type="text" placeholder="http://teamliquid.net" />
                    </div>
                    <div class="form-input">
                    <form:errors path="newsText" />
                        <form:textarea  path="newsText" type="text" placeholder="This is where it would be best to type a long lengthy text" />
                </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add News Item</button>
                    </div>
                </div>
    		</form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>