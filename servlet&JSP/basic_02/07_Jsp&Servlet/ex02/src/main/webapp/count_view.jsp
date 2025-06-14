<%--
  Created by IntelliJ IDEA.
  User: minnie
  Date: 6/14/25
  Time: 11:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>방문자 수 조회하기 화면</h1>
<%
    int count = (Integer) application.getAttribute("countValue");
%>
현재까지 총 방문자 수 : <%= count %>
</body>
</html>
