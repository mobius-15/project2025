<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="ex.Fruit" %>
    <% Fruit f1= (Fruit)application.getAttribute("fruit"); %>
   					<%--暗黙オブジェクト --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>商品名：<%= f1.getName() %>、価格 <%= f1.getPrice() %>円</p><br>
<p>(1)保存されたインスタンスをアプリケーションサーバにアクセスするユーザーで共有→アプリケーションスコープ</p>
<p>(2)サーバからブラウザにレスポンスが返ると消滅→リクエストスコープ</p>
<p>(3)ブラウザを閉じてもスコープが消滅しない→アプリケーションスコープ</p>
<p>(4)setAttribute()でインスタンス保存、getAttribute()でインスタンス取得→共通</p>
<p>(5)invalidate()→セッションスコープ破棄</p>
<p>(6)アプリケーションサーバを停止したり明示的な削除指示をしなければインスタンスは消えず、メモリを圧迫する→アプリケーションスコープ</p>

</body>
</html>