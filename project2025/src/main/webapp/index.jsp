<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Flight Planner</title>
</head>
<body>
<h1>Flight Planning System 2025</h1>
<form action="<c:url value='/LoginServlet'/>" method="post">
<p>ID：<input type="text" name="name"><br>
	PASS：<input type="password" name="pass"></p><br>
<input type="submit" value="GO">	
</form>
<div>
<br/>
<h2>HOW TO USE</h2>
<p><a href="https://github.com/mobius-15/project2025/tree/Major_Updated" />GitHubリンク</p>
<br/>
</div>
</body>
</html>