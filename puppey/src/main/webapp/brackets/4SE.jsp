<style>
	.team-box {
		float: left;
		height: 100%;
		width: 20%;
	} 
	.single-box {
		box-sizing: border-box;
		height: 50%;
		width: 100%;
		padding: 2px;
		font-size: 18px;
		color: #EFF4FF;
		font-weight: 700;
		line-height: 300%;
	}
	.team-title {
		width: 80%;
		padding: 2px;
	}
	.full-matchup {
		background: rgba(65,68,76,0.3);
		position: absolute;
	}
</style>
<div class="bracket-4SE">
	
<!-- lines -->
	<div style="position: absolute; top: 30%; left: 4%; background: #41444C; height: 1px; width: 44.1%;">
	</div>
	<div style="position: absolute; bottom: 30%; left: 4%; background: #41444C; height: 1px; width: 44.1%;">
	</div>
	<div style="position: absolute; bottom: 30%; left: 48%; background: #41444C; height: 40%; width: 1px;">
	</div>
	<div style="position: absolute; top: 50%; left: 48%; background: #41444C; height: 1px; width: 44.1%;">
	</div>
	
	
	<div   class="full-matchup" style="left: 4%; top: 20%; height: 20%; width: 40%;">
		<div class="single-box">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[0].team1Slug }}.png)"></div>
			<div style="width: 80%; padding: 2px;"> {{ selectedTournament.matchupList[0].team1Name }} </div>
		</div>
		<div  class="single-box">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[0].team2Slug }}.png)"></div>
			<div class="team-title"> {{ selectedTournament.matchupList[0].team2Name }} </div>
		</div>
	</div>
	<div class="full-matchup" style="left: 4%; bottom: 20%; height: 20%; width: 40%;">
		<div class="single-box">
				<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[1].team1Slug }}.png)"></div>
				<div class="team-title"> {{ selectedTournament.matchupList[1].team1Name }} </div>
			</div>
		<div class="single-box">
			<div class="team-box-small team-box" style="background-image: url(resources/images/teams/logos/{{ selectedTournament.matchupList[1].team2Slug }}.png)"></div>
			<div  class="team-title"> {{ selectedTournament.matchupList[1].team2Name }} </div>
		</div>	
	</div>
	<div  class="full-matchup" style="left: 52%; top: 40%; height: 20%; width: 40%;">	
	</div>
	

</div>