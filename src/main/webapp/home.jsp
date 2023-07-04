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
    <table>
        <tr>

            <c:choose>
                <c:when test="${sessionScope.user_id == null}">
                    <td>
                        <a href="login">Login</a>
                    </td>
                </c:when>
                <c:when test="${sessionScope.user_type == \"admin\"}">
                    <td>
                        <a href="clientes">CRUD Clientes</a>
                    </td>
                    <td>
                        <a href="locadoras">CRUD Locadoras</a>
                    </td>

                    <td>
                        <a href="logout">Logout</a>
                    </td>
                </c:when>
            </c:choose>
        </tr>
        <tr>
            <%@include file="deslogado/listar.jsp"%>
        </tr>
    </table>
</h>
</body>
</html>
