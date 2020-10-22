package servlet;

import model.DAO.ExtraccionesDB;
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

@WebServlet(urlPatterns="/consultaExtracciones", name = "consultaExtracciones")
public class consultaExtracciones extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int dni = Integer.parseInt(request.getParameter("dni"));
        int idExt = Integer.parseInt(request.getParameter("idExt"));

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");

        if(dni != 0 ){
            out.print("<h2>DNI: " + dni +"</h2>");
        }
        if(idExt != 0){
            out.print("<h2>ID extracción: " + idExt +"</h2>");
        }


        if (dni != 0) {
            Extracciones extraccion = ExtraccionesDB.selectExtraccion(dni, idExt);

            if (extraccion == null) {

                out.print("<h2>DNI inexistente: " + dni + "</h2>");

            } else {

              String fechaDonacion = extraccion.getFechaDonacion().get(Calendar.DAY_OF_MONTH) + "/" +
                      (extraccion.getFechaDonacion().get(Calendar.MONTH)+1) + "/" +
                      extraccion.getFechaDonacion().get(Calendar.YEAR);
                request.getSession().setAttribute("Peso", extraccion.getPesoDonador() );
                request.getSession().setAttribute("CantidadExtraida", extraccion.getCantExtraida());
                request.getSession().setAttribute("Presion", extraccion.getPresion());
                request.getSession().setAttribute("FechaDonacion", fechaDonacion);
                request.getSession().setAttribute("RecuentoGlobulosRojos", extraccion.getRecuentoGlobulosRojos());

                request.getRequestDispatcher("actualizacionExtraccion.jsp").forward(request, response);
            }
        }
        out.print("</body></html>");
    }
}
