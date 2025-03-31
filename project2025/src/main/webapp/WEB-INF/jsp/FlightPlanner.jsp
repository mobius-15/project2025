<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="aircrafts.*" %>
<% FlightPlan2 plan = (FlightPlan2) session.getAttribute("flightplan");%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlightPlanner</title>
</head>
<body>
 <h2>Mission:<c:out value="${sessionScope.missionType}" /></h2>
 <h3>Details</h3>
 <ul>
 <li>Aircraft:<c:out value="${fa18f.name}"/></li>
 <li>Type:<c:out value="${fa18f.type}"/></li>
 <li>Engines: <c:out value="${fa18f.nEngine}"/></li>
 <li>Combat Range: <c:out value="${fa18f.combatRange}"/> nm</li>
 <li>Ferry Range: <c:out value="${fa18f.ferryRange}"/> nm</li>
 <li>Ceiling Altitude: <c:out value="${fa18f.ceilingAlt}"/> ft</li>
 <li>Max Thrust: <c:out value="${fa18f.milThrust}"/> N</li>
 <li>Weaponized: <c:out value="${fa18f.weaponized ? 'YES':'NO' }" /></li>
 </ul>
 <h3>FlightPlan</h3>
     <table border="1" width="500px">
        <tr>
            <th>WP</th>
            <th>Altitude (feet)</th>
            <th>TAS (knots)</th>
            <th>Mach</th>
            <th>Distance (nm)</th>
            <th>Heading (°)</th>
            <th>Flight Time</th>
            <th>Fuel Used</th>
            <th>CAP</th>
        </tr>
    <c:forEach var="wp" items="${waypoints}" varStatus="status" >          
        <tr>
            <td>${status.index + 1}</td>
            <td>${wp.altitude}</td>
            <td>${wp.speed}</td>
           <td><fmt:formatNumber value="${wp.mach}" maxFractionDigits="2" /></td>
            <td>${wp.distance}</td>
            <td>${wp.heading}</td>
         <td><fmt:formatNumber value="${(wp.segmentTime / 60)}" maxFractionDigits="1" /> min</td>
         <td><fmt:formatNumber value="${segmentFuelList[status.index]}" maxFractionDigits="1" /> lb</td>
	<td><c:if test="${capIndex == status.index}"> CAP</c:if></td>            
        </tr>
        </c:forEach>
    </table>

  <h3>Squadron:VFA-102</h3>
  <table border="1">
  <tr>
  <th>Aircraft_Modex</th>
  <th>Aircraft_Type</th>
  <th>Fuel(lb)</th>
  </tr>
  <c:forEach var="entry" items="${squadron.entrySet()}" >
  <tr>
  <td>${entry.key}</td>
  <td>${entry.value.name}</td>
  <td><c:out value="${entry.value.intFuel+fa18f.extFuel*2}"/>lb</td>
  </tr>
  </c:forEach>
  </table> 
<table>
<c:if test="${not empty capIndex && not empty capDuration}">
  <p><strong>CAP executed at WP ${capIndex + 1} for ${capDuration} minutes</strong></p>
</c:if>	
</table>
<h3>Total Flight Time</h3>
<p><fmt:formatNumber value="${totalFlightTime / 60}" maxFractionDigits="1" /> minutes</p>  
    <h3>Remaining Fuel ${fa18f.modex}</h3>
<p>Initial Fuel: <c:out value="${fa18f.intFuel+(fa18f.extFuel * 2)}"/> lb</p>
<p>Final Fuel: <fmt:formatNumber value="${fa18f.remainingFuel}" maxFractionDigits="1" />  lb</p>
<form action="missionConfig" method="post">
    <button type="submit">Set Home Carrier</button>
</form>
</body>
</html>