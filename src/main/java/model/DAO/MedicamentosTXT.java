package model.DAO;

import model.Medicamentos;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class MedicamentosTXT {
    private static final String directorio = "D:\\\\IdeaProjects\\\\Guia-13-EJ2\\\\src\\\\main\\\\java\\\\model\\\\DAO\\\\";

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

    public static void grabarMedicamentosTXT(ArrayList<Medicamentos> medicamentos) {

        try {
            File fichero = new File(directorio + "Medicamentos.txt");

            if (fichero.exists()) {
                PrintWriter archivoSalida = new PrintWriter(fichero);

                Medicamentos medicamento;
                Iterator<Medicamentos> med = medicamentos.iterator();
                while (med.hasNext()) {
                    medicamento = med.next();

                    archivoSalida.println(String.format("%010d", medicamento.getIdMed()) + medicamento.getNombreMed() + medicamento.getNombreLab());

                }
                archivoSalida.close();
            }

        } catch (IOException e) {
            System.out.println("No se puede grabar el archivo de Medicamentos.txt");
        }
    }

    public static void grabarMedicamentoTXT(Medicamentos medicamento) {

        try {
            FileWriter fichero = new FileWriter(directorio + "Medicamentos.txt", true);

            PrintWriter archivoSalida = new PrintWriter(fichero);

            archivoSalida.println(String.format("%010d", medicamento.getIdMed()) + medicamento.getNombreMed() + medicamento.getNombreLab());

            archivoSalida.close();

        } catch (IOException e) {
            System.out.println("No se puede grabar el archivo de Medicamentos.txt");
        }
    }
}
