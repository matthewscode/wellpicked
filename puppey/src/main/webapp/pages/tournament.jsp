<div class="container" data-ng-init="init();">
	<div class="tournament-info" style="background-image: url(resources/images/tournaments/{{ selectedTournament.slug }}.png)">
		</div>
	<div class="tournament-options">
	<a href="#/bracket/create/{{ selectedTournament.slug }}" class="create-bracket-button">Create Bracket</a>
	<div class="tournament-general">{{selectedTournament.info}}</div>
	</div>
	<div class="tournament-bracket-container">
		<div ng-include="'brackets/' + selectedTournament.template + '.jsp'"></div>
	</div>
</div>