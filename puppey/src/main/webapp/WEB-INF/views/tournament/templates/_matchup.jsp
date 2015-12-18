<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="matchup matchup-${param.matchupType}">
    <div 
        class="team team-1"
        data-ng-class="{winner: matchups.${param.matchupType}.team1.winner <c:if test="${param.isEditable == 1}">, editable: matchups.${param.matchupType}.team1</c:if>}"
        <c:if test="${param.isEditable == 1}">data-ng-click="setWinner(matchups.${param.matchupType}, 1)"</c:if>
    >
        <span class="logo">
            <img data-ng-show="matchups.${param.matchupType}.team1" data-ng-src="<c:url value="/resources/images/teams/logos/{{matchups.${param.matchupType}.team1.teamSlug}}.png" />">
        </span>
        <span class="name">{{matchups.${param.matchupType}.team1.teamName}}</span>
        <span class="icon" data-ng-show="matchups.${param.matchupType}.team1.winner">${empty param.isFinal ? '&#61751;' : '&#61958;'}</span>
    </div>
    <div 
        class="team team-2" 
        data-ng-class="{winner: matchups.${param.matchupType}.team2.winner <c:if test="${param.isEditable == 1}">, editable: matchups.${param.matchupType}.team2</c:if>}"
        <c:if test="${param.isEditable == 1}">data-ng-click="setWinner(matchups.${param.matchupType}, 2)"</c:if>
    >
        <span class="logo">
            <img data-ng-show="matchups.${param.matchupType}.team2" data-ng-src="<c:url value="/resources/images/teams/logos/{{matchups.${param.matchupType}.team2.teamSlug}}.png" />">
        </span>
        <span class="name">{{matchups.${param.matchupType}.team2.teamName}}</span>
        <span class="icon" data-ng-show="matchups.${param.matchupType}.team2.winner">${empty param.isFinal ? '&#61751;' : '&#61958;'}</span>
    </div>
</div>