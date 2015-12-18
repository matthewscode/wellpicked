<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/news" var="newsUrl" htmlEscape="true"/>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Add News" />
<tiles:putAttribute name="pageId" value="admin news confirm" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${newsUrl}">News</a></span>
                    <span class="breadcrumb-item">Confirm News</span>
                </h3>
            </div>
<div class="wrapper tiles">
    <div class="grid">

        <!-- ROW 1 -->
        <div class="tile-12 container">
        <div class="${newsToConfirm.newsClass}">
                <div class="wrapper">
                    <div class="background" data-ng-bg-image="/puppey/resources/images/news/default.jpg"></div>
                    <div class="wrapper">
                        <div class="content">
                            <h2 class="title">${newsToConfirm.newsTitle}</h2>
                            <h4 class="subtitle">
                                ${newsToConfirm.newsText}
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
</div>
</div>
</div>

    		<form:form modelAttribute="newsToConfirm" class="add-team-form" enctype="multipart/form-data">
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Confirm</button>
                    </div>
                </div>
    		</form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>