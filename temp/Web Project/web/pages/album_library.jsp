<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 4/23/2017
  Time: 7:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Album library</title>
</head>
<body>
<c:forEach var="album" items="${contentEntityList}" end="0">
    <c:set var="artist" value="${album.artistName}" scope="page"/>
</c:forEach>
<h3>'${artist}' available albums</h3>
<hr/>
<ul>
    <c:forEach var="album" items="${contentEntityList}" varStatus="status">
        <li>
            <a href="?command=library&listLevel=song&id=${album.id}"><c:out value="${album.name}" /></a>
        </li>
    </c:forEach>
</ul>
<hr/>
<form method="post" action="controller">
    <input type="hidden" name="command" value="library">
    <input type="hidden" name="listLevel" value="artist">
    <input type="submit" value="Go back" />
</form>
</body>
</html>
