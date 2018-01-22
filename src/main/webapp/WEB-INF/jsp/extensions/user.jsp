<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${empty sessionScope.reader and empty sessionScope.admin}">
    <input formaction="<c:url value="/login"/>"
           formmethod="get"
           type="submit"
           name="login"
           value="${sessionScope.properties['periodicals.login']}">
    &nbsp;
    <input formaction="<c:url value="/signup"/>"
           formmethod="get"
           type="submit"
           name="signup"
           value="${sessionScope.properties['periodicals.signUp']}">
</c:if>

<c:if test="${not empty sessionScope.reader and empty sessionScope.admin}">
    <input formaction="<c:url value="/profile"/>"
           formmethod="get"
           type="submit"
           name="profile"
           value="${sessionScope.properties['periodicals.profile']}"/>
    &nbsp;
    <input formaction="<c:url value="/logout"/>"
           formmethod="get"
           type="submit"
           name="logout"
           value="${sessionScope.properties['periodicals.logout']}"/>
</c:if>

<c:if test="${empty sessionScope.reader and not empty sessionScope.admin}">
    <input formaction="<c:url value="/edit-periodicals"/>"
           formmethod="get"
           type="submit"
           name="edit-periodicals"
           value="${sessionScope.properties['periodicals.editPeriodicals']}"/>
    &nbsp;
    <input formaction="<c:url value="/logout"/>"
           formmethod="get"
           type="submit"
           name="logout"
           value="${sessionScope.properties['periodicals.logout']}"/>
</c:if>