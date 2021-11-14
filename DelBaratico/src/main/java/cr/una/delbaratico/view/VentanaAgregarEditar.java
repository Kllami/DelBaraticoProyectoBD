package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.List;

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

    public VentanaAgregarEditar(ServiceController servicio) {
        this.servicio = servicio;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
        this.habilitarCampos();
    }

    private void habilitarCampos() {
        String mensajeIntroduzca = "Introduzca la información aquí...";
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
            this.eanJLabel.setEnabled(true);
            this.precioTextField.setEnabled(true);
            this.precioJLabel.setEnabled(true);
            this.descripcionTextField.setEnabled(true);
            this.descripcionJLabel.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
            this.guardarDatosJButton.setEnabled(true);
        }else if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Seco")){
            this.pluJLabel.setEnabled(false);
            this.pluTextField.setText("Introduzca la información aquí...");
            this.pluTextField.setEnabled(false);
            this.pesoJLabel.setEnabled(false);
            this.pesoTextField.setText("Introduzca la información aquí...");
            this.pesoTextField.setEnabled(false);
            this.cantidadJLabel.setEnabled(true);
            this.cantidadTextField.setEnabled(true);
            this.areaIDJLabel.setEnabled(true);
            this.areaComboBox.setEnabled(true);
            this.precioJLabel.setEnabled(true);
            this.precioTextField.setEnabled(true);
            this.descripcionJLabel.setEnabled(true);
            this.descripcionTextField.setEnabled(true);
            this.eanJLabel.setEnabled(true);
            this.eanTextField.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
            this.guardarDatosJButton.setEnabled(true);
        }else{
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
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        List<Integer> areasIDS = this.servicio.areasIDS();
        this.areaComboBox.addItem("Seleccione...");
        for (int i = 0; i < areasIDS.size(); i++)
            this.areaComboBox.addItem(String.valueOf(areasIDS.get(i)));
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
                String plu = pluTextField.getText().trim();
                String peso = pesoTextField.getText().trim();

                String mensajeIntroduzca = "Introduzca la información aquí...";
                if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
                    if (ean.equals(mensajeIntroduzca) || descripcion.equals(mensajeIntroduzca) ||
                            precio.equals(mensajeIntroduzca) || plu.equals(mensajeIntroduzca) ||
                            peso.equals(mensajeIntroduzca))
                        JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                    else if (ean.equals("") || descripcion.equals("") || precio.equals("") ||
                            plu.equals("") || peso.equals(""))
                        JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                    else if (!servicio.esNumero(ean))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo EAN contiene letras");
                    else if (servicio.esNumero(descripcion))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Descripcion contiene numeros");
                    else if (!servicio.esNumero(precio))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Precio contiene letras");
                    else if (!servicio.esNumero(plu))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo PLU contiene letras");
                    else if (!servicio.esNumero(peso))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Peso contiene letras");
                    else {
                        //
                        if (servicio.findFrescoXEAN(Long.valueOf(ean)) != null || servicio.findFrescoXPLU(Integer.valueOf(plu)) != null ||
                                servicio.findSecoXEAN(Long.valueOf(ean)) != null)
                            JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                        else {
                            fresco = new Fresco(-1, Integer.valueOf(plu), Double.valueOf(peso), Long.valueOf(ean),
                                    descripcion, Double.valueOf(precio));
                            if (servicio.addFresco(fresco) > 0)
                                JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                            else
                                JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al agregar el producto");
                        }
                    }
                } else if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Seco")){
                    if (ean.equals(mensajeIntroduzca) || descripcion.equals(mensajeIntroduzca) ||
                            precio.equals(mensajeIntroduzca) || cantidad.equals(mensajeIntroduzca) ||
                            areaID.equals("Seleccione..."))
                        JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                    else if (ean.equals("") || descripcion.equals("") || precio.equals("") || cantidad.equals("") ||
                            areaID.equals(""))
                        JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                    else if (!servicio.esNumero(ean))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo EAN contiene letras");
                    else if (servicio.esNumero(descripcion))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Descripcion contiene numeros");
                    else if (!servicio.esNumero(precio))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Precio contiene letras");
                    else if (!servicio.esNumero(cantidad))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Cantidad contiene letras");
                    else if (!servicio.esNumero(areaID))
                        JOptionPane.showMessageDialog(panelPrincipal, "El campo Area ID contiene letras");
                    else {
                        //
                        if (servicio.findFrescoXEAN(Long.valueOf(ean)) != null ||servicio.findSecoXEAN(Long.valueOf(ean)) != null)
                            JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el codigo EAN o el codigo PLU introducido");
                        else {
                            seco = new Seco(-1, Long.valueOf(ean), descripcion, Double.valueOf(precio),
                                    Integer.valueOf(cantidad), Integer.valueOf(areaID));
                            if (servicio.addSeco(seco) > 0)
                                JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                            else
                                JOptionPane.showMessageDialog(panelPrincipal, "Hubo un problema al agregar el producto");
                        }
                    }
                }else{
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar el tipo del producto");
                }
            }
        });
    }
}
