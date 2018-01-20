<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['edit-periodicals.head']}"/>
    </title>
</head>
<body>
<h4>
    <c:out value="${sessionScope.properties['edit-periodicals.title']} ${sessionScope.admin.login}!"/>
</h4>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>
            <c:out value="${sessionScope.properties['edit-periodicals.name']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['edit-periodicals.publisher']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['edit-periodicals.monthPrice']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['edit-periodicals.actions']}"/>
        </th>
    </tr>
    <tbody>
    <c:forEach items="${sessionScope.periodicals}" var="periodical">
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
                    <c:out value="${sessionScope.properties['edit-periodicals.delete']}"/>
                </a>
                &nbsp;
                <a href="/periodicals/edit/${periodical.id}">
                    <c:out value="${sessionScope.properties['edit-periodicals.edit']}"/>
                </a>
                &nbsp;
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div>
    <p>
    <form action="<c:url value="/create"/>" method="get">
        <input type="submit"
               name="create"
               value="${sessionScope.properties['edit-periodicals.create']}"/>
    </form>
    <form action="<c:url value="/logout"/>" method="get">
        <input type="submit"
               name="logout"
               value="${sessionScope.properties['edit-periodicals.logout']}"/>
    </form>
</div>
</body>
</html>