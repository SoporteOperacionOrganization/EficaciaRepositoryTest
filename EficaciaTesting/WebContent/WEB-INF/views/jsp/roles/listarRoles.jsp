<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de roles</title>
</head>
<body>
<h1>Lista de roles</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Rol</th>
		</tr>
		<c:forEach items="${roles}" var="rol">
		<tr>
			<td>${rol.id}</td>
			<td>${rol.nombre}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>