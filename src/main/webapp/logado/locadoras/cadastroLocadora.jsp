<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<table border="1">
    <caption>
        <c:choose>
            <c:when test="${locadora != null}">
                <fmt:message key="edit.client" />
            </c:when>
            <c:otherwise>
                <fmt:message key="create.client" />
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${locadora != null}">
        <input type="hidden" name="id" value="${locadora.id}" />
    </c:if>
    <tr>
        <td><label for="email"><fmt:message key="user.email" /></label></td>
        <td><input type="text" id="email" name="email" size="11" required value="" /></td>
    </tr>
    <tr>
        <td><label for="senha"><fmt:message key="user.password" /></label></td>
        <td><input type="text" id="senha" name="senha" size="11" required value="" /></td>
    </tr>
    <tr>
        <td><label for="nome"><fmt:message key="rental.name" /></label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${locadora.nome}" /></td>
    </tr>
    <tr>
        <td><label for="CNPJ"><fmt:message key="rental.cnpj" /></label></td>
        <td><input type="text" id="CNPJ" name="CNPJ" size="11" required value="${locadora.CNPJ}" /></td>
    </tr>
    <tr>
        <td><label for="cidade"><fmt:message key="rental.city" /></label></td>
        <td><input type="text" id="cidade" name="cidade" size="15" required value="${locadora.cidade}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="<fmt:message key="save" />" /></td>
    </tr>
</table>
