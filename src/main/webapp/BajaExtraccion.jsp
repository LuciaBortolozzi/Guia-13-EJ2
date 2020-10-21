<%@ page import="java.io.IOException" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="model.Personas" %>
<%@ page import="model.DAO.PersonasDB" %><%--
  Created by IntelliJ IDEA.
  User: Flor
  Date: 18/10/2020
  Time: 02:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Eliminar Extracción</title>
</head>
<body>
<form action="consultaExtracciones" method="post">
    <table>
        <tr>
            <td>Documento:</td>
            <td>
                <input type="text" name="dni" />
            </td>
        </tr>
        <tr>
            <td>ID extraccion:</td>
            <td>
                <input type="text" name="idExt" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Buscar"/>
</form>
<form action="eliminarExtracciones" method="post">
    <input type="submit" value="Eliminar"/>
</form>
</body>
</html>
