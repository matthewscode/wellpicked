<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<%-- Setting URL vars --%>
<spring:url value="/tournament/" var="tournamentViewPath" htmlEscape="true" />
<spring:url value="/admin/tournament/edit/" var="tournamentEditPath" htmlEscape="true" />
<spring:url value="/admin/tournament/update/" var="tournamentUpdatePath" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="User - Transactions" />
<tiles:putAttribute name="pageId" value="user transaction list" />
<tiles:putAttribute name="body">
        
            <div class="tile-12 container">
                <h3 class="title">Transactions</h3>
            </div>
            
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-1">id</div>
                        <div class="tile-1 text-center">removed</div>
                        <div class="tile-1 text-center">added</div>
                        <div class="tile-1 text-center">reason</div>
                    </div>
                </div>
                <c:forEach items="${transactions}" var="item" varStatus="status">
                    <div class="list-item">
                        <div class="tiles">
                            <div class="tile-10">
                                <a href="#">${item.currencyTransactionId}</a>
                            </div>
                            <div class="tile-1 text-center">
                                <a href="#"><i class="icon">${item.decrease}</i></a>
                            </div>
                            <div class="tile-1 text-center">
                                <a href="#"><i class="icon">${item.increase}</i></a>
                            </div>
                                                        <div class="tile-1 text-center">
                                <a href="#"><i class="icon">${item.reason}</i></a>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            
        </tiles:putAttribute>
    </tiles:insertDefinition>
    