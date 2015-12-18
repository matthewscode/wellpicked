<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%-- Setting URL vars --%>
<spring:url value="/admin/tournaments" var="tournamentsUrl" htmlEscape="true"/>
<spring:url value="/delete--${editedTournament.tournamentId}--tournament" var="tournamentDeleteUrl" htmlEscape="true" />
<spring:url value="/admin/tournament/update/" var="tournamentUpdatePath" htmlEscape="true" />
    
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Edit Tournament" />
<tiles:putAttribute name="pageId" value="admin tournament edit" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${tournamentsUrl}">Tournaments</a></span>
                    <span class="breadcrumb-item">${editedTournament.tournamentName}</span>
                </h3>
            </div>
            
            <form:form modelAttribute="editedTournament" class="edit-tournament-form clearfix" enctype="multipart/form-data">
            <form:hidden path="tournamentId" />
            <form:hidden path="numTeams" />
            <form:hidden path="deleted" />
            <form:hidden path="creation" />
            <form:hidden path="template" />
                <div class="tile-6">
                    <div class="form-input">
                        <label for="tournamentName">
                            Name
                            <span class="help-block error">
                                <form:errors path="tournamentName" />
                            </span>
                        </label>
                        <form:input id="tournamentName" path="tournamentName" type="text" placeholder="Festivus 123" />
                    </div>
                
                    <div class="form-input">
                        <label for="tournamentSlug">
                            Slug
                            <span class="help-block error">
                                <form:errors path="tournamentSlug" />
                            </span>
                        </label>
                        <form:input id="tournamentSlug" path="tournamentSlug" type="text" placeholder="festivus-123" />
                    </div>
                    
                    <div class="form-input">
                        <label for="twitchTag">
                            Twitch Tag
                            <span class="help-block error">
                                <form:errors path="twitchTag" />
                            </span>
                        </label>
                        <form:input id="twitchTag" path="twitchTag" type="text" placeholder="tournament stream" />
                    </div>
                    
                    <div class="form-input">
                        <label for="tournamentDescription">
                            Description
                            <span class="help-block error">
                                <form:errors path="tournamentDescription" />
                            </span>
                        </label>
                        <form:textarea id="tournamentDescription" path="tournamentDescription" placeholder="Festivus 123 is hosted by Company ABC. 16 NA teams will be competing for a prize pool of 10,000 bitcoins."/>
                    </div>
                </div>
                
                <div class="tile-6">
                    
                    <div class="form-input">
                        <label for="tournamentStart">
                            Start Time
                            <span class="help-block error">
                                <form:errors path="tournamentStart" />
                            </span>
                        </label>
                        <div class="timestamper" data-ng-timestamper="true" data-timestamp="${editedTournament.tournamentStart}">
                            <div class="timestamper-date tiles">
                                <select class="timestamper-month tile-6"><option disabled>-- Month --</option></select>
                                <select class="timestamper-day tile-3"><option disabled>-- Day --</option></select>
                                <select class="timestamper-year tile-3"><option disabled>-- Year --</option></select>
                            </div>
                            <div class="timestamper-time tiles">
                                <select class="timestamper-hour tile-3"><option disabled>-- Hour --</option></select>
                                <select class="timestamper-minute tile-3"><option disabled>-- Minute --</option></select>
                                <div class="timestamper-zone tile-3">UTC/GMT</div>
                            </div>
                            <form:input id="tournamentStart" path="tournamentStart" type="hidden" />
                        </div>
                    </div>
                    
                    <div class="form-input">
                        <label for="tournamentEnd">
                            End Time
                            <span class="help-block error">
                                <form:errors path="tournamentEnd" />
                            </span>
                        </label>
                        <div class="timestamper" data-ng-timestamper="true" data-timestamp="${editedTournament.tournamentEnd}">
                            <div class="timestamper-date tiles">
                                <select class="timestamper-month tile-6"><option disabled>-- Month --</option></select>
                                <select class="timestamper-day tile-2"><option disabled>-- Day --</option></select>
                                <select class="timestamper-year tile-4"><option disabled>-- Year --</option></select>
                            </div>
                            <div class="timestamper-time tiles">
                                <select class="timestamper-hour tile-2"><option disabled>-- Hour --</option></select>
                                <select class="timestamper-minute tile-2"><option disabled>-- Minute --</option></select>
                                <div class="timestamper-zone tile-3">UTC/GMT</div>
                            </div>        
                            <form:input id="tournamentEnd" path="tournamentEnd" type="hidden" />
                        </div>
                    </div>
                </div>
                
                <div class="tile-12 container">
                    <div class="tile-6">
                        <div class="form-input form-submit no-margin">
                            <button type="submit">Edit Tournament</button>
                        </div>
                    </div>
                    <div class="tile-6 text-right">
                        <div class="form-input form-submit no-margin">
                            <a class="button small secondary" href="${tournamentUpdatePath}${editedTournament.tournamentId}">Update Tournament</a>
                            <a class="button small warning" href="${tournamentDeleteUrl}">Delete Tournament</a>
                        </div>
                    </div>
                </div>
            </form:form>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>