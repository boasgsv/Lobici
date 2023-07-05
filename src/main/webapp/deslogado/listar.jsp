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
    <form action="${contextPath}/Lobici" method="get">
        <select name="cidade" onchange="this.form.submit()">
            <option value="" selected disabled><fmt:message key="rental.prompt_filter_city"/></option>
            <c:forEach var="cidade" items="${requestScope.listaCidades}">
                <option value="${cidade}">${cidade}</option>
            </c:forEach>
        </select>
    </form>
    <div align="center">
        <h1>
            <table>
                <tr>
                    <td>
                        <fmt:message key="rental.listing" />
                    </td>
                </tr>
            </table>

        </h1>
        <table border="1">
            <tr>
                <th>No.</th>
                <th><fmt:message key="rental.name" /></th>
                <th><fmt:message key="rental.cnpj" /></th>
                <th><fmt:message key="rental.city" /></th>
            </tr>
            <c:forEach var="locadora" items="${requestScope.listaLocadorasSelecionadas}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${locadora.nome}</td>
                    <td>${locadora.CNPJ}</td>
                    <td>${locadora.cidade}</td>

                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</fmt:bundle>
</html>
