<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page import="aircrafts.*, weapons.*" %>
<!DOCTYPE html>
<html>
<head><title>Loadout Configuration</title>
<script>
function updateLoadoutSummary() {
    let totalWeight = 0;
    const selectBoxes = document.querySelectorAll("select[name^='station']");
    selectBoxes.forEach(select => {
        const selected = select.options[select.selectedIndex];
        const weight = parseFloat(selected.getAttribute("data-weight")) || 0;
        totalWeight += weight;
   });
    document.getElementById("totalLoadout").innerText = totalWeight.toFixed(0) + " lb";
    }
</script>
</head>
<body>
<h2>Configure Loadout for <c:out value="${fa18f.modex}" /></h2>

<form action="LoadoutServlet" method="post" onChange="updateLoadoutSummary()">
<input type="hidden" name="action" value="loadout_set" />
  <table border="1">
    <tr><th>Station</th><th>Weapon</th><th>Weight (lb)</th><th>Category</th></tr>
    <c:forEach begin="1" end="11" var="i">
      <tr>
        <td>Station ${i}</td>
        <td>
          <select name="station${i}">
            <option value="">-- NONE --</option>
            <c:forEach var="w" items="${weaponList}">
              <option value="${w.name}">${w.name} (${w.weightlb} lb)Type:(${w.category})</option>
            </c:forEach>
          </select>
        </td>
         <td id="weight${i}">--</td>
        <td id="category${i}">--</td>
      </tr>
    </c:forEach>
  </table>
  <script>
updateLoadoutSummary();
</script>
    <h3>Loadout Summary</h3>
  <p>Total Weapons Weight: <span id="totalLoadout">0 lb</span></p>
  <br/>
  <button type="submit" style="padding:10px 20px; font-size:16px;">Apply Loadout</button>
    <c:if test="${fa18f.loadoutWeight > 17000}">
  <p style="color: red; font-weight: bold;">
    ⚠️ WARNING: Loadout exceeds safe payload limit (17,000 lb)!
  </p>
</c:if>
</form>



</body>
</html>