<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="../css/bootstrap.css" type="text/css" />
</head>
<body>
<table class="table table-bordered">
    <tr>
        <td>Model</td>
        <td>ID</td>
        <td>Origin</td>
        <td>Length</td>
        <td>Height</td>
        <td>Width</td>
    </tr>
    <c:forEach items="${res}" var="element">
        <tr>
            <td>${element.model}</td>
            <td>${element.id}</td>
            <td>${element.origin}</td>
            <td>${element.length}</td>
            <td>${element.height}</td>
            <td>${element.width}</td>

        </tr>
    </c:forEach>
</table>
</body>
</html>
