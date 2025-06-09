<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Controller" method ="post">
<p>機種：<input type="text" name="type"></p><br>
<p>兵装1:<select name="AA" id="armament1"></p>
			<option value="AIM-154">AIM-154</option>
			<option value="AIM-120">AIM-120</option>
			<option value="AAM-4">AAM-4</option>			
			</select><br>
<p>数量：</p>
<p>増槽:<input type="checkbox" name="exttank"></p><br>
<p>数量：<input type="number" name="ntank"></p><br>
	
	<input type="submit" value ="Takeoff">
	</form>
</body>
</html>