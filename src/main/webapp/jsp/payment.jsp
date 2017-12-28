<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Payment</title>
</head>
<body>
<h2>Payment</h2>

<form action="/periodicals/payment/${payment.id}" method="post">
    <h4>Payer:</h4>
    <c:out value="${reader.lastName} ${reader.firstName} ${reader.middleName}"/>

    <h4>Email:</h4>
    <c:out value="${reader.email}"/>

    <h4>Periodical name:</h4>
    <c:out value="${periodical.name}"/>

    <h4>Price:</h4>
    <c:out value="${payment.price} ₴"/>
    <br/>
    <br/>
    <input type="submit" name="pay" value="Pay"/>
</form>

</body>
</html>