package servlet;

import controller.Controlador;
import model.Medicamentos;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ingresoMedicamentosSRV")
public class ingresoMedicamentosSRV extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMed = Integer.parseInt(request.getParameter("idMed"));
        String nombreMed = request.getParameter("nombreMed");
        String nombreLab = request.getParameter("nombreLab");

        Medicamentos med = Controlador.consultaMedicamento(idMed);
        if (med == null) {
            Medicamentos medicamento = new Medicamentos(idMed, nombreMed, nombreLab);
            Controlador.ingresoMedicamento(medicamento);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/menuMedicamentos.jsp");
            dispatcher.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();

            response.setContentType("text/html");
            out.print("<html><body>");
            out.print("<h2>Documento: " + idMed + " ya existe" + "</h2>");
            out.print("<br><br>");
            out.print("<h3><a href=\"menuMedicamentos.jsp\">Volver</a></h3>");
            out.print("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
