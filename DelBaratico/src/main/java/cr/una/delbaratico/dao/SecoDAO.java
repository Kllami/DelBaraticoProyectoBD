package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Area;
import main.java.cr.una.delbaratico.model.Seco;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class SecoDAO {

    private JdbcUtil jdbcUtil;

    public SecoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public int updateInventario(long cantidad, long idSeco) {
        String sql = "UPDATE system.seco SET cantidad = " + cantidad + " WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idSeco);
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateInventarioAbarrotes(long cantidad, long idSeco) {
        String sql = "UPDATE system.vista_producto_abarrotes set cantidad = " + cantidad + " WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idSeco);
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateInventarioCuidadoPersonal(long cantidad, long idSeco) {
        String sql = "UPDATE system.vista_producto_cuidado_personal SET cantidad = " + cantidad + " WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idSeco);
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateInventarioMercancias(long cantidad, long idSeco) {
        String sql = "UPDATE system.vista_producto_mercancias SET cantidad = " + cantidad + " WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idSeco);
        return jdbcUtil.executeUpdate(sql);
    }

    public Seco findById(long idSeco) throws SQLException {
        Seco seco = null;
        String sql = "SELECT * FROM system.seco WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, idSeco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            /*AreaDAO areaDAO = new AreaDAO(jdbcUtil);
            Area area = areaDAO.findById(resultSet.getInt("area_id"));*/
            seco = new Seco(resultSet.getLong("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getLong("cantidad"),
                    resultSet.getLong("area_id"));
        }
        resultSet.close();
        return seco;
    }

    public List<Seco> findAll() throws SQLException {
        List<Seco> secosList = new ArrayList<>();
        String sql = "SELECT * FROM system.seco WHERE esta_eliminado = 0";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Area area = areaDAO.findById(resultSet.getInt("area_id"));
            Seco seco = new Seco(resultSet.getLong("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getLong("cantidad"),
                    resultSet.getLong("area_id"));
        }
        resultSet.close();
        return secosList;
    }

    public List<Seco> findSimilarPercentDesc(String desripcion) throws SQLException {
        List<Seco> secosList = new ArrayList<>();
        String sql = "SELECT system.seco.*, UTL_MATCH.edit_distance_similarity('%s', system.seco.descripcion) " +
                "AS SIMILARITY_PERCENT FROM system.seco WHERE esta_eliminado = 0 ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, desripcion);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Seco seco = new Seco(resultSet.getLong("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getLong("cantidad"),
                    resultSet.getLong("area_id"));
            secosList.add(seco);
        }
        resultSet.close();
        return secosList;
    }

    public List<Seco> findSimilarPercentEAN(long ean) throws SQLException {
        List<Seco> secosList = new ArrayList<>();
        String sql = "SELECT system.seco.*, UTL_MATCH.edit_distance_similarity(%d, system.seco.ean) " +
                "AS SIMILARITY_PERCENT FROM system.seco WHERE esta_eliminado = 0 ORDER BY SIMILARITY_PERCENT DESC FETCH FIRST 10 ROWS ONLY";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Seco seco = new Seco(resultSet.getLong("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getLong("cantidad"),
                    resultSet.getLong("area_id"));
            secosList.add(seco);
        }
        resultSet.close();
        return secosList;
    }

    public Seco findByEAN(long ean) throws SQLException {
        String sql = "SELECT * FROM system.seco WHERE ean = %d AND esta_eliminado = 0";
        sql = String.format(sql, ean);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            Seco seco = new Seco(resultSet.getLong("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getLong("cantidad"),
                    resultSet.getLong("area_id"));
            return seco;
        }
        resultSet.close();
        return null;
    }

    public int add(Seco seco) {
        String sql = "INSERT INTO system.seco(ean, descripcion, precio, cantidad, area_id) VALUES " +
                "(%d, '%s', %f, %d, %d)";
        sql = String.format(sql, seco.getEan(), seco.getDescripcion(), seco.getPrecio(), seco.getCantidad(), seco.getAreaId());
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateSeco(Seco seco) {
        String sql = "UPDATE system.seco SET ean = %d, descripcion = '%s', precio = %f, cantidad = %d, area_id = %d WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, seco.getEan(), seco.getDescripcion(), seco.getPrecio(), seco.getCantidad(), seco.getAreaId(), seco.getIdSeco());
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateSecoAbarrotes(Seco seco) {
        String sql = "UPDATE system.vista_producto_abarrotes SET ean = %d, descripcion = '%s', precio = %f, cantidad = %d, area_id = %d WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, seco.getEan(), seco.getDescripcion(), seco.getPrecio(), seco.getCantidad(), seco.getAreaId(), seco.getIdSeco());
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateSecoMercancias(Seco seco) {
        String sql = "UPDATE system.vista_producto_mercancias SET ean = %d, descripcion = '%s', precio = %f, cantidad = %d, area_id = %d WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, seco.getEan(), seco.getDescripcion(), seco.getPrecio(), seco.getCantidad(), seco.getAreaId(), seco.getIdSeco());
        return jdbcUtil.executeUpdate(sql);
    }

    public int updateSecoCiudadoPersonal(Seco seco) {
        String sql = "UPDATE system.vista_producto_cuidado_personal SET ean = %d, descripcion = '%s', precio = %f, cantidad = %d, area_id = %d WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, seco.getEan(), seco.getDescripcion(), seco.getPrecio(), seco.getCantidad(), seco.getAreaId(), seco.getIdSeco());
        return jdbcUtil.executeUpdate(sql);
    }

    public int eliminarSeco(Seco seco) {
        String sql = "UPDATE system.seco SET esta_eliminado = 1 WHERE id_seco = %d AND esta_eliminado = 0";
        sql = String.format(sql, seco.getIdSeco());
        return jdbcUtil.executeUpdate(sql);
    }
}
