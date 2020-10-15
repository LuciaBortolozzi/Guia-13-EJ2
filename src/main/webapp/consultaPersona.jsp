<%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 10/14/2020
  Time: 11:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta de Persona</title>
</head>
<body>
<form action="consultaPersonas" method="post">
    <table>
        <tr>
            <td>Documento:</td>
            <td>
                <input type="text" name="dni" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Buscar"/>
</form>
</body>
</html>
