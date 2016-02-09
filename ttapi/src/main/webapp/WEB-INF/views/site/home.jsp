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
<script src="resources/js/ttapp.js"></script> 
<script src="resources/js/ckeditor/ckeditor.js"></script>
</head>
<body data-ng-app="ttApp">
<div class="ft-wrapper">
<div class="ft-top-nav">
<div class="title">mp.com</div>
</div>
<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/ft/all" />')" class="ft-container">
  <div data-ng-show="data" class="ft-list">
  	<div data-ng-repeat="ft in data" class="ft-box">
	  	<div ng-controller="FileController" ng-click="showDetails = !showDetails" class="ft-box-wrapper">
  			<img ng-src="{{ ft.originUrl }}" class="ft-list-image"/>
	  		<div class="ft-detail-box" ng-show="showDetails" ng-click="showDetails = !showDetails">
	  			<div class="ft-detail-image">
	  				<div class="ft-detail-image-top">
	  				<img ng-src="{{ ft.originUrl }}" class="ft-list-image" />
	  				</div>
	  				<div class="ft-detail-image-bot">
	  				<span>Checksum: {{ ft.checksum }}</span><br/>
	  				<span>URL: {{ ft.originUrl }}</span>
	  				</div>
	  			</div>
	  			<div class="ft-detail-detail">
		  			<div class="ft-detail-transcription">
						<textarea data-ng-model="ft.transcription" data-ck-editor></textarea>
					</div>
					<div class="ft-detail-translation">
						<textarea data-ng-model="ft.translation" data-ck-editor></textarea>
					</div>
	  			</div>
	  		</div>
	  	</div>
  	</div>
  	<button ng-click="loadMore('<c:url value="/api/ft/all" />')">load more</button>
  </div>
</div>
</div>
<div class="ft-bottom">
mp
</div>
</body>
</html>