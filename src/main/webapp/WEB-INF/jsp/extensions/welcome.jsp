<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty sessionScope.reader and empty sessionScope.admin}">
    <h4>
        <c:out value="${sessionScope.properties['welcome']} ${sessionScope.reader.firstName}!"/>
    </h4>
</c:if>

<c:if test="${empty sessionScope.reader and not empty sessionScope.admin}">
    <h4>
        <c:out value="${sessionScope.properties['welcome']} ${sessionScope.admin.login}!"/>
    </h4>
</c:if>