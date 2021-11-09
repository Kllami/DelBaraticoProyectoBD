package main.java.cr.una.delbaratico.model;

import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.dao.UsuarioDao;

import java.util.Calendar;
import java.util.Date;
import java.util.Observable;

public class Modelo extends Observable {
    Factura factura;
    private JdbcUtil jdbcUtil;
    private UsuarioDao usuarioDao;

    public Modelo() {
        usuarioDao = new UsuarioDao();
        Date todayDateUtil = Calendar.getInstance().getTime();
        factura = new Factura(0, 0, new java.sql.Date(todayDateUtil.getTime()), 0, "", new Caja(0, ""));
    }

    public void genericMethod() {
        //doSomething(x, y);
        setChanged();
        notifyObservers();
    }

    public boolean login(String username, String password){
        return this.usuarioDao.login(username, password);
    }

    public Usuario getUsuarioActual(){
        return this.usuarioDao.getUsuarioActual();
    }
}
