<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h2>Create new periodical</h2>
<form action="/edit-periodicals.jsp" method="post">
    <h4>Name:</h4>
    <input type="text" name="name" required/>
    <h4>Publisher:</h4>
    <input type="text" name="publisher" required/>
    <h4>Month price:</h4>
    <input type="number" name="monthPrice" required/>
    <br/>
    <br/>
    <input type="submit" name="add" value="Add periodical"/>
</form>
</body>
</html>