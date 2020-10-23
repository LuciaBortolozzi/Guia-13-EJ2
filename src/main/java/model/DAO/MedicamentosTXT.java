package model.DAO;

import model.Medicamentos;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MedicamentosTXT {
    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-10\\\\src\\\\resources\\\\";

    public static ArrayList<Medicamentos> bajarMedicamentosTXT() {

        ArrayList<Medicamentos> medicamentos = new ArrayList<Medicamentos>();
        try {
            File archivo = new File(directorio + "Medicamentos.txt");
            if (archivo.exists()) {
                Scanner leerArchivoMedicamentos = new Scanner(archivo);
                ArrayList<String> medicamentosST = new ArrayList<String>();

                //Guardar contenido en String
                while (leerArchivoMedicamentos.hasNext()) {
                    String lineaActual = leerArchivoMedicamentos.nextLine();
                    medicamentosST.add(lineaActual);
                }

                // Guardar objetos
                for (String s : medicamentosST) {

                    int idMed = Integer.parseInt(s.substring(0, 10).trim());
                    String nombreMed = s.substring(10, 58).trim();
                    String nombreLab = s.substring(58, 83).trim();

                    medicamentos.add(new Medicamentos(idMed, nombreMed, nombreLab));
                }

                leerArchivoMedicamentos.close();
            }

        } catch (InputMismatchException | IOException e) {
            e.printStackTrace();
        }

        return medicamentos;
    }
}
