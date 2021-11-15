package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Area;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.List;

public class AreaDAO {

    private JdbcUtil jdbcUtil;

    public AreaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Area findById(int idArea) throws SQLException {
        Area area = null;
        String sql = "SELECT * FROM system.area where id_area = %d";
        sql = String.format(sql, idArea);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            area = new Area(resultSet.getInt("id_area"),
                    resultSet.getString("descripcion"));
        }
        resultSet.close();
        return area;
    }

    public List<Area> findAll() throws SQLException {
        List<Area> areasList = new ArrayList<>();
        String sql = "SELECT * FROM area";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Area area = new Area(resultSet.getInt("id_area"),
                    resultSet.getString("descripcion"));
            areasList.add(area);
        }
        resultSet.close();
        return areasList;
   }

    public List<String> areasIDSNombres() throws SQLException {
        List<String> areasIDsList = new ArrayList<>();
        String sql = "SELECT id_area, descripcion FROM system.area";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            areasIDsList.add(String.valueOf(resultSet.getInt("id_area")) + " - " + resultSet.getString("descripcion"));
        }
        resultSet.close();
        return areasIDsList;
    }
}
