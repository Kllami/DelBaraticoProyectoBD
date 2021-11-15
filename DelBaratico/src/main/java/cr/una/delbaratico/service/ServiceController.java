package main.java.cr.una.delbaratico.service;

import main.java.cr.una.delbaratico.dao.JdbcUtil;
import main.java.cr.una.delbaratico.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceController {

    private Model modeloPrincipal;
    private JdbcUtil util;

    public ServiceController(Model iniciarSesionModel) {
        this.modeloPrincipal = iniciarSesionModel;
    }

    public boolean iniciarSesion(String DB_USER, String DB_PASSWORD) {
        return this.modeloPrincipal.login(DB_USER, DB_PASSWORD);
    }

    public Usuario getUsuarioActual(){
        return this.modeloPrincipal.getUsuarioActual();
    }

    public boolean esNumero(String str){
        boolean isNumeric = true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                isNumeric = false;
            }
        }
        return isNumeric;
    }

    public Seco findSecoById(long idSeco) {
        return this.modeloPrincipal.findSecoById(idSeco);
    }

    public void updateInventarioFresco(double peso, long idSeco) {
        this.modeloPrincipal.updateInventarioFresco(peso, idSeco);
    }

    public void updateInventarioSeco(long cantidad, long idSeco){
        this.modeloPrincipal.updateInventarioSeco(cantidad, idSeco);
    }

    public Fresco findFrescoById(double idFresco) {
        return this.modeloPrincipal.findFrescoById(idFresco);
    }

    public List<Caja> listaCajas() {
        return this.modeloPrincipal.listaCajas();
    }

    public void insertarFactura(Factura factura){
        this.modeloPrincipal.insertarFactura(factura);
    }

    public List<Seco> buscarSecosXDescripcion(String descripcion) {
        return this.modeloPrincipal.buscarSecosXDescripcion(descripcion);
    }

    public List<Seco> buscarSecosXEAN(long EAN) {
        return this.modeloPrincipal.buscarSecosXEAN(EAN);
    }

    public List<Fresco> buscarFrescosXDescripcion(String descripcion) {
        return this.modeloPrincipal.buscarFrescosXDescripcion(descripcion);
    }

    public List<Fresco> buscarFrescosXEAN(long EAN) {
        return this.modeloPrincipal.buscarFrescosXEAN(EAN);
    }

    public List<Fresco> buscarFrescosXPLU(long PLU) {
        return this.modeloPrincipal.buscarFrescosXPLU(PLU);
    }

    public double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
    /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
    LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
    return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                    costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    public List<Producto> ordenarProductosDeAcuerdoSimilitudDescr(List<Producto> productos, String descripcion){
        boolean sorted = false;
        Producto temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < productos.size() - 1; i++) {
                double similarityA = this.similarity(productos.get(i).getDescripcion(), descripcion);
                double similarityB = this.similarity(productos.get(i + 1).getDescripcion(), descripcion);
                if (similarityA > similarityB) {
                    temp = productos.get(i);
                    productos.set(i, productos.get(i + 1));
                    productos.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        return productos;
    }

    public List<Producto> ordenarProductosDeAcuerdoSimilitudEAN(List<Producto> productos, String EAN){
        boolean sorted = false;
        Producto temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < productos.size() - 1; i++) {
                double similarityA = this.similarity(productos.get(i).getEan(), EAN);
                double similarityB = this.similarity(productos.get(i + 1).getEan(), EAN);
                if (similarityA > similarityB) {
                    temp = productos.get(i);
                    productos.set(i, productos.get(i + 1));
                    productos.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        return productos;
    }

    public List<Producto> ordenarProductosDeAcuerdoSimilitudPLU(List<Producto> productos, String PLU){
        boolean sorted = false;
        Producto temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < productos.size() - 1; i++) {
                double similarityA = this.similarity(productos.get(i).getPlu(), PLU);
                double similarityB = this.similarity(productos.get(i + 1).getPlu(), PLU);
                if (similarityA > similarityB) {
                    temp = productos.get(i);
                    productos.set(i, productos.get(i + 1));
                    productos.set(i + 1, temp);
                    sorted = false;
                }
            }
        }
        return productos;
    }

    public int addFresco(Fresco fresco) {
        return this.modeloPrincipal.addFresco(fresco);
    }

    public int addSeco(Seco seco) {
        return this.modeloPrincipal.addSeco(seco);
    }

    public Seco findSecoXEAN(long ean){
        return this.modeloPrincipal.findSecoXEAN(ean);
    }

    public Fresco findFrescoXEAN(long ean){
        return this.modeloPrincipal.findFrescoXEAN(ean);
    }

    public Fresco findFrescoXPLU(long plu) {
        return this.modeloPrincipal.findFrescoXPLU(plu);
    }

    public List<String> areasIDSNombres() { return this.modeloPrincipal.areasIDSNombres();}

    public List<Factura> listaFacturas() {
        return this.modeloPrincipal.listaFacturas();
    }

}
