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
                    <span class="breadcrumb-item"><a href="${teamsUrl}">Items</a></span>
                    <span class="breadcrumb-item">Add Item</span>
                </h3>
            </div>
        
            <c:if test="${not empty success}">${success}</c:if>
            
    		<form:form modelAttribute="newItem" class="add-team-form" enctype="multipart/form-data">
                    <div class="form-input">
                        <label for="teamName">
                            Name
                            <span class="help-block error">
                                <form:errors path="itemName" />
                            </span>
                        </label>
                        <form:input id="itemName" path="itemName" type="text" placeholder="American Psychos" />
                    </div>
                    <div class="form-input">
                        <label for="teamAbbr">
                            Steam Market URL
                            <span class="help-block error">
                                <form:errors path="steamUrl" />
                            </span>
                        </label>
                        <form:input id="steamUrl" path="steamUrl" type="text" placeholder="e.g. https://steamcommunity.com/market/listings/570/Genuine%20Golden%20Gravelmaw" />
                    </div>
                    <div class="form-input">
                        <label for="region">
                            Rarity
                            <span class="help-block error">
                                <form:errors path="rarity" />
                            </span>
                        </label>
                        <form:select id="rarity" path="rarity">
                            <form:option value="">Choose</form:option>
                            <form:option value="Mythical">Mythical</form:option>
                            <form:option value="Legendary">Legendary</form:option>
                            <form:option value="Immortal">Immortal</form:option>
                            <form:option value="Arcana">Arcana</form:option>
                        </form:select>
                    </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add Item</button>
                    </div>
                </div>
    		</form:form>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>