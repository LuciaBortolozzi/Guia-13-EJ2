package servlet;

import controller.Controlador;
import model.Medicamentos;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modificarMedicamentosSRV")
public class modificarMedicamentosSRV extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idMed = request.getParameter("idMed");

        if (idMed == null) {
            idMed = request.getSession().getAttribute("idMed").toString();
        }

        boolean borrar = request.getParameterMap().containsKey("btnBorrar");


        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head><title>Servlets de actualización</title></head>");
        out.print("<body>");

        if (borrar) {
            out.print("<h2>Datos Ingresados a Borrar</h2>");
            out.print("ID Medicamento: " + idMed);
            Controlador.borrarMedicamento(Integer.parseInt(idMed));

        } else {
            out.print("<h2>Datos Ingresados a Modificar</h2>");
            if (idMed != null) {

                String nombreMed = request.getParameter("nombreMed");
                String nombreLab = request.getParameter("nombreLab");

                out.print("ID Medicamento: " + idMed + "<br>");
                out.print("Nombre Medicamento: " + nombreMed + "<br>");
                out.print("Nombre Laboratorio: " + nombreLab + "<br>");

                Medicamentos medicamento = new Medicamentos(Integer.parseInt(idMed), nombreMed, nombreLab);
                Controlador.ingresoMedicamento(medicamento);
            } else {
                out.print("ID Medicamento en null ");
            }
        }
        out.print("<h3><a href=\"index.jsp\">Volver</a></h3>");
        out.println("</html></body>");

        request.getSession().setAttribute("idMed", "");
        request.getSession().setAttribute("nombreMed", "");
        request.getSession().setAttribute("nombreLab", "");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
