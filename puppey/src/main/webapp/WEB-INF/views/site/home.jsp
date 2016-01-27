<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="bodyStructure" value="fluid" />
	<tiles:putAttribute name="pageBackground" value="" />
	<tiles:putAttribute name="body">

		<div class="container" style="height: 100%;">
			<div class="wrapper">
				<div class="home-banner"
					style="background-image: url(<c:url value="/resources/images/news/shanghai-art.jpg" />)">
					<div
						style="width: 520px; height:225px; left: 5%; top: 5%; position: absolute; background-image: url(<c:url value="/resources/images/news/shanghai-logo.png" />); z-index: 101;">
					</div>
					<div
						style="width: 560px; height: 225px; left: 5%; top: 5%; position: absolute; background: rgba(0, 0, 0, 0.5); background-opacity: 0.5; z-index: 5;">
					</div>
					<div class="homeBar"></div>
				</div>
				<div class="home-bot-wrapper">
					<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-1.png" />)">
						</div>
						<div class="homeBoxOverlay">
							<div class="homeBoxTitle">
								<h1>STARLADDER</h1>
								

							</div>
						</div>
					</div>
					<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-2.png" />)">
						</div>
						<div class="homeBoxOverlay">
							<div class="homeBoxTitle">
								<h1>THE SUMMIT</h1><br/>
								

							</div>
						</div>
					</div>
										<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-3.png" />)">
						</div>
						<div class="homeBoxOverlay">
							<div class="homeBoxTitle">
								<h1>FRANKFURT</h1>
								

							</div>
						</div>
					</div>
					<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-4.png" />)">
						</div>
						<div class="homeBoxOverlay">
							<div class="homeBoxTitle">
								<h1>THE INTERNATIONAL</h1><br/>
								

							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
