<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sistema de usuarios</title>
<jsp:include page="../fragments/resources.jsp" />
</head>
<body>
	<jsp:include page="../fragments/header.jsp" />
	<div class="container  "
		style="position: fixed; height: 100%; width: 100%">

		<form id="loginform" role="form"
			action="${pageContext.request.contextPath}/j_spring_security_check"
			method="POST">
			<div style="margin-top: 10px; text-align: center;" class="form-group">
				<!-- Button -->

				<div class="col-sm-12 controls" style="margin-left: 0%">
					<input type="submit" class="btn btn-primary"
						style="width: 60px; height: 27px; font-size: 12px;"
						value="Validar" />
					<div class="logueo">
						<div
							style="border-bottom: 1px solid #0099FF; margin-bottom: 3.5%; text-align: left;">
							User <span style="color: #0099FF">Login</span>
						</div>

						<div style="display: none" id="login-alert"
							class="alert alert-danger col-sm-12"></div>
						<div style="margin-bottom: 5px; width: 100%; height: 3px"
							class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span>
								 <input id="Soeid"
								type="text" class="form-control" name="soeid" value=""  autofocus="autofocus" 
								placeholder="Soeid" required="required" maxlength="7">
						
						</div>
						<div style="margin-bottom: 5px; width: 100%" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="login-password"
								maxlength="8"
								type="password" class="form-control" name="contrasena"
								placeholder="Contraseña" required="required">
						</div>

						<div></div>
						<br>
						<div style="margin-top: 10px; text-align: center;"
							class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls" style="margin-left: 0%">
								<input type="submit" class="btn btn-primary" id="pruebas"
									style="width: 60px; height: 27px; font-size: 12px;"
									value="Validar"  onclick="formatoSoeid()"/>
							</div>
							
						</div>
						<br> <br> <br>
					</div>
					
					<c:choose>
						<c:when test="${param.error == 'true'}">
							<div style="color:red;margin:10px 0px;">
				                <b>Error!!!</b>
				                ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
				            </div>
						</c:when>
						<c:when test="${param.userNotFound == 'true'}">
							<div style="color:red;margin:10px 0px;">
				                <b>Error!!!</b>
				                El usuario no se encuentra registrado
				         	</div>
						</c:when>
					</c:choose>
					
				</div>
			</div>
		</form>

	</div>

</body>
<footer style="position: fixed; bottom: 0; width: 100%">
	<jsp:include page="../fragments/footer.jsp" />
</footer>
</html>