<%--
  Created by IntelliJ IDEA.
  User: minnie
  Date: 6/24/25
  Time: 2:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>/security/admin page</h1>

<%--로그아웃 할 수 있는 폼 추가--%>
<form action = "/security/logout" method = "post"> <%--서버쪽 경로--%>
    <input type = "hidden" name = "${_csrf.parameterName}" value = "${_csrf.token}"/>
    <input type = "submit" value = "로그아웃"/>
</form>
</body>
</html>
