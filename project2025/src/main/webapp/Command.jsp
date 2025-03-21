<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.List,aircrafts.*" %>
   <% List<String> missionTypes = (List<String>)request.getAttribute("missionTypes"); %>
   <% String selectedMission = request.getParameter("missionType"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="TestPlan" method="post">
<input type="hidden" name="action" value="input_wp">
<p>Mission  Type :</p>
 <select name="missionType" required>
 <% for(String mission:missionTypes){ %>
 <option value="<%= mission %>"<%=(mission.equals(selectedMission))? "selected":"" %>>
 <%=mission %></option>
 <%} %>
 </select>
<p>How Many WayPoints?(1-20)</p>
<input type="number" name="wayPoint" min="1" max="20" required>

        <button type="submit">Send</button>
</form>
  
</body>
</html>