<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- Setting URL vars --%>
<spring:url value="/admin/groups" var="groupsUrl" htmlEscape="true"/>
<spring:url value="/resources/images/items/" var="itemImageUrl" htmlEscape="true" />  
<spring:url value="/admin/prize/" var="prizeEditUrl" htmlEscape="true"/>
 
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Edit Group Prizes" />
<tiles:putAttribute name="pageId" value="admin team add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
            <div class="tile-12 container">
                <h3 class="breadcrumbs title">
                    <span class="breadcrumb-item"><a href="${groupsUrl}">Group</a></span>
                    <span class="breadcrumb-item">${group.groupName}</span>
                     <span class="breadcrumb-item">Prizes</span>
                </h3>
            </div>
            
            

             <div class="tile-6">
                  <div class="form-input">
                      <label for="groupName">
                      	${group.groupName}
                      </label>
                  </div>
                    <br/>
                    <c:forEach items="${group.groupPrizes}" var="prize" varStatus="status">
                    	<img src="${itemImageUrl}${prize.item.slug}.png" width="100"><strong>${prize.place}:</strong><a href="${prize.item.steamUrl}"> ${prize.item.itemName}</a> <br><a href="${prizeEditUrl}${prize.prizeId}/edit"><font size="1">EDIT</font></a><br/><br>
                    </c:forEach>
                
            <form:form modelAttribute="newPrize"  class="add-group-form" enctype="multipart/form-data">
            	<form:hidden  path="userGroup" />
				<form:input path="place" type="text" />
 				<form:select path="item">
                	<form:options items="${itemList}" itemLabel="itemName" itemValue="itemId"/>
                </form:select>     
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add Prize</button>
                    </div>
                </div>   
             </form:form>
             </div>

        </tiles:putAttribute>
    </tiles:insertDefinition>
</tiles:putAttribute>
</tiles:insertDefinition>