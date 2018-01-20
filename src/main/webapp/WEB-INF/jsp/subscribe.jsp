<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['subscribe.head']}"/>
    </title>
</head>
<body>
<h2>
    <c:out value="${sessionScope.properties['subscribe.title']}"/>
</h2>
<form action="/periodicals/subscribe/${sessionScope.periodical.id}" method="post">
    <h4>
        <c:out value="${sessionScope.properties['subscribe.name']}"/>
    </h4>
    <c:out value="${sessionScope.periodical.name}"/>
    <h4>
        <c:out value="${sessionScope.properties['subscribe.price']}"/>
    </h4>
    <c:out value="${sessionScope.periodical.monthPrice}"/>
    <h4>
        <c:out value="${sessionScope.properties['subscribe.quantity']}"/>
    </h4>
    <input type="number"
           name="monthQuantity"
           placeholder="00"
           min="1"
           step="1"
           value="${sessionScope.monthQuantity}"
           required/>
    <br/>
    <br/>
    <input type="submit"
           name="continue"
           value="${sessionScope.properties['subscribe.continue']}"/>
</form>
</body>
</html>