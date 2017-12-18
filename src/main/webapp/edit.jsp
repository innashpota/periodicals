<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<h2>Edit periodical <c:out value="\"${periodical.getId()}\""/></h2>
<form action="/edit-periodicals.jsp" method="post">
    <h4>Name:</h4>
    <input type="text" name="name" value="${periodical.getName()}" required/>
    <h4>Publisher:</h4>
    <input type="text" name="publisher" value="${periodical.getPublisher()}" required/>
    <h4>Month price:</h4>
    <input type="number" name="monthPrice" value="${periodical.getMonthPrice()}" required/>
    <br/>
    <br/>
    <input type="submit" name="save" value="Save"/>
</form>
</body>
</html>