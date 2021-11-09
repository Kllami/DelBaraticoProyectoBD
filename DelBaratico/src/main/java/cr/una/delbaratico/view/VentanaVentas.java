package main.java.cr.una.delbaratico.view;


import main.java.cr.una.delbaratico.control.Control;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

public class VentanaVentas extends JFrame implements Observer {
    private JTable tableProductos;
    Control gestorPrincipal;
    private JButton botonBuscar;
    private JTextField campoBuscar;
    int totalWitdh;

    public VentanaVentas(Control gestorPrincipal) {
        super("Modulo de Ventas");
        totalWitdh = 0;
        this.gestorPrincipal = gestorPrincipal;
        this.loadTableWithData();
        this.configure();
        this.init();
        //this.pack();
    }

    public void loadTableWithData(){
        String[] columnsNames = {"ID", "Descripcion", "Precio", "Cantidad", "Area", "Peso", "EAN", "PLU"};

        Object[][] data = {
                {"234234", "Leche", 10000, 1, "Abarrotes", 1000, 6273545, 6584},
                {"2342344", "Jabon", 600, 1, "Cuidado Personal", 170, 11124756, 0},
                {"3453453", "Vaso de Vidrio", 100, 2, "Mercancias", 200, 4585454, 0},
                {"345345", "Jugo concentrado de Piña", 950, 1, "Frescos", 750, 5618653, 4585}
        };
        this.tableProductos = new JTable(data, columnsNames);
        this.resizeColumnWidth();
    }

    public void configure(){
        BoxLayout boxlayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.getContentPane().setLayout(boxlayout);


        this.botonBuscar = new JButton("Buscar");
        this.add(botonBuscar);
        this.campoBuscar = new JTextField(15);
        this.add(this.campoBuscar);

        this.add(new JScrollPane(tableProductos));
        this.setSize(totalWitdh, tableProductos.getRowCount()*35);
    }

    public void init(){
        this.setTitle("Ventana Ventas");
        this.setVisible(true);
        //this.setSize(tableProductos.getWidth(), tableProductos.getRowCount()*35);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        //pack();
    }

    public void resizeColumnWidth() {
        tableProductos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int column = 0; column < tableProductos.getColumnCount(); column++) {
            TableColumn tableColumn = tableProductos.getColumnModel().getColumn(column);
            int preferredWidth = tableColumn.getMinWidth();
            int maxWidth = tableColumn.getMaxWidth();

            for (int row = 0; row < tableProductos.getRowCount(); row++) {
                TableCellRenderer cellRenderer = tableProductos.getCellRenderer(row, column);
                Component c = tableProductos.prepareRenderer(cellRenderer, row, column);
                int width = c.getPreferredSize().width + tableProductos.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, width);

                //  We've exceeded the maximum width, no need to check other rows

                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }

            String header = (String)tableProductos.getColumnModel().getColumn(column).getHeaderValue();
            Font font = new Font("Arial", Font.PLAIN, 12);
            FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
            int headerWidth = (int)(font.getStringBounds(header, frc).getWidth());

            if(preferredWidth >= headerWidth) {
                tableColumn.setPreferredWidth(preferredWidth);
                totalWitdh = totalWitdh + preferredWidth;
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}