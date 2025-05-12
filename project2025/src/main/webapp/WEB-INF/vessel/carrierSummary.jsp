<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="carrierOps.*,aircrafts.*,flightplan.*,controller.*,logic.*,mission.*" %>
<% Carrier carrier =(Carrier)session.getAttribute("carrier");
FlightPlan2 plan =(FlightPlan2)session.getAttribute("flightplan");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>Home Carrier</h3>
<c:if test="${not empty carrier }" >
<ul>
  <li>Name: <c:out value="${carrier.name}" /></li>
  <li>Position: <c:out value="${carrier.latitude}"/>/<c:out value=" ${carrier.longitude}" /></li>
  <li>Speed: <c:out value="${carrier.speed}" /> knots</li>
  <li>Displacement(Full): <c:out value="${carrier.fullDisplacement}"/> tons </li>
</ul>
</c:if>
  <h3>Flight Plan Departure Check</h3>
  <c:if test="${not empty sessionScope.plan and not empty sessionScope.plan.waypoints}">
    <p>Start Point:<br>
      Altitude: <c:out value="${plan.waypoints[0].altitude}" /> ft<br>
      Speed: <c:out value="${plan.waypoints[0].speed}" /> kt<br>
      Location: Lat <c:out value="${plan.waypoints[0].latitude}" />, Lon <c:out value="${plan.waypoints[0].longitude}" />
    </p>
  </c:if>
  <c:if test="${not empty sessionScope.flightplan and not empty sessionScope.flightplan.waypoints}">
  <form action="CarrierInfo" method="post">
  <input type="hidden" name="carrierName" value="${carrier.name}">
  <input type="hidden" name="lat" value="${carrier.latitude}" />
  <input type="hidden" name="lon" value="${carrier.longitude}" />
  <input type="hidden" name="speed" value="${carrier.speed}" />
  <input type="hidden" name="displacement" value="${carrier.fullDisplacement}" />
  <input type="hidden" name="action" value="confirmMission" />
    <button type="submit">View Full Mission Summary</button>
  </form>
</c:if>

  <form action="TestPlan" method="get">
    <button type="submit">Return to Mission Planning</button>
    <input type="hidden" name="action" value="returned">
  </form>
  
</body>
</html>