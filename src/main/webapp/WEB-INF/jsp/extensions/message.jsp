<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${not empty sessionScope.message}">
    <c:out value="${sessionScope.message}"/>
</c:if>