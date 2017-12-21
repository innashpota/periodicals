<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Payment</title>
</head>
<body>
<h2>Payment </h2>
<h4>User email : ${email}</h4>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>Periodical name</th>
        <th>Month quantity</th>
        <th>Month price</th>
        <th>Price</th>
    </tr>
    <tbody>
    <tr>
        <td>
            <c:out value="${subscription.getPeriodicalsId().getName()}"/><%--periodicals.getName()--%>
        </td>
        <td>
            <c:out value="${subscription.getMonthQuantity()}"/>
        </td>
        <td>
            <c:out value="${subscription.periodical.getMonthPrice()}"/><%--periodicals.getMonthPrice()--%>
        </td>
        <td>
            <c:out value="${subscription.getPrice()}"/>
        </td>
    </tr>
    </tbody>
</table>

<div>
    <p>
    <form action="/periodicals" name="payment" method="post">
        <input type="submit" name="pay" value="Pay"/>
    </form>
    </p>
</div>
</body>
</html>