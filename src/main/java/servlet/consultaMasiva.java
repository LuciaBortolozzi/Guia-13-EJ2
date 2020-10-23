package servlet;

import controller.Controlador;
import model.DAO.*;
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

@WebServlet(urlPatterns="/consultaMasiva", name = "consultaMasiva")
public class consultaMasiva extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nombreProv = request.getParameter("nombreProv");
        String tipoSangre = request.getParameter("tipoSangre");

        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");

        if(nombreProv !=""){
            out.print("<h2>Provincia: " + nombreProv +"</h2>");
        }
        if(tipoSangre !=""){
            out.print("<h2>Tipo de sangre: " + tipoSangre +"</h2>");
        }


        if (nombreProv != null) {
            Controlador ctrl = new Controlador(nombreProv, tipoSangre);
            TreeSet<Personas> listaPersonas = ctrl.selectPersonasPorProvTipoSangre(nombreProv, tipoSangre);

            if (listaPersonas == null) {

                out.print("<h2>Persona inexistente: " + nombreProv + "</h2>");

            } else {

                out.print("<table border=\"1\" cellpadding=\"2\"> <tr> <td> DNI </td>");
                out.print("<td> Nombre </td>");
                out.print("<td> Apellido </td>");
                out.print("<td> Localidad </td>");
                out.print("<td> Fecha de Nacimiento </td>");
                out.print("<td> Sexo </td> </tr>");

                Personas persona;
                Iterator<Personas> iteratorPersonas = listaPersonas.iterator();
                while (iteratorPersonas.hasNext()) {
                    persona = iteratorPersonas.next();

                    out.print("<tr> <td>" + persona.getDni() + "</td>");
                    out.print("<td>" + persona.getNombre() + "</td>");
                    out.print("<td>" + persona.getApellido() + "</td>");
                    out.print("<td>" + persona.getLocalidad().getNombreLoc() + "</td>");
                    out.print("<td>" + persona.getFechaNac().get(Calendar.DAY_OF_MONTH) + "/" +
                            persona.getFechaNac().get(Calendar.MONTH) + "/" +
                            persona.getFechaNac().get(Calendar.YEAR) + "</td>");
                    out.print("<td>" + persona.getSexo() + "</td> </tr>");

                }
                out.print("</table>");
            }

            int tamanioTablaPersonas = ctrl.selectLongitudTablaPersonas();

            out.print(listaPersonas.size() + " coincidencias en " + tamanioTablaPersonas + " personas <br>");
        }
        out.print("</body></html>");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
