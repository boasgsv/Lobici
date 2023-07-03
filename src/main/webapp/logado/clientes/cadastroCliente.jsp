<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${cliente != null}">
                Edição de Cliente
            </c:when>
            <c:otherwise>
                Cadastro de Cliente
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${cliente != null}">
        <input type="hidden" name="CPF" value="${cliente.CPF}" />
    </c:if>
    <tr>
        <td><label for="id_usuario">Id usuario</label></td>
        <td><input type="text" id="id_usuario" name="id_usuario" size="11" required value="${cliente.id}" /></td>
    </tr>
    <tr>
        <td><label for="CPF">CPF</label></td>
        <td><input type="text" id="CPF" name="CPF" size="11" required value="${cliente.CPF}" /></td>
    </tr>
    <tr>
        <td><label for="nome">Nome</label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${cliente.nome}" /></td>
    </tr>
    <tr>
        <td><label for="telefone">Telefone</label></td>
        <td><input type="text" id="telefone" name="telefone" size="15" required value="${cliente.telefone}" /></td>
    </tr>
    <tr>
        <td><label for="sexo">Sexo</label></td>
        <td>
            <select id="sexo" name="sexo">
                <option value="M" ${cliente.sexo == 'M' ? 'selected' : ''}>Masculino</option>
                <option value="F" ${cliente.sexo == 'F' ? 'selected' : ''}>Feminino</option>
            </select>
        </td>
    </tr>
    <tr>
        <td><label for="data_nascimento">Data de Nascimento</label></td>
        <td><input type="date" id="data_nascimento" name="data_nascimento" required value="${cliente.dataNascimento}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salvar" /></td>
    </tr>
</table>
