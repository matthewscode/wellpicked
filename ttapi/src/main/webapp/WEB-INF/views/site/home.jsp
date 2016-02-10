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

<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.5.0/textAngular.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.5.0/textAngular-rangy.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.5.0/textAngular-sanitize.min.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/textAngular/1.5.0/textAngular.css">
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css">

</head>
<body data-ng-app="ttApp">
<div class="ft-wrapper">
<div class="ft-top-nav">
	<div class="ft-top-logo"></div>
	<div class="title">WORKBENCH</div>
</div>
<div data-ng-controller="ApiController" data-ng-init="init('<c:url value="/api/ft/start/0/end/25" />')" class="ft-container">
  <div ng-controller="FileController">
	<div class="ft-work-box" ng-show="showEditor">
		<div class="ft-work-image-box">
			<img src="{{ imgUrl }}" class="ft-work-image" ng-click="showEditor = false">
		</div>
		<div class="ft-work-editors">
		 <div class="ft-detail-transcription">
			<textarea data-ng-model="transcriptionText" data-ck-editor></textarea>
			<div class="button-full" ng-click="submitTranscription(transcriptionText, imageChecksumId)">TRANSCRIPTION</div>
		</div>
		<div class="ft-detail-translation">
			<textarea data-ng-model="ft.translation" data-ck-editor></textarea>
			<div class="button-full">TRANSLATION</div>
	  	</div>
		</div>
	</div>
  <div data-ng-show="data" class="ft-list">
  	<div data-ng-repeat="ft in data" class="ft-box" ng-click="setEditor(ft.originUrl, ft.id)">

  		<div class="ft-overlay"></div>

	  	<div ng-click="showDetails = !showDetails" class="ft-box-wrapper">
  			<img ng-src="{{ ft.originUrl }}" class="ft-list-image"/>
	  	</div>
  	</div>

  </div>
</div>

<div class="ft-bottom">
	<center><button ng-click="loadMore(25)">load more</button></center>
</div>
</div>
</div>
</body>
</html>