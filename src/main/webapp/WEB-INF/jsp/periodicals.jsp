<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['periodicals.head']}"/>
    </title>
</head>
<body>

<jsp:include page="extensions/user.jsp"/>

<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>
            <c:out value="${sessionScope.properties['periodicals.name']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['periodicals.publisher']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['periodicals.monthPrice']}"/>
        </th>
        <c:if test="${empty sessionScope.admin}">
            <th>
                <c:out value="${sessionScope.properties['periodicals.actions']}"/>
            </th>
        </c:if>
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
            <c:if test="${empty sessionScope.admin}">
                <td>
                    &nbsp;
                    <a href="/periodicals/subscribe/${periodical.id}">
                        <c:out value="${sessionScope.properties['periodicals.subscribe']}"/>
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