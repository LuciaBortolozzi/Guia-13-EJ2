package model.DAO;

import controller.Conexion;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TreeSet;

import static model.DAO.LocalidadesDB.buscarLocalidad;

public class EstadisticasDB {

    public static TreeSet<Personas> selectEstadisticaUno(){

        TreeSet<Personas> personas = new TreeSet<Personas>();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo FROM Personas WHERE provincia=" + 1);
            while (rs.next()) {

                personas.add( new Donadores(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                    null, null, rs.getString("sexo").charAt(0), null));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personas;
    }




}
