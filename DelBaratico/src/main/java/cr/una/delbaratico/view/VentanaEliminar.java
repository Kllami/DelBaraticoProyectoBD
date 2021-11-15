package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Producto;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaEliminar extends JFrame {
    private final Producto productoEliminable;
    private ServiceController servicio;


    private JComboBox cantidadJComboBox;
    private JTextField pesoJTextField;
    private JPanel panelPrincipal;
    private JLabel descripcionProducto;
    private JLabel idProducto;
    private JLabel pesoJLabel;
    private JLabel cantidadJLabel;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    public VentanaEliminar(ServiceController servicio, Producto productoEliminable) {
        this.productoEliminable = productoEliminable;
        this.servicio = servicio;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
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

    public void initComponents(){
        this.setContentPane(panelPrincipal);
        this.panelPrincipal.setPreferredSize(new Dimension(400,150));
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Modulo de Inventario");
        this.setResizable(false);
        this.habilitarCampos();
    }

    private void habilitarCampos() {
        this.descripcionProducto.setText("Descripcion: " + this.productoEliminable.getDescripcion());
        this.idProducto.setText("ID: " + this.productoEliminable.getID());

        if(!this.productoEliminable.getPlu().equals("NO APLICA")){ // Fresco
            this.cantidadJLabel.setEnabled(false);
            this.cantidadJComboBox.addItem("NO APLICA");
            this.cantidadJComboBox.setEnabled(false);

            this.pesoJLabel.setEnabled(true);
            this.pesoJTextField.setEnabled(true);
            this.pesoJTextField.setText("Introduzca la información aquí...");
        }else{ // Seco
            this.cantidadJLabel.setEnabled(true);
            this.cantidadJComboBox.setEnabled(true);
            long cantidad = Long.valueOf(this.productoEliminable.getCantidad());
            for (long i = cantidad; i > 0; i--)
                this.cantidadJComboBox.addItem(String.valueOf(i));

            this.pesoJLabel.setEnabled(false);
            this.pesoJTextField.setEnabled(false);
            this.pesoJTextField.setText("Introduzca la información aquí...");
        }
    }


    public void iniciarListeners(){
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
