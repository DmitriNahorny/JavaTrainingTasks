<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head>
    <style>
        table, td, th {
            border: 1px solid black;
            border-collapse: collapse;
        }
    </style>
    <title>Result Page</title>
</head>
<body>
<table>
    <tr>
        <th>#</th>
        <th>Tariff Name</th>
        <th>Tariff Operator</th>
        <th>Tariff Payroll</th>
    </tr>
    <c:forEach var="elem" items="${text_content}" varStatus="status">
        <tr>
            <td><c:out value="${status.count}" /></td>
            <td><c:out value="${elem.tariffName}" /></td>
            <td><c:out value="${elem.operatorName}" /></td>
            <td><c:out value="${elem.payroll}" /></td>
        </tr>
    </c:forEach>
</table>
<form name="Home" action="index.jsp" method="POST">
    <input type="submit" name="button" value="Home"/>
</form>
</body>
</html>