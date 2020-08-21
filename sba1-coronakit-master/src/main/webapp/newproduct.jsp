<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-Add New Product(Admin)</title>
<style>
table,tr,td{
border: 1px solid purple
}
</style>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<h3>Add Product Details</h3>

<form action="admin?action=insertproduct" method ="post">
		<table>
		<tr>
			<td><label for="pname">Enter Product Name : </label> </td><td><input type="text" id="pname" name="pname"></td>
		</tr>
		<tr>
			<td><label for="pdesc">Enter Product Description : </label></td><td><input type="text" id="pdesc" name="pdesc"></td> 
		</tr>
		<tr>
			<td><label for="pcost">Enter Product Cost : </label></td> <td><input type="text" id="pcost" name="pcost"></td>
		</tr>
		</table>
		<br/>
		<div>
			<input type="submit" value="Add Product">
		</div>

</form>


<%-- Required View Template --%>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>