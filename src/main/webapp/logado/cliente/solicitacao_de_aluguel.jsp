<html>
<fmt:bundle basename="messages">
<head>
  <title>Rent Request</title>
</head>
<body>
<c:if test="${requestScope.erros.existeErros}">
  <div id="erro">
    <ul>
      <c:forEach var="erro" items="${requestScope.erros.erros}">
        <li> ${erro} </li>
      </c:forEach>
    </ul>
  </div>
</c:if>
<form action="locacoes/criar" method="post">
  <label for="locadora_id"><fmt:message key="rent.select_rental_prompt"/>:</label><br>
  <select name="locadora_id" id="locadora_id">
    <c:forEach var="locadora" items="${requestScope.listaLocadoras}" varStatus="status">
      <option value="${locadora.id}">${status.count} - ${locadora.nome}</option>
    </c:forEach>
  </select><br>
  <label for="datahora_locacao"><fmt:message key="rent.select_datetime_prompt"/>:</label><br>
  <input type="datetime-local" id="datahora_locacao" name="datahora_locacao"><br>
  <input type="hidden" value="${sessionScope.user_id}" id="cliente_id" name="cliente_id">
  <input type="submit" value="<fmt:message key="rent.submit_rent_request"/>">
</form>
</body>
</fmt:bundle>
</html>