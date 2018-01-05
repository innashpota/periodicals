<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<h2>Profile</h2>
<h3>
    General information
</h3>
<table>
    <tbody>
    <tr>
        <td><b>Last name:</b></td>
        <td><c:out value="${reader.lastName}"/></td>
    </tr>
    <tr>
        <td><b>First name:</b></td>
        <td><c:out value="${reader.firstName}"/></td>
    </tr>
    <tr>
        <td><b>Middle name:</b></td>
        <td><c:out value="${reader.middleName}"/></td>
    </tr>
    <tr>
        <td><b>Email:</b></td>
        <td><c:out value="${reader.email}"/></td>
    </tr>
    </tbody>
</table>
<h3>
    Subscription
</h3>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>Name</th>
        <th>Publisher</th>
        <th>Month price</th>
        <th>Subscription date</th>
        <th>Month quantity</th>
        <th>Paid</th>
    </tr>
    <tbody>
    <c:forEach items="${information}" var="information">
        <tr>
            <td>
                <c:out value="${information.periodicalsName}"/>
            </td>
            <td>
                <c:out value="${information.periodicalsPublisher}"/>
            </td>
            <td>
                <c:out value="${information.periodicalsMonthPrice}"/>
            </td>
            <td>
                <c:out value="${information.subscriptionDate}"/>
            </td>
            <td>
                <c:out value="${information.monthQuantity}"/>
            </td>
            <td>
                <c:if test="${information.paymentPaid == true}">
                    YES
                </c:if>
                <c:if test="${information.paymentPaid == false}">
                    NO
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <p>
    <form action="/periodicals" method="get">
        <input type="submit"
               name="periodicals"
               value="Go to periodicals"/>
    </form>
    </p>
    <p>
    <form action="/logout" method="get">
        <input type="submit"
               name="logout"
               value="Log out"/>
    </form>
    </p>
</div>
</body>
</html>