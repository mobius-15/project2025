<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
  <%@ page import="aircrafts.*" %>
    <% FlightPlan2 plan = (FlightPlan2)session.getAttribute("flightplan"); 
    int wayPoint = (Integer) session.getAttribute("wayPoint");
    String missionType = (String) session.getAttribute("missionType");
    if(missionType==null)missionType="";%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>Mission: <%= missionType %></h2>
       <form action="TestPlan" method="post">
       <input type="hidden" name="action" value="submit_wp">
        <input type="hidden" name="missionType" value="<%= missionType %>">
        <input type="hidden" name="wayPoint" value="<%= wayPoint %>">
        
        <% for (int i = 0; i < wayPoint; i++) { %>
            <h3>WP <%= i + 1 %></h3>
            <p>Altitude (feet)</p>
            <input type="number" name="altitude<%= i %>" required>

            <p>Speed (CAS knots)</p>
            <input type="number" name="cas<%= i %>" required>

            <p>WP Distance (nm)</p>
            <input type="number" name="distance<%= i %>" required>
            
             <label>Heading (0-360°):</label>
            <input type="number" name="heading<%= i %>" min="0" max="360" required><br>
        <% } %>
          <button type="submit">Submit</button>

    </form>


</body>
</html>