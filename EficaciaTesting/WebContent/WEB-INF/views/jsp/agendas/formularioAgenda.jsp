<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registrar agenda</title>
<jsp:include page="../fragments/resources.jsp" />
<spring:url value="resources/css/datepicker.css" var="datepickerCss" />
<spring:url value="resources/js/bootstrap-datepicker.js"
	var="datepickerJs" />
<link href="${datepickerCss}" rel="stylesheet"></link>
<script src="${datepickerJs}"></script>
<script>
	$(document).ready(function() {
		var fechaTemp = $("#fechaTransaccion").val().split("/");
		var fecha = new Date (fechaTemp[2], fechaTemp[1]-1, fechaTemp[0]);
		$('.datepicker').datepicker({
			onRender : function(date) {
				return date.valueOf() < fecha.valueOf() ? 'disabled' : '';
			},
			format : 'dd/mm/yyyy'
		});
	});
</script>
<jsp:include page="../fragments/header.jsp" />
</head>
<body>
	<div class="container"
		style="width: 100%; margin-top: 10em; z-index: 1; position: absolute;">
		<div style="text-align: center; font-weight: bold; font-size: 20px">Registrar
			agenda</div>
		<br>
		<div class="contenidoUsuarios">
			<div class="contenedorFormulario">
				<div class="generic-container">

					<form:form method="POST" modelAttribute="agenda"
						class="form-horizontal">
						<form:input type="hidden" path="id" id="id" />

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="fechaTransaccion">Fecha
									transacción</label>
								<div class="col-md-7">
									<form:input type="text" path="fechaTransaccion"
										id="fechaTransaccion" value="${fechaTransaccion}"
										class="form-control input-sm" readonly="true" />
									<div class="has-error errores">
										<form:errors path="fechaTransaccion" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="fechaCierre">Fecha
									cierre *</label>
								<div class="col-md-7">
									<form:input type="text" path="fechaCierre" id="fechaCierre"
										class="form-control input-sm datepicker" />
									<div class="has-error errores">
										<form:errors path="fechaCierre" class="help-inline" />

									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="Digitos">
								<label class="col-md-3 control-lable" for="numeroCliente">No.
									cliente *</label>
								<div class="col-md-7">
									<form:input type="text" path="numeroCliente" id="numeroCliente"
										class="form-control input-sm"  maxlength="5" />
									<div class="has-error errores">
										<form:errors path="numeroCliente" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="RazonSocial">
								<label class="col-md-3 control-lable" for="razonSocial">Razón
									social *</label>
								<div class="col-md-7">
									<form:input type="text" path="razonSocial" id="razonSocial"
										class="form-control input-sm" maxlength="60"/>
									<div class="has-error errores">
										<form:errors path="razonSocial" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="Nombre">
								<label class="col-md-3 control-lable" for="nombreRepresentante">Nombre
									representante *</label>
								<div class="col-md-7">
									<form:input type="text" path="nombreRepresentante"
										id="nombreRepresentante" class="form-control input-sm" maxlength="40"/>
									<div class="has-error errores">
										<form:errors path="nombreRepresentante" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="Telefono">
								<label class="col-md-3 control-lable" for="numeroTelefono">Teléfono *</label>
								<div class="col-md-7">
									<form:input type="text" path="numeroTelefono"
										id="numeroTelefono" class="form-control input-sm"  maxlength="15"/>
									<div class="has-error errores">
										<form:errors path="numeroTelefono" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="Soeid">
								<label class="col-md-3 control-lable" for="soeid">SOEID *</label>
								<div class="col-md-7">
									<form:input type="text" path="soeid" id="soeid"
										class="form-control input-sm"  maxlength="7" style="text-transform:uppercase"/>
									<div class="has-error errores">
										<form:errors path="soeid" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12" id="Ejecutivo">
								<label class="col-md-3 control-lable" for="ejecutivo">Ejecutivo *</label>
								<div class="col-md-7">
									<form:input type="text" path="ejecutivo" id="ejecutivo"
										class="form-control input-sm" maxlength="40"/>
									<div class="has-error errores">
										<form:errors path="ejecutivo" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="form-group col-md-12">
								<label class="col-md-3 control-lable" for="sede">Sede *</label>
								<div class="col-md-7">
									<form:select path="sede" class="form-control input-sm">
										<form:option label="--- Selecciona---" value="" />
										<form:option label="Cuajimalpa" value="Cuajimalpa" />
										<form:option label="Santa fé" value="Santa fé" />
									</form:select>
									<div class="has-error errores">
										<form:errors path="sede" class="help-inline" />
									</div>
								</div>
							</div>
						</div>

						<br />

						<div class="row">
							<div class="form-actions floatRight">
								<c:choose>
									<c:when test="${edit}">
										<input type="submit" value="Modificar"
											class="btn btn-primary custom-width" /> o <a
											href="<c:url value='/listarAgendas' />">Cancelar</a>
									</c:when>
									<c:otherwise>
										<input type="submit" value="Agregar"
											class="btn btn-primary custom-width"
											style="background-color: #013F7A" />

										<a href="<c:url value='/listarAgendas' />"
											class="btn btn-primary custom-width"
											style="background-color: #941A26; border-color: #941A26">
											Cancelar</a>

									</c:otherwise>
								</c:choose>
							</div>
						</div>

					</form:form>
				</div>
			</div>
		</div>
		<div style="width: 100%">
			<jsp:include page="../fragments/footer.jsp" />
		</div>
	</div>
</body>
</html>