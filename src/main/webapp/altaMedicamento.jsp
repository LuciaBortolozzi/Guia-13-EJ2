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
    <title>Alta de Medicamentos</title>
    <script>
        function validar() {
            var idMed = document.getElementById("idMed");
            if (idMed.trim().length === 0 || isNaN(idMed))
                return false;
        }
    </script>
</head>
<body>
<form action="ingresoMedicamentos" onsubmit="validar()" method="post">
    <table>
        <tr>
            <td>ID Medicamento:</td>
            <td>
                <input type="text" id="idMed" name="idMed" />
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
    <input type="submit" name="btnIngresar" value="Ingresar"/>
    <input type="reset" value="Limpiar" />
</form>
</body>
</html>
