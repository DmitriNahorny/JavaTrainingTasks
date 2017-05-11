<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 4/25/2017
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Available songs</title>
</head>
<body>
<c:forEach var="song" items="${contentEntityList}" end="0">
    <c:set var="albumName" value="${song.albumName}" scope="page"/>
    <c:set var="artistId" value="${song.artistId}" scope="page"/>
</c:forEach>
<h3>'${albumName}' album songs</h3>
<hr/>
<table>
    <c:forEach var="song" items="${contentEntityList}" varStatus="status">
        <tr>
            <td><c:out value="${song.name}" /></td>
            <td>
                <c:if test="${user.discount > 0}"><s><fmt:formatNumber value="${song.price}" type="currency"/></s></c:if>
                <fmt:formatNumber value="${song.price * (100 - user.discount) / 100.0}" type="currency"/>
            </td>
            <td>
                <c:if test="${user.role == 'user'}">
                    <form method="post" action="controller">
                        <input type="hidden" name="command" value="order" />
                        <input type="hidden" name="usr" value="${user.uniqueId}" />
                        <input type="hidden" name="song" value="${song.id}" />
                        <input type="submit" value="Buy" />
                    </form>
                </c:if>
                <c:if test="${user.role == 'administrator'}">
                    <a href="pages/edit_song_form.jsp?id=${song.id}">edit</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
</table>
<hr/>
<form method="post" action="controller">
    <input type="hidden" name="command" value="library" />
    <input type="hidden" name="listLevel" value="album" />
    <input type="hidden" name="id" value="${artistId}" />
    <input type="submit" value="Go back" />
</form>
</body>
</html>
