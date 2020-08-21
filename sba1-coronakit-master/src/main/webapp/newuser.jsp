<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Corona Kit-New User(user)</title>
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

<form action="user?action=showproducts&callFromUser=true" method ="post">
		<table>
		<tr>
			<td><label for="pname">Enter  Name : </label> </td><td><input type="text" id="pname" name="pname"></td>
		</tr>
		<tr>
			<td><label for="pmail">Enter Email : </label></td><td><input type="text" id="pmail" name="pmail"></td> 
		</tr>
		<tr>
			<td><label for="pnum">Enter Mobile : </label></td> <td><input type="text" id="pnum" name="pnum"></td>
		</tr>
		</table>
		<br/>
		<div>
			<input type="submit" value="Go To Shopping">
		</div>

</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>