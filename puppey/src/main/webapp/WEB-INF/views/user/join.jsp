<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<c:url var="openIDLoginUrl" value="/j_spring_openid_security_check" />
<c:url var="loginUrl" value="/login" />

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="pageTitle" value="Sign Up" />
<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="pageId" value="login join" />
<tiles:putAttribute name="body">

    <div class="wrapper grid">
        <div class="tile-7h container centered-block">
            <div class="grid-item tile-6 tile-7h hover image">
                <div class="wrapper">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/pages/join--cm.jpg" />"></div>
                    <div class="wrapper">
                        <div class="content">
                            <h2 class="title">Welcome to the premier Dota 2 fantasy bracket game!</h2>
                            <h4 class="subtitle">
                                <p>It's probably the only one in existence so you don't really have an alternative.</p>
                                <p>But let's pretend you did ;)</p>
                            </h4>
                            <h4 class="subtitle">
                                <p>Already have an account?</p>
                                <a class="button over-dark-image" href="${loginUrl}">Login</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
            <div class="tile-6 tile-7h">
                <div class="wrapper well">
                    <h2 class="title">Sign Up</h2>
                    <div class="separator"></div>
                    
                    <form:form modelAttribute="newUser" class="join-form">
                        <div class="form-input">
                            <label for="username">
                                Username
                                <span class="help-block error">
                                    <c:if test="${not empty emailExists}">${emailExists}</c:if>
                                    <form:errors path="email" />
                                </span>
                            </label>
                            <input  id="username" name="username" type="text" placeholder="player123" />
                        </div>
                        <div class="form-input">
                            <label for="email">
                                Email
                                <span class="help-block error"></span>
                            </label>
                            <input  id="email" name="email" type="email" placeholder="your@email.com" />
                        </div>
                        <div class="form-input">
                            <label for="password">
                                Password
                                <span class="help-block error">
                                    <c:if test="${not empty userNameExists}">${userNameExists}</c:if>
                                </span>
                            </label>
                            <input  id="password" name="password" type="password" placeholder="********" />
                        </div>
                        <div class="form-input form-submit">
                            <button type="submit">Join</button>
                        </div>
                    </form:form>
                    
                    <form action="${openIDLoginUrl}" method="post" class="valign-container login-form-alternate">
                        <input name="openid_identifier" type="hidden" value="http://steamcommunity.com/openid" />
                        <div class="valign small italic">or</div>
                        <div class="valign">
                            <input type="image" src="<c:url value="/resources/images/pages/login--sits.png" />" alt="Sign In Through Steam"/>
                        </div>
                    </form>
                    
                </div>
            </div>
        </div>
    </div>
    
</tiles:putAttribute>
</tiles:insertDefinition>