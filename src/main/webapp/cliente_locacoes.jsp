<%--
  Created by IntelliJ IDEA.
  User: gsvboas
  Date: 7/1/23
  Time: 3:33 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Locacoes de Cliente</title>
</head>
<body>

    <table>
        <c:forEach items="${requestScope.locacoes}" var="locacao">
            <tr>
                <td>${locacao.id}</td>
                <td>${sessionScope.user_id}</td>
                <td>${locacao.locadoraId}</td>
                <td>${locacao.datahora}</td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
