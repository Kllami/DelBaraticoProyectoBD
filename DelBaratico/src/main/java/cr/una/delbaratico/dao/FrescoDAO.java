package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Fresco;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrescoDAO {

    private JdbcUtil jdbcUtil;

    public FrescoDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);;
    }

    public FrescoDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public Fresco findById(int idFresco) throws SQLException {
        Fresco fresco = null;
        String sql = "SELECT * FROM fresco where id_fresco = %d";
        sql = String.format(sql, idFresco);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
        }
        resultSet.close();
        return fresco;
    }

    public List<Fresco> findAll() throws SQLException {
        List<Fresco> frescosList = new ArrayList<>();
        String sql = "SELECT * FROM fresco";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet != null && resultSet.next()) {
            Fresco fresco = new Fresco(resultSet.getInt("id_fresco"),
                    resultSet.getInt("plu"),
                    resultSet.getDouble("peso"),
                    resultSet.getLong("ean"),
                    resultSet.getString("descripcion"),
                    resultSet.getDouble("peso"));
            frescosList.add(fresco);
        }
        resultSet.close();
        return frescosList;
    }
    
}
