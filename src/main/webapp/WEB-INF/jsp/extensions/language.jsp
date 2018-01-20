<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>

<form>
    <select id="language" name="language" onchange="submit()" title="SelectLanguage">
        <option value="en" ${sessionScope.language == 'en' ? 'selected' : ''}>
            <c:out value="${sessionScope.properties['language.en']}"/>
        </option>
        <option value="ua" ${sessionScope.language == 'ua' ? 'selected' : ''}>
            <c:out value="${sessionScope.properties['language.ua']}"/>
        </option>
    </select>
</form>