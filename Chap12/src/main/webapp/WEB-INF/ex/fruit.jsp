<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
   					<%--暗黙オブジェクト --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<p>商品名：<c:out value="${applicationScope.fruit.name}"/>、価格 <c:out value="${applicationScope.fruit.price }" />円</p>
<br>
<p>　アクションタグには、最初から利用可能な"標準アクションタグ"と、<br/>
開発者が独自に作成する"カスタムタグ"がある。代表的な標準アクションタグには<br/>
他のJSPファイルの"出力結果"を取り込む"jsp:include"タグがある。一方<br/>
便利なカスタムタグの集合であるJSTLは複数のタグライブラリから構成されており<br/>
その一部であるCoreタグは条件分岐、ループといった処理を行うタグを提供する。</p>
<p>　EL式を使うと、スコープに保存されたインスタンスを利用する処理を簡潔に記述できる。<br/>
ただし、EL式で分岐、ループを表すにはCoreタグを使用する必要がある。例えば以下の場合。</p>
<c:forEach var="human" items ="${humanList }" >
<table border="1" width="300px">
<tr>
<td width="150px">名前：<c:out value="${human.name}" /></td><td>年齢：<c:out value="${human.age }"/></td>
</tr>
</table>
</c:forEach>
</body>
</html>