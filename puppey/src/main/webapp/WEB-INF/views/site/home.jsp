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
					style="background-image: url(<c:url value="/resources/images/news/manila-major.png" />)">
					<div class="home-bar"></div>
				</div>
				<div class="home-bot-wrapper">
					<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-1.png" />)">
						</div>
						<div class="home-box-overlay">
							<div class="homeBoxTitle">
								<h1>SHANGHAI</h1>
								

							</div>
						</div>
					</div>
					<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-2.png" />)">
						</div>
						<div class="home-box-overlay">
							<div class="homeBoxTitle">
								<h1>SUMMIT</h1><br/>
								

							</div>
						</div>
					</div>
										<div class="home-bot-box"
						style="position: relative; padding-top: 5%; text-align: center;">

						<div class="homeBoxNewsImage"
							style="background-image: url(<c:url value="/resources/images/news/news-3.png" />)">
						</div>
						<div class="home-box-overlay">
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
						<div class="home-box-overlay">
							<div class="homeBoxTitle">
								<h1>INTERNATIONAL</h1><br/>
								

							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>
