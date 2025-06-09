<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="model.User" %>
    <% User registerUser=(User)session.getAttribute("registerUser"); %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>下記のユーザーを登録</p>
<p>ログインID:<%= registerUser.getId() %><br>
			<%= registerUser.getPass() %><br>
	名前:<%= registerUser.getName() %><br>
</p>
<a href="RegisterUser">戻る</a>
<a href="RegisterUser?action=done">登録</a>
</body>
</html>