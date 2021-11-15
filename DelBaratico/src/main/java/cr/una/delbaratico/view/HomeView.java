package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private JButton cajaViewButton;
    private JPanel panelPrincipal;
    private JButton facturasViewButton;
    private JButton bitacorasViewButton;
    private JButton inventarioViewButton;
    private CajaView cajaView;
    private FacturasView facturasView;
    private BitacorasView bitacorasView;
    private InventarioView inventarioView;
    private ServiceController servicio;
    private JPanel panel;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    public HomeView(ServiceController servicio) {
        this.servicio = servicio;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
    }

    public void initComponents(){
        this.panelPrincipal.setPreferredSize(new Dimension(400,100));
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("DelBaratico Home");
        this.setResizable(false);
        this.cajaViewButton.setText("Modulo de cajas");
        this.facturasViewButton.setText("Modulo de facturas");
        this.bitacorasViewButton.setText("Modulo de bitacoras");
        this.inventarioViewButton.setText("Modulo de inventario");
    }



    public void iniciarListeners(){
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.inventarioViewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                inventarioView = new InventarioView(servicio);
            }
        });

        this.cajaViewButton.addActionListener(e -> { cajaView = new CajaView(servicio); });

        this.bitacorasViewButton.addActionListener( e -> { bitacorasView = new BitacorasView(servicio); } );

        this.facturasViewButton.addActionListener(e -> { facturasView = new FacturasView(servicio); });
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}