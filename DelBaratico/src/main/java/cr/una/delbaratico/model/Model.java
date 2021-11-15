package main.java.cr.una.delbaratico.model;

import main.java.cr.una.delbaratico.dao.*;

import java.security.spec.ECField;
import java.sql.ResultSet;
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

    public Seco findSecoById(long idSeco) {
        try{
            return this.secoDAO.findById(idSeco);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int updateInventarioSeco(long cantidad, long idSeco){
        return this.secoDAO.updateInventario(cantidad, idSeco);
    }

    public Fresco findFrescoById(double idFresco) {
        try{
            return this.frescoDAO.findById(idFresco);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int updateInventarioFresco(double peso, long idFresco) {
        return this.frescoDAO.updateInventario(peso, idFresco);
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

    public List<Seco> buscarSecosXEAN(long EAN) {
        try{
            return this.secoDAO.findSimilarPercentEAN(EAN);
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

    public List<Fresco> buscarFrescosXEAN(long EAN) {
        try{
            return this.frescoDAO.findSimilarPercentEAN(EAN);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Fresco> buscarFrescosXPLU(long PLU) {
        try{
            return this.frescoDAO.findSimilarPercentPLU(PLU);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int addFresco(Fresco fresco) {
        return this.frescoDAO.add(fresco);
    }

    public int addSeco(Seco seco) {
        return this.secoDAO.add(seco);
    }

    public Seco findSecoXEAN(long ean){
        try{
            return this.secoDAO.findByEAN(ean);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Fresco findFrescoXEAN(long ean){
        try{
            return this.frescoDAO.findByEAN(ean);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Fresco findFrescoXPLU(long plu) {
        try{
            return this.frescoDAO.findByPLU(plu);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<String> areasIDSNombres() {
        try {
            return this.areaDAO.areasIDSNombres();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public List<Factura> listaFacturas() {
        try {
            return this.facturaDAO.findAll();
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return null;
    }

    public int updateFresco(Fresco fresco) {
        return this.frescoDAO.updateFresco(fresco);
    }

    public int updateSeco(Seco seco) {
        return this.secoDAO.updateSeco(seco);
    }

    public int eliminarSeco(Seco seco) {
        return this.secoDAO.eliminarSeco(seco);
    }

    public int eliminarFresco(Long id) {
        return this.frescoDAO.eliminarFresco(id);
    }
}
