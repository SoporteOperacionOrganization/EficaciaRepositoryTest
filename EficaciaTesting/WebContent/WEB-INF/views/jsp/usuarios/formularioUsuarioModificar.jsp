<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modificar Usuario</title>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap-glyphicons.css" rel="stylesheet" ></link>
<link href="${pageContext.request.contextPath}/resources/css/customStyle.css" rel="stylesheet" ></link>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/password-score.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-strength-meter.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/funciones.js" ></script>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
    
    $('#example-getting-started-input').strengthMeter('text', {
        container: $('#example-getting-started-text')
    });
    
});
</script>



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

							<span  class="headerText" style=" color: #C01722; font-weight: bold; letter-spacing: 1px; margin: 0em 1.2em 0em 0em ">
							<sec:authorize	access="hasAnyRole('ROLE_ADMIN', 'ROLE_EJECUTIVO')">Bienvenido:</sec:authorize></span>
						</h4>
						<h5>
							<span class="headerText"  style="color: #fff;   margin:  0em 1.2em 0em 0em "> <c:if
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
			<table  style="width: 100%; margin-top: 4.5em; height: 2em;">
			<tr >
					<td colspan="4" style="font-size: 14px">
					<nav class="navbar navbar-default" style="margin: 0% 0% 0% 0px;width: 100%;
					background-color:#013F7A ;border-color: #013F7A; border-radius: 0px 0px 0px 0px;" role="navigation">
						  <div class="navbar-header" >
							    <button type="button" class="navbar-toggle" data-toggle="collapse"
							            data-target=".navbar-ex1-collapse">
							      <span class="sr-only">Desplegar navegación</span>
							      <span class="icon-bar"></span>
							      <span class="icon-bar"></span>
							      <span class="icon-bar"></span>
							    </button>
						  </div>
						  
						  <div class="collapse navbar-collapse navbar-ex1-collapse">
							    <ul class="nav navbar-nav">	
							      <sec:authorize access="hasRole('ROLE_ADMIN')">						 
							      <li class="dropdown">
							        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
							          Usuarios <b class="caret"></b>
							        </a>
							        <ul class="dropdown-menu">
							          <li><a href="<c:url value='/listarUsuarios' />">Consultar</a></li>
									  <li><a href="<c:url value='/agregarUsuario' />">Agregar</a></li>
							        </ul>
							      </li>
							      </sec:authorize>
							      <li class="dropdown">
							        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
							          Agenda <b class="caret"></b>
							        </a>
							        <ul class="dropdown-menu">
							          	<li><a href="<c:url value='/listarAgendas' />">Consultar</a></li>
										<li><a href="<c:url value='/agregarAgenda' />">Agregar</a></li>
										<li role="separator" class="divider dividerColor"></li>
	
										<li><a class="subtitulosDropDown"
											style="color: #000; font-weight: bold; cursor: default;"
											>Carga masiva &nbsp;<span class="glyphicon glyphicon-upload"></span></a></li>
										<li role="separator" class="divider"></li>
										<li><a href="<c:url value='/cargaMasiva' />">Agregar</a></li>
										<li><a href="<c:url value='/eliminacionMasiva' />">Eliminar</a></li>
							        </ul>
							      </li>
							    </ul>
							    <ul  style="text-align: right; margin: 17px 5px 0px 0px">
									<a style="color: white; font-weight: bold;" href="<c:url value='/logout' />"><span class="glyphicon glyphicon-log-in"></span>&nbsp;Cerrar sesión</a>
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
<div class="container" style="width: 100% ; margin-top: 10em; ; z-index:2; position: absolute  ;">
		<div style="text-align: center; font-weight: bold;font-size: 20px" >MODIFICAR USUARIO</div>
	<br>
	<div class="contenidoUsuarios">
	<div class="contenedorFormulario">
	<div class="generic-container">
    
    <form:form method="POST" modelAttribute="usuario" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="soeid">Soeid *</label>
                <div class="col-md-7">
                    <form:input type="text" path="soeid" id="Soeid" class="form-control input-sm" readonly="true"/>
                    <div class="has-error errores">
                        <form:errors path="soeid" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
                <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="nombre">Nombre usuario *</label>
                <div class="col-md-7">
                    <form:input type="text" path="nombre" id="Ejecutivo" class="form-control input-sm" maxlength="40"/>
                    <div class="has-error errores">
                        <form:errors path="nombre" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="Nombre">Apellido paterno *</label>
                <div class="col-md-7">
                    <form:input type="text" path="apellidoPaterno" id="Nombre" class="form-control input-sm" maxlength="30"/>
                    <div class="has-error errores">
                        <form:errors path="apellidoPaterno" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="Nombre">Apellido materno</label>
                <div class="col-md-7">
                    <form:input type="text" path="apellidoMaterno" id="Nombre1" class="form-control input-sm" maxlength="30"/>
                    <div class="has-error errores">
                        <form:errors path="apellidoMaterno" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="telefono">Teléfono *</label>
                <div class="col-md-7">
                    <form:input type="text" path="telefono" id="Telefono" class="form-control input-sm" maxlength="15"/>
                    <div class="has-error errores">
                        <form:errors path="telefono" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Password</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="password" class="form-control input-sm" data-toggle="tooltip"
                     data-html="true"  maxlength="8"
                      title="1- Debe contener 8 caracteres <br> 2- No debe contener nombre ni apellido de usuario <br> 3- Al menos un caracter númerico <br> 4- Al menos una mayúscula <br> 5- Contener minúsculas <br> 6- No debe comenzar en cero <br>7- No debe contener palabras Banamex o Citi <br>8- No debe contener espacios en blanco <br>9- No debe repetirse mas de dos veces algún caracter de forma consecutiva"/>
                    <div class="has-error errores">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-2" id="fortalezaPassword" style="display:inline;font-weight:bold;padding:6px 12px;">
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="passwordConfirmation">Confirmación de contraseña</label>
                <div class="col-md-7">
                
                   
                    <form:input type="password" path="passwordConfirmation" id="passwordConfirmation" 
                    data-toggle="tooltip" data-html="true"  maxlength="8"
                     title="1- Debe contener 8 caracteres <br> 2- No debe contener nombre ni apellido de usuario <br> 3- Al menos un caracter númerico <br> 4- Al menos una mayúscula <br> 5- Contener minúsculas <br> 6- No debe comenzar en cero <br>7- No debe contener palabras Banamex o Citi <br>8- No debe contener espacios en blanco <br>9- No debe repetirse mas de dos veces algún caracter de forma consecutiva"
                    name="passwordConfirmation" class="form-control input-sm" value="${passwordConfirmation}"/>
                    <div class="has-error errores">
                        <form:errors path="passwordConfirmation" class="help-inline"/>
                    </div>
                   
                </div>
            </div>
        </div>
      
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="rol">Perfil *</label>
                <div class="col-md-7">
                	
                	<form:select path="rol.id" class="form-control input-sm">
						<form:options itemValue="id" itemLabel="nombre"  items="${roles}"></form:options>
					</form:select>
                	
                    <div class="has-error errores">
                        <form:errors path="rol" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div> 	
        
        <div class="row">
            <div class="form-actions floatRight">
                <c:choose>
                    <c:when test="${edit}">
                       <input type="submit" value="Editar"
											class="btn btn-primary custom-width" style="background-color: #013F7A" /> 
											
												<a href="<c:url value='/listarUsuarios' />"
										class="btn btn-primary custom-width" style="background-color: #941A26; border-color:#941A26 "> Cancelar</a>
								
                    </c:when>
                    <c:otherwise>
                    
                        <input type="submit" value="Agregar"
											class="btn btn-primary custom-width" style="background-color: #013F7A" 
											onclick="formatoSoeid()"/> 
											
												<a href="<c:url value='/listarUsuarios' />"
										class="btn btn-primary custom-width" style="background-color: #941A26; border-color:#941A26 "> Cancelar</a>
								
                    
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        
    </form:form>
    </div>
    </div>
    </div>
    
<br><br>
    
 
    </div>
            	<footer class="footerA" style="position: fixed; bottom: 0; width: 100%;">
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
	
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip(); 
    
    $('#password').strengthMeter('text', {
        container: $('#fortalezaPassword')
    });
    
});
</script>	

</html>