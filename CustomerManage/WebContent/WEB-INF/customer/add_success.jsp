<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<title>顧客管理</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>顧客管理</h1>
    <div class="main">
        <!-- 新規登録完了画面 -->
        <!-- <div class="confirmation"> -->
        <h2>新規登録完了</h2>
        <form action="CustomerServlet" method="post">

        <p>
            <button type="submit" name="state" value ="search">検索画面</button>
        </P>

</form>
        <!-- </div> -->
    </div>
</body>
</html>