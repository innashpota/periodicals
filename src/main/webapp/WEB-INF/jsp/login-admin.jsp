<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['login-admin.head']}"/>
    </title>
</head>
<body>
<h2>
    <c:out value="${sessionScope.properties['login-admin.title']}"/>
</h2>
<form action="<c:url value="/admin/login"/>" name="loginForm" method="post">
    <br>
    <c:out value="${sessionScope.properties['login-admin.login']}"/>
    <br>
    <label>
        <input type="text"
               name="login"
               maxlength="255"
               value="${sessionScope.login}"
               required/>
    </label>
    <br>
    <c:out value="${sessionScope.properties['login-admin.password']}"/>
    <br>
    <label>
        <input type="password"
               name="password"
               maxlength="255"
               value="${sessionScope.password}"
               required/>
    </label>
    <br/>
    <br>
    <input type="submit"
           name="login"
           value="${sessionScope.properties['login-admin.logIn']}"/>
    <br>
    <br>
    <jsp:include page="extensions/message.jsp"/>
</form>

<jsp:include page="extensions/copyright.jsp"/>

</body>
</html>