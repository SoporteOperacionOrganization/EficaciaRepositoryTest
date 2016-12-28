<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<spring:url value="resources/css/bootstrap.css" var="bootstrapCss" />
<spring:url value="resources/css/bootstrap.min.css" var="bootstrapCssMin" />
<spring:url value="resources/css/bootstrap-glyphicons.css" var="bootstrapIcons" />
<spring:url value="resources/css/customStyle.css" var="customCss" />
<spring:url value="resources/js/bootstrap.js" var="bootstrapJs" />
<spring:url value="resources/js/bootstrap.min.js" var="bootstrapJsMin" />
<spring:url value="resources/js/jquery-3.1.1.min.js" var="jquery311" />
<spring:url value="resources/js/customJs.js" var="customJs" />
<spring:url value="resources/js/funciones.js" var="funciones" />

<link href="${bootstrapCss}" rel="stylesheet" ></link>
<link href="${bootstrapCssMin}" rel="stylesheet" ></link>
<link href="${bootstrapIcons}" rel="stylesheet" ></link>
<link href="${customCss}" rel="stylesheet" ></link>
<script src="${jquery311}"></script>
<script src="${bootstrapJs}"></script>
<script src="${bootstrapJsMin}"></script>
<script src="${customJs}"></script>
<script src="${funciones}"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>