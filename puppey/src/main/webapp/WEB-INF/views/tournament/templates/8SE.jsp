<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 8 Team Single Elimination -->
<div class="bracket type-8SE">
    <div class="round round-1">
        <div class="wrapper">
            <div class="header"><span>Round 1</span></div>
            <div class="matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R1M1" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R1M2" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R1M3" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R1M4" />
                </jsp:include>
            </div>
        </div>
    </div>
    
    <div class="round round-2">
        <div class="wrapper">
            <div class="header"><span>Round 2</span></div>
            <div class="matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R2M1" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="R2M2" />
                </jsp:include>
            </div>
        </div>
    </div>
    
    <div class="round round-3 round-gf">
        <div class="wrapper">
            <div class="header"><span>Grand Finals</span></div>
            <div class="matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="GF" />
                    <jsp:param name="isFinal" value="true" />
                </jsp:include>
            </div>
        </div>
    </div>
</div>