<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%String job=(String)session.getAttribute("job");
    String message=(String)session.getAttribute("message");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action ="JobServlet" method="get">
<br>
職業を記入
<input type="text" name="job" value="<%= job %>">
<input type="submit" value="送信">
</form>
</body>
</html>