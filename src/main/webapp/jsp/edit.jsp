<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2>
    Edit periodical <c:out value="\"${periodical.getName()}\""/>
</h2>
<form action="/edit-periodicals/${periodical.getId()}/edit" method="post">
    <h4>Name:</h4>
    <input type="text" name="name"
           maxlength="255"
           value="${periodical.getName()}"
           required
    />
    <h4>Publisher:</h4>
    <input type="text" name="publisher"
           maxlength="255"
           value="${periodical.getPublisher()}"
           required
    />
    <h4>Month price:</h4>
    <input type="number" name="monthPrice"
           placeholder="000.00"
           max="999.99"
           min="0.01"
           step="0.01"
           value="${periodical.getMonthPrice()}"
           required
    />
    <br/>
    <br/>
    <input type="submit" name="save" value="Save"/>
</form>
</body>
</html>