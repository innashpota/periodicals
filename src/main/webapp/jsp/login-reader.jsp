<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h2>Log in</h2>
<form action="/periodicals" name="loginForm" method="post">
    <br>
    Email:
    </br>
    <label>
        <input type="text" name="email" maxlength="255" value="${email}"  required/>
    </label>
    <br>
    Password:
    </br>
    <label>
        <input type="text" name="password" maxlength="255" value="${password}"  required/>
    </label>
    <br/>
    <br>
    <input type="submit" name="login" value="Log in"/>
    Need an account? <a href="/signup"> Sign up</a>
    </br>
    <br>
    <c:if test="${not empty message}">
        <span style="color: red; "> Either user name or password is wrong. </span>
    </c:if>
</form>
</body>
</html>