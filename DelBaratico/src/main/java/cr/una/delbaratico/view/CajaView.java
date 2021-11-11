package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;

public class CajaView extends JFrame {

    ServiceController servicio;
    private JButton button1;

    CajaView(ServiceController servicio){
        this.servicio = servicio;
        this.setVisible(true);
    }
}
