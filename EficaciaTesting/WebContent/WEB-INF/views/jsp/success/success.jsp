<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
	<%@ taglib prefix="tag" uri="http://eficacia/paginacion.tld"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Agendas</title>
<jsp:include page="../fragments/resources.jsp" />
<jsp:include page="../fragments/header.jsp"  />
</head>
<body>
<div class="container" style="width: 100% ; margin-top: 10em; z-index:1; position: absolute;">
	<div class="container-fluid">
    	<div class="row">
        	<div class="col-md-6 col-md-offset-3">

				<c:if test="${not empty message}">
					<div class="alert alert-success">
						La operación ha sido exitosa. ${message} 
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>
</body>
<footer style="position: fixed; bottom: 0; width: 100%">
<jsp:include page="../fragments/footer.jsp"  />	
	</footer>
</html>