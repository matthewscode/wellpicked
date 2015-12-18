<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin" var="homeUrl" htmlEscape="true"/>
<spring:url value="/admin/tournaments" var="tournamentsUrl" htmlEscape="true"/>
<spring:url value="/admin/tournament/add" var="tournamentAddUrl" htmlEscape="true"/>
<spring:url value="/admin/teams" var="teamsUrl" htmlEscape="true"/>
<spring:url value="/admin/team/add" var="teamAddUrl" htmlEscape="true"/>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin" />
<tiles:putAttribute name="pageId" value="admin home" />
<tiles:putAttribute name="body">
    
    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
            
            <div class="grid tiles">
                <div class="tile-12 container">
                    <div class="tile-6 container">
                        <div class="grid-item color-1 tile-6 tile-2h linked iconed">
                            <a href="${tournamentAddUrl}"></a>
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE146;</i>
                                    <h3>Add Tournament</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-3 tile-6 tile-2h iconed">
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE30F;</i>
                                    <h3>11 Upcoming Tournaments</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-3 tile-6 tile-2h iconed">
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE30F;</i>
                                    <h3>421 Tournaments</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-1 tile-6 tile-2h linked iconed">
                            <a href=""></a>
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE146;</i>
                                    <h3>Add League</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-1 tile-6 tile-2h linked iconed">
                            <a href="${teamAddUrl}"></a>
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE146;</i>
                                    <h3>Add Team</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-3 tile-6 tile-2h iconed">
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE7FC;</i>
                                    <h3>5,841 Users</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="tile-6 container">
                        <div class="grid-item color-1 tile-12 tile-4h image">
                            <a href=""></a>
                            <div class="wrapper">
                                <div class="background" data-ng-bg-image="<c:url value="/resources/images/pages/admin--prophet.jpg" />"></div>
                            </div>
                        </div>
                        <div class="grid-item color-1 tile-6 tile-2h linked iconed">
                            <a href=""></a>
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE146;</i>
                                    <h3>Add News</h3>
                                </div>
                            </div>
                        </div>
                        <div class="grid-item color-3 tile-6 tile-2h iconed">
                            <div class="wrapper">
                                <div class="content">
                                    <i class="icon">&#xE8F0;</i>
                                    <h3>11,874 Brackets</h3>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
             
        </tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>