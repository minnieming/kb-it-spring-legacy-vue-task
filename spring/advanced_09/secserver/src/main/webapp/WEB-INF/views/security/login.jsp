<%--
  Created by IntelliJ IDEA.
  User: minnie
  Date: 6/24/25
  Time: 3:09 PM
  To change this template use File | Settings | File Templates.
--%>
<body>
<h1>login</h1>
<form name='f' action='/security/login' method='POST'>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <table>
        <tr>
            <td>User:</td>
            <td><input type='text' name='username' value=''></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='password' name='password' /></td>
        </tr>
        <tr>
            <td colspan='2'>
                <input name="submit" type="submit" value="Login" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>
