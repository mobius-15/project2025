<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>
    <%@ page import="java.util.*,controller.*,carrierOps.*,aircrafts.*,mission.*,flightplan.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="CarrierInfo" method="post">
<input type="hidden" name="action" value="confirmMission" />
  <label>Carrier Name:</label>
  <select name="carrierName"><br>
    <c:forEach var="entry" items="${carriers}">
      <option value="${entry.value}">${entry.key} - ${entry.value}</option>
    </c:forEach>
</select><br/>
  <p><label>Latitude:</label>
  <input type="number" name="lat" step="0.0001"></p>
  <br>

  <p><label>Longitude:</label>
  <input type="number" name="lon" step="0.0001"></p>
  <br>

 <p> <label>Speed (knots):</label>
  <input type="number" name="speed"></p>
 <br>

  <p><label>Full_Displacement (tons):</label>
  <input type="number" name="displacement" min="${carrier.displacement }" max="130000"></p>
  <br>

  <button type="submit">Save</button>
  <c:if test="${not empty inputError}">
  <p style="color:red; font-weight:bold;">${inputError}</p>
</c:if>
</form>
<script>
document.querySelector("form").addEventListener("submit", function(e) {
  const requiredFields = ["carrierName", "lat", "lon", "speed", "displacement"];
<!--  for (let name of requiredFields) {-->
<!--    const field = document.querySelector(`input[name="${name}"]`);-->
    if (!field || !field.value.trim()) {
      alert("Please fill in all carrier fields.");
      e.preventDefault();
      return;
    }
  }
});
</script>
</body>
</html>