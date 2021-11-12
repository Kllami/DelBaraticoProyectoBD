package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Auditoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuditoriaDAO {

    private JdbcUtil jdbcUtil;

    public AuditoriaDAO(JdbcUtil jdbcUtil) {
        this.jdbcUtil = jdbcUtil;
    }

    public AuditoriaDAO(String DB_USER, String DB_PASSWORD) {
        this.jdbcUtil = JdbcUtil.instance(DB_USER, DB_PASSWORD);
    }

    public Auditoria findById(int idAuditoria) throws SQLException {
        Auditoria auditoria = null;
        String sql = "SELECT * FROM system.auditoria where id_auditoria = %d";
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
        String sql = "SELECT * FROM system.auditoria";
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Auditoria auditoria = new Auditoria(resultSet.getInt("id_auditoria"),
                    resultSet.getString("usuario"),
                    resultSet.getString("tabla"),
                    resultSet.getString("transaccion"),
                    resultSet.getDate("fecha"));
            auditoriasList.add(auditoria);
        }
        System.out.println(auditoriasList);
        return auditoriasList;
    }

    public List<Auditoria> findSelect(String user) throws SQLException {
        List<Auditoria> auditoriasList = new ArrayList<>();
        String sql = "Select ENTRYID, username, timestamp, obj_name, action_name from dba_audit_object where username='" + user + "' AND action_name = 'SELECT' " +
                "AND (obj_name = 'AUDITORIA' OR obj_name = 'FACTURA' OR obj_name = 'AREA' OR obj_name = 'SECO' OR obj_name = 'FRESCO' OR obj_name = 'CAJA' OR obj_name = 'DETALLESECO' OR obj_name = 'DETALLEFRESCO')";

        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        while(resultSet.next()) {
            Auditoria auditoria = new Auditoria(resultSet.getInt("ENTRYID"),
                    resultSet.getString("username"),
                    resultSet.getString("obj_name"),
                    resultSet.getString("action_name"),
                    resultSet.getDate("timestamp"));
            auditoriasList.add(auditoria);
        }
        System.out.println(auditoriasList);
        return auditoriasList;
    }
    
}
