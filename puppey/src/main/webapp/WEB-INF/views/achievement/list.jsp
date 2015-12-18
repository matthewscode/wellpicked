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
<spring:url value="/resources/images/achievements/" var="achievementImgUrl" htmlEscape="true" />

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Achievements" />
<tiles:putAttribute name="pageId" value="admin achievement list" />
<tiles:putAttribute name="body">
    
        

                <h3 class="title">Achievements</h3>

            <div class="grid tiles">
            <div class="tile-12 container list">
                <div class="list-header">
                    <div class="tiles">
                        <div class="tile-10">Name</div>
                    </div>
                </div>
                <c:forEach items="${achievements}" var="item" varStatus="status">
                   <div class="grid-item tile-3 tile-3h hover image">
                      <div class="wrapper">
                         <div class="background" data-ng-bg-image="${achievementImgUrl}${item.achievementSlug}.png"></div>
                         <div class="wrapper">
                            <div class="content">
                               <h3>${item.achievementName}</h3>
                            </div>
                           </div>
                       </div>

                            
                        </div>
                </c:forEach>
                
                <!--  added for site -->
                 <c:forEach items="${achievements}" var="item" varStatus="status">
                   <div class="grid-item tile-3 tile-3h hover image">
                      <div class="wrapper">
                         <div class="background" data-ng-bg-image="${achievementImgUrl}${item.achievementSlug}.png"></div>
                         <div class="wrapper">
                            <div class="content">
                               <h3>${item.achievementName}</h3>
                            </div>
                           </div>
                       </div>

                            
                        </div>
                </c:forEach>
            </div>
            </div>
            
    
</tiles:putAttribute>
</tiles:insertDefinition>