<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
For Exam.
<p style="font-size:20px"> A.java(Servlet),B.java(WebFilter),C.java(ServletContextListener)<br> 
  順序：サーバ起動：contextInitialized()が実行（C.java）<br> 
  →A.javaをリクエスト：Aのinit(),BのdoFilter(),AのdoGet()の順に実行<br> 
  →A.javaをリクエスト２回目：BのdoFilter(),AのdoGet()の順に実行(initは1回目のみ)<br> 
  →サーバ終了：Aのdestroy(),CのcontextDestroyed()の順に実行</p>
</body>
</html>