package main.java.cr.una.delbaratico.view.iniciarsesion;

public class IniciarSesionController {

    private IniciarSesionModel iniciarSesionModel;

    public IniciarSesionController(IniciarSesionModel iniciarSesionModel) {
        this.iniciarSesionModel = iniciarSesionModel;
    }

    public IniciarSesionModel getIniciarSesionModel() {
        return iniciarSesionModel;
    }

    public void setIniciarSesionModel(IniciarSesionModel iniciarSesionModel) {
        this.iniciarSesionModel = iniciarSesionModel;
    }

    public boolean iniciarSesion(String DB_USER, String DB_PASSWORD) {
        return this.iniciarSesionModel.login(DB_USER, DB_PASSWORD);
    }

}
