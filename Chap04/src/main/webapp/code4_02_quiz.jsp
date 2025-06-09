<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="ex1.Employee" %>
    <% Employee emp = new Employee("0001","Minato"); %>
    <% for(int i=0;i<10;i++){ %>
<!DOCTYPE html>			<%-- インスタンス化する事 --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% if(i / 3==0){ %>
<p style="color:red"> 
<% }else{ %>
</p>
<% } %>
<p>IDは<%= emp.getId() %>、名前は<%= emp.getName() %></p>
<% } %>
</body>
</html>