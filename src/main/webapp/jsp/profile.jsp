<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h2>Profile</h2>
<h4>General information</h4>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>Last name</th>
        <th>First name</th>
        <th>Middle name</th>
        <th>Email</th>
    </tr>
    <tbody>
    <tr>
        <td>
            <c:out value="${reader.getLastName()}"/>
        </td>
        <td>
            <c:out value="${reader.getFirstName()}"/>
        </td>
        <td>
            <c:out value="${reader.getMiddleName()}"/>
        </td>
        <td>
            <c:out value="${reader.getEmail()}"/>
        </td>
    </tr>
    </tbody>
</table>
<h4>Subscription</h4>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Publisher</th>
        <th>Month price</th>
        <th>Subscription date</th>
        <th>Paid</th>
    </tr>
    <tbody>
    <c:forEach items="${subscription}" var="subscription">
        <tr>
            <td>
                <c:out value="${subscription.periodicals.getId()}"/><%--EDITED--%>
            </td>
            <td>
                <c:out value="${subscription.periodicals.getName()}"/>
            </td>
            <td>
                <c:out value="${subscription.periodicals.getPublisher()}"/>
            </td>
            <td>
                <c:out value="${subscription.periodicals.getMonthPrice()}"/>
            </td>
            <td>
                <c:out value="${subscription.periodicals.getDate()}"/>
            </td>
            <td>
                    <%--<c:out value="${}"/>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<a href="" onclick="history.back()"> Back to Previous Page </a>
<div>
    <p>
    <form action="/logout" method="get">
        <input type="submit" name="logout" value="Log out"/>
    </form>
    </p>
</div>
</body>
</html>