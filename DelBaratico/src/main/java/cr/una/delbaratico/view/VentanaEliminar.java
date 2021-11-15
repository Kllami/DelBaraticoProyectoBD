package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Producto;
import main.java.cr.una.delbaratico.model.Seco;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

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
    private JButton eliminarButton;
    private JButton atrasButton;
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
        
        this.atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        this.eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!productoEliminable.getPlu().equals("NO APLICA")) { // Fresco
                    eliminarFresco();
                }else { // Seco
                    eliminarSeco();
                }
                
            }
        });

        this.pesoJTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoJTextField.getText().equals("Introduzca la información aquí..."))
                        pesoJTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoJTextField.getText().equals(""))
                        pesoJTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void eliminarSeco() {
        if(String.valueOf(this.cantidadJComboBox.getSelectedItem()).equals(String.valueOf(this.productoEliminable.getCantidad()))){
            long idSeco = Long.valueOf(this.productoEliminable.getID());
            long ean  = Long.valueOf(this.productoEliminable.getEan());
            String descripcion = this.productoEliminable.getDescripcion();
            double precio = Double.valueOf(this.productoEliminable.getPrecio());
            long cantidad  = Long.valueOf(this.productoEliminable.getCantidad());
            long areaId = Long.valueOf(this.productoEliminable.getAreaId());
            Seco seco = new Seco(idSeco, ean, descripcion, precio, cantidad, areaId);
            if(this.servicio.eliminarSeco(seco) == 0){
                JOptionPane.showMessageDialog(this.panelPrincipal, "Hubo un problema al eliminar el producto");
            }else{
                JOptionPane.showMessageDialog(this.panelPrincipal, "El producto fue eliminado exitosamente");
                dispose();
            }
        }else{ // Si solo es necesario un update
            long cantidad = Long.valueOf(this.cantidadJComboBox.getSelectedItem().toString());
            long id = Long.valueOf(this.productoEliminable.getID());
            int resultado = this.servicio.updateInventarioSeco(Long.valueOf(this.productoEliminable.getCantidad()) - cantidad, id);
            if(resultado > 0) {
                JOptionPane.showMessageDialog(panelPrincipal, "Cantidad eliminada");
                dispose();
            }
        }
    }

    private void eliminarFresco() {
        String peso = this.pesoJTextField.getText();
        if (!servicio.esNumero2(peso))
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Peso contiene letras");
        else if(Double.valueOf(peso) > Double.valueOf(this.productoEliminable.getPeso()))
            JOptionPane.showMessageDialog(panelPrincipal, "Imposible, el peso introducido supera las existencias");
        else if(Double.valueOf(peso).equals(Double.valueOf(this.productoEliminable.getPeso()))){
            if(this.servicio.eliminarFresco(Long.valueOf(this.productoEliminable.getID())) == 0)
                JOptionPane.showMessageDialog(this.panelPrincipal, "Hubo un problema al eliminar el producto");
            else {
                JOptionPane.showMessageDialog(this.panelPrincipal, "El producto fue eliminado exitosamente");
                dispose();
            }
        }else{
            double pesoRestable = Double.parseDouble(peso);
            double pesoActual = Double.valueOf(this.productoEliminable.getPeso());
            double nuevoPeso = pesoActual - pesoRestable;
            int resultado = this.servicio.updateInventarioFresco(nuevoPeso, Long.valueOf(this.productoEliminable.getID()));
            if(resultado > 0) {
                JOptionPane.showMessageDialog(panelPrincipal, "Peso eliminado de existencias");
                dispose();
            }
        }
    }
}
