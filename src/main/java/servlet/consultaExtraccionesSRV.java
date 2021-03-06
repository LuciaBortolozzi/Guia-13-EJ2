package servlet;

import controller.Controlador;
import model.DAO.ExtraccionesDB;
import model.Donadores;
import model.Extracciones;
import model.Personas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.TreeSet;

@WebServlet("/consultaExtraccionesSRV")
public class consultaExtraccionesSRV extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int dni = Integer.parseInt(request.getParameter("txtDNIDonador"));
        int idExt = Integer.parseInt(request.getParameter("txtidExtraccion"));

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");

        if (dni != 0) {

            out.print("<h2>DNI: " + dni + "</h2>");

            if (idExt != 0) {
                out.print("<h2>ID extracción: " + idExt + "</h2>");
            }

            Controlador ctrl = new Controlador(dni, idExt);
            Personas persona = ctrl.seleccionarExtracciones(dni, idExt);

            if (persona == null) {

                out.print("<h2>DNI inexistente: " + dni + "</h2>");

            } else if (((Donadores) persona).getExtracciones().get(0).getPresion() == null) {

                out.print("<h2>No hay extracciones</h2>");

            } else {

                out.print("DNI: " + ((Donadores) persona).getDni() + "<br>");
                out.print("ID EXTRACCION: " + ((Donadores) persona).getExtracciones().get(0).getNroExtraccion() + "<br>");

                String fechaDonacion = ((Donadores) persona).getExtracciones().get(0).getFechaDonacion().get(Calendar.DAY_OF_MONTH) + "/" +
                        (((Donadores) persona).getExtracciones().get(0).getFechaDonacion().get(Calendar.MONTH) + 1) + "/" +
                        ((Donadores) persona).getExtracciones().get(0).getFechaDonacion().get(Calendar.YEAR);
                request.getSession().setAttribute("Peso", String.valueOf(((Donadores) persona).getExtracciones().get(0).getPesoDonador()));
                request.getSession().setAttribute("CantidadExtraida", String.valueOf(((Donadores) persona).getExtracciones().get(0).getCantExtraida()));
                request.getSession().setAttribute("Presion", ((Donadores) persona).getExtracciones().get(0).getPresion());
                request.getSession().setAttribute("FechaDonacion", String.valueOf(fechaDonacion));
                request.getSession().setAttribute("RecuentoGlobulosRojos", String.valueOf(((Donadores) persona).getExtracciones().get(0).getRecuentoGlobulosRojos()));

                request.getRequestDispatcher("actualizacionExtraccion.jsp").forward(request, response);
            }
        }
        out.print("</body></html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
