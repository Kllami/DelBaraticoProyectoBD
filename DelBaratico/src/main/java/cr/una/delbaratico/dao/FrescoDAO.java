package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Fresco;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class FrescoDAO {

    private JdbcUtil jdbcUtil;

    public FrescoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Fresco findById(long idFresco) throws SQLException {
        Fresco fresco = null;
        String sql = "SELECT * FROM system.fresco WHERE id_fresco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idFresco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
        }
        resultSet.close();
        return fresco;
    }

    public int updateInventario(double peso, long idFresco) {
        String sql = "UPDATE system.fresco set peso = " + peso + " WHERE id_fresco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idFresco);
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateFrescoVistaFrescos(Fresco fresco) {
        String sql = "UPDATE system.vista_producto_frescos SET plu = %d, peso = %f, ean = %d, descripcion = '%s', precio = %f WHERE id_fresco = %d AND esta_eliminado = 0";
        sql = String.format(sql, fresco.getPlu(), fresco.getPeso(), fresco.getEan(), fresco.getDescripcion(), fresco.getPrecio(),
                fresco.getIdFresco());
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateFresco(Fresco fresco) {
        String sql = "UPDATE system.fresco SET plu = %d, peso = %f, ean = %d, descripcion = '%s', precio = %f WHERE id_fresco = %d AND esta_eliminado = 0";
        sql = String.format(sql, fresco.getPlu(), fresco.getPeso(), fresco.getEan(), fresco.getDescripcion(), fresco.getPrecio(),
                fresco.getIdFresco());
        return jdbcUtil.executeUpdate(sql);
    }

    public List<Fresco> findAll() throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT * FROM system.fresco WHERE esta_eliminado = 0";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet != null && resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentDesc(String desripcion) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity('%s', system.fresco.descripcion) " +
                "AS SIMILARITY_PERCENT FROM system.fresco WHERE esta_eliminado = 0 ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, desripcion);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentEAN(long ean) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity(%d, system.fresco.ean) " +
                "AS SIMILARITY_PERCENT FROM system.fresco WHERE esta_eliminado = 0 ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public List<Fresco> findSimilarPercentPLU(long plu) throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT system.fresco.*, UTL_MATCH.edit_distance_similarity(%d, system.fresco.plu) " +
                "AS SIMILARITY_PERCENT FROM system.fresco WHERE esta_eliminado = 0 ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, plu);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }

    public Fresco findByEAN(long ean) throws SQLException {
        String sql = "SELECT * FROM system.fresco where ean = %d AND esta_eliminado = 0";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            return fresco;
        }
        resultSet.close();
        return null;
    }

    public Fresco findByPLU(long plu) throws SQLException {
        String sql = "SELECT * FROM system.fresco where plu = %d AND esta_eliminado = 0";
        sql = String.format(sql, plu);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getLong("id_fresco"),
                    resultSet.getLong("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"));
            return fresco;
        }
        resultSet.close();
        return null;
    }

    public int add(Fresco fresco) {
        String sql = "INSERT INTO system.fresco(plu, peso, ean, descripcion, precio, esta_eliminado) VALUES " +
                "(%d, %f, %d, '%s', %f, 0)";
        sql = String.format(sql, fresco.getPlu(), fresco.getPeso(), fresco.getEan(), fresco.getDescripcion(), fresco.getPrecio());
        return jdbcUtil.executeUpdate(sql);
    }

    public int eliminarFresco(Long id) {
        String sql = "UPDATE system.fresco SET esta_eliminado = 1 WHERE id_fresco = %d AND esta_eliminado = 0";
        sql = String.format(sql, id);
        return jdbcUtil.executeUpdate(sql);
    }
}
