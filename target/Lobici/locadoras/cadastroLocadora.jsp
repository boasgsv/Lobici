<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<table border="1">
    <caption>
        <c:choose>
            <c:when test="${locadora != null}">
                Edição de Locadora
            </c:when>
            <c:otherwise>
                Cadastro de Locadora
            </c:otherwise>
        </c:choose>
    </caption>
    <c:if test="${locadora != null}">
        <input type="hidden" name="CNPJ" value="${locadora.CNPJ}" />
    </c:if>
    <tr>
        <td><label for="CNPJ">CNPJ</label></td>
        <td><input type="text" id="CNPJ" name="CNPJ" size="11" required value="${locadora.CNPJ}" /></td>
    </tr>
    <tr>
        <td><label for="nome">Nome</label></td>
        <td><input type="text" id="nome" name="nome" size="45" required value="${locadora.nome}" /></td>
    </tr>
    <tr>
        <td><label for="cidade">Cidade</label></td>
        <td><input type="text" id="cidade" name="cidade" size="15" required value="${locadora.telefone}" /></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><input type="submit" value="Salva" /></td>
    </tr>
</table>