<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="ex.Fruit" %>
    <% Fruit f1= (Fruit)session.getAttribute("fruit"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>商品名：<%= f1.getName() %>、価格 <%= f1.getPrice() %>円</p><br>
<p>(1)A.jspから呼び出すサーブレットクラスがJSPファイルCに処理をリダイレクトする際にオブジェクトを渡す→セッションスコープ</p>
<p>(2)B.jspから呼び出すサーブレットクラスがJSPファイルDに処理をフォワードする際にオブジェクトを渡す→リクエストスコープ</p>
<p>(3)パスワード登録画面のsubmitボタンで起動するサーブレットクラス、入力パスワードが8文字未満で表示→リクエストスコープ</p>
<p>(4)ログイン画面の送信ボタンで起動するサーブレットクラス、入力されたID情報（文字列インスタンス）を保存し、様々なサーブレットクラス、JSPファイルで利用する→セッションスコープ</p>

</body>
</html>