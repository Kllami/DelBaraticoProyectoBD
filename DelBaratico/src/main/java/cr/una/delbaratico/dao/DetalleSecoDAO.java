package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.DetalleSeco;
import main.java.cr.una.delbaratico.model.Factura;
import main.java.cr.una.delbaratico.model.Seco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleSecoDAO {

    private JdbcUtil jdbcUtil;

    public DetalleSecoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public DetalleSeco findById(int idDetalleSeco) throws SQLException {
        DetalleSeco detalleSeco = null;
        String sql = "SELECT * FROM detalleSeco where id_detalleSeco = %d";
        sql = String.format(sql, idDetalleSeco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            SecoDAO secoDAO = new SecoDAO(jdbcUtil);
            Seco seco = secoDAO.findById(resultSet.getInt("id_seco"));
            FacturaDAO facturaDAO = new FacturaDAO(jdbcUtil);
            Factura factura = facturaDAO.findById(resultSet.getInt("id_factura"));

            detalleSeco = new DetalleSeco(resultSet.getInt("id_detalleSeco"),
                    resultSet.getInt("cantidad"),
                    resultSet.getDouble("subtotal"), seco, factura);
        }
        resultSet.close();
        return detalleSeco;
    }

    public List<DetalleSeco> findAll() throws SQLException {
        List<DetalleSeco> detalleSecosList = new ArrayList<>();
        String sql = "SELECT * FROM detalleSeco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        SecoDAO secoDAO = new SecoDAO(jdbcUtil);
        FacturaDAO facturaDAO = new FacturaDAO(jdbcUtil);
        while(resultSet.next()) {
            Seco seco = secoDAO.findById(resultSet.getInt("id_seco"));
            Factura factura = facturaDAO.findById(resultSet.getInt("id_factura"));
            DetalleSeco detalleSeco = new DetalleSeco(resultSet.getInt("id_detalleSeco"),
                    resultSet.getInt("subtotal"),
                    resultSet.getDouble("subtotal"), seco, factura);
            detalleSecosList.add(detalleSeco);
        }
        return detalleSecosList;
    }

    public Integer addDetalleSeco(DetalleSeco detalleSeco) throws Exception {
        try {
            String sql = "INSERT INTO DetalleSeco(cantidad, subtotal, seco_id, factura_id) "
                    + "VALUES(%d, %f, %d, %d)";
            sql = String.format(sql, detalleSeco.getCantidad(), detalleSeco.getSubtotal(), detalleSeco.getSecoId(), detalleSeco.getFacturaId());
            return jdbcUtil.executeAddAI(sql);
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer updateDetalleSeco(DetalleSeco detalleSeco) throws Exception {
        try{
            String sql="UPDATE DetalleSeco SET cantidad=%d, subtotal=%f, seco_id=%d, factura_id=%d  WHERE id_detalleSeco=%d";
            sql = String.format(sql, detalleSeco.getCantidad(), detalleSeco.getSubtotal(), detalleSeco.getSecoId(), detalleSeco.getFacturaId(), detalleSeco.getIdDetalleSeco());
            int result = jdbcUtil.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/DetalleSeco/{" + detalleSeco.getIdDetalleSeco() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer deleteDetalleSeco(String id_detalleSeco) throws Exception {
        try{
            String sql="DELETE FROM DetalleSeco WHERE id_detalleSeco=%d";
            sql = String.format(sql, id_detalleSeco);
            int result = jdbcUtil.executeUpdate(sql);
            if(result == 0){
                throw new Exception("DetalleSeco/{" + id_detalleSeco + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }
    
}
