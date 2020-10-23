<%--
  Created by IntelliJ IDEA.
  User: Flor
  Date: 18/10/2020
  Time: 02:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Alta Extracciones</title>
    <script>
        function validar() {
            var idMed = document.getElementById("idMed");
            if (idMed.trim().length === 0 || isNaN(idMed))
                return false;
        }
    </script>
</head>
<body>
<form action="ingresoExtracciones" onsubmit="validar()" method="post">
    <table>
        <tr>
            <td>DNI donador:</td>
            <td>
                <input type="text" id="dni" name="dni" />
            </td>
        </tr>
        <tr>
            <td>Peso:</td>
            <td>
                <input type="text" name="peso" />
            </td>
        </tr>
        <tr>
            <td>Presion:</td>
            <td>
                <input type="text" name="presion" />
            </td>
        </tr>
        <tr>
            <td>Recuento globulos:</td>
            <td>
                <input type="text" name="recuentoGlobulos" />
            </td>
        </tr>
        <tr>
            <td>Cantidad extraida:</td>
            <td>
                <input type="text" name="cantExtraida" />
            </td>
        </tr>
    </table>
    <input type="submit" name="btnIngresar" value="Ingresar"/>
    <input type="reset" value="Limpiar" />
</form>
</body>
</html>
