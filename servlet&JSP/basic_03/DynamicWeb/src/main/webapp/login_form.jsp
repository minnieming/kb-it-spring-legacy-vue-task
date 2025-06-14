<%--
  Created by IntelliJ IDEA.
  User: minnie
  Date: 6/14/25
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>getparameter 실습</h1>
<form action="login" method="post">
    <fieldset>
        <legend>로그인 폼</legend>
        <ul style = "list-style:none">
            <li>
                <label for = "userid">아이디</label>
                <input type="text" id="userid" name = "userid">
            </li>

            <li>
                <label for = "password">비밀번호</label>
                <input type="password" id="password" name = "password">
            </li>

            <li>
                <input type="submit" value="전송">
            </li>
        </ul>
    </fieldset>
</form>
</body>
</html>
