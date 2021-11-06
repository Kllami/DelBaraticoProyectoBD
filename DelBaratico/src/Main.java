import main.java.cr.una.delbaratico.dao.AreaDAO;
import main.java.cr.una.delbaratico.dao.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        AreaDAO areaDAO = new AreaDAO("system", "system");

        try {
            System.out.println(areaDAO.findAll());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
