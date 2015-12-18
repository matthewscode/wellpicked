<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/groups" var="groupsUrl" htmlEscape="true" />

<tiles:insertDefinition name="main">
	<tiles:putAttribute name="pageTitle" value="Admin - Edit Group" />
	<tiles:putAttribute name="pageId" value="admin team add" />
	<tiles:putAttribute name="body">

		<tiles:insertDefinition name="admin">
			<tiles:putAttribute name="admin.body">

				<div class="tile-12 container">
					<h3 class="breadcrumbs title">
						<span class="breadcrumb-item"><a href="${groupsUrl}">Prize</a></span>
					</h3>
				</div>



				<div class="tile-6">
					<div class="form-input">
						<label for="groupName"> ${prize.item.itemName} </label><a href="#"><font size="1">Delete</font></a>
					</div>

					<form:form modelAttribute="prize" class="add-group-form"
						enctype="multipart/form-data">
						<form:hidden  path="prizeId" />
						<form:hidden  path="userGroup" />
						<form:hidden  path="winner" />
						<form:hidden  path="isDistributed" />
						<form:input path="place" type="text" size="5"/>

						<form:select path="item">
							<form:options items="${itemList}" itemLabel="itemName"
								itemValue="itemId" />
						</form:select>

						<div class="tile-12">
							<div class="form-input form-submit no-margin">
								<button type="submit">Edit Prize</button>
							</div>
						</div>

					</form:form>
				</div>

			</tiles:putAttribute>
		</tiles:insertDefinition>
	</tiles:putAttribute>
</tiles:insertDefinition>