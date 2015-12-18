<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/pools" var="groupsUrl" htmlEscape="true"/>
<spring:url value="/admin/pool/${group.groupId}/prizes" var="prizesUrl" htmlEscape="true"/>
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Edit Group" />
<tiles:putAttribute name="pageId" value="admin team add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${groupsUrl}">Group</a></span>
                    <span class="breadcrumb-item">${group.groupName}</span>
                </h3>
            </div>
            
            

                <div class="tile-6">
                    <div class="form-input">
                        <label for="groupName">
                        ${group.groupName} - <a href="${prizesUrl}">EDIT PRIZES</a>
                            </label>
                    </div>
                
            <form:form modelAttribute="group"  class="add-group-form" enctype="multipart/form-data">
             <form:hidden  path="groupId" />
             <form:hidden  path="creation" />
             <form:hidden  path="featured" />
             <form:hidden path="user" />
 <form:input  id="groupName" path="groupName" type="text" />
<form:input id="entryCost" path="entryCost" type="text" />
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Edit Group</button>
                    </div>
                </div>
                
              </form:form></div>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>