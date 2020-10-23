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

    private int nroExtraccion;
    private int dni;

    public ExtraccionesDB() {
    }

    public ExtraccionesDB(int nroExtraccion, int dni) {
        this.nroExtraccion = nroExtraccion;
        this.dni =dni;
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

/*    public static void updateExtracciones(Personas persona) {

        try{

            int tipoPersona;

            if (persona instanceof Donadores) {
                tipoPersona = 1;
            } else {
                tipoPersona = 0;
            }

            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("UPDATE Personas SET nombre='" + persona.getNombre() + "', apellido='" + persona.getApellido()
                    + "', sexo='" + persona.getSexo() + "', fechaNac='" + persona.getFechaNac().get(Calendar.YEAR) + "-" + (persona.getFechaNac().get(Calendar.MONTH) + 1) + "-"
                    + persona.getFechaNac().get(Calendar.DAY_OF_MONTH) + "', provincia=" + persona.getLocalidad().getProvincia().getIdProvincia() + ", localidad=" + persona.getLocalidad().getIdLocalidad() +
                    ", tipoSangre=" + persona.getTipoSangre().getId() + " WHERE dni=" + persona.getDni() + "");

            conn.close();

            if (persona instanceof Donadores) {

                updateDonadores(persona);

            } else {

                updatePacientes(persona);
                updateMedicamentosPacientes(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

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
}
