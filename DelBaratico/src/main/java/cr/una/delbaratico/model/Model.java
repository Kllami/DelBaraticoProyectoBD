package main.java.cr.una.delbaratico.model;

import main.java.cr.una.delbaratico.dao.*;
import main.java.cr.una.delbaratico.model.Caja;
import main.java.cr.una.delbaratico.model.Factura;
import main.java.cr.una.delbaratico.model.Usuario;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

public class Model extends Observable {

    Factura factura;
    private final UsuarioDAO usuarioDao;

    public Model() {
        usuarioDao = new UsuarioDAO();
        Date todayDateUtil = Calendar.getInstance().getTime();
        factura = new Factura(0, 0, new java.sql.Date(todayDateUtil.getTime()), 0, "", new Caja(0, ""));
    }

    public void genericMethod() {
        //doSomething(x, y);
        setChanged();
        notifyObservers();
    }

    public boolean login(String username, String password) {
        return this.usuarioDao.login(username, password);
    }

    public void logot() {
        this.usuarioDao.cerrarConexion();
    }

    public Usuario getUsuarioActual() {
        return this.usuarioDao.getUsuarioActual();
    }
}
