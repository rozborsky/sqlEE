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
        	    <a href="mainPage"><h2>back</a></br>
             </div>

             <div class="content">
                 <h1>Error</h1>
                 <p>${message}</p>
                 <c:forEach items="${content}" var="row">
                      ${row}</br>
                 </c:forEach>
             </div>
        </div>
	</body>
</html>