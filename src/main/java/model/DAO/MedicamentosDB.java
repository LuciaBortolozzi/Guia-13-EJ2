package model.DAO;

import controller.Conexion;
import model.Medicamentos;
import model.Pacientes;
import model.Personas;

import java.sql.*;
import java.util.ArrayList;

public class MedicamentosDB {

    public static Medicamentos selectMedicamento(int idMed) {
        Medicamentos medicamento = null;

        try {
            Connection conn = Conexion.getConnection();
            PreparedStatement pStat = conn.prepareStatement("SELECT idMed, nombreMed, nombreLab FROM Medicamentos WHERE idMed = ?");
            pStat.setString(1, String.valueOf(idMed));
            ResultSet rs = pStat.executeQuery();
            while (rs.next()) {
                medicamento = new Medicamentos(rs.getInt("idMed"), rs.getString("nombreMed"), rs.getString("nombreLab"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamento;
    }

    public static ArrayList<Medicamentos> selectMedicamentos() {
        ArrayList<Medicamentos> medicamentos = new ArrayList<>();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT idMed, nombreMed, nombreLab FROM Medicamentos");
            while (rs.next()) {
                medicamentos.add(new Medicamentos(rs.getInt("idMed"), rs.getString("nombreMed"), rs.getString("nombreLab")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medicamentos;
    }

    public static void insertMedicamento(Medicamentos medicamento) {

        try {

            Connection conn = Conexion.getConnection();
            CallableStatement stmt = conn.prepareCall("{call insertMedicamento(?, ?, ?)}");
            stmt.setInt(1, medicamento.getIdMed());
            stmt.setString(2, medicamento.getNombreMed());
            stmt.setString(3, medicamento.getNombreLab());
            stmt.execute();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
