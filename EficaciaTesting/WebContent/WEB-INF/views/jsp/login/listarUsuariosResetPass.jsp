<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Usuarios</title>
<jsp:include page="../fragments/resources.jsp" />
<jsp:include page="../fragments/header.jsp" />
</head>
<body >
	<div class="container" 
		style="width: 100%; margin-top: 8em;; z-index: 1; position: absolute; text-align: center">
		<div style="text-align: center; font-weight: bold; font-size: 1.4em; ">Resetear contraseña de usuario</div>

<form class="navbar-form"  
			style="background-color: white; text-align: left; margin: 2% 0% 0% 24%; "
			role="search" action="filtrarReset">
			<div class="form-group" >
				<input type="text" name="criterio" class="form-control"
					placeholder="Buscar por SOEID" >
			</div>
			<button type="submit" class="btn btn-primary">
				<i style="height: 19px;" class="glyphicon glyphicon-search"></i>
			</button>
		</form>
	<br />
	
	<div class="contenidoUsuarios" style="margin-left: 25%; width: 50%" >
		
	<c:if test="${empty usuarios}">
	<div class="alert alert-warning">
  No hay registros para mostrar.

</div>
	
	</c:if>
	
	<c:if test="${not empty usuarios}">
		<table class="table table-striped table-hover tabla" style="font-size: 12px">
			<thead>
			<tr class="cabeceraTabla">
			<th>#</th>
				<th>SOEID</th>
				<th>Usuario</th>
				<th>Rol</th>
				<th>Acción</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach items="${usuarios}" var="usuario" varStatus="indice" >
				<tr>
				<td class="negrita">${indice.index + 1}</td>
					<td >${usuario.soeid}</td>
					<td >${usuario.nombre}</td>
					<td >${usuario.rol.nombre}</td>
						<td><c:choose>
								<c:when
									test="${pageContext.request.userPrincipal.name == usuario.soeid}">
									<a href="<c:url value='/modificarContrasenaReseteo/${usuario.soeid}' />"
										class="btn glyphicon-refresh disabled" style="font-size: 16px; text-align: center; color: gray;"></a>
								</c:when>
								<c:otherwise>
									<a href="<c:url value='/modificarContrasenaReseteo/${usuario.soeid}' />"
										class="btn glyphicon glyphicon-refresh" style="font-size: 16px ; color: black;text-align: center;"></a>
								</c:otherwise>
							</c:choose></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<c:choose>
			<c:when test="${filtro == true}">
				<div class="paginacion">
                    <div >
                           <tag:paginate limite="5" offset="${offset}" conteo="${count}" criterio="${criterio}" uri="filtrarUsuarios" siguiente="&raquo;" anterior="&laquo;" />
                           <span class="label label-primary">Total: ${count}</span>
                    </div>
             	</div>
			</c:when>
			<c:otherwise>
				<div class="paginacion">
                    <div >
                           <tag:paginate limite="5" offset="${offset}" conteo="${count}" uri="listarUsuarios" siguiente="&raquo;" anterior="&laquo;" />
                           <span class="label label-primary">Total: ${count}</span>
                    </div>
            	</div>
			</c:otherwise>
		</c:choose>

		</c:if>
	</div>

</div>
<br><br>
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>