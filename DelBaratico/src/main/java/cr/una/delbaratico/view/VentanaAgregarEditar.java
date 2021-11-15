package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import main.java.cr.una.delbaratico.model.Producto;
import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.*;
import java.awt.*;

public class VentanaAgregarEditar extends JFrame{
    private ServiceController servicio;
    private JLabel tipoProductoJLabel;
    private JComboBox tipoProdComboBox;
    private JPanel panelPrincipal;
    private JLabel eanJLabel;
    private JLabel descripcionJLabel;
    private JLabel precioJLabel;
    private JLabel cantidadJLabel;
    private JLabel areaIDJLabel;
    private JLabel pluJLabel;
    private JLabel pesoJLabel;
    private JTextField eanTextField;
    private JTextField descripcionTextField;
    private JTextField precioTextField;
    private JTextField cantidadTextField;
    private JTextField pluTextField;
    private JTextField pesoTextField;
    private JButton guardarDatosJButton;
    private JComboBox areaComboBox;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    private Fresco fresco;
    private Seco seco;
    Producto productoEditable;

    public VentanaAgregarEditar(ServiceController servicio) {
        this.servicio = servicio;
        this.productoEditable = null;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
        this.habilitarCampos();
    }

    public VentanaAgregarEditar(ServiceController servicio, Producto productoEditable){
        this.servicio = servicio;
        this.productoEditable = productoEditable;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
        this.habilitarCampos();
    }

    public void mostrarInfoProduEditable(){
        if (this.productoEditable.getCantidad().equals("NO APLICA")) { // Fresco
            this.tipoProductoJLabel.setEnabled(false);
            this.tipoProdComboBox.setSelectedItem("Fresco");
            this.tipoProdComboBox.setEnabled(false);

            this.cantidadJLabel.setEnabled(false);
            this.cantidadTextField.setText(this.productoEditable.getCantidad());
            this.cantidadTextField.setEnabled(false);

            this.areaIDJLabel.setEnabled(false);
            this.areaComboBox.setSelectedItem("Seleccione...");
            this.areaComboBox.setEnabled(false);

            this.pluJLabel.setEnabled(true);
            this.pluTextField.setText(this.productoEditable.getPlu());
            this.pluTextField.setEnabled(true);

            this.pesoJLabel.setEnabled(true);
            this.pesoTextField.setText(this.productoEditable.getPeso());
            this.pesoTextField.setEnabled(true);

            this.eanJLabel.setEnabled(true);
            this.eanTextField.setText(this.productoEditable.getEan());
            this.eanTextField.setEnabled(true);

            this.precioJLabel.setEnabled(true);
            this.precioTextField.setText(this.productoEditable.getPrecio());
            this.precioTextField.setEnabled(true);

            this.descripcionJLabel.setEnabled(true);
            this.descripcionTextField.setText(this.productoEditable.getDescripcion());
            this.descripcionTextField.setEnabled(true);

            this.guardarDatosJButton.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
        } else if (this.productoEditable.getPlu().equals("NO APLICA")) {//Seco
            this.tipoProductoJLabel.setEnabled(false);
            this.tipoProdComboBox.setSelectedItem("Seco");
            this.tipoProdComboBox.setEnabled(false);

            this.pluJLabel.setEnabled(false);
            this.pluTextField.setText(this.productoEditable.getPlu());
            this.pluTextField.setEnabled(false);

            this.pesoJLabel.setEnabled(false);
            this.pesoTextField.setText(this.productoEditable.getPeso());
            this.pesoTextField.setEnabled(false);

            this.cantidadJLabel.setEnabled(true);
            this.cantidadTextField.setText(this.productoEditable.getCantidad());
            this.cantidadTextField.setEnabled(true);

            this.areaIDJLabel.setEnabled(true);
            List<String> areasIDSNombres = this.servicio.areasIDSNombres();
            for (int i = 0; i < areasIDSNombres.size(); i++){
                if(areasIDSNombres.get(i).contains(this.productoEditable.getAreaId())) {
                    this.areaComboBox.setSelectedItem(String.valueOf(areasIDSNombres.get(i)));
                    break;
                }
            }
            this.areaComboBox.setEnabled(true);

            this.precioJLabel.setEnabled(true);
            this.precioTextField.setText(this.productoEditable.getPrecio());
            this.precioTextField.setEnabled(true);

            this.descripcionJLabel.setEnabled(true);
            this.descripcionTextField.setText(this.productoEditable.getDescripcion());
            this.descripcionTextField.setEnabled(true);

            this.eanJLabel.setEnabled(true);
            this.eanTextField.setText(this.productoEditable.getEan());
            this.eanTextField.setEnabled(true);

            this.guardarDatosJButton.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
        }
    }

