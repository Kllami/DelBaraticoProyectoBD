package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import javax.swing.*;
import java.awt.*;

public class FacturasView extends JFrame {
    private JTable table1;
    private JLabel facturasLabel;
    private JPanel panel1;
    private ServiceController servicio;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;


    public FacturasView(ServiceController servicio) {
        this.servicio = servicio;
        this.table1.setModel(new FacturaTableModel(servicio.listaFacturas()));
        this.panel1.setPreferredSize(new Dimension(900,500));
        this.setContentPane(panel1);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.ajustarMenus();
    }

    private void ajustarMenus() {
        this.menuPrincipal = new JMenuBar();
        this.menuPrincipal.add(this.menuArchivo = new JMenu("Archivo"));
        this.menuArchivo.add(this.itemSalir = new JMenuItem("Salir"));
        this.menuPrincipal.add(Box.createHorizontalGlue());
        this.menuPrincipal.add(new JLabel("Nombre de usuario: " +
                this.servicio.getUsuarioActual().getNombre() +
                "  ||  Rol: " +
                this.servicio.getUsuarioActual().getRol() +
                "   ",
                SwingConstants.RIGHT));
        this.setJMenuBar(this.menuPrincipal);
    }
}
