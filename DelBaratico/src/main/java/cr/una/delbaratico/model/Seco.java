package main.java.cr.una.delbaratico.model;


public class Seco {

  private double idSeco;
  private double ean;
  private String descripcion;
  private double precio;
  private double cantidad;
  private double areaId;

  public Seco(double idSeco, double ean, String descripcion, double precio, double cantidad, double areaId) {
    this.idSeco = idSeco;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
    this.cantidad = cantidad;
    this.areaId = areaId;
  }

  public double getIdSeco() {
    return idSeco;
  }

  public void setIdSeco(int idSeco) {
    this.idSeco = idSeco;
  }


  public double getEan() {
    return ean;
  }

  public void setEan(Long ean) {
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


  public double getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public double getAreaId() {
    return areaId;
  }

  public void setAreaId(int areaId) {
    this.areaId = areaId;
  }

  @Override
  public String toString() {
    return "Seco{" +
            "idSeco='" + idSeco + '\'' +
            ", ean='" + ean + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", precio=" + precio +
            ", cantidad='" + cantidad + '\'' +
            ", areaId='" + areaId + '\'' +
            '}';
  }
}
