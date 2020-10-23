package model.DAO;

import controller.Conexion;
import model.*;

import java.sql.*;
import java.util.Calendar;

import static model.DAO.LocalidadesDB.buscarLocalidad;

public class ExtraccionesDB {

    private int nroExtraccion;
    private int dni;
    private double peso;
    private double cantExtraida;
    private String presion;
    private double recuentoGlobRojos;

    public ExtraccionesDB() {
    }

    public ExtraccionesDB(int nroExtraccion, int dni) {
        this.nroExtraccion = nroExtraccion;
        this.dni =dni;
    }

    public ExtraccionesDB(int dni, int nroExtraccion, double peso, double cantExtraida, String presion, double recuentoGlobRojos) {
        this.nroExtraccion = nroExtraccion;
        this.dni =dni;
        this.peso = peso;
        this.cantExtraida=cantExtraida;
        this.presion=presion;
        this.recuentoGlobRojos=recuentoGlobRojos;
    }

    public ExtraccionesDB(int dni, double peso, String presion, double recuentoGlobRojos, double cantExtraida) {

        this.dni =dni;
        this.peso = peso;
        this.cantExtraida=cantExtraida;
        this.presion=presion;
        this.recuentoGlobRojos=recuentoGlobRojos;
    }

    public static Personas selectExtraccion(int dni, int idExt){

        Personas persona = null;

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT e.nroExtraccion, e.fechaDonacion, e.pesoDonador, e.pudoDonar, e.presion, " +
                    "e.recuentoGlobulosRojos, e.cantExtraida, p.dni, p.nombre, p.apellido, p.sexo. p.fechaNac, p.provincia," +
                    "p.localidad, p.tipoSangre, p.tipoPersona FROM Extracciones e INNER JOIN Personas p ON " +
                    "e.dniDonador = p.dni WHERE dniDonador=" + dni + " AND nroExtraccion =" + idExt);


            while (rs.next()) {

                TiposSangre tipoSangre = TiposSangreDB.buscarTipoSangre(rs.getInt("p.tipoSangre"));
                Localidades localidad = buscarLocalidad(rs.getInt("p.localidad"), rs.getInt("p.provincia"));

                Calendar fechaNac = Calendar.getInstance();
                fechaNac.setTime(rs.getDate("p.fechaNac"));


                persona = new Donadores(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                            localidad, fechaNac, rs.getString("sexo").charAt(0), tipoSangre);


                Calendar fechaDon = Calendar.getInstance();
                fechaDon.setTime(rs.getDate("e.fechaDonacion"));

                boolean pudoDonar = false;
                if(rs.getInt("pudoDonar") ==1){
                    pudoDonar = true;
                }

                ((Donadores)persona).setExtracciones(rs.getInt("nroExtraccion"), fechaDon,rs.getDouble("pesoDonador"),  pudoDonar, rs.getString("presion"),
                            rs.getDouble("recuentoGlobulosRojos"), rs.getDouble("cantExtraida"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persona;
    }

    public static void updateExtracciones(int dni, int idExt, double peso, double cantExtraida, String presion, double recuentoGlobRojos) {

        try{

            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Extracciones SET pesoDonador=" + peso + ", cantExtraida=" + cantExtraida
                    + ", presion='" + presion + "', ruentoGlobulosRojos=" + recuentoGlobRojos + " WHERE dniDonador=" + dni + " AND nroExtraccion = " + idExt);

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteExtraccion(int dni, int idExt) {

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM Extracciones WHERE dniDonador=" + dni + " AND nroExtraccion=" + idExt);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertExtracciones(int dni, double peso, String presion, double recuentoGlobRojos, double cantExtraida) {

        try {

            Connection conn = Conexion.getConnection();
            CallableStatement stmt = conn.prepareCall("{call insertMedicamento(?, ?, ?, ?, ?)}");
            stmt.setInt(1, dni);
            stmt.setDouble(2, peso);
            stmt.setString(3, presion);
            stmt.setDouble(4, recuentoGlobRojos);
            stmt.setDouble(5, cantExtraida);
            stmt.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
