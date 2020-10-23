package servlet;

import controller.Controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "modificarMedicamentos")
public class modificarMedicamentos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int idMed = Integer.parseInt(request.getParameter("idMed"));

        if (idMed == 0) {
            idMed = Integer.parseInt(request.getSession().getAttribute("idMed").toString());
        }

        boolean borrar = request.getParameterMap().containsKey("btnBorrar");

        String origen = "TXT";

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head><title>  </title></head>");
        out.print("<body>");

        if (borrar) {
            out.print("<h2>Datos Ingresados a Borrar</h2>");
            out.print("ID Medicamento: " + idMed);
            Controlador.borrarMedicamento(idMed);

        } else {
            out.print("<h2>Datos Ingresados a Modificar</h2>");
            if (idMed != 0) {

                String nombreMed = request.getParameter("txtMarca");
                String nombreLab = request.getParameter("txtModelo");

                out.print("ID Medicamento: " + idMed + "<br>");
                out.print("Modelo: " + nombreLab + "<br>");
                out.print("Marca: " + nombreMed + "<br>");
                
                Controlador.actualizarMedicamento(idMed, nombreMed, nombreLab, origen);
            } else {
                out.print("ID Medicamento en null ");
            }
        }
        out.print("<h3><a href=\"index.jsp\">Volver</a></h3>");
        out.println("</html></body>");
        request.getSession().setAttribute("Patente", "");
        request.getSession().setAttribute("Marca", "");
        request.getSession().setAttribute("Modelo", "");
    }

}
