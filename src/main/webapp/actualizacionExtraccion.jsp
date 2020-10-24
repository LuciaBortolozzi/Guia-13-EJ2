<%@ page import="java.util.Calendar" %><%--
  Created by IntelliJ IDEA.
  User: Flor
  Date: 18/10/2020
  Time: 02:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edicion de Extracciones</title>
</head>
<script>
    function setearDniDonadores()
    {
        var index=document.getElementById("dniDonadores").selectedIndex;
        document.getElementById("dniDonador").value=document.getElementById("dniDonadores").options[index].text;
    }

    function habilitar()
    {
        document.getElementById("txtIdPeso").disabled=false;
        document.getElementById("txtIdCantidadExtraida").disabled=false;
        document.getElementById("txtIdPresion").disabled=false;
        document.getElementById("txtIdFechaDonacion").disabled=false;
        document.getElementById("txtIdrecuentoGlobulosRojos").disabled=false;
        document.getElementById("btnIdConfirmaModificar").disabled=false;
    }

    function limpiar() {
        return confirm("¿Limpiar los datos?");
    }

</script>

<body >
<%@page import="controller.*" %>
<%@ page import="java.util.ArrayList" %>
<h3>Consulta y Edicion de Extracciones</h3>
<br>
<br>

<form action="consultaExtraccionesSRV" method="post">
    <table>
        <tr>
            <td>Dni:</td>
            <td>
                <input type="text" name="txtDNIDonador" />
            </td>
        </tr>
        <tr>
            <td>ID Extraccion:</td>
            <td>
                <input type="text" name="txtidExtraccion" />
            </td>
        </tr>
    </table>
    <input type="submit" value="Buscar" />
</form>

<%
        String peso = (String) session.getAttribute("Peso");
        String cantidadExtraida = (String) session.getAttribute("CantidadExtraida");
        String presion = (String) session.getAttribute("Presion");
        String recuentoGlobulosRojos = (String) session.getAttribute("RecuentoGlobulosRojos");
%>
<br><br>
<form name="Modifica" action="modificarExtraccionesSRV" >
    <table>
        <tr><td> Peso </td>
            <td><input type="text" name="txtPeso" id="txtIdPeso" disabled value="<%=peso == null ? "" : peso%>"  /> </td>
        </tr>
        <tr><td> Cantidad Extraida </td>
            <td><input type="text" name="txtCantidadExtraida" id="txtIdCantidadExtraida" disabled value="<%=cantidadExtraida == null ? "" : cantidadExtraida%>"  /> </td>
        </tr>
        <tr><td> Presion </td>
            <td><input type="text" name="txtPresion" id="txtIdPresion" disabled value="<%=presion == null ? "" : presion%>"  /> </td>
        </tr>
        <tr><td> Recuento Globulos Rojos </td>
            <td><input type="text" name="txtrecuentoGlobulosRojos" id="txtIdrecuentoGlobulosRojos" disabled value="<%=recuentoGlobulosRojos == null ? "" : recuentoGlobulosRojos%>"  /> </td>
        </tr>

        <tr>
            <td><input type="reset" name="btnLimpiar" value="Limpiar" onClick="limpiar()"/> </td>
            <td><input type="button" name="btnModificar" value="Modificar" onClick="habilitar()"/> </td>
            <td><input type="submit" name="btnBorrar" value="Borrar" /> </td>
            <td><input type="submit" name="btnConfirmaModificar" value="Confirma Modificar" id="btnIdConfirmaModificar" disabled/> </td>
        </tr>

    </table>
</form>
<h3><a href="index.jsp">Volver</a></h3>
</body>
</html>