<!-- MissionReview.jsp -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="java.util.*,controller.*,carrierOps.*,aircrafts.*,mission.*,flightplan.*,logic.*" %>
<%
MissionContext ctx = (MissionContext) session.getAttribute("ctx");
List<Target> targets = ctx != null ? ctx.getTargetPoints() : new ArrayList<>();
if (targets.isEmpty()) {
    out.println("No targets have been set.");
} else {
    for (Target target : targets) {
%>
        <div>
            <p>Target Name: <%= target.getName() %></p>
            <p>Latitude: <%= target.getLat() %></p>
            <p>Longitude: <%= target.getLon() %></p>
        </div>
<%
    }
}
%>

<c:if test="${empty sessionScope.ctx}">
  <p><strong>Error:</strong> Mission context is not available. Please return to planning.</p>
  <a href="index.jsp">Back to Start</a>
</c:if>
<c:if test="${not empty sessionScope.ctx}">
  <!-- ミッションの詳細を表示 -->
</c:if>

<html>
<head><title>Mission Review</title></head>
<body>
<h2>Mission Summary</h2>

<ul>
  <li>Mission Type: <c:out value="${ctx.missionType}" /></li>
  <li>Aircraft: <c:out value="${ctx.aircraft.name}" /></li>
  <li>Carrier: <c:out value="${ctx.carrier.name}" default="Not Assigned" /></li>
  <li>Position: <fmt:formatNumber value="${ctx.carrier.latitude}" pattern="0.######" />
  				<fmt:formatNumber value="${ctx.carrier.longitude}" pattern="0.######" />
  <li>MovingSpeed: ${ctx.carrier.speed} kt</li>
  <li>Flight Time: <fmt:formatNumber value="${ctx.totalFlightTime}" maxFractionDigits="1" /> min</li>
</ul>   

  <h3>Flight Plan Summary</h3>
  <table border="1">
    <tr><th>WP</th><th>Alt</th><th>TAS</th><th>Mach</th><th>Distance</th><th>Heading</th><th>Time</th><th>Fuel</th></tr>
 <c:forEach var="wp" items="${ctx.flightPlan.waypoints}" varStatus="status">
  <tr>
    <td>${status.index + 1}</td>
    <td>${wp.altitude}</td>
    <td>${wp.speed}</td>
    <td>${wp.mach}</td>
    <td>${wp.distance}</td>
    <td>${wp.heading}</td>
    <td><fmt:formatNumber value="${wp.segmentTime}" maxFractionDigits="1" /></td>
    <td><fmt:formatNumber value="${ctx.segmentFuelList[status.index]}" maxFractionDigits="0" /></td>
  </tr>
</c:forEach>
  </table>
  
<h3>Loadout Summary</h3>
<table border="1">
  <tr>
    <th>Station</th>
    <th>Weapon</th>
    <th>Weight (lb)</th>
    <th>Category</th>
  </tr>
  <c:forEach var="entry" items="${fa18f.stations.entrySet()}">
    <tr>
      <td>Station ${entry.key}</td>
      <td><c:out value="${entry.value.weapon.name}" default="NONE" /></td>
      <td><c:out value="${entry.value.weapon.weightlb}" default="-" /></td>
      <td><c:out value="${entry.value.weapon.category}" default="-" /></td>
    </tr>
  </c:forEach>
</table>
<form action="LoadoutServlet" method="get">
  <button type="submit">Configure Loadout</button>
</form>
<h4>Total Loadout Weight: <fmt:formatNumber value="${fa18f.loadoutWeight}" maxFractionDigits="1" /> lb</h4>

<h4>Gross Weight at Takeoff: <fmt:formatNumber value="${fa18f.fullWeight}" maxFractionDigits="1" /> lb</h4>
    <c:if test="${fa18f.loadoutWeight > 17000}">
  <p style="color: red; font-weight: bold;">
    ⚠️ WARNING: Loadout exceeds safe payload limit (17,000 lb)!
  </p>
</c:if>
 <h4>Estimated Landing Weight: <fmt:formatNumber value="${landingWeight}" maxFractionDigits="1" /> lb</h4> 
  <p><strong>Total Flight Time:</strong>
    <fmt:formatNumber value="${ctx.totalFlightTime}" maxFractionDigits="1" /> minutes</p>
    <form action="CarrierInfo" method="get">
  <input type="hidden" name="action" value="map" />
  <button type="submit"style="padding:10px 20px; font-size:16px;">>View on Map</button>
</form>

<h3>Wingman</h3>
<c:if test="${not empty ctx.wingmen}">
<ul>
<c:forEach var="wm" items="${ctx.wingmen }">
<li>Modex:<c:out value="${wm.modex}" /> -Type:<c:out value="${wm.name }" /> -Fuel:<c:out value="${wm.intFuel+wm.extFuel*2 }" />lb</li>
</c:forEach>
</ul>
</c:if>

<h3>Targets</h3>
<table border="1">
<tr><th>Name</th><th>Latitude</th><th>Longitude</th></tr>

<c:forEach var="tgt" items="${sessionScope.ctx.targetPoints}">
  <tr>
    <td>${tgt.name}</td>
    <td><fmt:formatNumber value="${tgt.lat}" maxFractionDigits="6" /></td>
    <td><fmt:formatNumber value="${tgt.lon}" maxFractionDigits="6" /></td>
  </tr>
</c:forEach>
<%= ((MissionContext) session.getAttribute("ctx")).getTargetPoints().size() %>
</table>

<h3>Flight Summary</h3>
<table border="1">
  <tr><th>Item</th><th>Value</th></tr>
  <tr><td>Initial Fuel</td><td><c:out value="${fa18f.intFuel + (fa18f.extFuel * 2)}" /> lb</td></tr>
  <tr><td>Final Fuel</td><td><fmt:formatNumber value="${fa18f.remainingFuel}" maxFractionDigits="1" /> lb</td></tr>
  <tr><td>Landing Weight</td><td><fmt:formatNumber value="${sessionScope.landingWeight}" maxFractionDigits="1" /> lb</td></tr>
  <tr><td>Adjusted Cruise Speed</td><td><c:out value="${sessionScope.adjustedCruiseSpeed}" /> kt</td></tr>
</table>
<form action="SaveServlet" method="post">
    <input type="hidden" name="action" value="saveDatabase"/>
    <button type="submit"style="padding:10px; margin:5px;">Save to Database</button>
</form>
<form action="SaveServlet" method="post">
    <input type="hidden" name="action" value="saveDatabase">
    <button type="submit"style="padding:10px; margin:5px;">Save to Database</button>
</form>

<form action="SaveServlet" method="post">
    <input type="hidden" name="action" value="exportJSON">
    <button type="submit"style="padding:10px; margin:5px;">Export JSON</button>
</form>
</form>
</body>
</html>