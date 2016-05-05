<div class="container" data-ng-init="init();">
<!-- 	<div class="tournament-banner-container" style="background-image: url(resources/images/tournaments/{{ selectedTournament.slug }}.png);"> -->
<!-- 	</div> -->
	<div class="tournament-bracket-container">
		<div ng-include="'brackets/' + selectedTournament.template + '.jsp'">
		</div>
	</div>
</div>