<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['payment.head']}"/>
    </title>
</head>
<body>
<h2>
    <c:out value="${sessionScope.properties['payment.title']}"/>
</h2>
<form action="/periodicals/payment/${sessionScope.payment.id}" method="post">
    <h4>
        <c:out value="${sessionScope.properties['payment.payer']}"/>
    </h4>
    <c:out value="${sessionScope.reader.lastName}
                  ${sessionScope.reader.firstName}
                  ${sessionScope.reader.middleName}"/>
    <h4>
        <c:out value="${sessionScope.properties['payment.email']}"/>
    </h4>
    <c:out value="${sessionScope.reader.email}"/>
    <h4>
        <c:out value="${sessionScope.properties['payment.periodicalName']}"/>
    </h4>
    <c:out value="${sessionScope.periodical.name}"/>
    <h4>
        <c:out value="${sessionScope.properties['payment.price']}"/>
    </h4>
    <c:out value="${sessionScope.payment.price} ₴"/>
    <br/>
    <br/>
    <input type="submit"
           name="pay"
           value="${sessionScope.properties['payment.pay']}"/>
</form>
</body>
</html>