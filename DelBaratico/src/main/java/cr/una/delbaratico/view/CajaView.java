package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.dao.CajaDAO;
import main.java.cr.una.delbaratico.model.*;
import main.java.cr.una.delbaratico.service.ServiceController;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CajaView extends JFrame {

    ServiceController servicio;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton registrarVentaButton;
    private JCheckBox secoCheckBox;
    private JTextField textField3;
    private List<Caja> cajasList;
    private CajaDAO caja;
    private Model modeloPrincipal;

    CajaView(ServiceController servicio){
        this.servicio = servicio;
        this.panel1.setPreferredSize(new Dimension(900,500));
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        rellenarComboBox();

        registrarVentaButton.addActionListener( e -> {

        });
    }

    public void rellenarComboBox () {

        this.caja = new CajaDAO(this.servicio.getUsuarioActual().getNombre(), this.servicio.getUsuarioActual().getPassword());

        try {
            this.cajasList = this.caja.findAll();

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
}
