<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${cliente != null}">
                <fmt:message key="edit.client" />
            </c:when>
            <c:otherwise>
                <fmt:message key="create.client" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${cliente != null}">
        <input type="hidden" name="id" value="${cliente.id}" />
    </c:if>
    <tr>
        <td><label for="email"><fmt:message key="user.email" /></label></td>
        <td><input type="text" id="email" name="email" size="11" required value="" /></td>
    </tr>
    <tr>w
        <td><label for="senha"><fmt:message key="user.password" /></label></td>
        <td><input type="text" id="senha" name="senha" size="11" required value="" /></td>
    </tr>
    <tr>
        <td><label for="CPF"><fmt:message key="cpf" /></label></td>
        <td><input type="text" id="CPF" name="CPF" size="11" required value="${cliente.CPF}" /></td>
    </tr>
    <tr>
        <td><label for="nome"><fmt:message key="name" /></label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${cliente.nome}" /></td>
    </tr>
    <tr>
        <td><label for="telefone"><fmt:message key="phone" /></label></td>
        <td><input type="text" id="telefone" name="telefone" size="15" required value="${cliente.telefone}" /></td>
    </tr>
    <tr>
        <td><label for="sexo"><fmt:message key="gender" /></label></td>
        <td>
            <select id="sexo" name="sexo">
                <option value="M" ${cliente.sexo == 'M' ? 'selected' : ''}><fmt:message key="male" /></option>
                <option value="F" ${cliente.sexo == 'F' ? 'selected' : ''}><fmt:message key="female" /></option>
            </select>
        </td>
    </tr>
    <tr>
        <td><label for="data_nascimento"><fmt:message key="birthdate" /></label></td>
        <td><input type="date" id="data_nascimento" name="data_nascimento" required value="${cliente.dataNascimento}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="<fmt:message key="save" />" /></td>
    </tr>
</table>
