<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Human" %>
<% 
//リクエストスコープからインスタンスを取得
Human h = (Human)request.getAttribute("human");
%>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>197ページcode7-3</title>
	</head>
	<body>
		<%= h.getName() %>さんは<%= h.getAge() %>歳です
	</body>
</html>