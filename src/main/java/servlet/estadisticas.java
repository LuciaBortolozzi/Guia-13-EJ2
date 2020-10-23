package servlet;

import controller.Controlador;
import model.Personas;
import model.DAO.*;

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

@WebServlet(urlPatterns="/estadisticas", name = "estadisticas")
public class estadisticas extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double peso = Double.parseDouble(request.getParameter("peso"));
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");

        Controlador ctrl = new Controlador();
        TreeSet<Personas> listaPersonas = ctrl.selectEstadisticaUno();

        out.print("<h2> EN CABA </h2>");
        out.print("<table border=\"1\" cellpadding=\"2\"> <tr> <td> DNI </td>");
        out.print("<td> Nombre </td>");
        out.print("<td> Apellido </td>");
        out.print("<td> Sexo </td> </tr>");

        Personas persona;
        Iterator<Personas> iteratorPersonas = listaPersonas.iterator();
        while (iteratorPersonas.hasNext()) {
            persona = iteratorPersonas.next();

            out.print("<tr> <td>" + persona.getDni() + "</td>");
            out.print("<td>" + persona.getNombre() + "</td>");
            out.print("<td>" + persona.getApellido() + "</td>");
            out.print("<td>" + persona.getSexo() + "</td> </tr>");
        }
        out.print("</table>");



        out.print("</body></html>");
    }
}
