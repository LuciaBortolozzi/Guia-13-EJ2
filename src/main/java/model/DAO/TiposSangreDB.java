package model.DAO;

import controller.Conexion;
import model.Provincias;
import model.TiposSangre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TiposSangreDB {

    public static TiposSangre buscarTipoSangre(int tipSan) {
        TiposSangre tipoSangre = new TiposSangre();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id, grupo, factor FROM TiposSangre WHERE id =" + tipSan);
            while (rs.next()) {
                tipoSangre.setId(rs.getInt("id"));
                tipoSangre.setGrupo(rs.getString("grupo"));
                tipoSangre.setFactor(rs.getString("factor"));
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tipoSangre;
    }


    public static ArrayList<TiposSangre> selectTiposSangre() {
        ArrayList<TiposSangre> tiposSangre = new ArrayList<TiposSangre>();

        try {
            Connection conn = Conexion.getConnection();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT id, grupo, factor FROM TiposSangre");
            while (rs.next()) {
                tiposSangre.add(new TiposSangre(rs.getInt("id"), rs.getString("grupo"), rs.getString("factor")));
            }
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposSangre;
    }


}
