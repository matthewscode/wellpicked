<div class="container" data-ng-init="init();">
	<div class="tournament-teams-container">
		<div class="box-inner">
			<div class="team-list">
				<div class="team-box-small"
					ng-repeat="team in selectedTournament.teamList"
					style="background-image: url(resources/images/teams/logos/{{ team.teamSlug }}.png);">
				</div>
			</div>
		</div>
	</div>
	<div class="tournament-desc-container">
		<div class="box-inner">{{ selectedTournament.desc }}</div>
	</div>
	<div class="tournament-bracket-container">
		<div class="box-inner">
			<div ng-include="'brackets/' + selectedTournament.template + '.jsp'">
			</div>
		</div>
	</div>
</div>