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
        extraccionesDB = new ExtraccionesDB(dni, idExt);
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

    public static Medicamentos consultaMedicamento(int idMed) {
        return MedicamentosDB.selectMedicamento(idMed);
    }

    public static ArrayList<String> seleccionarMedicamentos() {
        return MedicamentosDB.selectMedicamentosST();
    }

    public static void ingresoMedicamento(Medicamentos medicamento) {
        MedicamentosDB.insertMedicamento(medicamento);
    }


    public static void borrarMedicamento(int idMed) {
        MedicamentosDB.deleteMedicamento(idMed);
    }

    public static ArrayList<Medicamentos> leerMedicamentos(String origen) {
        ArrayList<Medicamentos> medicamentos = new ArrayList<Medicamentos>();

        if (origen == null)
            origen = "TXT";

        if (origen.equals("TXT")) {
            for (Medicamentos medTXT : MedicamentosTXT.bajarMedicamentosTXT()) {
                medicamentos.add(new Medicamentos(medTXT.getIdMed(), medTXT.getNombreMed(), medTXT.getNombreLab()));
            }
        } else {
            for (Medicamentos medDB : MedicamentosDB.selectMedicamentos()) {
                medicamentos.add(new Medicamentos(medDB.getIdMed(), medDB.getNombreMed(), medDB.getNombreLab()));
            }
        }
        return medicamentos;
    }

    public static void actualizarMedicamento(int idMed, String nombreMed, String nombreLab, String origen) {
/*        actualizarListMedicamento(medicamento.getIdMed(), 'M');
        MedicamentosDB.modificar(medicamento);
        MedicamentosTXT.modificar(medicamentos);*/
    }

    public static void actualizarListMedicamento(int idMed, char action) {
       /* for (Medicamentos med : medicamentos) {
            if (med.getIdMed() == idMed) {
                if (action == 'B') {
                    medicamentos.remove(med);
                } else {
                    med.setNombreMed(medicamento.getNombreMed());
                    med.setNombreLab(medicamento.getNombreLab());
                }
                break;
            }
        }*/
    }
}
