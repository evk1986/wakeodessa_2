<%--
  Created by IntelliJ IDEA.
  User: Egor
  Date: 18.10.2016
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=IUTF-8">
    <title>LoginPage</title>
</head>
<body>
<form action="loginPage" method="post">
    <table>
        <tr>

            <td><input type="login" brandName="login" required></td>
        </tr>
        <tr>

            <td><input type="password" brandName="password" placeholder="password" required></td>
        </tr>
    </table>
    <input type="submit" value="Войти">
</form>
<%if (request.getAttribute("wrongLoginOrPass") != null) { %>
Неправильный логин или пароль.
<%}%>
</body>
</html>
