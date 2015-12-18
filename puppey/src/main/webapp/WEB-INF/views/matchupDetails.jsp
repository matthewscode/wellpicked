<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
${newTournament.getTournamentName()} <br/>
${matchupList.get(0)}

	<form:form  modelAttribute="matchupWrapper" class="form-horizontal" enctype="multipart/form-data">
		<fieldset>
		<c:forEach begin="0" end="${matchupWrapper.matchupList.size()/2}" var="match" varStatus="status">
			<div class="form-group">
			<form:select path="matchupList[${status.index}].team1.teamId">
				<form:options items="${teamList}" itemValue="teamId"  />
			</form:select>
			</div>
			<div class="form-group">
			<form:select path="matchupList[${status.index}].team2.teamId">
				<form:options items="${teamList}" itemValue="teamId"  />
			</form:select>
			</div>
			<br/>
			<br/>
		</c:forEach>
		
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<input type="submit" value="Update" />
				</div>
			</div>
			
		</fieldset>
		</form:form>


 
    </tiles:putAttribute>
</tiles:insertDefinition>