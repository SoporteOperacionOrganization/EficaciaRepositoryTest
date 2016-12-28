<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Acceso denegado</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-glyphicons.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/customStyle.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/datepicker.css" rel="stylesheet" ></link>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>



</head>
<body>
<!-- INICIO CABECERA -->
<div style="background-image:url('${pageContext.request.contextPath}/resources/Images/Logos/Logo_CitiBanamex.png'); background-repeat:repeat-x; 
	top: 0%; ;	z-index:3 ; position: fixed; width: 100%; height: 100px">
		
		<table style="width: 100%; position: fixed;">
			<tr > 
			<td width="30%"></td>
				<td  width="43%">
					<h3 class="titleSize"
						style="color: #D7D7D7; margin-top: 0.0em">SISTEMA DE GESTIÓN EFICASIA</h3>
				</td>
				<td style="width: 52%; height: 100px;  text-align: right;"> 
				
						<h4>

							<span  class="headerText" style=" color: #C01722; font-weight: bold; letter-spacing: 1px; margin: 0.6em 0.5em 0em 0em ">
							<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">Bienvenido:</sec:authorize></span>
						</h4>
						<h5>
							<span class="headerText"  style="color: #fff;   margin:  0em 0.7em 0em 0em "> <c:if
									test="${UsuarioSesion != null}">
									<span class="tranformarMayusculas"><sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
									${UsuarioSesion.nombre} ${UsuarioSesion.apellidoPaterno}</sec:authorize> </span>
								</c:if>
							</span>
						</h5></td>
				<td ></td>
			</tr>
		</table>
		
		
		<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">
			<table style="width: 100%; margin-top: 4.5em; height: 2em;">
			<tr >
					<td colspan="4" style="font-size: 14px">
			<nav class="navbar-default " 
							style="margin: -0.2% 0% 0% 0px;width: 100%;
							background-color:#013F7A ;border-color: #013F7A;
							 border-radius: 0px 0px 0px 0px;">
							<div class="container" style="width: 100%; height: 3.4em" >
								<sec:authorize access="hasRole('ROLE_ADMIN')">
								
								<ul class="nav navbar-nav navbar-left" >
									<li style=" height: 3.4em;" class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false"  >Usuarios <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/listarUsuarios' />">Consultar</a></li>
											<li><a href="<c:url value='/agregarUsuario' />">Agregar</a></li>
										</ul>
										
										</li>
								</ul>
								</sec:authorize>
								<ul class="nav navbar-nav navbar-left">
									<li style=" height: 3.4em;" class="dropdown"><a href="#" class="dropdown-toggle"
										data-toggle="dropdown" role="button" aria-haspopup="true"
										aria-expanded="false">Agenda <span class="caret"></span></a>
										<ul class="dropdown-menu">
											<li><a href="<c:url value='/listarAgendas' />">Consultar</a></li>
											<li><a href="<c:url value='/agregarAgenda' />">Agregar</a></li>
											<li role="separator" class="divider dividerColor"></li>
											<li><a class="subtitulosDropDown"
												style="color: #000; font-weight: bold; cursor: default;"
												>Carga masiva&nbsp;<span class="glyphicon glyphicon-upload"></span></a></li>
											<li role="separator" class="divider"></li>
											<li><a href="<c:url value='/cargaMasiva' />">Agregar</a></li>
											<li><a href="<c:url value='/eliminacionMasiva' />">Eliminar</a></li>
										</ul></li>
								</ul>

								<ul  style="text-align: right; margin: 20px 0px 0px 0px">
									<a style="color: white; font-weight: bold;"
										href="<c:url value='/logout' />"><span
											class="glyphicon glyphicon-log-in"></span>&nbsp;Log out</a>
								</ul>

								
							</div>
						</nav>

					</td>
				
			</tr>
		</table>
		
		</sec:authorize>
		
		
		
	</div>
	<br>

<!-- FIN CABECERA -->

		<div class="container" style="width: 100% ; margin-top: 10em;  z-index:1; position: absolute; text-align: center;">
		<img style="width: 5%; height: 16%" class="inicioImages" src="${pageContext.request.contextPath}/resources/Images/Logos/seguridad.png">
			<h1>ACCESO DENEGADO</h1>
			<h5>No cuentas con los permisos necesarios para visualizar esta página</h5>
	</div>


<footer class="footerA" style="position: fixed; bottom: 0; width: 100%; " >
         <table style="width: 100%">
        <tbody><tr>
            <td align="left"><img src="${pageContext.request.contextPath}/resources/Images/Logos/banamex_logo_footer.gif" alt="">
            <br><a href="http://intranet.banamex.com/" class="FontFooterLinkBlue"  style="font-size: 11px">http://intranet.banamex.com/</a> 
            <span style="font-size: 11px">v 1.0.1.1</span> </td>
            <td align="right"><img src="${pageContext.request.contextPath}/resources/Images/Logos/citi_logo.gif" alt="">
            <br><div class="FontFooterLinkBlue"  style="font-size: 11px">Copyright © Citigroup 2012. Todos los derechos reservados.</div></td>
        </tr>
        </tbody></table>
      
    
</footer>	


</body>
</html>