<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<h4>Welcome ${admin.login}!</h4>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>Name</th>
        <th>Publisher</th>
        <th>Month price</th>
        <th>Actions</th>
    </tr>
    <tbody>
    <c:forEach items="${periodicals}" var="periodical">
        <tr>
            <td>
                <c:out value="${periodical.name}"/>
            </td>
            <td>
                <c:out value="${periodical.publisher}"/>
            </td>
            <td>
                <c:out value="${periodical.monthPrice}"/>
            </td>
            <td>
                &nbsp;
                <a href="/periodicals/delete/${periodical.id}">
                    Delete
                </a>
                &nbsp;
                <a href="/periodicals/edit/${periodical.id}">
                    Edit
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
        <input type="submit"
               name="create"
               value="Add periodical"/>
    </form>
    <form action="/logout" method="get">
        <input type="submit"
               name="logout"
               value="Log out"/>
    </form>
    </p>
</div>
</body>
</html>