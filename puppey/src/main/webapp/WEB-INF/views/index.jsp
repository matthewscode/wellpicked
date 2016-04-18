<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href='https://fonts.googleapis.com/css?family=Roboto:400,700' rel='stylesheet' type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.0/less.min.js"></script>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/wpgg.css" />"></head>
<body data-ng-app="wpApp">
	<div id="nav-container" data-ng-controller="mainCtrl" data-ng-init="init('<c:url value="/api/tournament/list/latest/4" />', '<c:url value="/api/tournament/" />');">
		<a class="nav-logo" href="<c:url value="/" />">
		well<br/>
		picked<br/>
		.gg
		</a>
		<div class="nav-tournament" ng-repeat="entry in data" style="background-image: url(<c:url value="/resources/images/tournaments/{{ entry.tournamentSlug }}.jpg" />);">
		</div>
	</div>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
<script src="<c:url value="/resources/js/wp.js" />"></script> 
</body>
</html>