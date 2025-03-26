<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="aircrafts.*" %>
<% List<Waypoint>waypoints=(List<Waypoint>)request.getAttribute("waypoints");
	TreeMap<String,FA_18F> squadron=(TreeMap<String,FA_18F>)request.getAttribute("squadron");
	List<Double> segmentFuelList = (List<Double>) request.getAttribute("segmentFuelList");
  FA_18F fa18f=(FA_18F)session.getAttribute("fa18f");
  String missionType = (String)session.getAttribute("missionType");
  double totalFlightTime=(double)request.getAttribute("totalFlightTime");
    Integer capIndex = (Integer) request.getAttribute("capIndex");
  Integer capDuration = (Integer) request.getAttribute("capDuration");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FlightPlanner</title>
</head>
<body>
 <h2>Mission:<%= missionType %></h2>
 <h3>Details</h3>
 <ul>
 <li>Aircraft:<%= fa18f.getName() %></li>
 <li>Type:<%= fa18f.getType() %></li>
 <li>Engines: <%=  fa18f.getNEngine() %></li>
 <li>Combat Range: <%=  fa18f.getCombatRange() %> nm</li>
 <li>Ferry Range: <%=  fa18f.getFerryRange() %> nm</li>
 <li>Ceiling Altitude: <%=  fa18f.getCeilingAlt() %> ft</li>
 <li>Max Thrust: <%=  fa18f.getMilThrust() %> N</li>
 <li>Weaponized: <%=  fa18f.isWeaponized() ? "Yes" : "No" %></li>
 </ul>
 <h3>FlightPlan</h3>
     <table border="1" width="500px">
        <tr>
            <th>WP</th>
            <th>Altitude (feet)</th>
            <th>TAS (knots)</th>
            <th>Distance (nm)</th>
            <th>Heading (°)</th>
            <th>Flight Time</th>
            <th>Fuel Used</th>
            <th>CAP</th>
        </tr>
      <%  for (int i = 0; i < waypoints.size(); i++) { 
          Waypoint wp = waypoints.get(i);
          double fuel = segmentFuelList.get(i);
          int minutes =(int)(wp.getSegmentTime()/60);
          int seconds =(int)(wp.getSegmentTime()%60);
          %>
        <tr>
            <td><%= i + 1 %></td>
            <td><%= wp.getAltitude() %></td>
            <td><%= wp.getSpeed() %></td>
            <td><%= wp.getDistance() %></td>
            <td><%= wp.getHeading() %></td>
            <td><%= minutes %> min<%= seconds %> sec</td>
            <td><%= String.format("%.1f", segmentFuelList.get(i)) %> lb</td>
<td>
  <% if (capIndex != null && i == capIndex) { %>
    CAP
  <% } %>
</td>
            
        </tr>
        <% } %>
    </table>

  <h3>Squadron:VFA-102</h3>
  <table border="1">
  <tr>
  <th>Aircraft_Modex</th>
  <th>Aircraft_Type</th>
  <th>Fuel(lb)</th>
  
  </tr>
  <% for(Map.Entry<String,FA_18F>entry:squadron.entrySet()) {%>
  <tr>
  <td><%= entry.getKey() %></td>
  <td><%= entry.getValue().getName() %></td>
  <td><%= entry.getValue().getIntFuel()+(FA_18F.getExtFuel()*2) %>lb</td>
  </tr>
  <% } %>
  </table> 
<table>
  <% 

  if (capIndex != null && capDuration != null) {
%>

  <p><strong>CAP executed at WP <%= capIndex + 1 %> for <%= capDuration %> minutes</strong></p>
<% } %>
</table>
    <%
    int totalMinutes = (int) (totalFlightTime / 60);
    int totalSeconds = (int) (totalFlightTime % 60);
%>
  	<h3>Total Flight Time</h3>
  	<p><%= totalMinutes %>min <%= totalSeconds %> sec</p>  
    <h3>Remaining Fuel <%=fa18f.getModex() %></h3>
<p>Initial Fuel: <%= fa18f.getIntFuel() + (FA_18F.getExtFuel() * 2) %> lb</p>
<p>Final Fuel: <%= fa18f.getRemainingFuel() %> lb</p>

</body>
</html>