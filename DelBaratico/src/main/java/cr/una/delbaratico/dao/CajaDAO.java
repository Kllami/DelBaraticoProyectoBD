package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Caja;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CajaDAO {

    private JdbcUtil jdbcUtil;

    public CajaDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);
    }

    public CajaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Caja findById(int idCaja) throws SQLException {
        Caja caja = null;
        String sql = "SELECT * FROM caja where id_caja = %d";
        sql = String.format(sql, idCaja);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            caja = new Caja(resultSet.getInt("id_caja"),
                    resultSet.getString("usuario"));
        }
        return caja;
    }

    public List<Caja> findAll() throws SQLException {
        List<Caja> cajasList = new ArrayList<>();
        String sql = "SELECT * FROM caja";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Caja caja = new Caja(resultSet.getInt("id_caja"),
                    resultSet.getString("usuario"));
            cajasList.add(caja);
        }
        return cajasList;
    }
    
}
