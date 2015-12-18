<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<c:url var="openIDLoginUrl" value="/login/openid" />
<c:url var="signUpUrl" value="/join" />

<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="pageTitle" value="Login" />
<tiles:putAttribute name="pageId" value="login" />
<tiles:putAttribute name="panelOpen" value="0" />
<tiles:putAttribute name="body">

    <div class="wrapper grid">
        <div class="tile-6h container centered-block">
            <div class="tile-6 tile-6h">
                <div class="wrapper well">
                    <h2 class="title">Login</h2>
                    <div class="separator"></div>
                    
                    <c:if test="${not empty error}">
                        <p class="error">${error}</p>
                    </c:if>
                    
                    <form action="<c:url value="/login" />" method="post" class="login-form">
                        <div class="form-input">
                            <label for="email">
                                Email
                                <span class="help-block error"></span>
                            </label>
                            <input  id="username" name="username" type="email" placeholder="your@email.com" />
                        </div>
                        <div class="form-input">
                            <label for="password">
                                Password
                                <span class="help-block error"></span>
                            </label>
                            <input  id="password" name="password" type="password" placeholder="********" />
                        </div>
                        <div class="form-input">
                            <div class="valign">
                                <button type="submit">Login</button>
                            </div>
                        </div>
                    </form>
                    
                    <form action="${openIDLoginUrl}" method="post" class="valign-container login-form-alternate">
                        <input name="openid_identifier" type="hidden" value="http://steamcommunity.com/openid" />
                        <div class="valign small italic">or</div>
                        <div class="valign">
                            <input type="image" src="<c:url value="/resources/images/pages/login--sits.png" />" alt="Sign In Through Steam"/>
                        </div>
                    </form>
                    
                </div>
            </div>
            <div class="grid-item tile-6 tile-6h hover image">
                <div class="wrapper">
                    <div class="background" data-ng-bg-image="<c:url value="/resources/images/pages/login--heroes.jpg" />"></div>
                    <div class="wrapper">
                        <div class="content">
                            <h2 class="title">Join our really awesome site, for reasons!</h2>
                            <h4 class="subtitle">
                                <p>Don't have an account?</p>
                                <a class="button over-dark-image" href="${signUpUrl}">Sign Up</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</tiles:putAttribute>
</tiles:insertDefinition>