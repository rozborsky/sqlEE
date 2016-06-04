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
	    <div class="mainPageLayout">
	        <div class="leftLayout">
                <h2>select table</h2>
                <h1>${tableName}</h1>

                <form action="tableName" method="post">
                    <select name="table">

                        <c:forEach items="${tables}" var="item">
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="ok"/>
                </form>

                <h2>select command</h2>

                <form action="commandExecutor" method="post">
                    <select name="tablee">
                        <c:forEach items="${commands}" var="command">
                            <option value="${command}">${command}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="ok"/>
                </form>

                <c:forEach items="${items}" var="item">
                    <a href="${item}"><h2>${item}</h2></a></br>
                </c:forEach>
            </div>

            <div class="content">
                <c:forEach items="${content}" var="row">
                    ${row}</br>
                </c:forEach>
            </div>
        </div>
	</body>
</html>