    private void habilitarCampos() {
        if (this.productoEditable == null) {
            if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
                this.cantidadJLabel.setEnabled(false);
                this.cantidadTextField.setText("Introduzca la información aquí...");
                this.cantidadTextField.setEnabled(false);

                this.areaIDJLabel.setEnabled(false);
                this.areaComboBox.setSelectedItem("Seleccione...");
                this.areaComboBox.setEnabled(false);

                this.pluJLabel.setEnabled(true);
                this.pluTextField.setEnabled(true);

                this.pesoTextField.setEnabled(true);
                this.pesoJLabel.setEnabled(true);

                this.eanJLabel.setEnabled(true);
                this.eanTextField.setEnabled(true);

                this.precioJLabel.setEnabled(true);
                this.precioTextField.setEnabled(true);

                this.descripcionJLabel.setEnabled(true);
                this.descripcionTextField.setEnabled(true);

                this.guardarDatosJButton.setEnabled(true);
                this.panelPrincipal.validate();
                this.panelPrincipal.repaint();
            } else if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Seco")) {
                this.pluJLabel.setEnabled(false);
                this.pluTextField.setText("Introduzca la información aquí...");
                this.pluTextField.setEnabled(false);

                this.pesoJLabel.setEnabled(false);
                this.pesoTextField.setText("Introduzca la información aquí...");
                this.pesoTextField.setEnabled(false);

                this.cantidadJLabel.setEnabled(true);
                this.cantidadTextField.setEnabled(true);

                this.areaIDJLabel.setEnabled(true);
                this.areaComboBox.setSelectedItem("Seleccione...");
                this.areaComboBox.setEnabled(true);

                this.precioJLabel.setEnabled(true);
                this.precioTextField.setEnabled(true);

                this.descripcionJLabel.setEnabled(true);
                this.descripcionTextField.setEnabled(true);

                this.eanJLabel.setEnabled(true);
                this.eanTextField.setEnabled(true);

                this.guardarDatosJButton.setEnabled(true);
                this.panelPrincipal.validate();
                this.panelPrincipal.repaint();
            } else {
                this.pluJLabel.setEnabled(false);
                this.eanJLabel.setEnabled(false);
                this.descripcionJLabel.setEnabled(false);
                this.precioJLabel.setEnabled(false);
                this.cantidadJLabel.setEnabled(false);
                this.areaIDJLabel.setEnabled(false);
                this.pluJLabel.setEnabled(false);
                this.pesoJLabel.setEnabled(false);

                this.eanTextField.setText("Introduzca la información aquí...");
                this.eanTextField.setEnabled(false);
                this.descripcionTextField.setText("Introduzca la información aquí...");
                this.descripcionTextField.setEnabled(false);
                this.precioTextField.setText("Introduzca la información aquí...");
                this.precioTextField.setEnabled(false);
                this.cantidadTextField.setText("Introduzca la información aquí...");
                this.cantidadTextField.setEnabled(false);
                this.pluTextField.setText("Introduzca la información aquí...");
                this.pluTextField.setEnabled(false);
                this.pesoTextField.setText("Introduzca la información aquí...");
                this.pesoTextField.setEnabled(false);
                this.guardarDatosJButton.setEnabled(false);
                this.areaComboBox.setSelectedItem("Seleccione...");
                this.areaComboBox.setEnabled(false);
            }
        }else{
            this.mostrarInfoProduEditable();
        }
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
        this.panelPrincipal.setPreferredSize(new Dimension(400,300));
        this.setContentPane(panelPrincipal);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Modulo de Inventario");
        this.tipoProdComboBox.addItem("Seleccione...");
        this.tipoProdComboBox.addItem("Fresco");
        this.tipoProdComboBox.addItem("Seco");
        this.tipoProdComboBox.setFocusable(true);
        this.tipoProdComboBox.setRequestFocusEnabled(true);
        this.tipoProdComboBox.requestFocus();
        List<String> areasIDSNombres = this.servicio.areasIDSNombres();
        this.areaComboBox.addItem("Seleccione...");
        for (int i = 0; i < areasIDSNombres.size(); i++)
            this.areaComboBox.addItem(String.valueOf(areasIDSNombres.get(i)));
    }

    public void iniciarListeners() {
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.tipoProdComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                habilitarCampos();
            }
        });

        this.eanTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(eanTextField.getText().equals("Introduzca la información aquí..."))
                        eanTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(eanTextField.getText().equals(""))
                        eanTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.descripcionTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(descripcionTextField.getText().equals("Introduzca la información aquí..."))
                        descripcionTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(descripcionTextField.getText().equals(""))
                        descripcionTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.precioTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(precioTextField.getText().equals("Introduzca la información aquí..."))
                        precioTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(precioTextField.getText().equals(""))
                        precioTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.cantidadTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(cantidadTextField.getText().equals("Introduzca la información aquí..."))
                        cantidadTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(cantidadTextField.getText().equals(""))
                        cantidadTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.pluTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pluTextField.getText().equals("Introduzca la información aquí..."))
                        pluTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pluTextField.getText().equals(""))
                        pluTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.pesoTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoTextField.getText().equals("Introduzca la información aquí..."))
                        pesoTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoTextField.getText().equals(""))
                        pesoTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.pesoTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoTextField.getText().equals("Introduzca la información aquí..."))
                        pesoTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(pesoTextField.getText().equals(""))
                        pesoTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.guardarDatosJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ean = eanTextField.getText().trim();
                String descripcion = descripcionTextField.getText().trim();
                String precio = precioTextField.getText().trim();
                String cantidad = cantidadTextField.getText().trim();
                String areaID = String.valueOf(areaComboBox.getSelectedItem()).trim();
                if(areaID.contains("1"))
                    areaID = "1";
                if(areaID.contains("2"))
                    areaID = "2";
                if(areaID.contains("3"))
                    areaID = "3";
                String plu = pluTextField.getText().trim();
                String peso = pesoTextField.getText().trim();
                String mensajeIntroduzca = "Introduzca la información aquí...";

                if(productoEditable == null)
                    agregarProducto(ean, descripcion, precio, cantidad, areaID, plu, peso, mensajeIntroduzca);
                else
                    editarProducto(ean, descripcion, precio, cantidad, areaID, plu, peso, mensajeIntroduzca);
            }
        });
    }

    public void agregarProducto(String ean, String descripcion, String precio, String cantidad, String areaID, String plu, String peso, String mensajeIntroduzca){
        if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
            if (verificarValoresCorrectosFresco(ean, descripcion, precio, plu, peso, mensajeIntroduzca)){
                if (servicio.findFrescoXEAN(Long.valueOf(ean)) != null || servicio.findFrescoXPLU(Long.valueOf(plu)) != null ||
                        servicio.findSecoXEAN(Long.valueOf(ean)) != null)
                    JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                else {
                    fresco = new Fresco(-1, Long.valueOf(plu), Double.valueOf(peso), Long.valueOf(ean),
                            descripcion, Double.valueOf(precio));
                    if (servicio.addFresco(fresco) > 0) { // Add Fresco
                        tipoProdComboBox.setSelectedItem("Seleccione...");
                        JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al agregar el producto");
                }
            }
        } else if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Seco")){
            if (verificarValoresCorrectosSeco(ean, descripcion, precio, cantidad, areaID, mensajeIntroduzca)){
                if (servicio.findFrescoXEAN(Long.valueOf(ean)) != null ||servicio.findSecoXEAN(Long.valueOf(ean)) != null)
                    JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                else {
                    seco = new Seco(-1, Long.valueOf(ean), descripcion, Double.valueOf(precio),
                            Long.valueOf(cantidad), Long.valueOf(areaID));
                    if (servicio.addSeco(seco) > 0) { // Add Seco
                        tipoProdComboBox.setSelectedItem("Seleccione...");
                        JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al agregar el producto");
                }
            }
        }else{
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar el tipo del producto");
        }
    }

    public boolean editarCodigosCorrectosFresco(Producto producto, String ean, String plu){
        boolean result = true;
        if (servicio.findFrescoXEAN(Long.valueOf(ean)) != null && !Long.valueOf(producto.getID()).equals(servicio.findFrescoXEAN(Long.valueOf(ean)).getIdFresco()))
            result = false;
        else if (servicio.findFrescoXPLU(Long.valueOf(plu)) != null && !Long.valueOf(producto.getID()).equals(servicio.findFrescoXPLU(Long.valueOf(plu)).getIdFresco()))
            result = false;
        else if(servicio.findSecoXEAN(Long.valueOf(ean)) != null && !Long.valueOf(producto.getID()).equals(servicio.findSecoXEAN(Long.valueOf(ean)).getIdSeco()))
            result = false;
        return result;
    }

    public boolean editarCodigosCorrectosSeco(Producto producto, String ean, String plu){
        boolean result = true;
        if(servicio.findFrescoXEAN(Long.valueOf(ean)) != null && !Long.valueOf(producto.getID()).equals(servicio.findFrescoXEAN(Long.valueOf(ean)).getIdFresco()))
                result = false;
        else if (servicio.findSecoXEAN(Long.valueOf(ean)) != null && !Long.valueOf(producto.getID()).equals(servicio.findSecoXEAN(Long.valueOf(ean)).getIdSeco()))
            result = false;
        return result;
    }

    public void editarProducto(String ean, String descripcion, String precio, String cantidad, String areaID, String plu, String peso, String mensajeIntroduzca){
        if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
            if (verificarValoresCorrectosFresco(ean, descripcion, precio, plu, peso, mensajeIntroduzca)){
                if (!editarCodigosCorrectosFresco(this.productoEditable, ean, plu))
                    JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                else {
                    fresco = new Fresco(Long.valueOf(this.productoEditable.getID()), Long.valueOf(plu), Double.valueOf(peso), Long.valueOf(ean),
                            descripcion, Double.valueOf(precio));
                    if(servicio.updateFresco(fresco) > 0) {
                        JOptionPane.showMessageDialog(panelPrincipal, "Producto editado");
                        dispose();
                    }else
                        JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al editar el producto");
                }
            }
        } else if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Seco")){
            if (verificarValoresCorrectosSeco(ean, descripcion, precio, cantidad, areaID, mensajeIntroduzca)){
                if (!editarCodigosCorrectosSeco(this.productoEditable, ean, plu))
                    JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                else {
                    seco = new Seco(Long.valueOf(this.productoEditable.getID()), Long.valueOf(ean), descripcion, Double.valueOf(precio),
                            Long.valueOf(cantidad), Long.valueOf(areaID));

                    if(servicio.updateSeco(seco) > 0) {
                        JOptionPane.showMessageDialog(panelPrincipal, "Producto editado");
                        dispose();
                    } else
                        JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al editar el producto");
                }
            }
        }else{
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar el tipo del producto");
        }
    }

    public boolean verificarValoresCorrectosFresco(String ean, String descripcion, String precio, String plu, String peso, String mensajeIntroduzca){
        boolean result = true;

        if (ean.equals(mensajeIntroduzca) || descripcion.equals(mensajeIntroduzca) ||
                precio.equals(mensajeIntroduzca) || plu.equals(mensajeIntroduzca) ||
                peso.equals(mensajeIntroduzca)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
        }else if (ean.equals("") || descripcion.equals("") || precio.equals("") ||
                plu.equals("") || peso.equals("")) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
        }else if (!servicio.esNumero(ean)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo EAN contiene letras");
        }else if (servicio.esNumero(descripcion)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Descripcion contiene numeros");
        }else if (!servicio.esNumero2(precio)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Precio contiene letras");
        }else if (!servicio.esNumero(plu)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo PLU contiene letras");
        }else if (!servicio.esNumero2(peso)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Peso contiene letras");
        }
        return result;
    }

    public boolean verificarValoresCorrectosSeco(String ean, String descripcion, String precio, String cantidad, String areaID, String mensajeIntroduzca){
        boolean result = true;

        if (ean.equals(mensajeIntroduzca) || descripcion.equals(mensajeIntroduzca) ||
                precio.equals(mensajeIntroduzca) || cantidad.equals(mensajeIntroduzca) ||
                areaID.equals("Seleccione...")) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
        }else if (ean.equals("") || descripcion.equals("") || precio.equals("") || cantidad.equals("") ||
                areaID.equals("")) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
        }else if (!servicio.esNumero(ean)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo EAN contiene letras");
        }else if (servicio.esNumero(descripcion)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Descripcion contiene numeros");
        }else if (!servicio.esNumero2(precio)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Precio contiene letras");
        }else if (!servicio.esNumero(cantidad)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Cantidad contiene letras");
        }else if (!servicio.esNumero(areaID)) {
            result = false;
            JOptionPane.showMessageDialog(panelPrincipal, "El campo Area ID contiene letras");
        }
        return result;
    }
}
