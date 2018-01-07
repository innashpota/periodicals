<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Periodicals</title>
</head>
<body>
<c:if test="${empty reader and empty admin}">
    <div>
        <p>
        <form action="/periodicals" method="get">
            <input type="submit"
                   name="login"
                   value="Log in"/>
        </form>
        <form action="/periodicals" method="get">
            <input type="submit"
                   name="signup"
                   value="Sign up"/>
        </form>
        </p>
    </div>
</c:if>
<c:if test="${not empty reader and empty admin}">
    <h4>Welcome ${reader.firstName}!</h4>
    <div>
        <p>
        <form action="/profile" method="get">
            <input type="submit"
                   name="profile"
                   value="View profile"/>
        </form>
        <form action="/logout" method="get">
            <input type="submit"
                   name="logout"
                   value="Log out"/>
        </form>
        </p>
    </div>
</c:if>
<c:if test="${empty reader and not empty admin}">
    <h4>Welcome ${admin.login}!</h4>
    <div>
        <p>
            &nbsp;
            <a href="/edit-periodicals">
                Edit periodicals
            </a>
            &nbsp;
        <form action="/logout" method="get">
            <input type="submit"
                   name="logout"
                   value="Log out"/>
        </form>
        </p>
    </div>
</c:if>

<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>Name</th>
        <th>Publisher</th>
        <th>Month price</th>
        <c:if test="${empty admin}">
            <th>Actions</th>
        </c:if>
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
            <c:if test="${empty admin}">
                <td>
                    &nbsp;
                    <a href="/periodicals/subscribe/${periodical.id}">
                        Subscribe
                    </a>
                    &nbsp;
                </td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>