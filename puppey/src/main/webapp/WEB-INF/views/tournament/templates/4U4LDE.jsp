<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 4 Upper / 4 Lower Double Elimination -->
<div class="bracket type-4U4LDE">
    <div class="round round-1">
        <div class="wrapper">
            <div class="lower matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR1M1" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR1M2" />
                </jsp:include>
            </div>
        </div>
    </div>
    <div class="round round-2">
        <div class="wrapper">
            <div class="header"><span>Round 1</span></div>
            <div class="matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="UR1M1" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="UR1M2" />
                </jsp:include>
            </div>
            <div class="lower matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR2M1" />
                </jsp:include>
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR2M2" />
                </jsp:include>
            </div>
        </div>
    </div>
    <div class="round round-3">
        <div class="wrapper">
            <div class="lower matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR3M1" />
                </jsp:include>
            </div>
        </div>
    </div>
    <div class="round round-4">
        <div class="wrapper">
            <div class="header"><span>Round 2</span></div>
            <div class="matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="UR2M1" />
                </jsp:include>
            </div>
            <div class="lower matchups">
                <jsp:include page="_matchup.jsp" >
                    <jsp:param name="matchupType" value="LR4M1" />
                </jsp:include>
            </div>
        </div>
    </div>
    <div class="round round-5">
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