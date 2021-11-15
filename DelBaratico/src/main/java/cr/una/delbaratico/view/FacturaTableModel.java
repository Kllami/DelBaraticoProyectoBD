package main.java.cr.una.delbaratico.view;

import main.java.cr.una.delbaratico.model.Factura;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FacturaTableModel extends AbstractTableModel{

    private List<Factura> facturas;

    public FacturaTableModel(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    @Override
    public int getRowCount() {
        return facturas.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Factura factura = facturas.get(rowIndex);
        switch(columnIndex){
            case 0: return factura.getNumero();
            case 1: return factura.getFecha();
            case 2: return factura.getTotal();
            case 3: return factura.getUsuario();
            case 4: return factura.getNumCaja();
            default: return "";
        }
    }

    @Override
    public String getColumnName(int columnIndex){
        switch(columnIndex){
            case 0: return "Número";
            case 1: return "Fecha";
            case 2: return "Total";
            case 3: return "Usuario";
            case 4: return "Número de caja";
            default: return "";
        }
    }
}
