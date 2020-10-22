package model.DAO;

import controller.Conexion;
import model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TreeSet;

import static model.DAO.LocalidadesDB.buscarLocalidad;

public class PersonasDB {

    protected String nombre;
    protected String apellido;
    protected int dni;
    protected Localidades localidad;
    protected Calendar fechaNac;
    protected char sexo;
    protected TiposSangre tipoSangre;

    public PersonasDB(){}

    public PersonasDB(int dni){

        this.dni = dni;
    }

    public static Personas selectPersona(int dnin){
        Personas persona = null;

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo, fechaNac, provincia, localidad, tipoSangre, tipoPersona FROM Personas WHERE dni=" + dnin);
            while (rs.next()) {
                TiposSangre tipoSangre = TiposSangreDB.buscarTipoSangre(rs.getInt("tipoSangre"));
                Localidades localidad = buscarLocalidad(rs.getInt("localidad"), rs.getInt("provincia"));

                Calendar fechaNac = Calendar.getInstance();
                fechaNac.setTime(rs.getDate("fechaNac"));

                if (rs.getInt("tipoPersona") == 0) {
                    persona = new Pacientes(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                            localidad, fechaNac, rs.getString("sexo").charAt(0), tipoSangre);
                } else {
                    persona = new Donadores(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                            localidad, fechaNac, rs.getString("sexo").charAt(0), tipoSangre);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persona;
    }

    public static TreeSet<Personas> selectPersonasPorProvTipoSangre(String provincia, String tiposSangre) {

        TreeSet<Personas> personas = new TreeSet<Personas>();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            String grupo=null;
            String factor=null;
            if(tiposSangre != "") {
                if (tiposSangre.length() == 11) {
                    grupo = tiposSangre.substring(0, 1);
                    factor = tiposSangre.substring(1, 11);
                } else {
                    grupo = tiposSangre.substring(0, 2);
                    factor = tiposSangre.substring(2, 12);
                }
            }
            ResultSet rs;

            if(provincia == "" && tiposSangre != ""){

                rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo, fechaNac, provincia, localidad, tipoSangre, tipoPersona FROM " +
                        "Personas p INNER JOIN Provincias po ON p.provincia = po.idProvincia " +
                        "INNER JOIN TiposSangre t ON p.tipoSangre = t.id WHERE t.grupo = '" + grupo + "' AND t.factor = '" + factor + "'");

            } else if(provincia != "" && tiposSangre == ""){

                rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo, fechaNac, provincia, localidad, tipoSangre, tipoPersona FROM " +
                        "Personas p INNER JOIN Provincias po ON p.provincia = po.idProvincia " +
                        "INNER JOIN TiposSangre t ON p.tipoSangre = t.id WHERE po.nombreProv = '" + provincia + "'");

            } else if(provincia == "" && tiposSangre == ""){

                rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo, fechaNac, provincia, localidad, tipoSangre, tipoPersona FROM " +
                        "Personas p INNER JOIN Provincias po ON p.provincia = po.idProvincia " +
                        "INNER JOIN TiposSangre t ON p.tipoSangre = t.id ");

            }else {
                rs = stmt.executeQuery("SELECT dni, nombre, apellido, sexo, fechaNac, provincia, localidad, tipoSangre, tipoPersona FROM " +
                        "Personas p INNER JOIN Provincias po ON p.provincia = po.idProvincia " +
                        "INNER JOIN TiposSangre t ON p.tipoSangre = t.id WHERE po.nombreProv = '" + provincia + "' AND t.grupo = '" + grupo + "' AND t.factor = '" + factor + "'");
            }

