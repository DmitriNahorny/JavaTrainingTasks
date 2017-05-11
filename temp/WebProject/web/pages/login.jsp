<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 3/30/2017
  Time: 3:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><head><title>Login</title></head>
<body>
<form name="loginForm" method="POST" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login" />
    Login:<br/>
    <input type="text" name="login" value=""/>
    <br/>Password:<br/>
    <input type="password" name="password" value=""/>
    <c:if test="${errorLoginPassMessage != null}">
        <br/>
        ${errorLoginPassMessage}
    </c:if>
    <c:if test="${wrongAction != null}">
        <br/>
        ${wrongAction}
    </c:if>
    <c:if test="${errorLoginPassMessage != null}">
        <br/>
        ${nullPage}
    </c:if>
    <br/>
    <input type="submit" value="Log in"/>
    <br/>
    <a href="${pageContext.request.contextPath}/pages/registration.jsp">Sign up</a>
</form><hr/>
</body></html>
