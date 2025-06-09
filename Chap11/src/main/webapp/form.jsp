<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
<form action ="FormServlet" method="POST">
名前：<br>
<input type="text" name="name"><br>
性別：<br>
男<input type="radio" name="gender" value="0">
女<input type="radio" name="gender" value="1" >
<input type="submit" value="登録">
</form>
</body>
</html>

<%--一般的な未入力チェックでは、入力値が空文字「""」文字数0に
一致するかを比較し、nullとの比較でリクエストパラメータが送信されているかも
チェックする --%>