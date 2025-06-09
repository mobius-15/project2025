<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="ex.Fruit" %>
    <% Fruit f1= (Fruit)request.getAttribute("fruit"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>商品名：<%= f1.getName() %>、価格 <%= f1.getPrice() %>円</p>
</body>
</html>