            while (rs.next()) {
                 Localidades local = buscarLocalidad(rs.getInt("provincia"), rs.getInt("localidad"));

                Calendar fechaNac = Calendar.getInstance();
                fechaNac.setTime(rs.getDate("fechaNac"));

                if (rs.getInt("tipoPersona") == 0) {
                    personas.add(new Pacientes(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                            local, fechaNac, rs.getString("sexo").charAt(0)));
                } else {
                    personas.add(new Donadores(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"),
                            local, fechaNac, rs.getString("sexo").charAt(0)));
                }

            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public static int selectLongitudTablaPersonas() {

        int cantidadFilas = 0;

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS row FROM Personas");

            while (rs.next()) {

                cantidadFilas = rs.getInt("row");

            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cantidadFilas;
    }

    public static ArrayList<String> selectDonadores() {

        ArrayList<String> personasStr = new ArrayList<String>();
        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT dni FROM Donadores");
            while (rs.next()) {
                for (String per : personasStr)
                {
                    per = (String.valueOf(rs.getInt("dni")));
                    personasStr.add(per);
                }
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personasStr;
    }

    public static ArrayList<String> selectExtracciones(int dni) {

        ArrayList<String> extraccionesStr = new ArrayList<String>();
        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT nroExtraccion FROM Extracciones WHERE dniDonador =" + dni);
            while (rs.next()) {
                for (String ext : extraccionesStr)
                {
                    ext = (String.valueOf(rs.getInt("nroExtraccion")));
                    extraccionesStr.add(ext);
                }
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extraccionesStr;
    }

    public static void selectDonadoresExtracciones(Personas persona) {
        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT dniDonador, nroExtraccion, fechaDonacion, pesoDonador, pudoDonar, " +
                    "presion, recuentoGlobulosRojos, cantExtraida FROM Extracciones WHERE dniDonador=" + persona.getDni());
            while (rs.next()) {

                Calendar fechaDonacion = Calendar.getInstance();
                fechaDonacion.setTime(rs.getDate("fechaDonacion"));

                ((Donadores) persona).setExtracciones(rs.getInt("nroExtraccion"), fechaDonacion, rs.getDouble("pesoDonador"),
                        rs.getBoolean("pudoDonar"), rs.getString("presion"), rs.getDouble("recuentoGlobulosRojos"),
                        rs.getDouble("cantExtraida"));


            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static TreeSet<Personas> selectPacientes(TreeSet<Personas> personas, ArrayList<Medicamentos> medicamentos) {
        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT dni, enfermedad, inicioTratamiento FROM Pacientes");
            while (rs.next()) {
                for (Personas per : personas
                ) {

                    Calendar inicioTratamiento = Calendar.getInstance();
                    inicioTratamiento.setTime(rs.getDate("inicioTratamiento"));

                    if (per instanceof Pacientes && rs.getInt("dni") == per.getDni()) {
                        ((Pacientes) per).setEnfermedad(rs.getString("enfermedad"));
                        ((Pacientes) per).setInicioTratamiento(inicioTratamiento);

                        ArrayList<Medicamentos> medsAux = new ArrayList<Medicamentos>();
                        medsAux = selectPacientesMedicamentos(per, medicamentos);
                        ((Pacientes) per).setMedicamentos(medsAux);

                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public static ArrayList<Medicamentos> selectPacientesMedicamentos(Personas persona, ArrayList<Medicamentos> medicamentos) {
        ArrayList<Medicamentos> medsAux = new ArrayList<Medicamentos>();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT dni, idMed FROM PacientesMedicamentos WHERE dni=" + persona.getDni());
            while (rs.next()) {

                for (Medicamentos medicamento : medicamentos
                ) {
                    if (medicamento.getIdMed() == rs.getInt("idMed")) {
                        medsAux.add(medicamento);
                    }
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medsAux;
    }

    public static void insertPersonas(Personas persona) {

        try {

            int tipoPersona;

            if (persona instanceof Donadores) {
                tipoPersona = 1;
            } else {
                tipoPersona = 0;
            }

            String fechaNac = persona.getFechaNac().get(Calendar.YEAR) + "-" + (persona.getFechaNac().get(Calendar.MONTH) + 1) + "-"
                    + persona.getFechaNac().get(Calendar.DAY_OF_MONTH);
            Connection conn = Conexion.getConnection();

            CallableStatement stmt = conn.prepareCall("{call insertPersonas(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
            stmt.setInt(1, persona.getDni());
            stmt.setString(2, persona.getNombre());
            stmt.setString(3, persona.getApellido());
            stmt.setString(4, String.valueOf(persona.getSexo()));
            stmt.setDate(5, Date.valueOf(fechaNac));
            stmt.setInt(6, persona.getLocalidad().getProvincia().getIdProvincia());
            stmt.setInt(7, persona.getLocalidad().getIdLocalidad());
            stmt.setInt(8, persona.getTipoSangre().getId());
            stmt.setInt(9, tipoPersona);
            stmt.execute();
            conn.close();


            if (persona instanceof Donadores) {

                insertDonadores(persona);

            } else {

                insertPacientes(persona);
                insertPacientesMedicamentos(persona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDonadores(Personas persona) {

        int donaSangre = 0;
        int donaPlaquetas = 0;
        int donaPlasma = 0;
        if (((Donadores) persona).isDonaSangre()) {
            donaSangre = 1;
        }
        if (((Donadores) persona).isDonaPlaquetas()) {
            donaPlaquetas = 1;
        }
        if (((Donadores) persona).isDonaPlasma()) {
            donaPlasma = 1;
        }

        try {
            Connection conn = Conexion.getConnection();
            CallableStatement stmt = conn.prepareCall("{call insertDonadores(?, ?, ?, ?)}");
            stmt.setInt(1, persona.getDni());
            stmt.setInt(2, donaSangre);
            stmt.setInt(3, donaPlaquetas);
            stmt.setInt(4, donaPlasma);
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPacientes(Personas persona) {

        String inicioTratamiento = ((Pacientes) persona).getInicioTratamiento().get(Calendar.YEAR) + "-" + (((Pacientes) persona).getInicioTratamiento().get(Calendar.MONTH) + 1) + "-"
                + ((Pacientes) persona).getInicioTratamiento().get(Calendar.DAY_OF_MONTH);
        try {
            Connection conn = Conexion.getConnection();
            CallableStatement stmt = conn.prepareCall("{call insertPacientes(?, ?, ?)}");
            stmt.setInt(1, persona.getDni());
            stmt.setString(2, ((Pacientes) persona).getEnfermedad());
            stmt.setDate(3, Date.valueOf(inicioTratamiento));
            stmt.execute();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertPacientesMedicamentos(Personas persona) {

        try {

            Connection conn = Conexion.getConnection();
            CallableStatement stmt;

            if (persona instanceof Pacientes) {

                for (Medicamentos med : ((Pacientes) persona).getMedicamentos()) {
                    stmt = conn.prepareCall("{call insertPacientesMedicamentos(?, ?)}");
                    stmt.setInt(1, persona.getDni());
                    stmt.setInt(2, med.getIdMed());
                    stmt.execute();
                }
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updatePersonas(Personas persona) {

        try {

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
    }

    public static void updateDonadores(Personas persona) throws SQLException {

        int donaSangre = 0;
        int donaPlasma = 0;
        int donaPlaquetas = 0;
        if (((Donadores) persona).isDonaSangre()) {
            donaSangre = 1;
        }
        if (((Donadores) persona).isDonaPlasma()) {
            donaPlasma = 1;
        }
        if (((Donadores) persona).isDonaPlaquetas()) {
            donaPlaquetas = 1;
        }

        Connection conn = Conexion.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE Donadores SET donaSangre=" + donaSangre + ", donaPlasma=" + donaPlasma
                + ", donaPlaquetas=" + donaPlaquetas + " WHERE  dni=" + persona.getDni() + "");
        conn.close();
    }

    public static void updatePacientes(Personas persona) throws SQLException {

        Connection conn = Conexion.getConnection();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("UPDATE Pacientes SET enfermedad='" + ((Pacientes) persona).getEnfermedad() + "', inicioTratamiento='"
                + ((Pacientes) persona).getInicioTratamiento().get(Calendar.YEAR) + "-" + (((Pacientes) persona).getInicioTratamiento().get(Calendar.MONTH) + 1) + "-"
                + ((Pacientes) persona).getInicioTratamiento().get(Calendar.DAY_OF_MONTH) + "' WHERE  dni=" + persona.getDni() + "");
        conn.close();
    }

    public static void updateMedicamentosPacientes(Personas persona) {

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("DELETE FROM PacientesMedicamentos WHERE dni=" + persona.getDni() + "");
            insertPacientesMedicamentos(persona);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deletePersona(Personas persona) {

        try {
            Connection conn = Conexion.getConnection();
            CallableStatement stmt;

            if (persona instanceof Pacientes) {

                if (!((Pacientes) persona).getMedicamentos().isEmpty()) {
                    stmt = conn.prepareCall("{call deletePacientesMedicamentos(?)}");
                    stmt.setInt(1, persona.getDni());
                    stmt.execute();
                }
                stmt = conn.prepareCall("{call deletePacientes(?)}");
                stmt.setInt(1, persona.getDni());
                stmt.execute();

            } else {

                if (!((Donadores) persona).getExtracciones().isEmpty()) {
                    stmt = conn.prepareCall("{call deleteExtracciones(?)}");
                    stmt.setInt(1, persona.getDni());
                    stmt.execute();
                }
                stmt = conn.prepareCall("{call deleteDonadores(?)}");
                stmt.setInt(1, persona.getDni());
                stmt.execute();

            }

            stmt = conn.prepareCall("{call deletePersonas(?)}");
            stmt.setInt(1, persona.getDni());
            stmt.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static double calcularMililitros() {

        double cantidad = 0;

        try {
            Connection conn = Conexion.getConnection();
            CallableStatement instruction = conn.prepareCall("{call calcularMililitros (?)}");
            instruction.registerOutParameter(1, java.sql.Types.DOUBLE);
            instruction.execute();

            cantidad = instruction.getDouble(1);
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cantidad;
    }

}
