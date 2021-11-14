package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FrescoDAO {

    private JdbcUtil jdbcUtil;

    public FrescoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Fresco findById(double idFresco) throws SQLException {
        Fresco fresco = null;
        String sql = "SELECT * FROM system.fresco where id_fresco = %f";
        sql = String.format(sql, idFresco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
        }
        resultSet.close();
        return fresco;
    }

    public void updateInventario(double peso, double idFresco) {
        String sql = "update system.fresco set peso = " + peso + " where id_fresco = %f";
        sql = String.format(sql, idFresco);
        jdbcUtil.executeQuery(sql);
    }

    public List<Fresco> findAll() throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT * FROM system.fresco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet != null && resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
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
                "AS SIMILARITY_PERCENT FROM system.fresco ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, desripcion);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentEAN(double ean) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity(%f, system.fresco.ean) " +
                "AS SIMILARITY_PERCENT FROM system.fresco ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentPLU(double plu) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity(%f, system.fresco.plu) " +
                "AS SIMILARITY_PERCENT FROM system.fresco ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, plu);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public Fresco findByEAN(double ean) throws SQLException {
        String sql = "SELECT * FROM system.fresco where ean = %f";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            return fresco;
        }
        resultSet.close();
        return null;
    }

    public Fresco findByPLU(double plu) throws SQLException {
        String sql = "SELECT * FROM system.fresco where plu = %f";
        sql = String.format(sql, plu);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getDouble("id_fresco"),
                    resultSet.getDouble("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            return fresco;
        }
        resultSet.close();
        return null;
    }

    public int add(Fresco fresco) {
        String sql = "INSERT INTO system.fresco(plu, peso, ean, descripcion, precio) VALUES " +
                "(%f, %f, %f, '%s', %f)";
        sql = String.format(sql, fresco.getPlu(), fresco.getPeso(), fresco.getEan(), fresco.getDescripcion(), fresco.getPrecio());
        return jdbcUtil.executeUpdate(sql);
    }
}
