package main.java.cr.una.delbaratico.service;

import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.model.*;

import java.sql.SQLException;
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

    public boolean esNumero(String str){
        boolean isNumeric = true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                isNumeric = false;
            }
        }
        return isNumeric;
    }

    public Seco findSecoById(int idSeco) throws SQLException {
        return this.modeloPrincipal.findSecoById(idSeco);
    }

    public void updateInventarioFresco(double cantidad, int idSeco) {
        this.modeloPrincipal.updateInventarioFresco(cantidad, idSeco);
    }

    public void updateInventarioSeco(int cantidad, int idSeco){
        this.modeloPrincipal.updateInventarioSeco(cantidad, idSeco);
    }

    public Fresco findFrescoById(int idFresco) throws SQLException {
        return this.modeloPrincipal.findFrescoById(idFresco);
    }

    public List<Caja> listaCajas() {
        return this.modeloPrincipal.listaCajas();
    }

    public void insertarFactura(Factura factura){
        this.modeloPrincipal.insertarFactura(factura);
    }
}
