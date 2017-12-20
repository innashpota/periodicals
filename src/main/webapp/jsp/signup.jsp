<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h2>Create your account</h2>
<form action="/periodicals" name="signupForm" method="post">
    Last name: <br>
    <input type="text" name="login" required/>
    </br>
    First name: <br>
    <input type="text" name="login" required/>
    </br>
    Middle name: <br>
    <input type="text" name="login" required/>
    </br>
    Email: <br>
    <input type="text" name="login" required/>
    </br>
    Password: <br>
    <input type="text" name="password" required/>
    <br/>
    <br/>
    <input type="submit" name="continue" value="Continue"/>
    <%--<c:if test="" message != null return message "This email used!"--%>
</form>
</body>
</html>