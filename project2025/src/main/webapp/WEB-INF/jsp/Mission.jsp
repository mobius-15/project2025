<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="aircrafts.*" %>
    <% FA_18 fa18=(FA_18)session.getAttribute("fa18"); %>
    <%  int totalFuel = fa18.getIntFuel()+(FA_18.getExtFuel()*fa18.getnTank()); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>初期搭載燃料<%= totalFuel %>lb</p>
</body>
</html>