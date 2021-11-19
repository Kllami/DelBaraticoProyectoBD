package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;


public class HomeView extends JFrame {
    private JButton registroVentasViewButton;
    private JPanel panelPrincipal;
    private JButton facturasViewButton;
    private JButton bitacorasViewButton;
    private JButton inventarioViewButton;
    private RegistroVentasView registroVentasView;
    private FacturasView facturasView;
    private BitacorasView bitacorasView;
    private InventarioView inventarioView;
    private ServiceController servicio;
    private JPanel panel;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    private static HomeView homeView;

    public HomeView(ServiceController servicio) {
        this.servicio = servicio;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
        this.homeView = this;
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

        Color color = new Color(255,255,153);
        this.registroVentasViewButton.setBackground(color);
        this.registroVentasViewButton.setText("Modulo de Registro de Ventas");
        this.registroVentasViewButton.setOpaque(true);

        color = new Color(153,255,204);
        this.facturasViewButton.setBackground(color);
        this.facturasViewButton.setText("Modulo de Facturas");
        this.facturasViewButton.setOpaque(true);

        color = new Color(255,204, 153);
        this.bitacorasViewButton.setBackground(color);
        this.bitacorasViewButton.setText("Modulo de Bitacoras");
        this.bitacorasViewButton.setOpaque(true);

        color = new Color(153,153, 255);
        this.inventarioViewButton.setBackground(color);
        this.inventarioViewButton.setText("Modulo de Inventario");
        this.inventarioViewButton.setOpaque(true);

        this.limitarAplicarPrivilegios();
    }

    public void limitarAplicarPrivilegios(){
        if(this.servicio.getUsuarioActual().getRol().equals("CAJERO")) {
            this.bitacorasViewButton.setEnabled(false);
            this.bitacorasViewButton.setVisible(false);

            this.facturasViewButton.setEnabled(false);
            this.facturasViewButton.setVisible(false);
        } else if(this.servicio.getUsuarioActual().getRol().contains("GERENTE")) {
            this.bitacorasViewButton.setEnabled(false);
            this.bitacorasViewButton.setVisible(false);

            this.registroVentasViewButton.setEnabled(false);
            this.registroVentasViewButton.setVisible(false);
        }
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
                inventarioView = new InventarioView(servicio, homeView);
            }
        });

        this.registroVentasViewButton.addActionListener(e -> {
            registroVentasView = new RegistroVentasView(servicio, homeView);
        });

        this.bitacorasViewButton.addActionListener( e -> {
            bitacorasView = new BitacorasView(servicio, homeView);
        } );

        this.facturasViewButton.addActionListener(e -> {
            facturasView = new FacturasView(servicio, homeView);
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}