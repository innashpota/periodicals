<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['login-reader.head']}"/>
    </title>
</head>
<body>
<div id="wrapper">
    <div id="fixed">
        <p>
        <form>
            <jsp:include page="extensions/language.jsp"/>
        </form>
    </div>
    <div id="container">
        <h2>
            <c:out value="${sessionScope.properties['login-reader.title']}"/>
        </h2>
        <form action="<c:url value="/login"/>" name="loginForm" method="post">
            <br/>
            <c:out value="${sessionScope.properties['login-reader.email']}"/>
            <br/>
            <label>
                <input type="text"
                       name="email"
                       maxlength="255"
                       value="${sessionScope.email}"
                       required/>
            </label>
            <br/>
            <c:out value="${sessionScope.properties['login-reader.password']}"/>
            <br/>
            <label>
                <input type="password"
                       name="password"
                       maxlength="255"
                       value="${sessionScope.password}"
                       required/>
            </label>
            <br/> <br/>
            <input type="submit"
                   name="login"
                   value="${sessionScope.properties['login-reader.login']}"/>
            <c:out value="${sessionScope.properties['login-reader.question']}"/>
            <a href="<c:url value="/signup"/>">
                <c:out value="${sessionScope.properties['login-reader.signUp']}"/>
            </a>
            <br/> <br/>
            <jsp:include page="extensions/message.jsp"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>