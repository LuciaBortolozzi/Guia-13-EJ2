package servlet;

import model.DAO.MedicamentosDB;
import model.Medicamentos;
import model.Personas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(urlPatterns="/consultaMedicamentos", name = "consultaMedicamentos")
public class consultaMedicamentos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMed = Integer.parseInt(request.getParameter("idMed"));
        PrintWriter out = response.getWriter();

        response.setContentType("text/html");
        out.print("<html><body>");
        out.print("<h2>ID Medicamento: " + idMed +"</h2>");

        if (idMed != 0) {
            Medicamentos medicamento = MedicamentosDB.selectMedicamento(idMed);
            if (medicamento == null) {
                out.print("<h2>Medicamento inexistente: " + idMed + "</h2>");
            } else {
                out.print("Nombre Medicamento: " + medicamento.getNombreMed() + "<br>");
                out.print("Nombre Laboratorio: " + medicamento.getNombreLab() + "<br>");
            }
        }
        out.print("</body></html>");
    }
}
