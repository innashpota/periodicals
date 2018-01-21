<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<jsp:include page="extensions/language.jsp"/>

<html>
<head>
    <title>
        <c:out value="${sessionScope.properties['profile.head']}"/>
    </title>
</head>
<body>
<h2>
    <c:out value="${sessionScope.properties['profile.title']}"/>
</h2>
<h3>
    <c:out value="${sessionScope.properties['profile.general']}"/>
</h3>
<table>
    <tbody>
    <tr>
        <td><b>
            <c:out value="${sessionScope.properties['profile.lastName']}"/>
        </b></td>
        <td><c:out value="${sessionScope.reader.lastName}"/></td>
    </tr>
    <tr>
        <td><b>
            <c:out value="${sessionScope.properties['profile.firstName']}"/>
        </b></td>
        <td><c:out value="${sessionScope.reader.firstName}"/></td>
    </tr>
    <tr>
        <td><b>
            <c:out value="${sessionScope.properties['profile.middleName']}"/>
        </b></td>
        <td><c:out value="${sessionScope.reader.middleName}"/></td>
    </tr>
    <tr>
        <td><b>
            <c:out value="${sessionScope.properties['profile.email']}"/>
        </b></td>
        <td><c:out value="${sessionScope.reader.email}"/></td>
    </tr>
    </tbody>
</table>
<h3>
    <c:out value="${sessionScope.properties['profile.subscription']}"/>
</h3>
<table cellspacing="0" cellpadding="1" border="1">
    <tr>
        <th>
            <c:out value="${sessionScope.properties['profile.name']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['profile.publisher']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['profile.monthPrice']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['profile.date']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['profile.quantity']}"/>
        </th>
        <th>
            <c:out value="${sessionScope.properties['profile.paid']}"/>
        </th>
    </tr>
    <tbody>
    <c:forEach items="${sessionScope.information}" var="information">
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
                    <c:out value="${sessionScope.properties['profile.yes']}"/>
                </c:if>
                <c:if test="${information.paymentPaid == false}">
                    <c:out value="${sessionScope.properties['profile.no']}"/>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <p>
    <form action="<c:url value="/periodicals"/>" method="get">
        <input type="submit"
               name="periodicals"
               value="${sessionScope.properties['profile.periodicals']}"/>
    </form>
    <p>
    <form action="<c:url value="/logout"/>" method="get">
        <input type="submit"
               name="logout"
               value="${sessionScope.properties['profile.logout']}"/>
    </form>
</div>

<jsp:include page="extensions/copyright.jsp"/>

</body>
</html>