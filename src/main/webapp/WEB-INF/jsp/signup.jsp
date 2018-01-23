<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/image/favicon.png"/>"/>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['signup.head']}"/>
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
            <c:out value="${sessionScope.properties['signup.title']}"/>
        </h2>
        <form action="<c:url value="/signup"/>" name="signupForm" method="post">
            <br/>
            <table>
                <tbody>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['signup.lastName']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="lastName"
                                   maxlength="255"
                                   value="${sessionScope.lastName}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['signup.firstName']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="firstName"
                                   maxlength="255"
                                   value="${sessionScope.firstName}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['signup.middleName']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="middleName"
                                   maxlength="255"
                                   value="${sessionScope.middleName}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['signup.email']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="email"
                                   maxlength="255"
                                   value="${sessionScope.email}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['signup.password']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="password"
                                   name="password"
                                   maxlength="255"
                                   value="${sessionScope.password}"
                                   required/>
                        </label>
                    </td>
                </tr>
                </tbody>
            </table>
            <br/>
            <input type="submit"
                   name="continue"
                   value="${sessionScope.properties['signup.continue']}"/>
            <br/> <br/>
            <jsp:include page="extensions/message.jsp"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>