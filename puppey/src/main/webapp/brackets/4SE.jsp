<style>
	.team-box {
		margin-top: 0.5%;
		float: left;
		height: 92%;
		width: 20%;
	} 
	.single-box {
		padding: 5px;
		box-sizing: border-box;
		height: 46%;
		width: 100%;
		padding: 2px;
		font-size: 18px;
		color: #EFF4FF;
		font-weight: 700;
		line-height: 45px;
		border: 1px solid #41444C;
		background: rgba(239,244,255,0.5);	
	}
	.team-title {
		width: 80%;
		padding: 2px;
	}
	.full-matchup {
		position: absolute;
		box-sizing: border-box;
	}
	.spacer {
		height: 1px;
		width: 100%;
	}

	.h-line{
		background: #41444C;
		position: absolute;
		height: 1px;
		z-index: 20;
	}
	.v-line{
		background: #41444C;
		position: absolute;
		width: 1px;
		z-index: 20;
	}
</style>
<div class="bracket-4SE">
	
<!-- lines -->
	<!-- R1M1 to GF -->
	<div class="h-line" style="top: 19.0%; left: 44.4%; width: 3.6%;"></div>
	<div class="v-line" style="top: 19.0%; left: 48%; height: 10.4%;"></div>
	<div class="h-line" style="top: 29.5%; left: 48%; width: 3.6%;"></div>
	
	<!-- R1M2 to GF -->
	<div class="h-line" style="top: 49.0%; left: 44.4%; width: 3.6%;"></div>
	<div class="v-line" style="top: 39.0%; left: 48%; height: 10.2%;"></div>
	<div class="h-line" style="top: 39%; left: 48%; width: 3.6%;"></div>
<!-- end lines -->
	
	<div   class="full-matchup" style="left: 4%; top: 10%; height: 20%; width: 40%;">
		<div class="single-box" style="background: rgba({{ selectedTournament.matchupList[0].team1Color }},0.5); border-color: rgb({{ selectedTournament.matchupList[0].team1Color }});">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[0].team1Slug }}.png)"></div>
			<div style="width: 80%; padding: 2px;"> {{ selectedTournament.matchupList[0].team1Name }} </div>
		</div>
		<div class="spacer"></div>
		<div  class="single-box" style="background: rgba({{ selectedTournament.matchupList[0].team2Color }},0.5); border-color: rgb({{ selectedTournament.matchupList[0].team2Color }});">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[0].team2Slug }}.png)"></div>
			<div class="team-title"> {{ selectedTournament.matchupList[0].team2Name }} </div>
		</div>
	</div>
	<div class="full-matchup" style="left: 4%; top: 40%; height: 20%; width: 40%;">
		<div class="single-box" style="background: rgba({{ selectedTournament.matchupList[1].team1Color }},0.5); border-color: rgb({{ selectedTournament.matchupList[1].team1Color }});">
				<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[1].team1Slug }}.png)"></div>
				<div class="team-title"> {{ selectedTournament.matchupList[1].team1Name }} </div>
			</div>
			<div class="spacer"></div>
		<div class="single-box"  style="background: rgba({{ selectedTournament.matchupList[1].team2Color }},0.5); border-color: rgb({{ selectedTournament.matchupList[1].team2Color }});">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[1].team2Slug }}.png)"></div>
			<div  class="team-title"> {{ selectedTournament.matchupList[1].team2Name }} </div>
		</div>	
	</div>
	<div  class="full-matchup" style="left: 52%; top: 25%; height: 20%; width: 40%;">
		<div class="single-box">
				
			</div>
			<div class="spacer"></div>
		<div class="single-box">
			
		</div>	
	</div>
	

</div>