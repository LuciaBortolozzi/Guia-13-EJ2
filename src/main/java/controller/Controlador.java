package controller;

import model.*;
import model.DAO.*;

import java.util.*;

public class Controlador {

    private PersonasDB personasDB = new PersonasDB();
    private ExtraccionesDB extraccionesDB = new ExtraccionesDB();
    private EstadisticasDB estadisticasDB = new EstadisticasDB();

    public Controlador(int dni) {
        personasDB = new PersonasDB(dni);
    }

    public Controlador(int dni, int idExt) {
        extraccionesDB = new ExtraccionesDB(dni, idExt);
    }

    public Controlador(int dni, int idExt, double peso, double cantExtraida, String presion, double recuentoGlobRojos) {
        extraccionesDB = new ExtraccionesDB(dni, idExt);
    }

    public Controlador(String nombreProv, String tipoSangre) {
        personasDB = new PersonasDB(nombreProv, tipoSangre);
    }

    public Controlador(int dni, double peso, String presion, double recuentoGlobRojos, double cantExtraida) {
        extraccionesDB = new ExtraccionesDB(dni, peso, presion, recuentoGlobRojos, cantExtraida);
    }

    public Controlador() {
    }

    public Personas consultaPersona(int dni) {
        return PersonasDB.selectPersona(dni);
    }

    public ArrayList<String> seleccionarDonadores() {
        return PersonasDB.selectDonadores();
    }

    public Personas seleccionarExtracciones(int dni, int idExt) {
        return ExtraccionesDB.selectExtraccion(dni, idExt);
    }

    public void borrarExtraccion(int dni, int idExt) {
        ExtraccionesDB.deleteExtraccion(dni, idExt);
    }

    public void updateExtracciones(int dni, int idExt, double peso, double cantExtraida, String presion, double recuentoGlobRojos) {
        ExtraccionesDB.updateExtracciones(dni, idExt, peso, cantExtraida, presion, recuentoGlobRojos);
    }

    public TreeSet<Personas> selectPersonasPorProvTipoSangre(String nombreProv, String tipoSangre) {
        return PersonasDB.selectPersonasPorProvTipoSangre(nombreProv, tipoSangre);
    }

    public int selectLongitudTablaPersonas() {
        return PersonasDB.selectLongitudTablaPersonas();
    }

    public void insertExtracciones(int dni, double peso, String presion, double recuentoGlobRojos, double cantExtraida) {
        ExtraccionesDB.insertExtracciones(dni, peso, presion, recuentoGlobRojos, cantExtraida);
    }

    public TreeSet<Personas> selectEstadisticaUno() {
        return EstadisticasDB.selectEstadisticaUno();
    }
}