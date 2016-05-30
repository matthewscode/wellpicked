<div class="container" data-ng-init="init();">
	<div class="news-not-selected"  ng-repeat="news in dNews" data-ng-class="{'selected' : selectedNewsId == news.newsId}">
	 	<div class="news-box-big">
	 		<div class="news-image-box" style="background-image: url(resources/images/news/{{ news.slug }}.png);"></div>
	 		<div class="news-text-box">
		 		<div class="box-inner" style="text-align: left;">
		 			<h1>{{ news.newsTitle }}</h1>
		 			<p>HELLO JOHN</p>{{ news.newsText }}
		 		</div>
	 		</div>
	 	</div>
	</div>
	<div class="news-list-container">
		<div class="news-list">
			<div class="news-box-small"
				ng-repeat="news in dNews" ng-click="$parent.selectedNewsId = news.newsId"
				style="background-image: url(resources/images/news/{{ news.slug }}.png);" >
			</div>
		</div>
	</div>
</div>