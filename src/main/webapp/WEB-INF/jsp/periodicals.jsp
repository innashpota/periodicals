<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/image/favicon.png"/>" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['periodicals.head']}"/>
    </title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="extensions/welcome.jsp"/>
    <div id="fixed">
        <p>
        <form>
            <jsp:include page="extensions/user.jsp"/>
            &nbsp;
            <jsp:include page="extensions/language.jsp"/>
        </form>
    </div>
    <div id="container">
        <table class="border">
            <tr>
                <th class="border">
                    <c:out value="${sessionScope.properties['periodicals.name']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['periodicals.publisher']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['periodicals.monthPrice']}"/>
                </th>
                <c:if test="${empty sessionScope.admin}">
                    <th class="border">
                        <c:out value="${sessionScope.properties['periodicals.actions']}"/>
                    </th>
                </c:if>
            </tr>
            <tbody>
            <c:forEach items="${sessionScope.periodicals}" var="periodical">
                <tr>
                    <td class="border">
                        <c:out value="${periodical.name}"/>
                    </td>
                    <td class="border">
                        <c:out value="${periodical.publisher}"/>
                    </td>
                    <td class="center border">
                        <c:out value="${periodical.monthPrice}"/>
                    </td>
                    <c:if test="${empty sessionScope.admin}">
                        <td class="border">
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
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>