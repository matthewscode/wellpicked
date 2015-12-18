<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<form:form modelAttribute="newGroup" class="form-horizontal"
			enctype="multipart/form-data">
			<fieldset>
				<legend>Create Group</legend>
				<form:errors path="groupName"/><br/>
				<form:errors path="user"/>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">Group
						Name</label>
					<form:input id="groupName" path="groupName" type="text"
						class="form:input-large" />
						
				</div>
				<div class="form-group">
					<label class="control-label col-lg-2 col-lg-2" for="name">Tournament</label>
					<form:select path="tournament.tournamentId">
					<form:option value="-1">Select Active Tournament</form:option>
						<form:options items="${activeTournaments}" itemLabel="tournamentName" itemValue="tournamentId"/>
					</form:select>
				</div>


				<div class="form-group">
					<div class="col-lg-offset-2 col-lg-10">
						<input type="submit" value="Add" />
					</div>
				</div>

			</fieldset>
		</form:form>
	</tiles:putAttribute>
</tiles:insertDefinition>