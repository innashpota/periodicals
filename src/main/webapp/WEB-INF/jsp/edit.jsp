<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <link rel="shortcut icon" href="<c:url value="/image/favicon.png"/>"/>
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
            <br/>
            <table>
                <tbody>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['edit.name']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="name"
                                   maxlength="255"
                                   value="${sessionScope.periodical.name}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['edit.publisher']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="text"
                                   name="publisher"
                                   maxlength="255"
                                   value="${sessionScope.periodical.publisher}"
                                   required/>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td><b>
                        <c:out value="${sessionScope.properties['edit.monthPrice']}"/>
                    </b></td>
                    <td>
                        <label>
                            <input type="number"
                                   name="monthPrice"
                                   placeholder="000.00"
                                   max="999.99"
                                   min="0.01"
                                   step="0.01"
                                   value="${sessionScope.periodical.monthPrice}"
                                   required/>
                        </label>
                    </td>
                </tr>
                </tbody>
            </table>
            <br/>
            <input type="submit"
                   name="save"
                   value="${sessionScope.properties['edit.save']}"/>
        </form>
    </div>
</div>
<jsp:include page="extensions/copyright.jsp"/>
</body>
</html>