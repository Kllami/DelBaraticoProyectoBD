package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Area;
import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecoDAO {

    private JdbcUtil jdbcUtil;

    public SecoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public void updateInventario(int cantidad, int idSeco) {
        String sql = "update system.seco set cantidad = " + cantidad + " where id_seco = %d";
        sql = String.format(sql, idSeco);
        jdbcUtil.executeQuery(sql);
    }

    public Seco findById(int idSeco) throws SQLException {
        Seco seco = null;
        String sql = "SELECT * FROM system.seco where id_seco = %d";
        sql = String.format(sql, idSeco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            /*AreaDAO areaDAO = new AreaDAO(jdbcUtil);
            Area area = areaDAO.findById(resultSet.getInt("area_id"));*/
            seco = new Seco(resultSet.getInt("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("cantidad"),
                    resultSet.getInt("area_id"));
        }
        resultSet.close();
        return seco;
    }

    public List<Seco> findAll() throws SQLException {
        List<Seco> secosList = new ArrayList<>();
        String sql = "SELECT * FROM system.seco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        AreaDAO areaDAO = new AreaDAO(jdbcUtil);
        while(resultSet.next()) {
            Area area = areaDAO.findById(resultSet.getInt("area_id"));
            Seco seco = new Seco(resultSet.getInt("id_seco"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("precio"),
                    resultSet.getInt("cantidad"),
                    resultSet.getInt("area_id"));
            secosList.add(seco);
        }
        resultSet.close();
        return secosList;
    }
    
}
