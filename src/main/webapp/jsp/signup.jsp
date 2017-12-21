<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h2>Create your account</h2>
<form action="/periodicals" name="signupForm" method="post">
    <br>
    Last name:
    </br>
    <label>
        <input type="text" name="lastName" required/>
    </label>
    <br>
    First name:
    </br>
    <label>
        <input type="text" name="firstName" required/>
    </label>
    </br>
    Middle name:
    </br>
    <label>
        <input type="text" name="middleName" required/>
    </label>
    <br>
    Email:
    </br>
    <label>
        <input type="text" name="email" required/>
    </label>
    <br>
    Password:
    </br>
    <label>
        <input type="text" name="password" required/>
    </label>
    <br/>
    <br>
    <input type="submit" name="continue" value="Continue"/>
    </br>
    <br>
    <c:if test="${not empty message}">
        <span style="color: red; "> This email used! </span>
    </c:if>
</form>
</body>
</html>