<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['profile.head']}"/>
    </title>
</head>
<body>
<div id="wrapper">
    <div id="fixed">
        <p>
        <form>
            <input formaction="<c:url value="/periodicals"/>"
                   formmethod="get"
                   type="submit"
                   name="periodicals"
                   value="${sessionScope.properties['profile.periodicals']}"/>
            &nbsp;
            <input formaction="<c:url value="/logout"/>"
                   formmethod="get"
                   type="submit"
                   name="logout"
                   value="${sessionScope.properties['profile.logout']}"/>
            &nbsp;
            <jsp:include page="extensions/language.jsp"/>
        </form>
    </div>
    <div id="container">
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
        <table class="border">
            <tr>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.name']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.publisher']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.monthPrice']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.date']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.quantity']}"/>
                </th>
                <th class="border">
                    <c:out value="${sessionScope.properties['profile.paid']}"/>
                </th>
            </tr>
            <tbody>
            <c:forEach items="${sessionScope.information}" var="information">
                <tr>
                    <td class="border">
                        <c:out value="${information.periodicalsName}"/>
                    </td>
                    <td class="border">
                        <c:out value="${information.periodicalsPublisher}"/>
                    </td>
                    <td class="center border">
                        <c:out value="${information.periodicalsMonthPrice}"/>
                    </td>
                    <td class="center border">
                        <c:out value="${sessionScope.formatter.format(information.subscriptionDate)}"/>
                    </td>
                    <td class="center border">
                        <c:out value="${information.monthQuantity}"/>
                    </td>
                    <td class="center border">
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
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>