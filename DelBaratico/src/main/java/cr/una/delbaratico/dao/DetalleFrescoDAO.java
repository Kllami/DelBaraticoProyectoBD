package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Factura;
import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.DetalleFresco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleFrescoDAO {

    private JdbcUtil jdbcUtil;

    public DetalleFrescoDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);
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

}
