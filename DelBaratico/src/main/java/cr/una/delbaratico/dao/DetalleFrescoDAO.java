package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.DetalleFresco;
import main.java.cr.una.delbaratico.model.Factura;
import main.java.cr.una.delbaratico.model.Fresco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleFrescoDAO {

    private JdbcUtil jdbcUtil;

    public DetalleFrescoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public DetalleFresco findById(int idDetalleFresco) throws SQLException {
        DetalleFresco detalleFresco = null;
        String sql = "SELECT * FROM detalleFresco where id_detalleFresco = %d";
        sql = String.format(sql, idDetalleFresco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            FrescoDAO frescoDAO = new FrescoDAO(jdbcUtil);
            Fresco fresco = frescoDAO.findById(resultSet.getInt("id_fresco"));
            FacturaDAO facturaDAO = new FacturaDAO(jdbcUtil);
            Factura factura = facturaDAO.findById(resultSet.getInt("id_factura"));

            detalleFresco = new DetalleFresco(resultSet.getInt("id_detalleFresco"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("subtotal"), fresco, factura);
        }
        resultSet.close();
        return detalleFresco;
    }

    public List<DetalleFresco> findAll() throws SQLException {
        List<DetalleFresco> detalleFrescosList = new ArrayList<>();
        String sql = "SELECT * FROM detalleFresco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        FrescoDAO frescoDAO = new FrescoDAO(jdbcUtil);
        FacturaDAO facturaDAO = new FacturaDAO(jdbcUtil);
        while(resultSet.next()) {
            Fresco fresco = frescoDAO.findById(resultSet.getInt("id_fresco"));
            Factura factura = facturaDAO.findById(resultSet.getInt("id_factura"));
            DetalleFresco detalleFresco = new DetalleFresco(resultSet.getInt("id_detalleFresco"),
                    resultSet.getDouble("peso"),
                    resultSet.getDouble("subtotal"), fresco, factura);
            detalleFrescosList.add(detalleFresco);
        }
        resultSet.close();
        return detalleFrescosList;
    }

    public Integer addDetalleFresco(DetalleFresco detalleFresco) throws Exception {
        try {
            String sql = "INSERT INTO DetalleFresco(peso, subtotal, seco_id, factura_id) "
                    + "VALUES(%d, %f, %d, %d)";
            sql = String.format(sql, detalleFresco.getPeso(), detalleFresco.getSubtotal(), detalleFresco.getFrescoId(), detalleFresco.getFacturaId());
            return jdbcUtil.executeAddAI(sql);
        } catch(Exception e) {
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer updateDetalleFresco(DetalleFresco detalleFresco) throws Exception {
        try{
            String sql="UPDATE DetalleFresco SET peso=%d, subtotal=%f, seco_id=%d, factura_id=%d  WHERE id_detalleFresco=%d";
            sql = String.format(sql, detalleFresco.getPeso(), detalleFresco.getSubtotal(), detalleFresco.getFrescoId(), detalleFresco.getFacturaId(), detalleFresco.getIdDetalleFresco());
            int result = jdbcUtil.executeUpdate(sql);
            if(result == 0){
                throw new Exception("/DetalleFresco/{" + detalleFresco.getIdDetalleFresco() + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }

    public Integer deleteDetalleFresco(String id_detalleFresco) throws Exception {
        try{
            String sql="DELETE FROM DetalleFresco WHERE id_detalleFresco=%d";
            sql = String.format(sql, id_detalleFresco);
            int result = jdbcUtil.executeUpdate(sql);
            if(result == 0){
                throw new Exception("DetalleFresco/{" + id_detalleFresco + "} Does not exist in DataBase");
            }
            return result;
        }catch(Exception e){
            throw new Exception("Exception: " + e.getMessage());
        }
    }
    
}
