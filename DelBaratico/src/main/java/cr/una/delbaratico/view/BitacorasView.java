package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.service.ServiceController;
import main.java.cr.una.delbaratico.dao.AuditoriaDAO;
import main.java.cr.una.delbaratico.model.Auditoria;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import java.awt.*;


public class BitacorasView extends JFrame {
    private ServiceController servicio;
    private JPanel panel1;
    private JTable table1;
    private JMenuBar menuPrincipal;
    private JMenu menuArchivo;
    private JMenuItem itemSalir;

    public BitacorasView(ServiceController servicio) {
        this.servicio = servicio;
        this.panel1.setPreferredSize(new Dimension(900,500));
        this.setContentPane(panel1);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.ajustarMenus();
        llenarTabla();
    }

    private void acomodarTabla(){
        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null},
                        {null, null, null, null, null}
                },
                new String [] {
                        "Id", "Usuario", "Tabla", "Transaccion", "Fecha"}
        ));
    }

    private void ajustarMenus() {
        this.menuPrincipal = new JMenuBar();
        this.menuPrincipal.add(this.menuArchivo = new JMenu("Opciones"));
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

    private void llenarTabla(){
        AuditoriaDAO auditoria = new AuditoriaDAO(this.servicio.getUsuarioActual().getNombre(), this.servicio.getUsuarioActual().getPassword());

        String col[] = {"Id", "Usuario", "Tabla", "Transaccion", "Fecha"};

        javax.swing.table.DefaultTableModel tableModel = new javax.swing.table.DefaultTableModel(col, 0);

        try {

            table1.setModel(tableModel);

            List<Auditoria> auditoriaList = auditoria.findAll();
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }
            auditoriaList = auditoria.findSelect("MARIA_FALLAS_MENDEZ");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("JOHNNY_CHACON_GAIRAUD");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("JOSE_PEREZ_ZAMORA");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("GIANCARLO_ALVARADO_SANCHEZ");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("JUAN_HERNANDEZ_RAMIREZ");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("SAMANTHA_ARROYO_ARRIETA");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("MARLON_FREER_ACEVEDO");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("ALEX_SOTO_MOREIRA");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("DAVID_CAMACHO_MELENDEZ");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }
            auditoriaList = auditoria.findSelect("DIANA_QUIROS_UGALDE");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            auditoriaList = auditoria.findSelect("TATIANA_TORRES_FERNANDEZ");
            for(int index = 0; index < auditoriaList.size(); index++){
                Object[] obj = new Object[] {
                        auditoriaList.get(index).getIdAuditoria(),
                        auditoriaList.get(index).getUsuario(),
                        auditoriaList.get(index).getTabla(),
                        auditoriaList.get(index).getTransaccion(),
                        auditoriaList.get(index).getFecha()
                };


                tableModel.addRow(obj);
            }

            System.out.println(auditoriaList.toString());
        } catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }


}
