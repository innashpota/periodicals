<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h2>Log in</h2>
<form action="/admin/login" name="loginForm" method="post">
    <br>
    Login:
    </br>
    <label>
        <input type="text" name="login" maxlength="255" value="${sessionScope.login}" required/>
    </label>
    <br>
    Password:
    </br>
    <label>
        <input type="password" name="password" maxlength="255" value="${sessionScope.password}" required/>
    </label>
    <br/>
    <br>
    <input type="submit" name="login" value="Log in"/>
    </br>
    <br>
    <c:if test="${not empty message}">
        <span style="color: red; "> <c:out value="${message}"/> </span>
    </c:if>
</form>
</body>
</html>