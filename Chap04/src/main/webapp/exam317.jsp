<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="ex1.Employee" %>
  <% Employee emp=new Employee("0001","Minatoku"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>IDは<%=emp.getId() %>、名前は<%=emp.getName() %></p>
</body>
</html>