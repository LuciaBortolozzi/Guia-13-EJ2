package servlet;

import controller.Controlador;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ingresoExtraccionesSRV")
public class ingresoExtraccionesSRV extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int dni = Integer.parseInt(request.getParameter("dni"));
        double peso = Double.parseDouble(request.getParameter("peso"));
        String presion = request.getParameter("presion");
        double recuentoGlobulos = Double.parseDouble(request.getParameter("recuentoGlobulos"));
        double cantExtraida = Double.parseDouble(request.getParameter("cantExtraida"));


        Controlador ctrl = new Controlador(dni, peso, presion, recuentoGlobulos, cantExtraida);
        ctrl.insertExtracciones(dni, peso, presion, recuentoGlobulos, cantExtraida);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/menuExtracciones.jsp");
        dispatcher.forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
