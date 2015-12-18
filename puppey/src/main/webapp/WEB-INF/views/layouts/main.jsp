<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<%-- Setting template vars --%>
<c:set var="pageId"><tiles:getAsString name="pageId" /></c:set>
<c:set var="pageTitle"><tiles:getAsString name="pageTitle" /></c:set>
<c:set var="pageKeywords"><tiles:getAsString name="pageKeywords" /></c:set>
<c:set var="pageDescription"><tiles:getAsString name="pageDescription" /></c:set>
<c:set var="bodyStructure"><tiles:getAsString name="bodyStructure" /></c:set>
<c:set var="panelOpen"><tiles:getAsString name="panelOpen" /></c:set>
<c:set var="latestTournament" value="${latestTournament}" />
<c:set var="daysLeft" value="${daysLeft}" />
<%-- Current URL --%>
<c:set value="${requestScope['javax.servlet.forward.request_uri']}" var="currentUrl" />

<%-- Setting URL vars --%>
<spring:url value="/" var="homeUrl" htmlEscape="true"/>
<spring:url value="/login" var="loginUrl" htmlEscape="true" />
<spring:url value="/join" var="joinUrl" htmlEscape="true" />
<spring:url value="/profile" var="profileUrl" htmlEscape="true" />
<spring:url value="/account" var="accountUrl" htmlEscape="true" />
<spring:url value="/logout" var="logoutUrl" htmlEscape="true" />
<spring:url value="/pool" var="poolPattern" htmlEscape="true"/>
<spring:url value="/pools" var="poolUrl" htmlEscape="true"/>
<spring:url value="/pool/create" var="poolCreateUrl" htmlEscape="true"/>
<spring:url value="/pool/user" var="poolUserUrl" htmlEscape="true"/>
<spring:url value="/tournaments" var="tournamentsUrl" htmlEscape="true"/>
<spring:url value="/tournaments/completed" var="tournamentsCompletedUrl" htmlEscape="true"/>
<spring:url value="/bracket" var="bracketPattern" htmlEscape="true"/>
<spring:url value="/brackets" var="bracketsUrl" htmlEscape="true"/>
<spring:url value="/teams" var="teamsUrl" htmlEscape="true"/>
<spring:url value="/admin" var="adminUrl" htmlEscape="true" />
<spring:url value="/admin/tournaments" var="adminTournamentsUrl" htmlEscape="true" />
<spring:url value="/admin/team/add" var="adminTeamAddUrl" htmlEscape="true"/>
<spring:url value="/admin/tournament/add" var="adminTournamentAddUrl" htmlEscape="true"/>
<spring:url value="/resources/images/teams/logos/" var="teamLogoUrl" htmlEscape="true" />
<spring:url value="/stream/" var="streamUrl" htmlEscape="true" />
<spring:url value="/HallOfFame" var="hofUrl" htmlEscape="true" />

<!DOCTYPE html>
<html lang="en" data-ng-app="wpApp">
<head>
    <meta charset="UTF-8">
    <meta name="keywords" content="${pageKeywords} dota 2, dota, tournaments, wellpicked.gg, wellpicked">
    <meta name="description" content="${pageDescription} WellPicked.gg is the premier site for Dota 2 tournament information and brackets.">
    
    <title><c:if test="${!empty pageTitle}">${pageTitle} - </c:if>wellpicked.gg</title>
    
    <!-- LESS: COMPILE IN PRODUCTION -->
    <link rel="stylesheet/less" type="text/css" href="<c:url value="/resources/css/main.less" />">
    <script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.0/less.min.js"></script>

    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/foundicons/3.0.0/foundation-icons.css">
    
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <tiles:insertAttribute name="headAppend" ignore="true" /> 
</head>
<body class="page ${pageId}" style="background: #131313">

    <!-- #CONTAINER -->
    <div id="container">
    <c:choose>
        <c:when test="${bodyStructure == 'fluid'}">
            <tiles:insertAttribute name="body" />
        </c:when>
        <c:otherwise>
<%--             <div class="${panelOpen == 1 ? 'panel-open' : ''}"> --%>
                <tiles:insertAttribute name="body" />
