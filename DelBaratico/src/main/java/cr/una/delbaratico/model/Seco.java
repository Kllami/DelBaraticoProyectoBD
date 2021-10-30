package main.java.cr.una.delbaratico.model;


public class Seco {

  private String idSeco;
  private String ean;
  private String descripcion;
  private double precio;
  private String cantidad;
  private String areaId;

  public Seco(String idSeco, String ean, String descripcion, double precio, String cantidad, String areaId) {
    this.idSeco = idSeco;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
    this.cantidad = cantidad;
    this.areaId = areaId;
  }

  public String getIdSeco() {
    return idSeco;
  }

  public void setIdSeco(String idSeco) {
    this.idSeco = idSeco;
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


  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
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

}
