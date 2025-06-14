<%--
  Created by IntelliJ IDEA.
  User: minnie
  Date: 6/14/25
  Time: 11:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.invalidate();

    response.sendRedirect("loginForm.html");
%>
