<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/image/favicon.png"/>" />
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['subscribe.head']}"/>
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
            <br/> <br/>
            <input type="submit"
                   name="continue"
                   value="${sessionScope.properties['subscribe.continue']}"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>