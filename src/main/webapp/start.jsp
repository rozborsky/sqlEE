<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>SQLCmd</title>
        <style>
           <%@include file='style.css' %>
        </style>
	</head>

	<body>
        <h1 class="startH1">SQL CMD</h1>
        <div class="logForm">
            <form action="connect" method="post">
                <table>
                    <tr>
                        <td>Database</td>
                        <td><input type="text" name="dbname" value="public"/></td>
                    </tr>
                    <tr>
                        <td>User name</td>
                        <td><input type="text" name="username" value="postgres"/></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" value="mainuser"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="connect"/></td>
                    </tr>
                </table>
            </form>
        </div>
	</body>
</html>