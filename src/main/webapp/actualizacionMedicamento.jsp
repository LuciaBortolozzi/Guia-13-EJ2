<%--
  Created by IntelliJ IDEA.
  User: lucia
  Date: 10/19/2020
  Time: 6:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Modificacion y baja de Medicamento</title>
</head>
<body>
<form action="actualizacionMedicamento" method="post">
    <table>
        <tr>
            <td>ID Medicamento:</td>
            <td>
                <input disabled type="text" name="idMed" />
            </td>
        </tr>
        <tr>
            <td>Nombre Medicamento:</td>
            <td>
                <input type="text" name="nombreMed" />
            </td>
        </tr>
        <tr>
            <td>Nombre Laboratorio:</td>
            <td>
                <input type="text" name="nombreLab" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Ingresar"/>
</form>
</body>
</html>
