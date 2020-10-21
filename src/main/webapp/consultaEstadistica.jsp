<%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 10/14/2020
  Time: 11:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consultar estadisticas</title>
</head>
<body>
<h1>Consultar estadisticas</h1>
<form action="estadisticas" method="post">
    <tr>
        <td>Ingrese un peso:</td>
        <td>
            <input type="text" name="peso" />
        </td>
    </tr>
    <input type="submit" value="Consultar"/>
</form>
</body>
</html>
