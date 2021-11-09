import main.java.cr.una.delbaratico.dao.AreaDAO;
import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.view.Login;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class DelBaratico {
    public DelBaratico() {
    }

    public static void mostrarInterfaz() {
        Login login = new Login();
    }

    public static void main(String[] args) {
        try {
            //AreaDAO areaDAO = new AreaDAO("system", "system");
            //System.out.println(areaDAO.findAll());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            mostrarInterfaz();
        });

    }

}
