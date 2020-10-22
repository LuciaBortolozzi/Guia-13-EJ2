package controller;

import model.*;
import model.DAO.*;

import java.util.*;

public class Controlador {

    private PersonasDB personasDB = new PersonasDB();

    public Controlador(int dni) {
        personasDB = new PersonasDB(dni);
    }

    public Controlador() {
    }

    public Personas consultaPersona(int dni) {
        return PersonasDB.selectPersona(dni);
    }

    public ArrayList<String> seleccionarDonadores() {
        return PersonasDB.selectDonadores();
    }

    public ArrayList<String> seleccionarExtracciones(int dni) {
        return PersonasDB.selectExtracciones(dni);
    }
}
