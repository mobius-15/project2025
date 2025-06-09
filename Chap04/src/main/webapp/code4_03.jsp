<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date,java.text.SimpleDateFormat" %>
  <% String[]luckArray= {"甲","乙","丙"};
	int index=(int)(Math.random()*3);
	String luck=luckArray[index]; %>
	
<%	Date date =new Date();
	SimpleDateFormat sdf=new SimpleDateFormat("MM月dd日");
	String today =sdf.format(date); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p><%= today %>の結果は[<%= luck %>]</p>
</body>
</html>