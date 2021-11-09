package main.java.cr.una.delbaratico.control;

import main.java.cr.una.delbaratico.model.Modelo;
import main.java.cr.una.delbaratico.model.Usuario;

public class Control {
    private Modelo modeloPrincipal;

    public Control(Modelo m) {
        this.modeloPrincipal = m;
    }

    public Control() {
        this(new Modelo());
    }

    public Usuario getUsuarioActual(){
        return this.modeloPrincipal.getUsuarioActual();
    }

    public boolean login(String user, String password){
        return this.modeloPrincipal.login(user, password);
    }

}
