<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<c:if test="${empty sessionScope.reader and empty sessionScope.admin}">
    <div>
        <p>
        <form action="<c:url value="/login"/>" method="get">
            <input type="submit"
                   name="login"
                   value="${sessionScope.properties['periodicals.login']}"/>
        </form>
        <form action="<c:url value="/signup"/>" method="get">
            <input type="submit"
                   name="signup"
                   value="${sessionScope.properties['periodicals.signUp']}"/>
        </form>
    </div>
</c:if>

<c:if test="${not empty sessionScope.reader and empty sessionScope.admin}">
    <h4>
        <c:out value="${sessionScope.properties['periodicals.welcome']} ${sessionScope.reader.firstName}!"/>
    </h4>
    <div>
        <p>
        <form action="<c:url value="/profile"/>" method="get">
            <input type="submit"
                   name="profile"
                   value="${sessionScope.properties['periodicals.profile']}"/>
        </form>
        <form action="<c:url value="/logout"/>" method="get">
            <input type="submit"
                   name="logout"
                   value="${sessionScope.properties['periodicals.logout']}"/>
        </form>
    </div>
</c:if>

<c:if test="${empty sessionScope.reader and not empty sessionScope.admin}">
    <h4>
        <c:out value="${sessionScope.properties['periodicals.welcome']} ${sessionScope.admin.login}!"/>
    </h4>
    <div>
        <p>
            &nbsp;
            <a href="<c:url value="/edit-periodicals"/>">
                <c:out value="${sessionScope.properties['periodicals.editPeriodicals']}"/>
            </a>
            &nbsp;
        <form action="<c:url value="/logout"/>" method="get">
            <input type="submit"
                   name="logout"
                   value="${sessionScope.properties['periodicals.logout']}"/>
        </form>
    </div>
</c:if>