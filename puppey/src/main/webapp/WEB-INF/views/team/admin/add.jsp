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
            
    		<form:form modelAttribute="newTeam" class="add-team-form" enctype="multipart/form-data">
                <div class="tile-6">
                    <div class="form-input">
                        <label for="teamName">
                            Name
                            <span class="help-block error">
                                <c:if test="${not empty teamNameExists}">${teamNameExists}</c:if>
                                <form:errors path="teamName" />
                            </span>
                        </label>
                        <form:input id="teamName" path="teamName" type="text" placeholder="American Psychos" />
                    </div>
                    <div class="form-input">
                        <label for="teamAbbr">
                            Abbreviation
                            <span class="help-block error">
                                <form:errors path="teamAbbr" />
                            </span>
                        </label>
                        <form:input id="teamAbbr" path="teamAbbr" type="text" placeholder="APs" />
                    </div>
                    <div class="form-input">
                        <label for="steamId">
                            Steam ID
                            <span class="help-block error">
                                <form:errors path="steamId" />
                            </span>
                        </label>
                        <form:input id="steamId" path="steamId" type="text" placeholder="654321" />
                    </div>
                    <div class="form-input">
                        <label for="liquidTag">
                            Liquid Tag
                            <span class="help-block error">
                                <form:errors path="liquidTag" />
                            </span>
                        </label>
                        <form:input id="liquidTag" path="liquidTag" type="text" placeholder="aps" />
                    </div>
                     <div class="form-input">
                        <label for="twitchTag">
                            Twitch Tag
                            <span class="help-block error">
                                <form:errors path="twitchTag" />
                            </span>
                        </label>
                        <form:input id="twitchTag" path="twitchTag" type="text" placeholder="aps" />
                    </div>
                </div>
                <div class="tile-6">
                    <div class="form-input">
                        <label for="region">
                            Region
                            <span class="help-block error">
                                <form:errors path="region" />
                            </span>
                        </label>
                        <form:select id="region" path="region">
                            <form:option value=""></form:option>
                            <form:option value="NA">NA</form:option>
                            <form:option value="EU">EU</form:option>
                            <form:option value="CN">CN</form:option>
                            <form:option value="SEA">SEA</form:option>
                        </form:select>
                    </div>
                    <div class="form-input">
                        <label for="color">
                            Color (primary)
                            <span class="help-block error">
                                <form:errors path="color" />
                            </span>
                        </label>
                        <form:input id="color" path="color" type="text" placeholder="#ff0000" />
                    </div>
                    <div class="form-input">
                        <label for="color">
                            Color (secondary)
                            <span class="help-block error">
                                <form:errors path="secondaryColor" />
                            </span>
                        </label>
                        <form:input id="secondaryColor" path="secondaryColor" type="text" placeholder="#000000" />
                    </div>
                    <div class="form-input">
                        <label for="teamBio">
                            Bio
                            <span class="help-block error">
                                <form:errors path="teamBio" />
                            </span>
                        </label>
                        <form:textarea id="teamBio" path="teamBio" placeholder="American Psychos was founded by Patrick Bateman in the 90's. Nicknamed \"Yuppies\", this team is known for brutally slaughtering their opponents."/>
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