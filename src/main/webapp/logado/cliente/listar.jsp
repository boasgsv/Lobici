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
                <th><fmt:message key="rent.datetime"/></th>
                <th><fmt:message key="rent.actions"/></th>
            </tr>
            <form method="post" action="criar">
            <c:forEach var="locadora" items="${requestScope.listaLocadoras}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${locadora.nome}</td>
                    <td>${locadora.CNPJ}</td>
                    <td>${locadora.cidade}</td>
                        <input type="hidden" value="${sessionScope.user_id}" name="cliente_id"/>
                        <input type="hidden" value="${locadora.id}" name="locadora_id"/>
                    <td>
                        <input type="datetime-local" name="datahora_locacao" id="datahora_locacao"/>
                    </td>
                    <td>
                        <input type="submit" value="<fmt:message key="rent.rent" />"/>
                    </td>

                </tr>
            </c:forEach>
            </form>
        </table>
    </div>
</body>
</fmt:bundle>
</html>
