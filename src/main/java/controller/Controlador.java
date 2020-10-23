package controller;

import model.*;
import model.DAO.*;

import java.util.*;

public class Controlador {

    private PersonasDB personasDB = new PersonasDB();
    private ExtraccionesDB extraccionesDB = new ExtraccionesDB();
    public Controlador(int dni) {
        personasDB = new PersonasDB(dni);
    }

    public Controlador(int dni, int idExt) {
        extraccionesDB = new ExtraccionesDB(dni,idExt);
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
        return ExtraccionesDB.selectExtraccion( dni, idExt);
    }

    public void borrarExtraccion(int dni, int idExt) {
        ExtraccionesDB.deleteExtraccion(dni,idExt);
    }
}
