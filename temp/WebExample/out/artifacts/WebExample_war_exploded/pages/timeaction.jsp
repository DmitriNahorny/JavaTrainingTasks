<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html><head><title>Result Page</title></head>
<body>
<p>Кнопка нажата через ${res} сек </p>
<p>Today is ${text_content}</p>
<form name="Home" action="index.jsp" method="POST">
    <input type="submit" name="button" value="Home"/>
</form>
</body>
</html>