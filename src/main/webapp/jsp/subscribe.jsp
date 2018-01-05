<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<html>
<head>
    <title>Subscribe</title>
</head>
<body>
<h2>Subscribe</h2>
<form action="/periodicals/subscribe/${periodical.id}" method="post">
    <h4>
        Periodical name:
    </h4>
    <c:out value="${periodical.name}"/>
    <h4>
        Month price:
    </h4>
    <c:out value="${periodical.monthPrice}"/>
    <h4>
        Month quantity:
    </h4>
    <input type="number"
           name="monthQuantity"
           placeholder="00"
           min="1"
           step="1"
           value="${sessionScope.monthQuantity}"
           required/>
    <br/>
    <br/>
    <input type="submit"
           name="continue"
           value="Continue"/>
</form>
</body>
</html>