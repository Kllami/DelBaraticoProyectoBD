package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import main.java.cr.una.delbaratico.model.*;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import javax.swing.*;
import java.awt.*;

public class RegistroVentasView extends JFrame {
    double precio = 0;
    ServiceController servicio;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JButton registrarVentaButton;
    private JCheckBox secoCheckBox;
    private JTextField textField3;
    private JButton agregarALaListaButton;
    private JButton salirButton;
    private JButton consultarPrecioButton;
    public JTextField pesoTextField;
    private JLabel pesoJLabel;
    private JPanel panel2;
    private JButton consultarButton;
    private Caja caja;
    private List preciosList;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    HomeView homeView;

    RegistroVentasView(ServiceController servicio, HomeView homeView){
        this.servicio = servicio;
        this.homeView = homeView;
        //this.panel1.setPreferredSize(new Dimension(900,500));
        Color color = new Color(255,255,153);
        this.panel2.setVisible(true);
        this.panel2.setBackground(color);
        this.setContentPane(panel1);
        this.pack();
        this.setVisible(true);
        this.setTitle("Modulo de Registro de Ventas");
        this.setLocationRelativeTo(null);
        preciosList = new ArrayList<>();
        rellenarComboBox();
        this.ajustarMenus();
        this.agregarListeners();
    }

    public void ajustarMenus(){
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

    public void agregarListeners(){
        secoCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(secoCheckBox.isSelected())
                    pesoTextField.setEnabled(false);
                else
                    pesoTextField.setEnabled(true);
                panel1.validate();
                panel1.repaint();
            }
        });

        agregarALaListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secoCheckBox.isSelected() && !textField3.getText().equals("") && servicio.esNumero(textField3.getText())){
                    //Deshabilitar campo de peso
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Seco seco;
                    try {

                        seco = servicio.findSecoById(sec);
                        if(seco.getCantidad() > 0){
                            preciosList.add(seco.getPrecio());
                            servicio.updateInventarioSeco(seco.getCantidad() - 1, seco.getIdSeco());
                            secoCheckBox.setSelected(false);
                            textField3.setText("");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(panel1 , "No hay mas productos de este tipo en inventario");
                        }
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if(!secoCheckBox.isSelected() && !textField3.getText().equals("") && servicio.esNumero(textField3.getText())){
                    //Deshabilitar campo de peso
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Fresco fresco;
                    try {
                        fresco = servicio.findFrescoById(sec);
                        double pesoFresco = 0;
                        if(servicio.esNumero2(pesoTextField.getText())) {
                            pesoFresco = Double.parseDouble(pesoTextField.getText());
                            if(fresco.getPeso() > 0) {
                                preciosList.add(fresco.getPrecio());
                                servicio.updateInventarioFresco(fresco.getPeso() - pesoFresco, fresco.getIdFresco());
                                secoCheckBox.setSelected(false);
                                textField3.setText("");
                            }
                            else
                                JOptionPane.showMessageDialog(panel1, "No hay mas productos de este tipo en inventario");
                        }
                        else
                            JOptionPane.showMessageDialog(panel1, "El campo para peso solo debe contener numeros");
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if (textField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Debe indicar el id del producto por agregar");
                }
                else if(!servicio.esNumero(textField3.getText())) {
                    JOptionPane.showMessageDialog(panel1, "El id del producto solo puede contener numeros");
                }
            }
        });


        registrarVentaButton.addActionListener( e -> {
            registrarVenta();
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homeView.setVisible(true);
            }
        });

        consultarPrecioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secoCheckBox.isSelected() && !textField3.getText().equals("") && servicio.esNumero(textField3.getText())){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Seco seco;
                    try {
                        seco = servicio.findSecoById(sec);
                        JOptionPane.showMessageDialog(panel1, seco.getPrecio());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if(!secoCheckBox.isSelected() && !textField3.getText().equals("")){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Fresco fresco;
                    try {
                        fresco = servicio.findFrescoById(sec);
                        JOptionPane.showMessageDialog(panel1, fresco.getPrecio());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if (textField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Debe indicar el id del producto por agregar");
                }
                else if(!servicio.esNumero(textField3.getText())) {
                    JOptionPane.showMessageDialog(panel1, "El id del producto solo puede contener numeros");
                }
            }
        });

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
    }

    public void rellenarComboBox () {
        List<Caja> cajasList = servicio.listaCajas();

        for(int index = 0; index < cajasList.size(); index++){
            if(cajasList.get(index).getUsuario().toString().equals(this.servicio.getUsuarioActual().getNombre())){
                System.out.println(cajasList.get(index).getUsuario().toString());
                this.comboBox1.addItem(cajasList.get(index).getIdCaja());
            }

            System.out.println(cajasList.toString());
        }
    }

    public void registrarVenta(){

        for (int index = 0; index < preciosList.size(); index++) {
            precio = precio + (double) preciosList.get(index);
        }
        java.util.Date d = new java.util.Date();
        Date date = new Date(d.getTime());
        String c = this.comboBox1.getSelectedItem().toString();
        assert c != null;
        int caja = Integer.parseInt(c);
        System.out.println(precio);
        Factura factura = new Factura(0, 0, date, precio, this.servicio.getUsuarioActual().getNombre(), caja);
        if(precio > 0){
            servicio.insertarFactura(factura);
        }
        else{
            JOptionPane.showMessageDialog(panel1, "Debe ingresar al menos un artículo");
        }
    }
}
