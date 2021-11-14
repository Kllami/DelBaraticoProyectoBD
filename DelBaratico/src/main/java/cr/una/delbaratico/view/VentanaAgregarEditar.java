package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;

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
    private JTextField areaIDTextField;
    private JTextField pluTextField;
    private JTextField pesoTextField;
    private JButton guardarDatosJButton;
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
        if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
            this.cantidadJLabel.setEnabled(false);
            this.cantidadTextField.setEnabled(false);
            this.areaIDJLabel.setEnabled(false);
            this.areaIDTextField.setEnabled(false);
            this.pluJLabel.setEnabled(true);
            this.pluTextField.setEnabled(true);
            this.pesoTextField.setEnabled(true);
            this.pesoJLabel.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
        }else{
            this.pluJLabel.setEnabled(false);
            this.pluTextField.setEnabled(false);
            this.pesoTextField.setEnabled(false);
            this.pesoJLabel.setEnabled(false);
            this.cantidadJLabel.setEnabled(true);
            this.cantidadTextField.setEnabled(true);
            this.areaIDJLabel.setEnabled(true);
            this.areaIDTextField.setEnabled(true);
            this.panelPrincipal.validate();
            this.panelPrincipal.repaint();
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
        this.tipoProdComboBox.addItem("Fresco");
        this.tipoProdComboBox.addItem("Seco");
        this.tipoProdComboBox.setFocusable(true);
        this.tipoProdComboBox.setRequestFocusEnabled(true);
        this.tipoProdComboBox.requestFocus();
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

        this.areaIDTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(areaIDTextField.getText().equals("Introduzca la información aquí..."))
                        areaIDTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(areaIDTextField.getText().equals(""))
                        areaIDTextField.setText("Introduzca la información aquí...");
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
                String areaID = areaIDTextField.getText().trim();
                String plu = pluTextField.getText().trim();
                String peso = pesoTextField.getText().trim();

                String mensajeIntroduzca = "Introduzca la información aquí...";
                if(ean.equals("") || descripcion.equals("") || precio.equals("") || cantidad.equals("") ||
                        areaID.equals("") || plu.equals("") || peso.equals(""))
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                else if(ean.equals(mensajeIntroduzca) || descripcion.equals(mensajeIntroduzca) ||
                        precio.equals(mensajeIntroduzca) || cantidad.equals(mensajeIntroduzca) ||
                        areaID.equals(mensajeIntroduzca) || plu.equals(mensajeIntroduzca) ||
                        peso.equals(mensajeIntroduzca))
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                else if(!servicio.esNumero(ean))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo EAN contiene letras");
                else if(servicio.esNumero(descripcion))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo Descripcion contiene numeros");
                else if(!servicio.esNumero(precio))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo Precio contiene letras");
                else if(!servicio.esNumero(cantidad))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo Cantidad contiene letras");
                else if(servicio.esNumero(areaID))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo Area ID contiene numeros");
                else if(!servicio.esNumero(plu))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo PLU contiene letras");
                else if(!servicio.esNumero(peso))
                    JOptionPane.showMessageDialog(panelPrincipal, "El campo Peso contiene letras");
                else{
                    if (String.valueOf(tipoProdComboBox.getSelectedItem()).equals("Fresco")) {
                        if(servicio.findFrescoXEAN(Long.valueOf(ean))  == null) {
                            fresco = new Fresco(-1, Integer.valueOf(plu), Double.valueOf(peso), Long.valueOf(ean),
                                    descripcion, Double.valueOf(precio));
                            servicio.addFresco(fresco);
                            JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                        }else
                            JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el EAN introducido");
                    }else{
                        if(servicio.findSecoXEAN(Long.valueOf(ean)) == null) {
                            seco = new Seco(-1, Long.valueOf(ean), descripcion, Double.valueOf(precio),
                                    Integer.valueOf(cantidad), Integer.valueOf(areaID));
                            servicio.addSeco(seco);
                            JOptionPane.showMessageDialog(panelPrincipal, "Producto agregado");
                        }else
                            JOptionPane.showMessageDialog(panelPrincipal, "Ya existe un producto con el EAN introducido");
                    }
                }
            }
        });
    }
}
