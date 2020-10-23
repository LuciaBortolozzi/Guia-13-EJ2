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

    function setearTxtIDMed()
    {
        var index=document.getElementById("idMedicamentos").selectedIndex;
        document.getElementById("IdMed").value=document.getElementById("idMedicamentos").options[index].text;
    }

    function habilitar()
    {
        document.getElementById("nombreMed").disabled=false;
        document.getElementById("nombreLab").disabled=false;
        document.getElementById("btnIdConfirmaModificar").disabled=false;
    }

    function limpiar()
    {
        document.getElementById("IdMed").value="";
        document.getElementById("nombreMed").value="";
        document.getElementById("nombreLab").value="";
        session.setAttribute("IdMedic", "");
        session.setAttribute("NombreMed", "");
        session.setAttribute("NombreLab", "");
    }

</script>

<body >
<%@page import="controller.*" %>
<%@ page import="javax.naming.ldap.Control" %>
<h3>Actualizacion y baja de Medicamento</h3>
<br><br>

<form name="CargaCombo">
    <table>
        <tr>
            <td>Consultar desde: </td>
            <td>Archivo  <input type="radio" name="rdbOrigen" id="OrigARCH" value="TXT" onclick="document.CargaCombo.submit()"/> </td>
            <td>Base de Datos <input type="radio" name="rdbOrigen" id="OrigBD" value="BD" onclick="document.CargaCombo.submit()"/> </td>
        </tr>
    </table>
</form>
Origen datos: <%= request.getParameter("rdbOrigen") != null? request.getParameter("rdbOrigen"): "" %>
<br>
<br>
<%! String origen; %>
<%  origen=request.getParameter("rdbOrigen"); %>
<form name="Consulta" action="consultaMedicamentos" method="POST" >
    <table>
        <tr>
            <td>ID Medicamento: </td>
            <td><input type="text" name="txtIDMed" id="IdMed"
                       value="<%= request.getSession().getAttribute("IdMedic")!= null?request.getSession().getAttribute("IdMedic"):"" %>" /> </td>

            <td><SELECT name="cmbIdMeds" id="idMedicamentos" onchange="setearTxtIDMed()">
                <OPTION>         </OPTION>
                <%
                    if (origen!=null)
                    {
                        Controlador.leerMedicamentos(origen);
                        for(String medST: Controlador.seleccionarMedicamentos())
                        {
                            out.print("<OPTION>" + medST + "</OPTION>");
                        }
                    }
                %>
            </SELECT>
        </tr>
        <tr>
            <td align="center"><input type="submit" name="btnBuscar" value="Buscar"/> </td>
            <td><input type="reset" name="btnCancela" value="Cancela" /> </td>
        </tr>
        <tr>
            <td><input type="hidden" name="txtOrigenOculto" value="<%= origen %>"/> </td>
        </tr>
    </table>
</form>


<%
    String nombreMed = (String) session.getAttribute("nombreMed");
    String nombreLab = (String) session.getAttribute("nombreLab");
%>
<br><br>
<form name="Modifica" action="modificarMedicamentos" >
    <table>
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