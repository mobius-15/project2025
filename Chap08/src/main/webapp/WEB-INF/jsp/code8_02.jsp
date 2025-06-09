<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="code8_01.Human" %>	<%--ディレクティブからインポート --%>
    <% Human h =(Human)session.getAttribute("human"); %><%--セッションスコープを取得 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>氏名：<%=h.getName() %> 年齢：<%=h.getAge() %></p>
</body>
</html>