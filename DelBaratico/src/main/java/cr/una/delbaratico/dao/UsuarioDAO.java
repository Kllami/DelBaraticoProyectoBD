package main.java.cr.una.delbaratico.dao;

import main.java.cr.una.delbaratico.model.Usuario;
import java.sql.SQLException;
import java.sql.ResultSet;


public class UsuarioDAO {
    private JdbcUtil jdbcUtil;
    private Usuario usuarioActual;

    public UsuarioDAO() {
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
                this.usuarioActual = new Usuario(username, this.getRol(username), password);
            }
            return result;
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return result;
    }

    public JdbcUtil returnJdbcUtil(){
        return this.jdbcUtil;
    }

    public String getRol(String username) throws SQLException {
        String rol = "";
        String sql = " SELECT GRANTED_ROLE FROM DBA_ROLE_PRIVS WHERE GRANTEE = '%s'";//NOMBRE EN MAYUSC
        sql = String.format(sql, username.toUpperCase());

        ResultSet resultSet = jdbcUtil.executeQuery(sql);
        if(resultSet.next()) {
            rol = resultSet.getString("GRANTED_ROLE");
        }
        resultSet.close();
        return rol;
    }

    public void cerrarConexion(){
        this.jdbcUtil.cerrarConexion();
    }
}
