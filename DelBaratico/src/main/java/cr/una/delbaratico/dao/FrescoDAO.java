package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Fresco;
import java.sql.SQLException;
import java.sql.ResultSet;
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
                    resultSet.getDouble("precio"));
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
        String sql = "SELECT * FROM system.fresco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet != null && resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentDesc(String desripcion) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity('%s', system.fresco.descripcion) " +
                "AS SIMILARITY_PERCENT FROM system.fresco ORDER BY SIMILARITY_PERCENT DESC";
        sql = String.format(sql, desripcion);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }
}
