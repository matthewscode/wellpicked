<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageId" value="upcoming tournaments" /> 
<tiles:putAttribute name="pageTitle" value="Admin - Items" /> 
<tiles:putAttribute name="body">
<h2 class="page-title">Items</h2>
    
    <div class="grid tiles">
			<c:forEach var="item" items="${items}">
			
			<c:set var="itemImage" value="" />
            <c:import url="/resources/images/items/${item.slug}.png" var="itemImage"/>
            <a href="${item.steamUrl}">
            <div class="grid-item tournament linked image tile-2 tile-1h" data-ng-cloak="true">
                <div class="wrapper">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/items/${empty itemImage ? 'default' : item.slug}.png"/>"></div>
                    <div class="wrapper">
                        <div class="content">
                            <h4 class="title">${item.itemName}</h4>
                        </div>
                    </div>
                </div>
            </div>
            </a>
            			</c:forEach>

 
	</div>
</tiles:putAttribute>
</tiles:insertDefinition>