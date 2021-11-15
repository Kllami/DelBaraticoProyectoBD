package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import main.java.cr.una.delbaratico.model.Producto;
import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Seco;
import javax.swing.table.DefaultTableModel;
import java.util.Collections;
import java.util.ArrayList;
import java.awt.event.*;
import java.util.Vector;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class InventarioView extends JFrame {
    private ServiceController servicio;
    private JPanel panelPrincipal;
    private JScrollPane jScrollPanel;
    private JButton buscarButton;
    private JTextField buscarTextField;
    private JComboBox buscarJComboBox;
    private JTable productosJTable;
    private JButton eliminarButton;
    private JButton agregarButton;
    private JButton editarButton;
    private JButton atrasButton;
    private JLabel areasIDsNom;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    DefaultTableModel defaultJTableModel;
    List<Seco> secosList;
    List<Fresco> frescosList;
    List<Producto> todosProductosList;
    VentanaAgregarEditar ventanaAgregarEditar;
    VentanaEliminar ventanaEliminar;
    HomeView homeView;

    public InventarioView(ServiceController servicio, HomeView homeView) {
        this.servicio = servicio;
        this.homeView = homeView;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
    }

    public void initComponents(){
        this.setContentPane(panelPrincipal);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Modulo de Inventario");
        this.buscarJComboBox.addItem("Descripción");
        this.buscarJComboBox.addItem("ID");
        this.buscarJComboBox.addItem("EAN");
        this.buscarJComboBox.addItem("PLU");
        this.buscarJComboBox.setFocusable(true);
        this.buscarJComboBox.setRequestFocusEnabled(true);
        this.buscarJComboBox.requestFocus();

        Color color = new Color(102,255,178);
        this.agregarButton.setBackground(color);
        this.agregarButton.setOpaque(true);

        color = new Color(255,153,153);
        this.eliminarButton.setBackground(color);
        this.eliminarButton.setOpaque(true);

        color = new Color(102,178,255);
        this.editarButton.setBackground(color);
        this.editarButton.setOpaque(true);
    }

    private void fillTabla(String valorBusqueda, String criterioBusqueda){
        if(criterioBusqueda.equals("Descripción")) {
            this.secosList = this.servicio.buscarSecosXDescripcion(valorBusqueda);
            this.frescosList = this.servicio.buscarFrescosXDescripcion(valorBusqueda);
        }else if(criterioBusqueda.equals("ID")) {
            Seco secoTemp = this.servicio.findSecoById(Long.valueOf(valorBusqueda));
            this.secosList = new ArrayList<>();
            if(secoTemp!=null)
                secosList.add(secoTemp);
            Fresco frescoTemp = this.servicio.findFrescoById(Double.valueOf((valorBusqueda)));
            this.frescosList = new ArrayList<>();
            if(frescoTemp!=null)
                this.frescosList.add(frescoTemp);
        }else if(criterioBusqueda.equals("EAN")) {
            this.secosList = this.servicio.buscarSecosXEAN(Long.valueOf(valorBusqueda));
            this.frescosList = this.servicio.buscarFrescosXEAN(Long.valueOf(valorBusqueda));
        }else if(criterioBusqueda.equals("PLU")) {
            this.frescosList = this.servicio.buscarFrescosXPLU(Long.valueOf(valorBusqueda));
        }

        if(this.secosList.size() + this.frescosList.size() == 0)
            JOptionPane.showMessageDialog(this.panelPrincipal, "No existen productos con el/la " + criterioBusqueda + " brindada(o)");

        this.todosProductosList = new ArrayList<>();
        String ID, ean, descripcion, precio, cantidad, areaId, plu, peso;
        for(Seco seco: secosList){
            ID = String.valueOf(seco.getIdSeco());
            ean = String.valueOf(seco.getEan());
            descripcion = String.valueOf(seco.getDescripcion());
            precio = String.valueOf(seco.getPrecio());
            cantidad = String.valueOf(seco.getCantidad());
            areaId = String.valueOf(seco.getAreaId());
            plu = "NO APLICA";
            peso = "NO APLICA";
            this.todosProductosList.add(new Producto(ID, ean, descripcion, precio, cantidad, areaId, plu, peso));
        }

        for(Fresco fresco: frescosList){
            ID = String.valueOf(fresco.getIdFresco());
            ean = String.valueOf(fresco.getEan());
            descripcion = String.valueOf(fresco.getDescripcion());
            precio = String.valueOf(fresco.getPrecio());
            cantidad = "NO APLICA";
            areaId = "4";
            plu = String.valueOf(fresco.getPlu());
            peso = String.valueOf(fresco.getPeso());
            this.todosProductosList.add(new Producto(ID, ean, descripcion, precio, cantidad, areaId, plu, peso));
        }

        if(criterioBusqueda.equals("Descripción")) {
            this.todosProductosList = this.servicio.ordenarProductosDeAcuerdoSimilitudDescr(todosProductosList, valorBusqueda);
            Collections.reverse(this.todosProductosList);
        }else if(criterioBusqueda.equals("ID")) {
            //Nothing, since sorting is not needed due to the fact only should be one product for each ID
        }else if(criterioBusqueda.equals("EAN")) {
            this.todosProductosList = this.servicio.ordenarProductosDeAcuerdoSimilitudEAN(todosProductosList, valorBusqueda);
            Collections.reverse(this.todosProductosList);
        }else if(criterioBusqueda.equals("PLU")) {
            this.todosProductosList = this.servicio.ordenarProductosDeAcuerdoSimilitudPLU(todosProductosList, valorBusqueda);
            Collections.reverse(this.todosProductosList);
        }

        this.defaultJTableModel = new DefaultTableModel();

        defaultJTableModel.addColumn("ID");
        defaultJTableModel.addColumn("EAN");
        defaultJTableModel.addColumn("Descripcion");
        defaultJTableModel.addColumn("Precio");
        defaultJTableModel.addColumn("Cantidad");
        defaultJTableModel.addColumn("AreaID");
        defaultJTableModel.addColumn("PLU");
        defaultJTableModel.addColumn("Peso");

        for(Producto producto: this.todosProductosList) {
            Vector<Object> row = new Vector<Object>();

            ID = producto.getID();
            ean = producto.getEan();
            descripcion = producto.getDescripcion();
            precio = producto.getPrecio();
            cantidad = producto.getCantidad();
            areaId = producto.getAreaId();
            plu = producto.getPlu();
            peso = producto.getPeso();

            defaultJTableModel.addRow(new Object[]{ID,ean,descripcion,precio,cantidad,areaId,plu,peso});
        }

        this.productosJTable = new JTable(this.defaultJTableModel);
        this.jScrollPanel.setViewportView(productosJTable);
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

    public void iniciarListeners(){
        this.itemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        this.buscarTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(buscarTextField.getText().equals("Introduzca la información aquí..."))
                        buscarTextField.setText("");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(buscarTextField.getText().equals(""))
                        buscarTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        });

        this.buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String valorBusqueda = buscarTextField.getText().trim();

                if(valorBusqueda.equals("") || valorBusqueda.equals("Introduzca la información aquí..."))
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe especificar que información buscar");
                else {
                    if (String.valueOf(buscarJComboBox.getSelectedItem()).equals("Descripción")) {
                        if (!servicio.esNumero(valorBusqueda))
                            fillTabla(valorBusqueda, "Descripción");
                        else
                            JOptionPane.showMessageDialog(panelPrincipal, "La descripcion contiene numeros");
                    } else if (String.valueOf(buscarJComboBox.getSelectedItem()).equals("ID")) {
                        if (servicio.esNumero(valorBusqueda))
                            fillTabla(valorBusqueda, "ID");
                        else
                            JOptionPane.showMessageDialog(panelPrincipal, "La descripcion contiene letras");
                    } else if (String.valueOf(buscarJComboBox.getSelectedItem()).equals("EAN")) {
                        if (servicio.esNumero(valorBusqueda))
                            fillTabla(valorBusqueda, "EAN");
                        else
                            JOptionPane.showMessageDialog(panelPrincipal, "La descripcion contiene letras");
                    } else if (String.valueOf(buscarJComboBox.getSelectedItem()).equals("PLU")) {
                        if (servicio.esNumero(valorBusqueda))
                            fillTabla(valorBusqueda, "PLU");
                        else
                            JOptionPane.showMessageDialog(panelPrincipal, "La descripcion contiene letras");
                    }
                }
            }
        });

        this.agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ventanaAgregarEditar = new VentanaAgregarEditar(servicio);
            }
        });

        this.editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productosJTable.getSelectedRow();
                if(row>-1) {// If there is a selected row
                    String ID = productosJTable.getModel().getValueAt(row, 0).toString().trim();
                    String ean = productosJTable.getModel().getValueAt(row, 1).toString().trim();
                    String descripcion = productosJTable.getModel().getValueAt(row, 2).toString().trim();
                    String precio = productosJTable.getModel().getValueAt(row, 3).toString().trim();
                    String cantidad = productosJTable.getModel().getValueAt(row, 4).toString().trim();
                    String areaID = productosJTable.getModel().getValueAt(row, 5).toString().trim();
                    String plu = productosJTable.getModel().getValueAt(row, 6).toString().trim();
                    String peso = productosJTable.getModel().getValueAt(row, 7).toString().trim();
                    Producto productoEditable = new Producto(ID, ean, descripcion, precio, cantidad, areaID, plu, peso);

                    ventanaAgregarEditar = new VentanaAgregarEditar(servicio, productoEditable);
                }else if(row > 1)
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar solo un producto de la tabla");
                else
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un producto de la tabla");
            }
        });

        atrasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                homeView.setVisible(true);
            }
        });

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = productosJTable.getSelectedRow();
                if(row>-1) {// If there is a selected row
                    String ID = productosJTable.getModel().getValueAt(row, 0).toString().trim();
                    String ean = productosJTable.getModel().getValueAt(row, 1).toString().trim();
                    String descripcion = productosJTable.getModel().getValueAt(row, 2).toString().trim();
                    String precio = productosJTable.getModel().getValueAt(row, 3).toString().trim();
                    String cantidad = productosJTable.getModel().getValueAt(row, 4).toString().trim();
                    String areaID = productosJTable.getModel().getValueAt(row, 5).toString().trim();
                    String plu = productosJTable.getModel().getValueAt(row, 6).toString().trim();
                    String peso = productosJTable.getModel().getValueAt(row, 7).toString().trim();
                    Producto productoEditable = new Producto(ID, ean, descripcion, precio, cantidad, areaID, plu, peso);

                    ventanaEliminar = new VentanaEliminar(servicio, productoEditable);
                }else if(row > 1)
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar solo un producto de la tabla");
                else
                    JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un producto de la tabla");
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
}
