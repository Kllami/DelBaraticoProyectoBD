package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Caja;
import main.java.cr.una.delbaratico.model.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDao {
    private JdbcUtil jdbcUtil;
    private Usuario usuarioActual;

    public UsuarioDao() {
        usuarioActual = null;
    }

    public Usuario getUsuarioActual(){
        return this.usuarioActual;
    }

    public boolean login(String username, String password){
        boolean result = false;

        try{
            this.jdbcUtil = JdbcUtil.instance(username, password);
            result = this.jdbcUtil.isThereAConnection();
            if(result){
                this.usuarioActual = new Usuario(username, this.getRol(username));
            }
            return result;
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String getRol(String username) throws SQLException {
        String rol = "";
        String sql = " SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = '%s'";//NOMBRE EN MAYUSC
        sql = String.format(sql, username.toUpperCase());
        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            rol = resultSet.getString("GRANTED_ROLE");
        }
        return rol;
    }
}
