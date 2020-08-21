<%@ page language="java" import="com.iiht.evaluation.coronokit.model.ProductMaster" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-All Products(Admin)</title>
<style>
table,th,td{
border: 1px solid purple
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<table>
<tr>
<th>Product Name</th>
<th>Product Description</th>
<th>Price/Unit</th>
</tr>
<%List<ProductMaster> productsList= (List<ProductMaster>)request.getAttribute("list");
for(ProductMaster item:productsList){
%>
<tr>
<td><%=item.getProductName()%></td>
<td><%=item.getProductDescription()%></td>
<td><%=item.getCost()%></td>
<td><a href="admin?action=editproduct&id=<%=item.getId()%>"><input type="button" value="Edit"></a></td>
<td><a href="admin?action=deleteproduct&id=<%=item.getId()%>"><input type="button" value="Delete"></a></td>
</tr>
<%} %>
</table>
<hr/>	
<a href="admin?action=newproduct"><input type="button" value="Add Product"></a>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>