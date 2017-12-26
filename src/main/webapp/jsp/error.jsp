<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Error</title>
</head>

<body>
<h1>Error</h1>

<h3>
    <c:if test="${not empty message}">
        <c:out value="${message}"/>
    </c:if>
    <c:if test="${empty message}">
        <c:out value="Internal error occurred. Please contact administrator."/>
    </c:if>
</h3>
</body>
</html>