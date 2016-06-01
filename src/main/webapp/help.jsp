<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>SQLCmd</title>
	</head>
	<body>
	<h1>Help</h1>
	<c:forEach items="${items}" var="item">
        <a href="${item}"> ${item}</a></br>
    </c:forEach>

    <h2>HELP</h2>

                Available commands:</br>
                _____________________________________________________________________</br>
                list - for a list of all database tables</br>
                _____________________________________________________________________</br>
                find - to obtain the contents of the current table</br>
                _____________________________________________________________________</br>
                insert - to write to the current table</br>
                _____________________________________________________________________</br>
                update - to update current table</br>
                _____________________________________________________________________</br>
                delete - to delete row</br>
                _____________________________________________________________________</br>
                clear - to clean up the current table</br>
                _____________________________________________________________________</br>
                help - to read help</br>
                _____________________________________________________________________</br>
                exit - for exit from the program</br>
	</body>
</html>