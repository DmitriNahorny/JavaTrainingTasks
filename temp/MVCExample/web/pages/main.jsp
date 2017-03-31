<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 3/30/2017
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html><head><title>Welcome</title></head>
<body>
<h3>Welcome</h3>
<hr/>
${user}, hello!
<hr/>
<form name="loginForm" method="POST" action="controller">
    <input type="hidden" name="command" value="logout" />
    <input type="submit" value="Logout"/>
</form>
</body></html>
