<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SUKKIRI SHOP</title>
</head>
<body>
<p>ようこそ<c:out value="${userID}" />さん</p>
<a href="WelcomeServlet">Back</a>
</body>
</html>