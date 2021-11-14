package main.java.cr.una.delbaratico.model;

import main.java.cr.una.delbaratico.dao.*;

import java.security.spec.ECField;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Model extends Observable {

    Factura factura;
    private static JdbcUtil jdbcUtil;
    private final UsuarioDAO usuarioDao;
    private SecoDAO secoDAO;
    private FrescoDAO frescoDAO;
    private CajaDAO cajaDAO;
    private FacturaDAO facturaDAO;
    private AreaDAO areaDAO;
    private AuditoriaDAO auditoriaDAO;
    private DetalleFrescoDAO detalleFrescoDAO;
    private DetalleSecoDAO detalleSecoDAO;

    public Model() {
        usuarioDao = new UsuarioDAO();
        Date todayDateUtil = Calendar.getInstance().getTime();
        factura = new Factura(0, 0, new java.sql.Date(todayDateUtil.getTime()), 0, "", 0);
    }

    public void genericMethod() {
        //doSomething(x, y);
        setChanged();
        notifyObservers();
    }

    //Las DAOS deben ser inicializadas solamente en el instante en el que el login es exitoso
    public boolean login(String username, String password) {
        boolean result = this.usuarioDao.login(username, password);
        if(result){
            this.jdbcUtil = this.usuarioDao.returnJdbcUtil();
            this.secoDAO = new SecoDAO(this.jdbcUtil);
            this.frescoDAO = new FrescoDAO(this.jdbcUtil);
            this.cajaDAO = new CajaDAO(this.jdbcUtil);
            this.facturaDAO = new FacturaDAO(this.jdbcUtil);
            this.areaDAO = new AreaDAO(this.jdbcUtil);
            this.auditoriaDAO = new AuditoriaDAO(this.jdbcUtil);
            this.detalleFrescoDAO = new DetalleFrescoDAO(this.jdbcUtil);
            this.detalleSecoDAO = new DetalleSecoDAO(this.jdbcUtil);
        }
        return result;
    }

    public void logot() {
        this.usuarioDao.cerrarConexion();
    }

    public Usuario getUsuarioActual() {
        return this.usuarioDao.getUsuarioActual();
    }

    public Seco findSecoById(int idSeco) throws SQLException {
        return this.secoDAO.findById(idSeco);
    }

    public void updateInventarioSeco(int cantidad, int idSeco){
        this.secoDAO.updateInventario(cantidad, idSeco);
    }

    public Fresco findFrescoById(int idFresco) throws SQLException {
        return this.frescoDAO.findById(idFresco);
    }

    public void updateInventarioFresco(double peso, int idFresco) {
        this.frescoDAO.updateInventario(peso, idFresco);
    }

    public List<Caja> listaCajas() {
        try {
            return this.cajaDAO.findAll();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void insertarFactura(Factura factura){
        this.facturaDAO.insertarFactura(factura);
    }

    public List<Seco> buscarSecosXDescripcion(String descripcion) {
        try{
            return this.secoDAO.findSimilarPercentDesc(descripcion);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Fresco> buscarFrescosXDescripcion(String descripcion) {
        try{
            return this.frescoDAO.findSimilarPercentDesc(descripcion);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}
