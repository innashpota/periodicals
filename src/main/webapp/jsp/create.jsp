<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<h2>Create new periodical</h2>
<form action="/edit-periodicals" method="post">
    <h4>Name:</h4>
    <input type="text" name="name" maxlength="255" required/>
    <h4>Publisher:</h4>
    <input type="text" name="publisher" maxlength="255" required/>
    <h4>Month price:</h4>
    <input type="number" name="monthPrice" placeholder="00000.00" maxlength="255" required/>
    <br/>
    <br/>
    <input type="submit" name="add" value="Add periodical"/>
</form>
</body>
</html>