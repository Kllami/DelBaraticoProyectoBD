import main.java.cr.una.delbaratico.dao.AreaDAO;
import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.view.iniciarsesion.IniciarSesionController;
import main.java.cr.una.delbaratico.view.iniciarsesion.IniciarSesionModel;
import main.java.cr.una.delbaratico.view.iniciarsesion.IniciarSesionView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static IniciarSesionController iniciarSesionController;
    public static IniciarSesionView iniciarSesionView;

    public static void main(String[] args) {
        iniciarSesionController = new IniciarSesionController(new IniciarSesionModel());
        iniciarSesionView = new IniciarSesionView(iniciarSesionController);
    }
}
