<%@page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<spring:url value="resources/images/achievements/" var="achievementImgUrl" htmlEscape="true" />
<spring:url value="resources/images/backgrounds/" var="profileBackgroundUrl" htmlEscape="true" />
<spring:url value="/profile/" var="profileUrl" htmlEscape="true" />
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="pageTitle" value="${user.username}" />
<tiles:putAttribute name="pageId" value="user profile" />
<tiles:putAttribute name="body">
<c:set var="profileBg" value="profile_bg.png" />
<div class="container" style="height: 100%;">
    <div class="wrapper">
    <div id="profileBackground"  style="background-image: url(<c:url value="${profileBackgroundUrl}${profileBg}" />);">
    </div>
        <div id="profileBanner">
           <div class="profileTopContainer">
            <div class="profileImageContainer">
                <div class="profileImage" style="background-image: url(<c:url value="${achievementImgUrl}${user.avatarName}.png" />);">
                </div>
                <div class="profileImageLeft">
            <c:forEach items="${user.favoriteTeams}" var="item" varStatus="status">
                    
                  <div class="profileTeamBox" style="background-image: url(<c:url value="${achievementImgUrl}${item.teamSlug}.png" />);"></div>
                         
           </c:forEach>
 	</div>
             </div>

            </div>
            <div id="profileBar"></div>
        </div>
        <div id="profileBotWrapper">
        <div class="profileInfoBox">
         <div class="profileAchievementBox" style="width: 39.4%; padding: 25px;">
             <h1>${user.username}</h1>
             </div>
        <div class="profileAchievementBox">
             <h5>JOINED</h5>
             <h1>03|2015</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>BRACKETS</h5>
             <h1>27</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>ACHIVEMENTS</h5>
             <h1>5</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>COIN FINISHES</h5>
             <h1>19</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>ITEMS WON</h5>
             <h1>1</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>WINS</h5>
             <h1>0</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>MMR</h5>
             <h1>2446</h1>
             </div>
             <div class="profileAchievementBox">
             <h5>PERFECT</h5>
             <h1>0</h1>
             </div>
             <c:forEach items="${user.achievements}" var="item" varStatus="status">
                    <div class="profileAchievementBox" style="background-image: url(<c:url value="${achievementImgUrl}${item.achievementSlug}.png" />);">
                    </div>
             </c:forEach>
        </div>
             <jsp:include page="../site/components/_comments.jsp">
                <jsp:param name="elId" value="discussion" />
                <jsp:param name="elClass" value="profileCommentBox" />
                <jsp:param name="objectName" value="user" />
                <jsp:param name="objectId" value="${user.userId}" />
            </jsp:include>
        </div>
    </div>
</div>
<!-- <div class="wrapper grid"> -->

<!--             <div class="tile-6 tile-6h"> -->
<!--             <div class="wrapper well"> -->
<!--                 <h3 class="title">Brackets</h3> -->
<!--                The International 2014<br/> -->
<!--                 <a href="#"><font size="1">TI4 Sure Thing</font><br/></a> -->
<!--                <a href="#"> <font size="1">TI4 YoLo</font><br/><br/></a> -->
                
<!--                 DotaPit Season 3<br/> -->
<!--                 <a href="#"><font size="1">EG Easy wing</font><br/><br/></a> -->
                
<!--                ESL: Frankfurt Champtionships<br/> -->
<!--                <a href="#"> <font size="1">esl? more like dsl!</font><br/><br/></a> -->
<!--             <h3 class="title">Leagues</h3> -->
<%--               <c:forEach items="${user.groups}" var="item" varStatus="status"> --%>
<!--                 <p> -->
<%--                 -- <a href="#">${item.groupName}</a> --%>
<!--                 </p> -->
<%--              </c:forEach> --%>
<%--              <c:if test="${empty user.groups}"> --%>
<!--              <p><font color="#ffd700" size="2">Well Picked! The International 2014</font></p> -->
<!--              <p><font color="#c0c0c0" size="2">The Dotes Pit</font></p> -->
<!--              <p><font color="#c0c0c0" size="2">Frankfurt is in Germany idiot</font></p> -->
<%--              </c:if> --%>
<!--            <br/><br/> -->
<!--         </div> -->
<!--         </div> -->
<!--                     <div class="grid-item tile-6 tile-6h hover image"> -->
<!--                 <div class="wrapper"> -->
<%--                     <div class="background" data-ng-bg-image="${achievementImgUrl}${user.avatarName}.png"></div> --%>
<!--                     <div class="wrapper"> -->
<!--                         <div class="content"> -->
<%--                             <h2 class="title">${user.username}</h2> --%>
<%--                             <h3>${user.currency}</h3> --%>
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
        
<!--             <div class="tile-6 tile-6h"> -->
<!--                 <div class="wrapper well"> -->
 
<!--            <h2>Wall</h2> -->
<%--            <c:forEach items="${comments}" var="item" varStatus="status"> --%>
<%--            ${item}<br/><font size="1" face="verdana">by: ${item.user.username}</font><br/> --%>
<%--            </c:forEach> --%>
<%--             <form:form modelAttribute="wallComment" class="add-group-form" enctype="multipart/form-data"> --%>
<!--                     <div class="form-input"> -->

<%--                         <form:input id="comment" path="comment" type="text" placeholder="Slytherin's TI5" /> --%>

<!--                     </div> -->
<!--                 <div class="tile-12"> -->
<!--                     <div class="form-input form-submit no-margin"> -->
<!--                         <button type="submit">Comment</button> -->
<!--                     </div> -->
<!--                 </div> -->
<%--             </form:form> --%>
<!--         </div> -->
<!--         </div> -->
<!--                                 <div class="tile-6 tile-6h"> -->
<!--                 <div class="wrapper well">  -->
<!--              <h2>Achievements</h2> -->
<%--               <div><c:forEach items="${user.achievements}" var="item" varStatus="status"> --%>
<%--               <a href="${profileUrl}${userData.userId}/avatar/${item.achievementId}"> --%>
<!--               <div class="grid-item tournament linked image tile-2 tile-1h" data-ng-cloak="true"> -->
<!--                 <div class="wrapper"> -->
<%--                     <div class="background" data-ng-bg-image="${achievementImgUrl}${item.achievementSlug}.png"> --%>
<!--                     <div class="wrapper"> -->
<!--                     </div> -->
<!--                     </div> -->
<!--                 </div></div></a> -->
               
<%--              </c:forEach></div><br/><br/><br/><br/><br/> --%>
             
<!--              <div> -->
<!--             <h2>Favorite Teams</h2> -->
<%--            <c:forEach items="${user.favoriteTeams}" var="item" varStatus="status"> --%>
<%--             <a href="${profileUrl}avatar/${item.teamSlug}"> --%>
<!--             <div class="grid-item tournament linked image tile-2 tile-1h" data-ng-cloak="true"> -->
<!--             <div class="wrapper"> -->
<%--                <div class="background" data-ng-bg-image="${achievementImgUrl}${item.teamSlug}.png"> --%>
<!--                <div class="wrapper"> -->
<!--                     </div> -->
<!--                     </div> -->
<!--                 </div></div></a> -->
<%--              </c:forEach> --%>
<!-- </div> -->
<!--         </div> -->
<!--         </div> -->
<!--         </div> -->
        
</tiles:putAttribute>
</tiles:insertDefinition>