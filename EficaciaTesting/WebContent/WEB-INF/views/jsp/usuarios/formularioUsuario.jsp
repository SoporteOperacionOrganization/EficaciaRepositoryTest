<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar Usuario</title>
<jsp:include page="../fragments/resources.jsp" />
<spring:url value="resources/js/password-score.js" var="passwordScoreJs" />
<spring:url value="resources/js/bootstrap-strength-meter.js" var="strength" />
<script src="${passwordScoreJs}"></script>
<script src="${strength}"></script>
<jsp:include page="../fragments/header.jsp" />

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

       <div class="container" style="width: 100% ; margin-top: 10em; ; z-index:1; position: absolute  ;">
             <div style="text-align: center; font-weight: bold;font-size: 20px" >Registrar usuarios</div>
             <br>
       <div class="contenidoUsuarios">
       <div class="contenedorFormulario">
       <div class="generic-container">
    
    <form:form method="POST" modelAttribute="usuario" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="soeid">SOEID *</label>
                <div class="col-md-7">
                    <form:input type="text" path="soeid" id="Soeid" class="form-control input-sm"   maxlength="7" style="text-transform:uppercase"/>
                    <div class="has-error errores">
                        <form:errors path="soeid" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="nombre">Nombre de usuario *</label>
                <div class="col-md-7">
                    <form:input type="text" path="nombre" id="nombre" class="form-control input-sm" maxlength="40"/>
                    <div class="has-error errores">
                        <form:errors path="nombre" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="apellidoPaterno">Apellido paterno *</label>
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
                <label class="col-md-3 control-lable" for="apellidoMaterno">Apellido materno</label>
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
                    <form:input type="text" path="telefono" id="Telefono" class="form-control input-sm" maxlength="15" />
                    <div class="has-error errores">
                        <form:errors path="telefono" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>
        
         <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="password">Contraseña *</label>
                <div class="col-md-7">
                    <form:input type="password" path="password" id="example-getting-started-input" maxlength="8" 
                     class="form-control input-sm"  data-toggle="tooltip" data-html="true"
                     title="1- Debe contener 8 caracteres <br> 2- No debe contener nombre ni apellido de usuario <br> 3- Al menos un caracter númerico <br> 4- Al menos una mayúscula <br> 5- Contener minúsculas <br> 6- No debe comenzar en cero <br>7- No debe contener palabras Banamex o Citi <br>8- No debe contener espacios en blanco <br>9- No debe repetirse mas de dos veces algún caracter de forma consecutiva" />               
                    <div class="has-error errores">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                </div>
                <div class="col-md-2" id="example-getting-started-text" style="display:inline;font-weight:bold;padding:6px 12px;">
                    </div>
            </div>
            
        </div>
        
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-3 control-lable" for="passwordConfirmation">Confirmación de contraseña *</label>
                <div class="col-md-7">
                    <form:input type="password" path="passwordConfirmation" id="passwordConfirmation" 
                    class="form-control input-sm" maxlength="8" 
                     data-toggle="tooltip" data-html="true" title="1- Debe contener 8 caracteres <br> 2- No debe contener nombre ni apellido de usuario <br> 3- Al menos un caracter númerico <br> 4- Al menos una mayúscula, <br> 5- Contener minúsculas <br> 6- No debe comenzar en cero <br>7- No debe contener palabras Banamex o Citi <br>8- No debe contener espacios en blanco <br>9- No debe repetirse mas de dos veces algún caracter de forma consecutiva"/>
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
        
        <br/>
        
        <div class="row">
            <div class="form-actions floatLeft">
                <c:choose>
                    <c:when test="${edit}">
                        <input type="submit" value="Modificar" class="btn btn-primary btn-sm"/> o <a href="<c:url value='/listarUsuarios' />">Cancelar</a>
                    </c:when>
                    <c:otherwise>
                                       <input type="submit" value="Agregar"
                                                                          class="btn btn-primary custom-width" style="background-color: #013F7A"
                                                                          onclick="formatoSoeid()" /> 
                                                                          
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
<jsp:include page="../fragments/footer.jsp"  />       
</div>

</html>