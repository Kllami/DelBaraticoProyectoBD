package main.java.cr.una.delbaratico.view.iniciarsesion;

import main.java.cr.una.delbaratico.service.Service;

public class IniciarSesionController {

    private IniciarSesionModel iniciarSesionModel;
    private IniciarSesionView iniciarSesionView;

    public IniciarSesionController(IniciarSesionView iniciarSesionView) {
        this.iniciarSesionView = iniciarSesionView;
    }

    public IniciarSesionModel getIniciarSesionModel() {
        return iniciarSesionModel;
    }

    public void setIniciarSesionModel(IniciarSesionModel iniciarSesionModel) {
        this.iniciarSesionModel = iniciarSesionModel;
    }

    public IniciarSesionView getIniciarSesionView() {
        return iniciarSesionView;
    }

    public void setIniciarSesionView(IniciarSesionView iniciarSesionView) {
        this.iniciarSesionView = iniciarSesionView;
    }

    public void iniciarSesion(String DB_USER, String DB_PASSWORD) {
        Service.instance(DB_USER, DB_PASSWORD);
        if(Service.verifyConnection()) {
            iniciarSesionView.showMessage("Inicio de sesión exitóso");
            // Aquí se muestra la siguiente ventana y se esconde la anterior
            iniciarSesionView.getPanel1().setVisible(false);
        }
        else {
            iniciarSesionView.showMessage("Inicio de sesión fallido");
        }
    }

}
