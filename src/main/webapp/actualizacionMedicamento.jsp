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
    <title>Actualizacion y baja de Medicamento</title>
</head>
<script>

    function validar(){
        var idMed = document.getElementById("idMed");
        if (idMed.trim().length === 0 || isNaN(idMed))
            return false;
    }

    function habilitar()
    {
        document.getElementById("nombreMed").disabled=false;
        document.getElementById("nombreLab").disabled=false;
        document.getElementById("btnIdConfirmaModificar").disabled=false;
    }

    function limpiar()
    {
        document.getElementById("idMed").value="";
        document.getElementById("nombreMed").value="";
        document.getElementById("nombreLab").value="";
        session.setAttribute("idMed", "");
        session.setAttribute("nombreMed", "");
        session.setAttribute("nombreLab", "");
    }

</script>

<body >
<h3>Actualizacion y baja de Medicamento</h3>
<br><br>
<form name="Consulta" action="consultaMedicamentosSRV" onsubmit="validar()" method="POST">
    <table>
        <tr>
            <td>ID Medicamento:</td>
            <td>
                <input type="text" name="txtIDMed" id="idMed" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Buscar" />
    <td><input type="reset" name="btnCancela" value="Cancela" /> </td>
</form>

<%
    String idMedAux = (String) session.getAttribute("idMed");
    String nombreMed = (String) session.getAttribute("nombreMed");
    String nombreLab = (String) session.getAttribute("nombreLab");
%>
<br><br>
<form name="Modifica" action="modificarMedicamentosSRV" onsubmit="validar()" >
    <table>
        <tr>
            <td>ID Medicamento:</td>
            <td><input type="text" name="idMed" id="idMedAux" disabled value="<%=idMedAux == null ? "" : idMedAux%>" /></td>
        </tr>
        <tr><td> Nombre Medicamento: </td>
            <td><input type="text" name="nombreMed" id="nombreMed" disabled
                       value="<%=nombreMed == null ? "" : nombreMed%>" /> </td>
        </tr>
        <tr><td> Nombre Laboratorio: </td>
            <td><input type="text" name="nombreLab" id="nombreLab" disabled
                       value="<%=nombreLab == null ? "" : nombreLab%>" /> </td>
        </tr>
        <tr>
            <td><input type="button" name="btnLimpiar" value="Limpiar" onClick="limpiar()"/> </td>
            <td><input type="button" name="btnModificar" value="Modificar" onClick="habilitar()"/> </td>
            <td><input type="submit" name="btnBorrar" value="Borrar" /> </td>
            <td><input type="submit" name="btnConfirmaModificar" value="Confirma Modificar" id="btnIdConfirmaModificar" disabled/> </td>
        </tr>

    </table>
</form>
<h3><a href="index.jsp">Volver</a></h3>
</body>
</html>