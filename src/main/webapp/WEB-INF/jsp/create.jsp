<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="stylesheet" href="<c:url value="/css/styles.css"/>"/>
    <title>
        <c:out value="${sessionScope.properties['create.head']}"/>
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
            <c:out value="${sessionScope.properties['create.title']}"/>
        </h2>
        <form action="<c:url value="/create"/>" method="post">
            <h4>
                <c:out value="${sessionScope.properties['create.name']}"/>
            </h4>
            <label>
                <input type="text"
                       name="name"
                       maxlength="255"
                       required/>
            </label>
            <h4>
                <c:out value="${sessionScope.properties['create.publisher']}"/>
            </h4>
            <label>
                <input type="text"
                       name="publisher"
                       maxlength="255"
                       required/>
            </label>
            <h4>
                <c:out value="${sessionScope.properties['create.monthPrice']}"/>
            </h4>
            <input type="number"
                   name="monthPrice"
                   placeholder="000.00"
                   max="999.99"
                   min="0.01"
                   step="0.01"
                   required/>
            <br/> <br/>
            <input type="submit"
                   name="add"
                   value="${sessionScope.properties['create.add']}"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>