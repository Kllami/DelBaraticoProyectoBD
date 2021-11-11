package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeView extends JFrame {
    private JButton cajaViewButton;
    private JPanel panel1;
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
        this.panel1.setPreferredSize(new Dimension(300,100));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //setMinimumSize(new Dimension(300,100));
        this.setTitle("DelBaratico Home");

        this.cajaViewButton = new JButton("Modulo Caja");
        this.facturasViewButton = new JButton("Modulo Facturas");
        this.bitacorasViewButton = new JButton("Modulo Bitacora");
        this.inventarioViewButton = new JButton("Modulo Inventario");
        this.ajustarMenus();

        this.iniciarListeners();//Siempre se llama de ultimo
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