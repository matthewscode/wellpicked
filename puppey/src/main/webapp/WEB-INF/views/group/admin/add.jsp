<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%-- Setting URL vars --%>
<spring:url value="/admin/groups" var="groupsUrl" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Add Group" />
<tiles:putAttribute name="pageId" value="admin group add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${groupsUrl}">Groups</a></span>
                    <span class="breadcrumb-item">Create Group</span>
                </h3>
            </div>
            
            <form:form modelAttribute="newGroup" class="add-group-form" enctype="multipart/form-data">
                <div class="tile-6">
                    <div class="form-input">
                        <label for="groupName">
                            Name
                            <span class="help-block error">
                                <form:errors path="groupName" />
                                <form:errors path="user" />
                            </span>
                        </label>
                        <form:input id="groupName" path="groupName" type="text" placeholder="Slytherin's TI5" />
                    </div>
                    <div class="form-input">
                        <label for="tournamentList">
                            Tournament
                            <span class="help-block error">
                            </span>
                        </label>
                        <form:select id="tournamentList" path="tournamentList">
                            <form:options items="${activeTournaments}" itemLabel="tournamentName" itemValue="tournamentId"/>
                        </form:select>
                    </div>
                    <label for="entryCost">Entry</label>
                    <span class="help-block error">
                                <form:errors path="entryCost" />
                            </span>
                    <form:input id="entryCost" path="entryCost" type="text" placeholder="5000" />
                    <div class="form-input checkbox">
                        <form:checkbox path="featured" id="featured"/>
                        <label for="featured">
                            Featured
                            <span class="help-block error">
                            </span>
                        </label>
                    </div>
                </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add Group</button>
                    </div>
                </div>
            </form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>