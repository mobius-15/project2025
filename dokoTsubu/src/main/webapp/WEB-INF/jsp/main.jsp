<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="java.util.*" %>
   <%@ page import="model.*" %>
   <%--セッションスコープに保存されたユーザー情報、投稿リストを取得 --%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>どこつぶ</h1>
<h3>メイン</h3>
<p>
<c:out value="${loginUser.name}" />さん、ログイン中
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
<textarea id=text name ="text"   maxlength="140"></textarea>
<input type="submit" value="tweet">
</form>
<c:if test="${not empty errorMsg }"><p><c:out value="${errorMsg }" />
<p><c:out value="${ errorMsg}"/></p>
</c:if>
	<%--リストの全体を表示 --%>
<c:forEach var="mutter" items="${mutterList}">

<p><c:out value="${mutter.userName}"/>:<c:out value="${mutter.text}" /></p>
</c:forEach>
</body>
</html>