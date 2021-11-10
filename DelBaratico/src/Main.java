import main.java.cr.una.delbaratico.dao.AreaDAO;
import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.view.iniciarsesion.IniciarSesionController;
import main.java.cr.una.delbaratico.view.iniciarsesion.IniciarSesionView;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static IniciarSesionController iniciarSesionController;
    public static IniciarSesionView iniciarSesionView;

    public static void main(String[] args) {

        iniciarSesionController = new IniciarSesionController(null);
        iniciarSesionView = new IniciarSesionView(iniciarSesionController);
        
        iniciarSesionController.setIniciarSesionView(iniciarSesionView);
        JFrame jFrame = new JFrame();
        jFrame.setContentPane(iniciarSesionView.getPanel1());
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);

    }

}
