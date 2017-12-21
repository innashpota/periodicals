<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<html>

<head>
    <title>Periodicals</title>
</head>

<body>
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
                <a href="/edit-periodicals/${periodical.getId()}/delete">Delete</a>
                </a>
                &nbsp;
                <a href="/edit-periodicals/${periodical.getId()}/edit">Edit</a>
                </a>
                &nbsp;
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <p>
    <form action="/create" method="get">
        <input type="submit" name="create" value="Add periodical"/>
    </form>
    <form action="/logout" method="get">
        <input type="submit" name="logout" value="Log out"/>
    </form>
    </p>
</div>
</body>

</html>