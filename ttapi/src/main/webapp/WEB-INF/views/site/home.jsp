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
<div class="ft-wrapper">
<div class="ft-top-nav">
<div class="title">mp.com</div>
</div>
<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/ft/all" />')">
  <div data-ng-show="data">
  	<div data-ng-repeat="ft in data" class="ft-list">
  	<div ng-controller="FileController">
  		<div class="ft-box" ng-click="showDetails = !showDetails">
  			<img src="{{ ft.originUrl }}" class="ft-list-image"/>
  		</div>
  		<div class="ft-detail-box" ng-show="showDetails" ng-click="showDetails = !showDetails">
  			<div class="ft-detail-image">
  				<div class="ft-detail-image-top">
  				<img src="{{ ft.originUrl }}" class="ft-list-image" />
  				</div>
  				<div class="ft-detail-image-bot">
  				details
  				</div>
  			</div>
  			<div class="ft-detail-detail">
  				checksum: etc etc
  			</div>
  		</div>
  		
  	</div>
  	</div>
  </div>
</div>
</div>
<div class="ft-bottom">
mp
</div>
</body>
</html>