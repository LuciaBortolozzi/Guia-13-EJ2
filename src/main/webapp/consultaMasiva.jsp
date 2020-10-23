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
    <title>Consulta Masiva</title>
</head>
<body>
<form action="consultaMasivaSRV" method="post">
    <table>
        <tr>
            <td>Provincia:</td>
            <td>
                <input type="text" name="nombreProv" />
            </td>
        </tr>
        <tr>
            <td>Tipo de sangre:</td>
            <td>
                <input type="text" name="tipoSangre" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Buscar"/>
</form>
</body>
</html>
