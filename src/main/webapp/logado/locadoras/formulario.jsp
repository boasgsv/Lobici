<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
<fmt:bundle basename="messages">

</head>

<body>
	<div align="center">
		<h1><fmt:message key="rental.management" /></h1>
		<h2>
			<a href="listar"><fmt:message key="rental.list" /></a>
		</h2>
	</div>
	<div align="center">
		<c:choose>
			<c:when test="${locadora != null}">
				<form action="atualizacao" method="post">
					<%@include file="cadastroLocadora.jsp"%>
				</form>
			</c:when>
			<c:otherwise>
				<form action="insercao" method="post">
					<%@include file="cadastroLocadora.jsp"%>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	<c:if test="${!empty requestScope.mensagens}">
		<ul class="erro">
			<c:forEach items="${requestScope.mensagens}" var="mensagem">
				<li>${mensagem}</li>
			</c:forEach>
		</ul>
	</c:if>
</body>
</fmt:bundle>
</html>