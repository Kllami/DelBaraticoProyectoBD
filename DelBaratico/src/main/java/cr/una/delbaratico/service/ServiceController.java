package main.java.cr.una.delbaratico.service;

import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.model.Caja;
import main.java.cr.una.delbaratico.model.Model;
import main.java.cr.una.delbaratico.model.Usuario;

import java.util.List;

public class ServiceController {

    private Model modeloPrincipal;
    private JdbcUtil util;

    public ServiceController(Model iniciarSesionModel) {
        this.modeloPrincipal = iniciarSesionModel;
    }

    public boolean iniciarSesion(String DB_USER, String DB_PASSWORD) {
        return this.modeloPrincipal.login(DB_USER, DB_PASSWORD);
    }

    public Usuario getUsuarioActual(){
        return this.modeloPrincipal.getUsuarioActual();
    }
}
