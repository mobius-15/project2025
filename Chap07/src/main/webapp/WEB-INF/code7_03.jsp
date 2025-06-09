<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="model.Human" %>	<%--取得するインスタンスのクラスをインポート --%>
       <%
	Human h =(Human)request.getAttribute("human");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>
<%=h.getName() %>: <%= h.getAge() %> old
</p>
</body>
</html>