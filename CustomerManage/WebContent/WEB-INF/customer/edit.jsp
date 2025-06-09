<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean class="user.UserBean" id="user" scope="session" />
<jsp:useBean class="customer.CustomerBean" id="customer" scope="session" />
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顧客管理</title>
<link rel="stylesheet"
    href="${pageContext.request.contextPath}/style.css">
</head>
<body>
    <h1>顧客管理</h1>
    <div class="main" >
<h2>既存データの編集</h2>
        <!-- TODO ｢既存データ編集･削除｣画面 -->
<form action="CustomerServlet" method="post" onsubmit="return funcConfirm()">
   <table>
   <tr>
      <td><label for="name">氏名:</label></td>
      <td><input type="text" id="name" name="name" value="<%= customer.getName() %>" maxlength="20"></td>
   </tr>
   <tr>
      <td><label for="zip">郵便番号:</label></td>
      <td><input type="text" id="zip" name="zip" value="<%= customer.getZip() %>" maxlength="20"></td>
   </tr>
   <tr>
      <td><label for="address1">住所１:</label></td>
      <td><input type="text" id="address1" name="address1" value="<%= customer.getAddress1() %>" maxlength="100"></td>
   </tr>
   <tr>
      <td><label for="address2">住所2:</label></td>
      <td>
         <input type="text" id="address2" name="address2" value="<%= customer.getAddress2() != null ? customer.getAddress2() : "" %>" maxlength="100">
      </td>
   </tr>
   <tr>
      <td><label for="tel">TEL:</label></td>
      <td><input type="text" id="tel" name="tel" value="<%= customer.getTel() %>" maxlength="20"></td>
   </tr>
   <tr>
      <td><label for="fax">FAX:</label></td>
      <td>
         <input type="text" id="fax" name="fax" value="<%= customer.getFax() != null ? customer.getFax() : "" %>" maxlength="20">
      </td>
   </tr>
   <tr>
      <td><label for="email">E-mail:</label></td>
      <td><input type="text" id="email" name="email" value="<%= customer.getEmail() %>" maxlength="100"></td>
   </tr>

   </table>
   <p>
      <button name="state" value="edit_confirm">送信</button>
      <input type="button" value="戻る" onclick="history.back()">
  </p>
</form>
</div>
</body>
<script type="text/javascript">
function funcConfirm() {
    let name = document.getElementById("name").value.trim();
    let email = document.getElementById("email").value.trim();
    let address1 = document.getElementById("address1").value.trim();
    let tel = document.getElementById("tel").value.trim();
    let zip = document.getElementById("zip").value.trim();

    let errorMessages = [];

    if (name === "") {
        errorMessages.push("氏名が未入力です。");
    }
    if (email === "") {
        errorMessages.push("メールアドレスを入力してください。");
    }
    if (address1 === "") {
        errorMessages.push("住所1が未入力です。");
    }
    if (tel === "" ) { // Adjusted to validate numbers with at least 7 digits
        errorMessages.push("電話番号を入力してください。");
    }
    if (zip === "") {
        errorMessages.push("郵便番号がが未入力です。");
    }

    if (errorMessages.length > 0) {
        alert(errorMessages.join("\n"));
        return false;
    }

    return true;
}
</script>
</html>
