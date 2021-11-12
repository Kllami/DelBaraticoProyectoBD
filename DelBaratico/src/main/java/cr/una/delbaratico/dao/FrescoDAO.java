package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Fresco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrescoDAO {

    private JdbcUtil jdbcUtil;

    public FrescoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Fresco findById(int idFresco) throws SQLException {
        Fresco fresco = null;
        String sql = "SELECT * FROM system.fresco where id_fresco = %d";
        sql = String.format(sql, idFresco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("cantidad"));
        }
        resultSet.close();
        return fresco;
    }

    public void updateInventario(double peso, int idFresco) {
        String sql = "update system.fresco set peso = " + peso + " where id_fresco = %d";
        sql = String.format(sql, idFresco);
        jdbcUtil.executeQuery(sql);
    }

    public List<Fresco> findAll() throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT * FROM fresco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet != null && resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"),
                    resultSet.getInt("cantidad"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }
    
}
