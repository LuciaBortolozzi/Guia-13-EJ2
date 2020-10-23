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

@WebServlet(urlPatterns="/consultaMedicamentos", name = "consultaMedicamentos")
public class consultaMedicamentos extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMed = Integer.parseInt(request.getParameter("txtIDMed"));
      
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.print("<html><body>");

        if (idMed != 0) {

            Medicamentos medicamento = Controlador.consultaMedicamento(idMed);

            if ( medicamento == null) {

                out.print("<h2>ID Medicamento inexistente: " + idMed + "</h2>");

            } else {
                out.print("<h2>ID Medicamento: " + idMed +"</h2>");

                request.getSession().setAttribute("nombreMed", medicamento.getNombreMed() );
                request.getSession().setAttribute("nombreLab", medicamento.getNombreLab() );

                request.getRequestDispatcher("actualizacionMedicamento.jsp").forward(request, response);
            }
        }
        out.print("</body></html>");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
