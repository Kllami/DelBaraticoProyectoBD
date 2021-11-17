package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Caja;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class CajaDAO {

    private JdbcUtil jdbcUtil;

    public CajaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Caja findById(int idCaja) throws SQLException {
        Caja caja = null;
        String sql = "SELECT * FROM system.caja where id_caja = %d";
        sql = String.format(sql, idCaja);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            caja = new Caja(resultSet.getInt("id_caja"),
                    resultSet.getString("usuario"));
        }
        resultSet.close();
        return caja;
    }

    public List<Caja> findAll() throws SQLException {
        List<Caja> cajasList = new ArrayList<>();
        String sql = "SELECT * FROM system.caja";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Caja caja = new Caja(resultSet.getInt("id_caja"),
                    resultSet.getString("usuario"));
            cajasList.add(caja);
        }
        resultSet.close();
        return cajasList;
    }
    
}
