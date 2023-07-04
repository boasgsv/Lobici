<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Locadora" %>

<html>
<fmt:bundle basename="messages">
<head>
    <title><fmt:message key="rental.listing" /></title>
</head>

<body>
    <div align="center">
        <h1>
            <table>
                <tr>
                    <td>
                        <a href="${pageContext.request.contextPath}"><fmt:message key="home"/></a>
                    </td>
                </tr>
                <tr>
                    <td>
                        <fmt:message key="rental.listing" />
                    </td>
                </tr>
            </table>

        </h1>
        <c:set var="contextPath" value="${pageContext.request.contextPath}" />
        <h2><a href="${contextPath}/locadoras/cadastro"><fmt:message key="rental.add" /></a></h2>
        <table border="1">
            <tr>
                <th>No.</th>
                <th><fmt:message key="rental.name" /></th>
                <th><fmt:message key="rental.cnpj" /></th>
                <th><fmt:message key="rental.city" /></th>
                <th><fmt:message key="rental.actions" /></th>
            </tr>
            <c:forEach var="locadora" items="${requestScope.listaLocadoras}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${locadora.nome}</td>
                    <td>${locadora.CNPJ}</td>
                    <td>${locadora.cidade}</td>
                    <td>
                        <a href="${contextPath}/locadoras/edicao?id=${locadora.id}"><fmt:message key="rental.edit" /></a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${contextPath}/locadoras/remocao?id=${locadora.id}"
                           onclick="return confirm('<fmt:message key="rental.confirm.delete" />');">
                            <fmt:message key="rental.delete" />
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</fmt:bundle>
</html>
