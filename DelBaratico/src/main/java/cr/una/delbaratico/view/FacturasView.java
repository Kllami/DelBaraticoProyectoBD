package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FacturasView extends JFrame {
    private JTable table1;
    private JPanel panelPrincipal;
    private ServiceController servicio;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    HomeView homeView;

    public FacturasView(ServiceController servicio, HomeView homeView) {
        this.servicio = servicio;
        this.homeView = homeView;
        this.table1.setModel(new FacturaTableModel(servicio.listaFacturas()));
        this.panelPrincipal.setPreferredSize(new Dimension(900,500));
        Color color = new Color(153,255,204);
        this.panelPrincipal.setBackground(color);
        this.setContentPane(panelPrincipal);
        this.setTitle("Modulo de Facturas");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.ajustarMenus();
        this.agregarListeners();
    }

    private void agregarListeners() {
        this.addWindowListener(new WindowAdapter() {
            //windowOpened METHOD WILL BE CALLED WHEN A JFRAME IS OPENED
            public void windowOpened(WindowEvent evt) {
                homeView.setVisible(false);
            }

            //windowClosing METHOD WILL BE CALLED WHEN A JFRAME IS CLOSING
            public void windowClosing(WindowEvent evt) {
                homeView.setVisible(true);
            }
        });
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.exit(0);
            }
        });
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
