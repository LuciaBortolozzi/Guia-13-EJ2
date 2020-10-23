package servlet;

import controller.Controlador;
import model.Personas;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

@WebServlet(name = "modificarExtracciones")
public class modificarExtracciones extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double peso = Double.parseDouble(request.getParameter("txtPeso"));
        double cantExtraida = Double.parseDouble(request.getParameter("txtCantidadExtraida"));
        String presion = request.getParameter("txtPresion");
        double recuentoGlobRojos = Double.parseDouble(request.getParameter("txtrecuentoGlobulosRojos"));
        int dni = Integer.parseInt(request.getParameter("txtDNIDonador"));
        int idExt = Integer.parseInt(request.getParameter("txtidExtraccion"));

        boolean borrar = (request.getParameterMap().containsKey("btnBorrar"))?true:false;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<html>");
        out.print("<head><title>Servelts de actualizacion</title></head>");
        out.print("<body>");

        if (borrar)
        {
            out.print("<h2>Datos Ingresados a Borrar</h2>");
            out.print("DNI Donador: " + dni);
            out.print("ID Extraccion: " + idExt);
            Controlador ctrl = new Controlador(dni, idExt);
            ctrl.borrarExtraccion(dni, idExt);
        }
        else
        {
            out.print("<h2>Datos Ingresados a Modificar</h2>");
            if (dni!=0 && idExt!=0)
            {
                String anoPatentamiento=request.getParameter("txtPatentamiento");
                String marca=request.getParameter("txtMarca");
                String modelo=request.getParameter("txtModelo");

/*                out.print("Patente: " + patente+"<br>");*/
                out.print("Año de Patentamiento: " + anoPatentamiento+"<br>");
                out.print("Modelo: " + modelo+"<br>");
                out.print("Marca: " + marca+"<br>");

/*                int anio=Integer.parseInt(anoPatentamiento);
                VehiculosCtrl vc = new VehiculosCtrl(patente,anio , marca, modelo, origen);
                vc.modificarVehiculo();*/

            }
            else
            {	out.print("Patente en null ");}
        }
        out.print("<h3><a href=\"index.jsp\">Volver</a></h3>");
        out.println("</html></body>");
        request.getSession().setAttribute("Patente", "");
        request.getSession().setAttribute("AñoPatentamiento","");
        request.getSession().setAttribute("Marca", "");
        request.getSession().setAttribute("Modelo", "");

    }
}
