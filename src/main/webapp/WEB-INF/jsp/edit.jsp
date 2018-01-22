<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['edit.head']}"/>
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
            <c:out value="${sessionScope.properties['edit.title']} \"${sessionScope.periodical.name}\""/>
        </h2>
        <form action="/periodicals/edit/${sessionScope.periodical.id}" method="post">
            <h4>
                <c:out value="${sessionScope.properties['edit.name']}"/>
            </h4>
            <label>
                <input type="text"
                       name="name"
                       maxlength="255"
                       value="${sessionScope.periodical.name}"
                       required/>
            </label>
            <h4>
                <c:out value="${sessionScope.properties['edit.publisher']}"/>
            </h4>
            <label>
                <input type="text"
                       name="publisher"
                       maxlength="255"
                       value="${sessionScope.periodical.publisher}"
                       required/>
            </label>
            <h4>
                <c:out value="${sessionScope.properties['edit.monthPrice']}"/>
            </h4>
            <input type="number"
                   name="monthPrice"
                   placeholder="000.00"
                   max="999.99"
                   min="0.01"
                   step="0.01"
                   value="${sessionScope.periodical.monthPrice}"
                   required/>
            <br/> <br/>
            <input type="submit"
                   name="save"
                   value="${sessionScope.properties['edit.save']}"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>