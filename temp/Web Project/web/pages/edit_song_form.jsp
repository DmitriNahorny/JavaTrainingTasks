<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 5/10/2017
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit song</title>
</head>
<body>
    <h3>Edit song form</h3>
    <hr/>
    <form method="post" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="manager" />
        <input type="hidden" name="contentType" value="song" />
        <input type="hidden" name="actionType" value="edit" />
        <input type="hidden" name="songId" value="${param.songId}" />
        Song name:
        <input type="text" name="songName" value="${param.songName}" />
        <br/>
        Song price:
        <input type="text" name="songPrice" value="${param.songPrice}" />
        <input type="submit" value="Save and finish" />
    </form>
    <hr/>
</body>
</html>
