<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>SQLCmd</title>
	</head>
	<body>
        <h1>SQL CMD</h1>
        <h1>${settable}</h1>
        <c:forEach items="${items}" var="item">
            <a href="${item}"> ${item}</a></br>
        </c:forEach>

        <form action="selecttable" method="post">
                    <select name="table">
                    <option disabled>choose table</option>
                        <c:forEach items="${tables}" var="item">
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="ok"/>
                </form>

        <p>choose command</p>
        <form action="commandss" method="post">
                    <select name="tablee">
                        <c:forEach items="${commands}" var="itemd">
                            <option value="${itemd}">${itemd}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="ok"/>
                </form>

                <c:forEach items="${content}" var="itemda">
                    <a href="${itemda}"> ${itemda}</a></br>

                                        </c:forEach>
	</body>
</html>