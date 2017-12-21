<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Periodicals</title>
</head>

<body>

<div>
    <p>
    <form action="/login" method="get">
        <input type="submit" name="login" value="Log in"/>
    </form>
    <form action="/signup" method="get">
        <input type="submit" name="signup" value="Sign up"/>
    </form>
    </p>
</div>

<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Publisher</th>
        <th>Month price</th>
        <th>Actions</th>
    </tr>
    <tbody>
    <c:forEach items="${periodicals}" var="periodical">
        <tr>
            <td>
                <c:out value="${periodical.getId()}"/><%--EDITED--%>
            </td>
            <td>
                <c:out value="${periodical.getName()}"/>
            </td>
            <td>
                <c:out value="${periodical.getPublisher()}"/>
            </td>
            <td>
                <c:out value="${periodical.getMonthPrice()}"/>
            </td>
            <td>
                &nbsp;
                <a href="/subscribe/${periodical.getId()}"> Subscribe</a>
                &nbsp;
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <p>
    <form action="/profile" method="get">
        <input type="submit" name="profile" value="View profile"/>
    </form>
    <form action="/logout" method="get">
        <input type="submit" name="logout" value="Log out"/>
    </form>
    </p>
</div>
</body>

</html>