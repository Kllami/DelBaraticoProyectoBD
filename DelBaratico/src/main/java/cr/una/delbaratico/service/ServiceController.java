package main.java.cr.una.delbaratico.service;

import main.java.cr.una.delbaratico.model.Model;
import main.java.cr.una.delbaratico.model.Usuario;

public class ServiceController {

    private Model modeloPrincipal;

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
