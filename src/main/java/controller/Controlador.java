package controller;

import model.*;
import model.DAO.*;

import java.util.*;

import static controller.Controlador.personas;

public class Controlador {

    private PersonasDB personasDB = new PersonasDB();
    private ExtraccionesDB extraccionesDB = new ExtraccionesDB();
    private EstadisticasDB estadisticasDB = new EstadisticasDB();
    static ArrayList<Medicamentos> medicamentos = MedicamentosTXT.bajarMedicamentosTXT();

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

    public static Medicamentos consultaMedicamento(int idMed) {
        Medicamentos medicamento = null;
        for (Medicamentos med : medicamentos) {
            if (med.getIdMed() == idMed)
                medicamento = med;
        }
        return medicamento;
    }

    public static ArrayList<String> seleccionarMedicamentos() {
        ArrayList<String> medicamentosST = new ArrayList<String>();
        for (Medicamentos med : medicamentos)
            medicamentosST.add(String.valueOf(med.getIdMed()));
        return medicamentosST;
    }

    public static void ingresoMedicamento(Medicamentos medicamento) {
        MedicamentosTXT.grabarMedicamentoTXT(medicamento);
    }


    public static void borrarMedicamento(int idMed) {

        Medicamentos medicamento;
        Iterator<Medicamentos> med = medicamentos.iterator();
        while (med.hasNext()) {
            medicamento = med.next();

            if (medicamento.getIdMed() == idMed) {
                medicamentos.remove(medicamento);
                break;
            }
        }
        MedicamentosTXT.grabarMedicamentosTXT(medicamentos);
    }

    public static void estadisticas(){
        TreeSet<Personas> personas = PersonasDB.selectPersonas();
        TreeSet<Personas> personasAux = new TreeSet<Personas>();
        TreeSet<Personas> personasAux2 = new TreeSet<Personas>();
        double cantidad = 0;
        double totalPeso;

        Calendar seisMesesAntes = Calendar.getInstance();
        seisMesesAntes.add(Calendar.MONTH, -6);


        String provincia = "CABA";

        for (Personas p : personas) {

            if (p.getLocalidad().getProvincia().getNombreProv().equals(provincia)) {

                personasAux2.add(p);
            }
        }

        boolean hayEnCaba = personas.stream().filter(p -> p.getLocalidad().getProvincia().getNombreProv().equals("CABA"))
                .map(p -> p.getLocalidad().getProvincia().getNombreProv())
                .forEach(p -> personasAux.add(p));

        for (Personas p : personas) {

            if (p instanceof Donadores) {
                if (((Donadores) p).isDonaPlaquetas() && ((Donadores) p).isDonaSangre() && ((Donadores) p).isDonaPlasma()) {

                    for (int i = 0; i < ((Donadores) p).getExtracciones().size(); i++) {

                        if (((Donadores) p).getExtracciones().get(i).getFechaDonacion().after(seisMesesAntes)) {

                            cantidad = cantidad + ((Donadores) p).getExtracciones().get(i).getCantExtraida();
                        }
                    }
                }
            }
        }

        for (Personas p : personas) {

            if (p instanceof Donadores) {
                for (int i = 0; i < ((Donadores) p).getExtracciones().size(); i++) {
                    if (((Donadores) p).getExtracciones().get(i).getPesoDonador() != totalPeso) {

                        personasAux.add(p);
                    }
                }
            }
        }
    }
}