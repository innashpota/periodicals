<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2>
    Edit periodical <c:out value="\"${periodical.name}\""/>
</h2>
<form action="/periodicals/edit/${periodical.id}" method="post">
    <h4>Name:</h4>
    <input type="text"
           name="name"
           maxlength="255"
           value="${periodical.name}"
           required/>
    <h4>Publisher:</h4>
    <input type="text"
           name="publisher"
           maxlength="255"
           value="${periodical.publisher}"
           required/>
    <h4>Month price:</h4>
    <input type="number"
           name="monthPrice"
           placeholder="000.00"
           max="999.99"
           min="0.01"
           step="0.01"
           value="${periodical.monthPrice}"
           required/>
    <br/>
    <br/>
    <input type="submit"
           name="save"
           value="Save"/>
</form>
</body>
</html>