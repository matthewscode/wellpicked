<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<tiles:insertDefinition name="main">
<tiles:putAttribute name="pageTitle" value="Admin - Add Achievement" />
<tiles:putAttribute name="pageId" value="admin achievement add" />
<tiles:putAttribute name="body">

    <tiles:insertDefinition name="admin">
        <tiles:putAttribute name="admin.body">
        
    		<form:form modelAttribute="achievement" class="add-achievement-form" enctype="multipart/form-data">
    			
                <div class="tile-6">
                    <div class="form-input">
                        <label for="achievementName">
                            Name
                            <span class="help-block error">
                                <form:errors path="achievementName" />
                            </span>
                        </label>
                        <form:input id="achievementName" path="achievementName" type="text" placeholder="Name" />
                        <form:input path="reward" type="text" placeholder="1000" />
                        <form:input id="description" path="description" type="text" placeholder="Description" />
                    </div>
                </div>
                <div class="tile-12">
                    <div class="form-input form-submit no-margin">
                        <button type="submit">Add Achievement</button>
                    </div>
                </div>
    		</form:form>
        
		</tiles:putAttribute>
    </tiles:insertDefinition>
    
</tiles:putAttribute>
</tiles:insertDefinition>
