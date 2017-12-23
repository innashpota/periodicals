<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Create</title>
</head>
<body>
<h2>Create new periodical</h2>

<form action="/create" method="post">
    <h4>Name:</h4>
    <input type="text" name="name" maxlength="255" required/>
    <h4>Publisher:</h4>
    <input type="text" name="publisher" maxlength="255" required/>
    <h4>Month price:</h4>
    <input type="number" name="monthPrice" placeholder="000.00" max="999.99"  min="0.01" step="0.01" required/>
    <br/>
    <br/>
    <input type="submit" name="add" value="Add periodical"/>
</form>
</body>
</html>