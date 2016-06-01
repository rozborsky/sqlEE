<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>SQLCmd</title>
	</head>
	<body>
        <h1>sqlSMD</h1>
        <c:forEach items="${items}" var="item">
            <a href="${item}"> ${item}</a></br>
        </c:forEach>
	</body>
</html>