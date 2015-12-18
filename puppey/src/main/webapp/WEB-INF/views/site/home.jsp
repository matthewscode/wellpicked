<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<tiles:insertDefinition name="defaultTemplate">
<tiles:putAttribute name="bodyStructure" value="fluid" />
<tiles:putAttribute name="pageBackground" value="" />
<tiles:putAttribute name="body">

<div class="container" style="height: 100%;">
    <div class="wrapper">
        <div id="homeBanner" style="background-image: url(http://localhost:8080/puppey/resources/images/20150609/frankfurt_news.jpg)">
            <div id="homeBar"><h5>WELCOME</h5></div>
        </div>
        <div id="homeBotWrapper">
            <div class="homeBotBox" style="background-image: url(http://localhost:8080/puppey/resources/images/20150609/news1.png)">

            </div>
            <div class="homeBotBox" style="background-image: url(http://localhost:8080/puppey/resources/images/20150609/news2.png)">

            </div>
            <div class="homeBotBox" style="background-image: url(http://localhost:8080/puppey/resources/images/20150609/news3.png)">

            </div>
            <div class="homeBotBox" style="background-image: url(http://localhost:8080/puppey/resources/images/20150609/news4.png)">

            </div>
        </div>
    </div>
</div>

<!-- <!-- HOMEPAGE GRID --> 
<!-- <div class="wrapper tiles"> -->
<!--     <div class="grid"> -->

<!--         ROW 1 -->
<!--         <div class="tile-12 container"> -->
<!--             <div class="grid-item tile-8 tile-6h linked image"> -->
<!--                 <div class="wrapper"> -->
<%--                     <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/frankfurt_news.jpg" />"></div> --%>
<!--                     <div class="wrapper"> -->
<!--                         <div class="content"> -->
                            
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
            
<!--             <div class="tile-4 container"> -->
<!--                 <div class="grid-item tile-6 tile-3h color-1 linked iconed"> -->
<!--                     <div class="wrapper"> -->
<!--                         <div class="content"> -->
<!--                             <i class="icon">&#xE53E;</i> -->
<!--                             <h3>1,487 Points</h3> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
                
<!--                 <div class="grid-item tile-6 tile-3h color-2 linked iconed"> -->
<!--                     <div class="wrapper"> -->
<!--                         <div class="content"> -->
<!--                             <i class="icon">&#xE7F4;</i> -->
<!--                             <h4>Don't forget to submit your bracket for League Awesome.</h4> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
                
                
        
<!--                 <div class="grid-item tile-12 tile-3h linked image"> -->
<!--                     <div class="wrapper"> -->
<%--                         <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/collectorscache.jpg" />"></div> --%>
<!--                         <div class="wrapper"> -->
<!--                             <div class="content"> -->
<!--                                 <h2 class="title">Collector's Cache</h2> -->
<!--                                 <h4 class="subtitle"> -->
<!--                                     <p>Available for purchase exclusively to Compendium owners, the International 2015 Collector's Cache costs $1.99.</p> -->
<!--                                 </h4> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         /ROW 1 -->
        
<!--         ROW 2 -->
<!--         <div class="tile-12 container"> -->
<!--             <div class="tile-6 container"> -->
<!--                 <div class="grid-item tile-12 tile-3h linked content"> -->
<!--                     <div class="wrapper"> -->
<!--                         <h2 class="title">New Music Festival Just Large Empty Field To Do Drugs In</h2> -->
<!--                         <h4 class="subtitle"> -->
<!--                             <p>Declaring the event a rousing success so far, organizers confirmed more than 45,000 people turned out Wednesday for the first annual Cavalcade Folk and Roots Festival, a four-day gathering that consists solely of a big empty field to do drugs in.</p> -->
<!--                         </h4> -->
<!--                     </div> -->
<!--                 </div> -->
                
<!--                 <div class="grid-item tile-6 tile-3h color-3 linked content"> -->
<!--                     <div class="wrapper"> -->
<!--                         <h2 class="title">Clinton Loomis @FearDotA</h2> -->
<!--                         <h4 class="subtitle"> -->
<!--                             <p>"getting 7k on us servers was the most frustrating experience of my life. 1/10 would not do again."</p> -->
<!--                         </h4> -->
<!--                     </div> -->
<!--                 </div> -->
                
<!--                 <div class="grid-item tile-6 tile-3h color-3 linked image"> -->
<!--                     <div class="wrapper"> -->
<%--                         <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/monster.jpg" />"></div> --%>
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="tile-4 container"> -->
<!--                 <div class="grid-item tile-12 tile-6h linked image"> -->
<!--                     <div class="wrapper"> -->
<%--                         <div class="background" data-ng-bg-image="<c:url value="/resources/images/20150609/windrunner.jpg" />"></div> --%>
<!--                         <div class="wrapper"> -->
<!--                             <div class="content"> -->
<!--                                 <h2 class="title">Vestibulum pellentesque iaculis finibus</h2> -->
<!--                                 <h4 class="subtitle"> -->
<!--                                     <p>Nullam neque urna, faucibus sed urna quis, iaculis bibendum est. Quisque vitae metus mauris. Nam luctus dolor nec blandit vestibulum.</p> -->
<!--                                 </h4> -->
<!--                             </div> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="tile-2 container"> -->
<!--                 <div class="grid-item tile-12 tile-6h color-1 linked content"> -->
<!--                     <div class="wrapper"> -->
<!--                         <h2 class="title">Top Players</h2> -->
<!--                         <div> -->
<!--                             <ol> -->
<!--                                 <li>Player 1</li> -->
<!--                                 <li>Player 2</li> -->
<!--                                 <li>Player 3</li> -->
<!--                                 <li>Player 4</li> -->
<!--                                 <li>Player 5</li> -->
<!--                                 <li>Player 6</li> -->
<!--                                 <li>Player 7</li> -->
<!--                                 <li>Player 8</li> -->
<!--                                 <li>Player 9</li> -->
<!--                                 <li>Player 10</li> -->
<!--                             </ol> -->
<!--                         </div> -->
<!--                     </div> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--         /ROW 2 -->
<!--     </div> -->
<!-- </div> -->
<!-- <!-- /HOMEPAGE GRID -->

</tiles:putAttribute>
</tiles:insertDefinition>
