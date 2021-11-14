package main.java.cr.una.delbaratico.model;

public class Producto {
    private String ID, ean, descripcion, precio, cantidad, areaId, plu, peso;

    public Producto(String ID, String ean, String descripcion, String precio, String cantidad, String areaId, String plu, String peso) {
        this.ID = ID;
        this.ean = ean;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.areaId = areaId;
        this.plu = plu;
        this.peso = peso;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getPlu() {
        return plu;
    }

    public void setPlu(String plu) {
        this.plu = plu;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }
}
