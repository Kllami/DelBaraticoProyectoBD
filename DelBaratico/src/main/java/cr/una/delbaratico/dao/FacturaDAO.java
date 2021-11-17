package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Caja;
import main.java.cr.una.delbaratico.model.Factura;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class FacturaDAO {

    private JdbcUtil jdbcUtil;

    public FacturaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Factura findById(int idFactura) throws SQLException {
        Factura factura = null;
        String sql = "SELECT * FROM system.factura where id_factura = %d";
        sql = String.format(sql, idFactura);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            CajaDAO cajaDAO = new CajaDAO(jdbcUtil);
            Caja caja = cajaDAO.findById(resultSet.getInt("id_caja"));
            factura = new Factura(resultSet.getInt("id_factura"),
                    resultSet.getInt("numero"),
                    resultSet.getDate("fecha"),
                    resultSet.getDouble("total"),
                    resultSet.getString("usuario"),
                    resultSet.getInt("num_caja"));
        }
        resultSet.close();
        return factura;
    }

    public List<Factura> findAll() throws SQLException {
        List<Factura> facturasList = new ArrayList<>();
        String sql = "SELECT * FROM system.factura";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        CajaDAO cajaDAO = new CajaDAO(jdbcUtil);
        while(resultSet.next()) {
            Caja caja = cajaDAO.findById(resultSet.getInt("num_caja"));
            Factura factura = new Factura(resultSet.getInt("id_factura"),
                    resultSet.getInt("numero"),
                    resultSet.getDate("fecha"),
                    resultSet.getDouble("total"),
                    resultSet.getString("usuario"),
                    resultSet.getInt("num_caja"));
            facturasList.add(factura);
        }
        resultSet.close();
        return facturasList;
    }

    public void insertarFactura(Factura factura) {
        String sql = "INSERT INTO system.factura(numero, fecha, total, usuario, num_caja) VALUES " +
                "(numero_factura_seq.NEXTVAL, " + "TO_DATE(" + "'" + factura.getFecha() + "'" + ", 'YYYY-MM-DD HH:MI:SS')" + ", " + factura.getTotal()+ ", "
                + "'" + factura.getUsuario() +  "'" +  ", " + factura.getNumCaja() + ")";
        jdbcUtil.executeQuery(sql);
    }
}
