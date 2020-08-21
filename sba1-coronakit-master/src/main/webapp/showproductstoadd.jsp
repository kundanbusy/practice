<%@ page language="java" import="com.iiht.evaluation.coronokit.model.ProductMaster" import="com.iiht.evaluation.coronokit.model.CoronaKit" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(user)</title>
<style>
table,th,td{
border: 1px solid purple
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<%List<ProductMaster> productsList= (List<ProductMaster>)request.getAttribute("availableProdList");
CoronaKit user=(CoronaKit)request.getAttribute("user");%>
<form action="user?action=addnewitem&cid=<%=user.getId()%>" method="post">
<table>
<tr>
<th>Product Name</th>
<th>Product Description</th>
<th>Price/Unit</th>
<th>Qty.</th>
</tr>
<%for(ProductMaster item:productsList){
%>
<tr>
<td><%=item.getProductName()%></td>
<td><%=item.getProductDescription()%></td>
<td><%=item.getCost()%></td>
<td><input type="text" name="qty_<%=item.getId()%>" value="0"/></td>
<td><input type="submit" value="Add to Kit" name="btn_<%=item.getId()%>"></td>
</tr>
<%} %>
</table>
</form>
<hr/>	
<a href="user?action=placeorder"><input type="button" value="Confirm Order"></a>     <a href="user?action=showkit&cid=<%=user.getId()%>"><input type="button" value="Show Order"></a>     
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>