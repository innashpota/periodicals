<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['error.head']}"/>
    </title>
</head>
<body>
<h1>
    <c:out value="${sessionScope.properties['error.title']}"/>
</h1>
<h3>
    <jsp:include page="extensions/message.jsp"/>
    <c:if test="${empty sessionScope.message}">
        <c:out value="${sessionScope.properties['error.defaultMessage']}"/>
    </c:if>
</h3>

<jsp:include page="extensions/copyright.jsp"/>

</body>
</html>