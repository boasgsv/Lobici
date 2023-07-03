<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<fmt:bundle basename="message">

	<head>
<title><fmt:message key="page.title" /></title>
	</head>

	<body>
		<%
			String contextPath = request.getContextPath().replace("/", "");
		%>
		<div align="center">
			<h1>
				<fmt:message key="users.welcome" />
			</h1>
			<h2>
			    <a href="/<%=contextPath%>/locadoras"> 
			    	<fmt:message key="rental.crud" />
				</a> 
				&nbsp;&nbsp;&nbsp;
				<a href="/<%=contextPath%>/clientes"> 
					<fmt:message key="users.crud" />
				</a> 
				<br/>
				<br/>
				<a href="${pageContext.request.contextPath}/logout.jsp">
					<fmt:message key="exit.link" />
				</a>
			</h2>
			<br/>
		</div>
	</body>
</fmt:bundle>

</html>