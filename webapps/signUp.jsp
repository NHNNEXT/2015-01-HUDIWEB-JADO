<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jado</title>
</head>
<body>
    <%@ include file="/include/top.jspf" %>
    <h1>SHOPPING!!!</h1>
    <c:choose>
    <c:when test="${empty userId}">
		<%@ include file="/include/signForm.jspf" %>
	</c:when>
	<c:otherwise>
		<h1>WELCOME!!!</h1>
	</c:otherwise>
	</c:choose>
</body>
</html>