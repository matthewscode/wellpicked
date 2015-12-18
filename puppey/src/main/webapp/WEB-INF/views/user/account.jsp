<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Account" />
<tiles:putAttribute name="pageId" value="account settings" />
<tiles:putAttribute name="body">

        
<div class="tiles">
    <div class="tile-12 well">
        <h2 class="title">Account Settings</h2>
        <div class="separator"></div>

        <c:if test="${not empty success}">
            <div class="alert alert-success" role="alert">${success}</div>
        </c:if>

        <form:form modelAttribute="user" enctype="multipart/form-data">
            <div class="tile-6">
                <div class="form-input">
                    <label for="username">
                        Username
                        <span class="help-block error">
                            <c:if test="${not empty userExists}">${userExists}</c:if>
                            <form:errors path="username" />
                        </span>
                    </label>
                    <form:input id="username" path="username" type="text" placeholder="CoolUser55" />
                </div>
                
                <div class="form-input">
                    <label for="email">
                        Email
                        <span class="help-block error">
                            <c:if test="${not empty emailExists}">${emailExists}</c:if>
                            <form:errors path="email" />
                        </span>
                    </label>
                    <form:input id="email" path="email" type="text" placeholder="you@example.com" />
                </div>
                
                <div class="form-input">
                    <label for="password">
                        Current Password
                        <span class="help-block hint">
                            required to change password
                        </span>
                        <span class="help-block error">
                            <form:errors path="password" />
                        </span>
                    </label>
                    <form:input id="password" path="password" type="password" />
                </div>
                
                <div class="form-input">
                    <label for="newPassword">
                        New Password
                        <span class="help-block hint">
                            leave blank to keep current password
                        </span>
                        <span class="help-block error">
                            <form:errors path="newPassword" />
                        </span>
                    </label>
                    <form:input id="newPassword" path="newPassword" type="password" />
                </div>
            </div>
            <div class="tile-12">
                <div class="form-input form-submit no-margin">
                    <button type="submit">Update</button>
                </div>
            </div>
        </form:form>
    </div>
</div>
        
</tiles:putAttribute>
</tiles:insertDefinition>