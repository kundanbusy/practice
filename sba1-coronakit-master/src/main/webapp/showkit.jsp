<%@ page language="java" import="com.iiht.evaluation.coronokit.model.KitDetail" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-My Kit(user)</title>
<style>
table,th,td{
border: 1px solid purple
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>
<h1>Order Details</h1>
<table>
<tr>
<th>Product Id</th>
<th>Quantity Added</th>
<th>Total Amount</th>
</tr>
<%List<KitDetail> kitList= (List<KitDetail>)request.getAttribute("list");
int cid=0;
for(KitDetail item:kitList){
	cid=item.getCoronaKitId();
%>
<tr>
<td><%=item.getProductId()%></td>
<td><%=item.getQuantity()%></td>
<td><%=item.getAmount()%></td>
</tr>
<%} %>
</table>
<hr/>	
<a href="user?action=showproducts&cid=<%=cid%>"><input type="button" value="Go Back To Products Page"></a>
<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>