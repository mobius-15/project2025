<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Login" method="post" onsubmit="return checkForm();">
<h1></h1>
<p><input type="text" ></p><br>
<p><input type="submit" value="Logging"></p>
<script>
<% if(request.getAttribute("error")!=null ){ %>
alert("<%=request.getAttribute("error") %>");
<%} %>
</script>
<script src="loginCheck.js" ></script>
</form>
</body>
</html>