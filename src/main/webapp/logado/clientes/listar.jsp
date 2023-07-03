<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.ufscar.dc.dsw.domain.Cliente" %>

<html>
<head>
    <title>Listagem de Clientes</title>
</head>

<body>
    <div align="center">
        <h1>Listagem de Clientes</h1>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>CPF</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>Sexo</th>
                <th>Data de Nascimento</th>
                <th>Ações</th>
            </tr>
            <c:set var="contextPath" value="${pageContext.request.contextPath}" />
            <c:forEach var="cliente" items="${requestScope.listaClientes}">
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.CPF}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.telefone}</td>
                    <td>${cliente.sexo}</td> 
                    <td>${cliente.dataNascimento}</td>
                    <td>
                        <a href="${contextPath}/clientes/edicao?id=${cliente.id}">Edição</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="${contextPath}/clientes/remocao?id=${cliente.id}"
                           onclick="return confirm('Tem certeza de que deseja excluir este item?');">
                            Remoção
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
