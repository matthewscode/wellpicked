<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/ttapi.css" />">
<script src="//cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.16/angular.min.js"></script>
<script src="<c:url value="/resources/js/ttapp.js" />"></script> 
</head>
<body data-ng-app="ttApp">
<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/ft/all" />')">
  <div data-ng-show="data">
  	<div data-ng-repeat="ft in data" class="ft-list">
  		<div class="ft-box">
  			<img src="{{ ft.originUrl }}" class="ft-list-image"/>
  		</div>
  	</div>
  </div>
</div>
</body>
</html>