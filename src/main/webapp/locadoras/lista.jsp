<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufscar.dc.dsw.domain.Cliente" %>

<html>
<head>
    <title>Listagem de Locadoras</title>
</head>

<body>
    <div align="center">
        <h1>Listagem de Locadoras</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>CNPJ</th>
                <th>Nome</th>
                <th>Cidade</th>
            </tr>
            <c:forEach var="locadora" items="${requestScope.listaLocadoras}">
                <tr>
                    <td>${locadora.id}</td>
                    <td>${locadora.CNPJ}</td>
                    <td>${locadora.nome}</td>
                    <td>${locadora.cidade}</td>
                    <td><a href="${pageContext.request.contextPath}/locadoras/edicao?id=${locadora.CNPJ}">Edição</a>
						&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="${pageContext.request.contextPath}/locadoras/remocao?id=${locadora.CNPJ}"
						onclick="return confirm('Tem certeza de que deseja excluir este item?');">
							Remoção</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>