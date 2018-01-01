<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Sign up</title>
</head>
<body>
<h2>Create your account</h2>
<form action="/signup" name="signupForm" method="post">
    <br>
    Last name:
    </br>
    <label>
        <input type="text" name="lastName"
               maxlength="255"
               value="${lastName}"
               required
        />
    </label>
    <br>
    First name:
    </br>
    <label>
        <input type="text" name="firstName"
               maxlength="255"
               value="${firstName}"
               required
        />
    </label>
    </br>
    Middle name:
    </br>
    <label>
        <input type="text" name="middleName"
               maxlength="255"
               value="${middleName}"
               required
        />
    </label>
    <br>
    Email:
    </br>
    <label>
        <input type="text" name="email"
               maxlength="255"
               value="${email}"
               required
        />
    </label>
    <br>
    Password:
    </br>
    <label>
        <input type="text" name="password"
               maxlength="255"
               value="${password}"
               required
        />
    </label>
    <br/>
    <br>
    <input type="submit" name="continue"
           value="Continue"
    />
    </br>
    <br>
    <c:if test="${not empty message}">
        <span style="color: red; ">
            <c:out value="${message}"/>
        </span>
    </c:if>
</form>
</body>
</html>