package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Area;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AreaDAO {

    private JdbcUtil jdbcUtil;

    public AreaDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);;
    }

    public AreaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Area findById(int idArea) throws SQLException {
        Area area = null;
        String sql = "SELECT * FROM area where id_area = %d";
        sql = String.format(sql, idArea);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            area = new Area(resultSet.getInt("id_area"),
                    resultSet.getString("descripcion"));
        }
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
        return areasList;
    }
}
