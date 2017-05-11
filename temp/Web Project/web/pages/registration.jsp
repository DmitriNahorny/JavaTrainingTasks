<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 4/14/2017
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign Up</title>
</head>
<body>
    <form name="registrationForm" method="POST" action="${pageContext.request.contextPath}/controller">
        <input type="hidden" name="command" value="registration" />
        Login:<br/>
        <input type="text" name="login" value="" pattern="(\w{3,15})"/>
        <br/>Password:<br/>
        <input type="password" name="password" value="" pattern="(.{4,})"/>
        <br/>Confirm password:<br/>
        <input type="password" name="password_conf" value="" pattern="(.{4,})"/>
        <br/>Email address:<br/>
        <input type="text" name="email" value="" pattern="(\w{6,})@(\w+\.)([a-z]{2,4})"/>
        <br/>
        ${errorRegMessage}
        <br/>
        <input type="submit" value="Sign up"/>
        <br/>
        <a href="${pageContext.request.contextPath}/pages/login.jsp">Log in</a>
    </form>
    <hr/>
</body>
</html>
