package servlet;

import controller.Controlador;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/modificarExtraccionesSRV")
public class modificarExtraccionesSRV extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double peso = Double.parseDouble(request.getParameter("txtPeso"));
        double cantExtraida = Double.parseDouble(request.getParameter("txtCantidadExtraida"));
        String presion = request.getParameter("txtPresion");
        double recuentoGlobRojos = Double.parseDouble(request.getParameter("txtrecuentoGlobulosRojos"));
        int dni = Integer.parseInt(request.getParameter("txtDNIDonador"));
        int idExt = Integer.parseInt(request.getParameter("txtidExtraccion"));

        if (dni == 0) {
            dni = Integer.parseInt(request.getSession().getAttribute("Dni").toString());
        }
        if (idExt == 0) {
            idExt = Integer.parseInt(request.getSession().getAttribute("NroExt").toString());
        }

        boolean borrar = request.getParameterMap().containsKey("btnBorrar");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head><title>Servelts de actualizacion</title></head>");
        out.print("<body>");

        if (borrar) {
            out.print("<h2>Datos Ingresados a Borrar</h2>");
            out.print("DNI Donador: " + dni);
            out.print("ID Extraccion: " + idExt);
            Controlador ctrl = new Controlador(dni, idExt);
            ctrl.borrarExtraccion(dni, idExt);
        } else {
            out.print("<h2>Datos Ingresados a Modificar</h2>");
            if (dni != 0 && idExt != 0) {
                String pesoAux = request.getParameter("txtPeso");
                String cantExtraidaAux = request.getParameter("txtCantidadExtraida");
                String presionAux = request.getParameter("txtPresion");
                String recuentoGlobRojosAux = request.getParameter("txtrecuentoGlobulosRojos");

                out.print("Peso: " + peso + "<br>");
                out.print("Cantidad extraida: " + cantExtraida + "<br>");
                out.print("Persion: " + presion + "<br>");
                out.print("Recuento globulos rojos: " + recuentoGlobRojos + "<br>");

                Controlador ctrl = new Controlador(dni, idExt, peso, cantExtraida, presion, recuentoGlobRojos);
                ctrl.updateExtracciones(dni, idExt, peso, cantExtraida, presion, recuentoGlobRojos);

            } else {
                out.print("Patente en null ");
            }
        }
        out.print("<h3><a href=\"index.jsp\">Volver</a></h3>");
        out.println("</html></body>");
        request.getSession().setAttribute("Dni", "");
        request.getSession().setAttribute("NroExt", "");
        request.getSession().setAttribute("Peso", "");
        request.getSession().setAttribute("CantidadExtraida", "");
        request.getSession().setAttribute("Presion", "");
        request.getSession().setAttribute("FechaDonacion", "");
        request.getSession().setAttribute("RecuentoGlobulosRojos", "");

    }
}
