<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['signup.head']}"/>
    </title>
</head>
<body>
<h2>
    <c:out value="${sessionScope.properties['signup.title']}"/>
</h2>
<form action="<c:url value="/signup"/>" name="signupForm" method="post">
    <br>
    <c:out value="${sessionScope.properties['signup.lastName']}"/>
    <br>
    <label>
        <input type="text"
               name="lastName"
               maxlength="255"
               value="${sessionScope.lastName}"
               required/>
    </label>
    <br>
    <c:out value="${sessionScope.properties['signup.firstName']}"/>
    <br>
    <label>
        <input type="text"
               name="firstName"
               maxlength="255"
               value="${sessionScope.firstName}"
               required/>
    </label>
    <br>
    <c:out value="${sessionScope.properties['signup.middleName']}"/>
    <br>
    <label>
        <input type="text"
               name="middleName"
               maxlength="255"
               value="${sessionScope.middleName}"
               required/>
    </label>
    <br>
    <c:out value="${sessionScope.properties['signup.email']}"/>
    <br>
    <label>
        <input type="text"
               name="email"
               maxlength="255"
               value="${sessionScope.email}"
               required/>
    </label>
    <br>
    <c:out value="${sessionScope.properties['signup.password']}"/>
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
           name="continue"
           value="${sessionScope.properties['signup.continue']}"/>
    <br>
    <br>
    <jsp:include page="extensions/message.jsp"/>
</form>
</body>
</html>