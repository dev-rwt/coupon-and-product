<%@ page import="java.util.*" %>
<%@ page import="com.devansh.cnp.model.Coupon" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Coupons</title>
<style>
table, th, td {
  border: 1px solid black;
  border-collapse: collapse;
}
</style>
</head>
<body>

<table>
<tr>
<th>Id</th>
<th>Code</th>
<th>Discount</th>
</tr>


<c:forEach var="type" items="${list}" >
<tr>
<td><c:out value="${type.getId()}" /></td>
<td><a href = 'coupons?action=find&couponCode=${type.getCode()}'><c:out value="${type.getCode()}" /></a></td>
<td><c:out value="${type.getDiscount()}" /></td>
</tr>
</c:forEach>



</table>


</body>
</html>