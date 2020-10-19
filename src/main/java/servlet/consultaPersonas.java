package servlet;

import model.DAO.PersonasDB;
import model.Personas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(urlPatterns="/consultaPersonas", name = "consultaPersonas")
public class consultaPersonas extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int dni = Integer.parseInt(request.getParameter("dni"));
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");
        out.print("<h2>Documento: " + dni +"</h2>");

        if (dni != 0) {
            Personas persona = PersonasDB.selectPersona(dni);
            if (persona == null) {
                out.print("<h2>Persona inexistente: " + dni + "</h2>");
            } else {
                out.print("Nombre: " + persona.getNombre() + "<br>");
                out.print("Apellido: " + persona.getApellido() + "<br>");
                out.print("Fecha nacimiento:" + persona.getFechaNac().get(Calendar.DAY_OF_MONTH) + "/" +
                        (persona.getFechaNac().get(Calendar.MONTH)+1) + "/" +
                            persona.getFechaNac().get(Calendar.YEAR) + "<br>");
                out.print("Sexo: " + persona.getSexo() + "<br>");
                out.print("Tipo de Sangre: " + persona.getTipoSangre().getGrupo() + persona.getTipoSangre().getFactor() + "<br>");
                out.print("Localidad: " + persona.getLocalidad().getNombreLoc() + "<br>");
                out.print("Provincia: " + persona.getLocalidad().getProvincia().getNombreProv() + "<br>");
            }
        }
        out.print("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
