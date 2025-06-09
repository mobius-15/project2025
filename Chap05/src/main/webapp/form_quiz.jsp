<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Ex5_1" method="post">
名前：<input type="text" name="name"><br>

<select name="qtype"><br>

<option  value="company">会社について</option>
<option value="product">製品について</option>
<option value="support">アフターサポートについて</option>

</select><br>

<textarea name="body" cols="40" rows="5">

</textarea><br>

<input type="submit" value="登録">

</form>

</body>
</html>