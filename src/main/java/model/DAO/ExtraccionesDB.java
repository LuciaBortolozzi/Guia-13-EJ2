package model.DAO;

import controller.Conexion;
import model.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import static model.DAO.LocalidadesDB.buscarLocalidad;

public class ExtraccionesDB {

    public static Extracciones selectExtraccion(int dni, int idExt){
        Extracciones extraccion = null;

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT nroExtraccion, fechaDonacion, pesoDonador, pudoDonar, presion, recuentoGlobulosRojos, cantExtraida FROM Extracciones WHERE dniDonador=" + dni + " AND nroExtraccion =" + idExt);
            while (rs.next()) {

                Calendar fechaDon = Calendar.getInstance();
                fechaDon.setTime(rs.getDate("fechaDonacion"));

                boolean pudoDonar = false;
                if(rs.getInt("pudoDonar") ==1){
                    pudoDonar = true;
                }

                extraccion = new Extracciones(rs.getInt("nroExtraccion"), fechaDon,rs.getDouble("pesoDonador"),  pudoDonar, rs.getString("presion"),
                            rs.getDouble("recuentoGlobulosRojos"), rs.getDouble("cantExtraida"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return extraccion;
    }
}
