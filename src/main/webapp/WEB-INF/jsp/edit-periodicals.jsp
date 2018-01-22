<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['edit-periodicals.head']}"/>
    </title>
</head>
<body>
<div id="wrapper">
    <jsp:include page="extensions/welcome.jsp"/>
    <div id="fixed">
        <p>
        <form>
            <input formaction="<c:url value="/create"/>"
                   formmethod="get"
                   type="submit"
                   name="create"
                   value="${sessionScope.properties['edit-periodicals.create']}"/>
            &nbsp;
            <input formaction="<c:url value="/logout"/>"
                   formmethod="get"
                   type="submit"
                   name="logout"
                   value="${sessionScope.properties['edit-periodicals.logout']}"/>
            &nbsp;
            <jsp:include page="extensions/language.jsp"/>
        </form>
    </div>
    <div id="container">
        <table class="border">
            <tr>
                <th class="border">
                    <c:out value="${sessionScope.properties['edit-periodicals.name']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['edit-periodicals.publisher']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['edit-periodicals.monthPrice']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['edit-periodicals.actions']}"/>
                </th>
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
                    <td class="border">
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
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>