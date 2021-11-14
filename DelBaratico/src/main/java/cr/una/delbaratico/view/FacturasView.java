package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;

public class FacturasView extends JFrame {


    private JTable table1;
    private JLabel facturasLabel;
    private JPanel panel1;
    private ServiceController servicio;



    public FacturasView(ServiceController servicio) {

        this.servicio = servicio;

        this.table1.setModel(new FacturaTableModel(servicio.listaFacturas()));
        this.panel1.setPreferredSize(new Dimension(900,500));
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

}
