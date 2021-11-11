package main.java.cr.una.delbaratico.service;

import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.dao.UsuarioDAO;


public class Service {

    private UsuarioDAO usuarioDao;
    private static Service service;

    private Service(String DB_USER, String DB_PASSWORD) {
        //usuarioDao = new UsuarioDAO(DB_USER, DB_PASSWORD);
    }

    public static Service instance(String DB_USER, String DB_PASSWORD) {
        if (service == null) {
            service = new Service(DB_USER, DB_PASSWORD);
        }
        return service;
    }

    //public static boolean verifyConnection() {
        //return JdbcUtil.verifyConnection();
    //}

    //public Area findAreaById(int idArea) throws Exception {
    //    return areaDAO.findById(idArea);
    //}

    //public List<Area> findAllAreas() throws Exception {
    //    return areaDAO.findAll();
   // }
}
