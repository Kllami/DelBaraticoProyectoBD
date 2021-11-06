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

    public DetalleSecoDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);
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
    
}
