<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<h2>Login</h2>
<form action="/periodicals" name="loginForm" method="post">
    Login: <br>
    <input type="text" name="login" required/>
    </br>
    Password: <br>
    <input type="text" name="password" required/>
    <br/>
    <br/>
    <input type="submit" name="login" value="Login"/>
    <%--<c:if test="" message != null return message "Login or password not correct. Maybe you want sign up?"--%>
</form>
</body>
</html>