<!--             </div> -->
        </c:otherwise>
    </c:choose>
    </div>
    <!-- /#CONTAINER -->

    <!-- #NAVIGATION -->
    <div id="navigation">
        <div class="main panel">
            <a class="logo" href="${homeUrl}">Well Picked<span>.gg</span></a>
            
            <div class="menu">
                <div class="menu-item has-secondary tournaments">
                    <a class="menu-item-link" href="${tournamentsUrl}">
                        <i class="icon">&#xE021;</i>
                        <span class="name">Tournaments</span>
                    </a>
                    <div class="secondary panel">
                        <div class="menu">
                            <div class="menu-item tournaments-upcoming">
                                <a class="menu-item-link" href="${tournamentsUrl}">
                                    <i class="icon">&#xE031;</i>
                                    <span class="name">Upcoming</span>
                                </a>
                            </div>
                            <div class="menu-item tournaments-archive">
                                <a class="menu-item-link" href="${tournamentsCompletedUrl}">
                                    <i class="icon">&#xE88C;</i>
                                    <span class="name">Completed</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menu-item teams ${fn:contains(currentUrl, teamsUrl) ? 'active' : ''}">
                    <a class="menu-item-link" href="${teamsUrl}">
                        <i class="icon">&#xE7FB;</i>
                        <span class="name">Teams</span>
                    </a>
                </div>
                <div class="menu-item has-secondary brackets">
                    <a class="menu-item-link" href="${bracketsUrl}">
                        <i class="icon">&#xE8F0;</i>
                        <span class="name">Brackets</span>
                    </a>
                    <div class="secondary panel">
                        <div class="menu">
                        <div class="menu-item brackets-trending">
                                <a class="menu-item-link" href="${bracketsUrl}">
                                    <i class="icon">&#xE80E;</i>
                                    <span class="name">Popular</span>
                                </a>
                            </div>
                            <div class="menu-item brackets-user">
                                <a class="menu-item-link" href="${hofUrl}">
                                    <i class="icon">&#xE83A;</i>
                                    <span class="name">Hall of Fame</span>
                                </a>
                            </div>
                            
                        </div>
                    </div>
                </div>
                <div class="menu-item has-secondary pools ${fn:contains(currentUrl, poolPattern) ? 'active' : ''}">
                    <a class="menu-item-link" href="${poolUrl}">
                        <i class="icon">&#xE886;</i>
                        <span class="name">Pools</span>
                    </a>
                    <div class="secondary panel">
                        <div class="menu">
                            <div class="menu-item pools-browse ${fn:contains(currentUrl, poolUrl) ? 'active' : ''}">
                                <a class="menu-item-link" href="${poolUrl}">
                                    <i class="icon">&#xE8B6;</i>
                                    <span class="name">Browse</span>
                                </a>
                            </div>
                            <div class="menu-item pools-user" ${fn:contains(currentUrl, poolUserUrl) ? 'active' : ''}>
                                <a class="menu-item-link" href="${poolUserUrl}">
                                    <i class="icon">&#xE7EF;</i>
                                    <span class="name">Your Pools</span>
                                </a>
                            </div>
                            <div class="menu-item pools-create ${fn:contains(currentUrl, poolCreateUrl) ? 'active' : ''}">
                                <a class="menu-item-link" href="${poolCreateUrl}">
                                    <i class="icon">&#xE145;</i>
                                    <span class="name">Create</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="menu-item leaderboards">
                    <a class="menu-item-link" href="${homeUrl}">
                        <i class="icon">&#xE838;</i>
                        <span class="name">Achievements</span>
                    </a>
                    <div class="secondary panel">
                        <div class="menu">
                            <div class="menu-item">
                                <a class="menu-item-link" href="${homeUrl}">
                                    <i class="icon">&#xE8E5;</i>
                                    <span class="name">List</span>
                                </a>
                            </div>
                            <div class="menu-item">
                                <a class="menu-item-link" href="${homeUrl}">
                                    <i class="icon">&#xE8E5;</i>
                                    <span class="name">Artists</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                
            <c:choose>
            <c:when test="${userData.username != null}">
                <div class="menu-item user ">
                    <a href="${profileUrl}" class="menu-item-link">
                        <i class="icon">&#xE7FD;</i>
                        <span class="name">${userData.username}</span>
                        <span class="name currency">
                            ${userData.currency}<i class="icon">&#xE24A;</i>
                        </span>
                    </a>
                    <div class="secondary panel">
                        <div class="menu">
                            <div class="menu-item user-account">
                                <a class="menu-item-link" href="${accountUrl}">
                                    <i class="icon">&#xE851;</i>
                                    <span class="name">Account</span>
                                </a>
                            </div>
                            <security:authorize access="hasRole('ROLE_ADMIN')">
                            <div class="menu-item user-admin">
                                <a class="menu-item-link" href="${adminUrl}">
                                    <i class="icon">&#xE8B9;</i>
                                    <span class="name">Admin</span>
                                </a>
                            </div>
                            </security:authorize>
                            <div class="menu-item user-logout">
                                <a class="menu-item-link" href="${logoutUrl}">
                                    <i class="icon">&#xE879;</i>
                                    <span class="name">Logout</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div class="menu-item login">
                    <a href="${loginUrl}" class="menu-item-link">
                        <i class="icon">&#xE7FD;</i>
                        <span class="name">Login / Sign Up</span>
                    </a>
                </div>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
    </div>
    <!-- /#NAVIGATION -->

    <!-- #SIDEBAR -->
    <div id="sidebar" style="background: rgba(231, 231, 231, 1.0)">
        <div class="wrapper">
        <c:choose>
        <c:when test="${not empty userData}">
            <div class="section" style="height: 350px">

                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/achievements/${userData.avatarName}" />.png">
                    
                    </div>

            </div>
            </c:when>
            <c:otherwise>
            <div class="section" style="height: 350px">
                <a href="http://www.dota2.com">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/news/linas.jpg" />">
                    
                    </div>
                    <div style="position: absolute; bottom: 50%; left: 98px"><img src="https://steamcommunity-a.akamaihd.net/public/images/signinthroughsteam/sits_small.png" /></div>
                </a>
            </div>
            </c:otherwise>  
            </c:choose>       
            <div class="section">
                    <div class="section sticky-navigation" style="height: 42px; background: #1e4880;">

                    <div class="content" style="bottom: auto; background: #1e4880; height: 42px; padding: 13px">
                        <h5 style="color: #e7e7e7; font: 12px/1.34 'Lato', sans-serif">LIVE STREAMS</h5>
                    </div>

            </div>
                            <div class="top-brackets tile-6" data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/streams/favorite_teams" />')">
                            <div class="loader" data-ng-hide="data"></div>
                            <div data-ng-show="data">
                                <div data-ng-repeat="team in data">
                                    <div data-ng-repeat="channel in team.team.channels" ng-if="channel.channel.meta_game=='Dota 2'" style="height: 41px;"><div style="display:inline-block; line-height: 40px; height: 40px; width: 40px; background-image: url(<c:url value="/resources/images/teams/logos/{{team.team.slug}}.png" />); background-size: cover; background-position: center;">&nbsp;</div><div style="display: inline-block; height: 40px; margin: auto; line-height: 40px; padding-left: 5px;"><a href="${streamUrl}{{channel.channel.name}}">{{channel.channel.display_name}}</a></div></div>

                                </div>
                        </div>
