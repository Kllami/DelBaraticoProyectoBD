package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.control.Control;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaHome extends JFrame {
    private VentanaVentas ventanaVentas;
    private VentanaConsultas ventanaConsultas;
    private VentanaEdicion ventanaEdicion;
    private VentanaBorrar ventanaBorrar;
    private Control gestorPrincipal;
    private JButton ventanaVentasButton, ventanaConsultasButton, ventanaEdicionButton, ventanaBorrarButton;
    private JPanel panel;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    public VentanaHome(Control gestorPrincipal) {
        this.gestorPrincipal = gestorPrincipal;
        this.ventanaConsultas = new VentanaConsultas();
        this.ventanaEdicion = new VentanaEdicion();
        this.ventanaBorrar = new VentanaBorrar();
        this.ventanaVentasButton = new JButton("Modulo Ventas");
        this.ventanaConsultasButton = new JButton("Modulo Consultas");
        this.ventanaEdicionButton = new JButton("Modulo Edicion");
        this.ventanaBorrarButton = new JButton("Modulo Borrado");

        this.ajustarMenus();
        this.addComponentsToContainer();
        this.init();
        this.addListeners();
    }

    public void addComponentsToContainer() {
        this.panel = new JPanel(new GridLayout(3, 3));
        this.panel.add(ventanaVentasButton);
        this.panel.add(ventanaConsultasButton);
        this.panel.add(ventanaEdicionButton);
        this.panel.add(ventanaBorrarButton);
        this.add(panel, BorderLayout.CENTER);
    }

    public void init(){
        this.setTitle("DelBaratico Home");
        this.setVisible(true);
        this.setSize(400, 160);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
    }

    public void addListeners(){
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }

        });

        this.ventanaVentasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaVentas = new VentanaVentas(gestorPrincipal);
            }
        });
    }

    private void ajustarMenus() {
        menuPrincipal = new JMenuBar();
        menuPrincipal.add(menuArchivo = new JMenu("Archivo"));
        menuArchivo.add(itemSalir = new JMenuItem("Salir"));
        menuPrincipal.add(Box.createHorizontalGlue());
        menuPrincipal.add(new JLabel("Nombre de usuario: " +
                gestorPrincipal.getUsuarioActual().getNombre() +
                        "  ||  Rol: " +
                        gestorPrincipal.getUsuarioActual().getRol() +
                "   ",
                SwingConstants.RIGHT));
        setJMenuBar(menuPrincipal);
    }
}
