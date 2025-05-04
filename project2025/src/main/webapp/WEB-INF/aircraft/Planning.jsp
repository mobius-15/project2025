<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>
		Mission:
		<c:out value="${sessionScope.missionType }" />
	</h2>
	<form action="TestPlan" method="post">
		<c:if test="${not empty inputError}">
			<p style="color: red;">${inputError}</p>
		</c:if>
		<input type="hidden" name="action" value="submit_wp"> <input
			type="hidden" name="missionType" value="${sessionScope.missionType}">
		<input type="hidden" name="wayPoint" value="${sessionScope.wayPoint}">


		<c:forEach var="i" begin="0" end="${sessionScope.wayPoint -1}">
			<h3>WP ${i+1}</h3>
			<p>Altitude (feet)</p>
			<input type="number" name="altitude${i}" required>

			<p>Speed (CAS knots)</p>
			<input type="number" name="cas${i}" required>

			<p>WP Distance (nm)</p>
			<input type="number" name="distance${i}" required>

			<label>Heading (0-360°):</label>
			<input type="number" name="heading${i}" min="0" max="360" required>
			<br>

		</c:forEach>

		<h3>CAP Configuration</h3>
		<p>Select CAP Waypoint and Duration:</p>
		<label>CAP at WP:</label> <select name="capIndex">
			<c:forEach begin="0" end="${sessionScope.wayPoint -1 }" var="i">

				<option value="${i}">WP ${i+1}</option>
			</c:forEach>
		</select> <label>Duration (minutes):</label> <input type="number"
			name="capDuration" min="1" max="60" required />
		<button type="submit">Submit</button>

	</form>

</body>
</html>