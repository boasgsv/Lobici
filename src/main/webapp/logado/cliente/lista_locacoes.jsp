<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="br.ufscar.dc.dsw.domain.Cliente" %>

<html>
<fmt:bundle basename="messages">
  <head>
    <title><fmt:message key="rent.listing" /></title>
  </head>

  <body>
  <div align="center">
    <h1>
      <table>
        <tr>
          <td>
            <a href="${pageContext.request.contextPath}"><fmt:message key="home"/></a>
          </td>
        </tr>
        <tr>
          <td>
            <fmt:message key="rent.listing" />
          </td>
        </tr>
      </table>

    </h1>
    <c:set var="contextPath" value="${pageContext.request.contextPath}" />

    <table border="1">
      <tr>
        <th>No.</th>
        <th><fmt:message key="rent.id" /></th>
        <th><fmt:message key="rent.rental_cnpj" /></th>
        <th><fmt:message key="rent.rental_name" /></th>
        <th><fmt:message key="rent.date" /></th>
      </tr>
      <c:forEach var="i" begin="0" end="${locacoes.size() - 1}">
        <c:set var="locacao" value="${locacoes[i]}"/>
        <c:set var="locadora" value="${locadoras[i]}"/>
        <tr>
          <td>${i + 1}</td>
          <td>${locacao.id}</td>
          <td>${locadora.CNPJ}</td>
          <td>${locadora.nome}</td>
          <td>${locacao.datahora}</td>
        </tr>
      </c:forEach>
    </table>
  </div>
  </body>
</fmt:bundle>
</html>
