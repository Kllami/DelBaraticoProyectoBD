import main.java.cr.una.delbaratico.service.ServiceController;
import main.java.cr.una.delbaratico.model.Model;
import main.java.cr.una.delbaratico.view.LoginView;

public class Main {
    public static LoginView iniciarSesionView;

    public static void main(String[] args) {
        try {
            iniciarSesionView = new LoginView(new ServiceController(new Model()));
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
