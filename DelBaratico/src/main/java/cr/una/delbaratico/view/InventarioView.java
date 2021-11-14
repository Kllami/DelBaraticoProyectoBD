package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Fresco;
import main.java.cr.una.delbaratico.model.Producto;
import main.java.cr.una.delbaratico.model.Seco;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

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
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;
    Vector<String> columnasTabla;
    DefaultTableModel model;
    List<Seco> secosList;
    List<Fresco> frescosList;
    List<Producto> todosProductosList;

    public InventarioView(ServiceController servicio) {
        this.servicio = servicio;
        this.ajustarMenus();
        this.initComponents();
        this.iniciarListeners();
    }

    public void initComponents(){
        this.setContentPane(panelPrincipal);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("Modulo de Inventario");
        this.buscarJComboBox.addItem("Descripción");
        this.buscarJComboBox.addItem("Código");
        this.buscarJComboBox.setFocusable(true);
        this.buscarJComboBox.setRequestFocusEnabled(true);
        this.buscarJComboBox.requestFocus();
    }

    private void fillTabla(String descripcionUsuario){
        this.secosList = this.servicio.buscarSecosXDescripcion(descripcionUsuario);
        this.frescosList = this.servicio.buscarFrescosXDescripcion(descripcionUsuario);

        if(this.secosList.size() + this.frescosList.size() == 0)
            JOptionPane.showMessageDialog(this.panelPrincipal, "No existen productos con la descripcion brindada");

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
            areaId = "NO APLICA";
            plu = String.valueOf(fresco.getPlu());
            peso = String.valueOf(fresco.getPeso());
            this.todosProductosList.add(new Producto(ID, ean, descripcion, precio, cantidad, areaId, plu, peso));
        }

        this.todosProductosList = this.servicio.ordenarProductosDeAcuerdoSimilitud(todosProductosList, descripcionUsuario);
        Collections.reverse(this.todosProductosList);

        this.model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("EAN");
        model.addColumn("Descripcion");
        model.addColumn("Precio");
        model.addColumn("Cantidad");
        model.addColumn("AreaID");
        model.addColumn("PLU");
        model.addColumn("Peso");

        for(Producto producto: this.todosProductosList) {
            Vector<Object> row = new Vector<Object>();

            ID = producto.getID();
            ean = producto.getEan();
            descripcion = producto.getDescripcion();
            precio = producto.getPrecio();
            cantidad = producto.getCantidad();
            areaId = producto.getCantidad();
            plu = producto.getPlu();
            peso = producto.getPeso();

            model.addRow(new Object[]{ID,ean,descripcion,precio,cantidad,areaId,plu,peso});
        }

        this.productosJTable = new JTable(this.model);
        this.jScrollPanel.setViewportView(productosJTable);

        /*this.model = new DefaultTableModel(this.columnasTabla, this.todosProductosList.size());

        for(Producto producto: this.todosProductosList) {
            Vector<Object> row = new Vector<Object>();
            row.add(producto.getID());
            row.add(producto.getEan());
            row.add(producto.getDescripcion());
            row.add(producto.getPrecio());
            row.add(producto.getCantidad());
            row.add(producto.getAreaId());
            row.add(producto.getPlu());
            row.add(producto.getPeso());
            model.addRow(row);
        }

        this.productosJTable = new JTable(this.model);*/
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
                    System.out.println(e.getMessage());
                }
            }

            public void focusLost(java.awt.event.FocusEvent focusEvent) {
                try {
                    if(buscarTextField.getText().equals(""))
                        buscarTextField.setText("Introduzca la información aquí...");
                    panelPrincipal.validate();
                    panelPrincipal.repaint();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        this.buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(String.valueOf(buscarJComboBox.getSelectedItem()).equals("Descripción")){
                    String descripcion = buscarTextField.getText().trim();
                    if(!servicio.esNumero(descripcion) && !descripcion.equals("")){
                        fillTabla(descripcion);
                        //jScrollPanel.revalidate();
                        //jScrollPanel.repaint();
                        //panelPrincipal.revalidate();
                        //panelPrincipal.repaint();

                    }

                }else{

                }
            }
        });

        this.agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //model.addRow(new Object[model.getColumnCount()]); //adds a new, empty row to the table
            }
        });
    }
}