</div>


            </div>
            <div class="section sticky-navigation" style="height: 42px; background: #1e4880;">

                    <div class="content" style="bottom: auto; background: #1e4880; height: 42px; padding: 13px">
                        <h5 style="color: #e7e7e7; font: 12px/1.34 'Lato', sans-serif">BRACKET DUE</h5>
                    </div>

            </div>
            <div class="section" style="height: 140px">
                <a href="<c:url value="/tournament/${latestTournament.tournamentSlug}"/>">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/tournaments/${latestTournament.tournamentSlug}.jpg" />"></div>
                    <div class="content">
                        <h3 class="title">${latestTournament.tournamentName }</h3>
                        <h4 class="subtitle">
                        ${daysLeft} days
                        </h4>
                    </div>
                </a>
            </div>
            <div class="section sticky-navigation" style="background: #1e4880;">

                    <div class="content" style="bottom: auto; background: #1e4880; padding: 15px">
                        <h5 style="color: #e7e7e7; font: 12px/1.34 'Lato', sans-serif">DOTA 2 UPDATE</h5>
                    </div>

            </div>
            <div class="section" style="height: 100px">
                <a href="http://www.dota2.com">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/news/frankfurt.png" />"></div>
                    <div class="content">

                    </div>
                </a>
            </div>
          
<!--             <div class="section"> -->
<!--                 <a href="#"> -->
<%--                     <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/swords.jpg" />"></div> --%>
<!--                     <div class="content"> -->
<!--                         <h3 class="title">Fire & Swords!</h3> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
<!--             <div class="section"> -->
<!--                 <a href="#"> -->
<%--                     <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/whitewalker.jpg" />"></div> --%>
<!--                     <div class="content"> -->
<!--                         <h3 class="title">With Light Image</h3> -->
<!--                         <h4 class="subtitle"> -->
<!--                             <p>Is this a white walker? I'm not sure. It could be.</p> -->
<!--                         </h4> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
<!--             <div class="section"> -->
<!--                 <a href="#"> -->
<!--                     <div class="content"> -->
<!--                         <h3 class="title">Report: Asian Ivy League Applicants Coached To Be 'Less Asian'</h3> -->
<!--                         <p class="text"> -->
<!--                             According to a profile in the Boston Globe, many consulting businesses catering to Asian-American students applying to Ivy League  -->
<!--                             colleges coach them to appear 'less Asian' on their applications so they can distinguish themselves from other high-achieving  -->
<!--                             Asian-American candidates. What do you think? -->
<!--                         </p> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
<!--             <div class="section"> -->
<!--                 <a href="#"> -->
<%--                     <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/puppy.jpg" />"></div> --%>
<!--                     <div class="content"> -->
<!--                         <h3 class="title">Congratulations, it's a puppy!</h3> -->
<!--                         <h4 class="subtitle"> -->
<!--                             <p>Nawwwww.</p> -->
<!--                         </h4> -->
<!--                     </div> -->
<!--                 </a> -->
<!--             </div> -->
        </div>
    </div>
    <!-- /SIDEBAR -->
    
    <!-- Load vendor scripts -->    
    <script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.3/moment.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.16/angular.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery.perfect-scrollbar/0.6.2/js/min/perfect-scrollbar.jquery.min.js"></script>
    
    <!-- Load our scripts --> 
    <script src="<c:url value="/resources/js/main.js" />"></script>
    <script src="<c:url value="/resources/js/wpapp.js" />"></script> 
     
    <tiles:insertAttribute name="bodyAppend" ignore="true" /> 
</body>
</html>