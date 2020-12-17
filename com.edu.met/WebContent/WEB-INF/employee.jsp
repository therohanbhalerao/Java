
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>



<body>
		
		<table align="center" border="1">
			
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Designation</th>
				<th>EmailId</th>
				<th>Edit</th>
				<th>delete<th>
			</tr>
			
			<c:forEach var="employee" items="${listEmployee }">
				<tr>
					<td>${employee.id }</td>
					<td>${employee.name }</td>
					<td>${employee.designation }</td>
					<td>${employee.emailId }</td>
					<td> <a href="employee/get/${employee.id }" >edit</a> </td>
					<td> <a href="employee/delete/${employee.id }" >delete</a> </td>
				</tr>
			</c:forEach>
			
		</table>
	
	

</body>

</html>