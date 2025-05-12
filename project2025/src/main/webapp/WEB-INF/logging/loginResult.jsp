<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="logger.*" %>
       <% User loginUser= (User)session.getAttribute("loginUser"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<% if(loginUser != null){ %>
<p>Successed</p>
<p><%= loginUser.getName() %></p>
<a href="TestPlan">Start Planning</a>
<% }else{ %>
<p>Failed</p>
<a href="index.jsp">TOP</a>
<% } %>
</body>
</html>