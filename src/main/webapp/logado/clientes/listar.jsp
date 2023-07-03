<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Cliente" %>

<html>
<fmt:bundle basename="messages">
<head>
    <title><fmt:message key="customer.listing" /></title>
</head>

<body>
    <div align="center">
        <h1><fmt:message key="customer.listing" /></h1>
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <h2><a href="${contextPath}/clientes/cadastro"><fmt:message key="customer.add" /></a></h2>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>CPF</th>
                <th><fmt:message key="customer.name" /></th>
                <th><fmt:message key="customer.phone" /></th>
                <th><fmt:message key="customer.gender" /></th>
                <th><fmt:message key="customer.birthdate" /></th>
                <th><fmt:message key="customer.actions" /></th>
            </tr>
            <c:forEach var="cliente" items="${requestScope.listaClientes}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.CPF}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.sexo}</td> 
                    <td>${cliente.dataNascimento}</td>
                    <td>
                        <a href="${contextPath}/clientes/edicao?id=${cliente.id}"><fmt:message key="customer.edit" /></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${contextPath}/clientes/remocao?id=${cliente.id}"
                           onclick="return confirm('<fmt:message key="customer.confirm.delete" />');">
                            <fmt:message key="customer.delete" />
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</fmt:bundle>
</html>
