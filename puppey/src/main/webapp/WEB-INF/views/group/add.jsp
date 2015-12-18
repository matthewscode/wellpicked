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
				<div class="form-input">
					<label for="name">Group
						Name</label>
					<form:input id="groupName" path="groupName" type="text"
						class="form:input-large" />
						
				</div>
				 <div class="form-input">
                        <label for="tournamentName">
                           Tournamnet
                            <span class="help-block error">
                                <form:errors path="tournamentList" />
                            </span>
                        </label>
                        <form:select id="tournamentList[0].tournamentId" path="tournamentList[0].tournamentId" items="${activeTournaments}" itemValue="tournamentId" />
                    </div>
                    <div class="form-input">
                        <label for="groupDescription">
                           Description
                            <span class="help-block error">
                                <form:errors path="groupDescription" />
                            </span>
                        </label>
                        <form:textarea  path="groupDescription" />
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