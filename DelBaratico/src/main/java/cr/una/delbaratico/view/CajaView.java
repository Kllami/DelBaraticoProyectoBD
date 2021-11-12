package main.java.cr.una.delbaratico.view;

import com.sun.tools.jconsole.JConsoleContext;
import main.java.cr.una.delbaratico.dao.CajaDAO;
import main.java.cr.una.delbaratico.dao.FacturaDAO;
import main.java.cr.una.delbaratico.dao.FrescoDAO;
import main.java.cr.una.delbaratico.dao.SecoDAO;
import main.java.cr.una.delbaratico.model.*;
import main.java.cr.una.delbaratico.service.ServiceController;
import oracle.sql.DATE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CajaView extends JFrame {
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
    private JTextField textField1;
    private JButton consultarButton;
    private Model modeloPrincipal;
    private Caja caja;
    private List preciosList;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    CajaView(ServiceController servicio){
        this.servicio = servicio;
        this.panel1.setPreferredSize(new Dimension(900,500));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        preciosList = new ArrayList<>();
        rellenarComboBox();

        agregarALaListaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secoCheckBox.isSelected() && !textField3.getText().equals("")){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Seco seco;
                    SecoDAO secoDAO = new SecoDAO(servicio.getUsuarioActual().getNombre(), servicio.getUsuarioActual().getPassword());
                    try {
                        seco = secoDAO.findById(sec);
                        preciosList.add(seco.getPrecio());
                        secoCheckBox.setSelected(false);
                        textField3.setText("");
                    } catch (SQLException ex){
                        System.out.println(ex.toString());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if(!secoCheckBox.isSelected() && !textField3.getText().equals("")){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Fresco fresco;
                    FrescoDAO frescoDAO = new FrescoDAO(servicio.getUsuarioActual().getNombre(), servicio.getUsuarioActual().getPassword());
                    try {
                        fresco = frescoDAO.findById(sec);
                        preciosList.add(fresco.getPrecio());
                        secoCheckBox.setSelected(false);
                        textField3.setText("");
                    } catch (SQLException ex){
                        System.out.println(ex.toString());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if (textField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Debe indicar el id del producto por agregar");
                }
            }
        });


        registrarVentaButton.addActionListener( e -> {
            registrarVenta();
        });

        salirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        consultarPrecioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(secoCheckBox.isSelected() && !textField3.getText().equals("")){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Seco seco;
                    SecoDAO secoDAO = new SecoDAO(servicio.getUsuarioActual().getNombre(), servicio.getUsuarioActual().getPassword());
                    try {
                        seco = secoDAO.findById(sec);
                        JOptionPane.showMessageDialog(panel1, seco.getPrecio());
                    } catch (SQLException ex){
                        System.out.println(ex.toString());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if(!secoCheckBox.isSelected() && !textField3.getText().equals("")){
                    String c = textField3.getText();
                    assert c != null;
                    int sec = Integer.parseInt(c);
                    Fresco fresco;
                    FrescoDAO frescoDAO = new FrescoDAO(servicio.getUsuarioActual().getNombre(), servicio.getUsuarioActual().getPassword());
                    try {
                        fresco = frescoDAO.findById(sec);
                        JOptionPane.showMessageDialog(panel1, fresco.getPrecio());
                    } catch (SQLException ex){
                        System.out.println(ex.toString());
                    } catch (NullPointerException nu){
                        JOptionPane.showMessageDialog(panel1, "Este producto no esta registrado en el sistema");
                    }
                }
                else if (textField3.getText().equals("")) {
                    JOptionPane.showMessageDialog(panel1, "Debe indicar el id del producto por agregar");
                }
            }
    });
    }

    public void rellenarComboBox () {

        CajaDAO caja = new CajaDAO(this.servicio.getUsuarioActual().getNombre(), this.servicio.getUsuarioActual().getPassword());

        try {
            List<Caja> cajasList = caja.findAll();

            for(int index = 0; index < cajasList.size(); index++){
                if(cajasList.get(index).getUsuario().toString().equals(this.servicio.getUsuarioActual().getNombre())){
                    System.out.println(cajasList.get(index).getUsuario().toString());
                    this.comboBox1.addItem(cajasList.get(index).getIdCaja());
                }
            }
        } catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }

    public void registrarVenta(){

        for (int index = 0; index < preciosList.size(); index++) {
            precio = precio + (double) preciosList.get(index);
        }
        FacturaDAO facturaDAO = new FacturaDAO(this.servicio.getUsuarioActual().getNombre(), this.servicio.getUsuarioActual().getPassword());
        try {
            java.util.Date d = new java.util.Date();
            Date date = new Date(d.getTime());
            String c = this.comboBox1.getSelectedItem().toString();
            assert c != null;
            int caja = Integer.parseInt(c);
            System.out.println(precio);
            Factura factura = new Factura(0, 0, date, precio, this.servicio.getUsuarioActual().getNombre(), caja);
            if(precio > 0){
                facturaDAO.insertarFactura(factura);
            }
            else{
                JOptionPane.showMessageDialog(panel1, "Debe ingresar al menos un artículo");
            }

        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
}
