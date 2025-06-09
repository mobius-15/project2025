<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>	<%-- pageディレクティブ --%>
   <% String name="etwetr";  int age =231; %>	<%-- スクリプトレット --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	<%-- HTMLテンプレート --%>
</head>
<body>
name:<%= name %>    age:<%= age %>	<%-- スクリプト式 --%>
</body>
</html>								<!-- テンプレート -->