<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/image/favicon.png"/>" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['payment.head']}"/>
    </title>
</head>
<body>
<div id="wrapper">
    <div id="fixed">
        <p>
        <form>
            <jsp:include page="extensions/language.jsp"/>
        </form>
    </div>
    <div id="container">
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
            <br/> <br/>
            <input type="submit"
                   name="pay"
                   value="${sessionScope.properties['payment.pay']}"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>