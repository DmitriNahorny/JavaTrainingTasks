<%--
  Created by IntelliJ IDEA.
  User: Dmitri_Nahorny
  Date: 3/30/2017
  Time: 3:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${userLocale}" />
<fmt:setBundle basename="properties.pagecontent" />
<html>
    <head><title>Welcome</title></head>
    <body>
        <form name="englishLanguage" method="POST" action="controller">
            <input type="hidden" name="command" value="locale" />
            <input type="hidden" name="selectedLocale" value="en_US"/> <!-- Use hidden value not type -->
            <input type="submit" value="EN"/>
        </form>
        <form name="russianLanguage" method="POST" action="controller">
            <input type="hidden" name="command" value="locale" />
            <input type="hidden" name="selectedLocale" value="ru_RU"/> <!-- Use hidden value not type -->
            <input type="submit" value="RU"/>
        </form>
        <hr/>
        <h3>Welcome</h3>
        <hr/>
        ${user.login}, <fmt:message key="message.welcome" />
        <c:if test="${orderConfirmation != null}">
            <hr/>
            ${orderConfirmation}
        </c:if>
        <c:if test="${techIssueMessage != null}">
            <hr/>
            ${techIssueMessage}
        </c:if>
        <hr/>
        <form name="libraryForm" method="POST" action="controller">
            <input type="hidden" name="command" value="library" />
            <input type="hidden" name="listLevel" value="artist" />
            <input type="submit" value="Music Library"/>
        </form>
        <hr/>
        <form name="loginForm" method="POST" action="controller">
            <input type="hidden" name="command" value="logout" />
            <input type="submit" value="Logout"/>
        </form>
    </body>
</html>
