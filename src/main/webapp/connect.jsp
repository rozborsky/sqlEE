<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>SQLCmd</title>
	</head>
	<body>
        <h1>connect</h1>
        <c:forEach items="${items}" var="item">
            <a href="${item}"> ${item}</a></br>
        </c:forEach>
        <p>public postgres mainuser</p>
        <form action="connect" method="post">
            <table>
                <tr>
                    <td>Database name</td>
                    <td><input type="text" name="dbname"/></td>
                </tr>
                <tr>
                    <td>User name</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="connect"/></td>
                </tr>
            </table>
        </form>
	</body>
</html>