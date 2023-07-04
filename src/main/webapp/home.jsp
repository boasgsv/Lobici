<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 7/4/23
  Time: 7:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lobici</title>
</head>
<body>
<h>


<c:choose>
    <c:when test="${sessionScope.user_id == null}">
    <table>
        <tr>
            <td>
                <a href="login">Login</a>
            </td>
        </tr>
        <tr>
            <%@include file="deslogado/listar.jsp"%>
        </tr>
    </table>
    </c:when>
    <c:when test="${sessionScope.user_type == \"admin\"}">
    <table>
        <tr>
            <td>
                <a href="clientes">CRUD Clientes</a>
            </td>
            <td>
                <a href="locadoras">CRUD Locadoras</a>
            </td>

            <td>
                <a href="logout">Logout</a>
            </td>
        </tr>
    </table>
    </c:when>
    <c:when test="${sessionScope.user_type == \"cliente\"}">
    <table>
        <tr>
            <td>
                <a href="locacoes">Locacoes</a>
            </td>
            <td>
                <a href="logout">Logout</a>
            </td>
        </tr>

        <tr>
            <%@include file="logado/cliente/listar.jsp"%>
        </tr>
    </table>
    </c:when>
    <c:when test="${sessionScope.user_type == \"locadora\"}">
    <table>
        <tr>
            <td>
                <a href="locacoes">Locacoes</a>
            </td>
            <td>
                <a href="logout">Logout</a>
            </td>
        </tr>
    </table>
    </c:when>
</c:choose>
</h>
</body>
</html>
