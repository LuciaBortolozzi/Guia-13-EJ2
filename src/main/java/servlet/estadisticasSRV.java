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

@WebServlet("/estadisticasSRV")
public class estadisticasSRV extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");

        Controlador ctrl = new Controlador();
        TreeSet<Personas> listaPersonas = ctrl.selectTodasPersonas();

        out.print("<h2> EN CABA </h2>");
        out.print("<table border=\"1\" cellpadding=\"2\"> <tr> <td> DNI </td>");
        out.print("<td> Nombre </td>");
        out.print("<td> Apellido </td>");
        out.print("<td> Sexo </td> </tr>");

        listaPersonas.stream()
                .forEach( (p)  ->
                {
                    out.print("<tr> <td>" + p.getDni() + "</td>");
                    out.print("<td>" + p.getNombre() + "</td>");
                    out.print("<td>" + p.getApellido() + "</td>");
                    out.print("<td>" + p.getSexo() + "</td> </tr>");
                });
        out.print("</table>");


        out.print("</body></html>");
    }
}
