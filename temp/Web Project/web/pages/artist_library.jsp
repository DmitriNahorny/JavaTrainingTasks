<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 4/20/2017
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Artist library</title>
</head>
<body>
<h3>Available artists</h3>
<hr/>
<ul>
    <c:forEach var="artist" items="${contentEntityList}" varStatus="status">
        <li><a href="?command=library&listLevel=album&id=${artist.id}"><c:out value="${artist.name}" /></a></li>
    </c:forEach>
</ul>
</body>
</html>
