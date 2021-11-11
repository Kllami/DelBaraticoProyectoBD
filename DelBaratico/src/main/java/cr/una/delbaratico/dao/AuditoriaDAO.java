package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Auditoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaDAO {

    private JdbcUtil jdbcUtil;

    public AuditoriaDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);;
    }

    public Auditoria findById(int idAuditoria) throws SQLException {
        Auditoria auditoria = null;
        String sql = "SELECT * FROM auditoria where id_auditoria = %d";
        sql = String.format(sql, idAuditoria);
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            auditoria = new Auditoria(resultSet.getInt("id_auditoria"),
                    resultSet.getString("usuario"),
                    resultSet.getString("tabla"),
                    resultSet.getString("transaccion"),
                    resultSet.getDate("fecha"));
        }
        resultSet.close();
        return auditoria;
    }

    public List<Auditoria> findAll() throws SQLException {
        List<Auditoria> auditoriasList = new ArrayList<>();
        String sql = "SELECT * FROM auditoria";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Auditoria auditoria = new Auditoria(resultSet.getInt("id_auditoria"),
                    resultSet.getString("usuario"),
                    resultSet.getString("tabla"),
                    resultSet.getString("transaccion"),
                    resultSet.getDate("fecha"));
            auditoriasList.add(auditoria);
        }
        return auditoriasList;
    }
    
}
