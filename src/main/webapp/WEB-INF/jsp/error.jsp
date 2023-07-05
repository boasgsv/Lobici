<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<fmt:bundle basename="message">
<head>
  <title><fmt:message key="error.title"/></title>
  <style>
    body {
      background-color: #f2f2f2;
      color: #333;
      font-family: Arial, sans-serif;
      padding: 20px;
    }
    .error-message {
      background-color: #fff;
      border: 1px solid #ccc;
      padding: 20px;
      margin-bottom: 20px;
    }
  </style>
</head>
<body>
<h1><fmt:message key="error.header"/></h1>
<div class="error-message">
  <c:choose>
    <c:when test="${errorMessage == 'rental'}">
      <fmt:message key="error.rental"/>
    </c:when>
    <c:when test="${errorMessage == 'customer'}">
      <fmt:message key="error.customer"/>
    </c:when>
    <c:otherwise>
      <fmt:message key="error.another"/>${errorMessage}
    </c:otherwise>
  </c:choose>
</div>
<a href="${contextPath}/Lobici"><fmt:message key="error.back"/></a>
</body>
</fmt:bundle>
</html>