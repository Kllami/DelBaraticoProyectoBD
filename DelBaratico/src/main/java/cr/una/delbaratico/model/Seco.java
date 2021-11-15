package main.java.cr.una.delbaratico.model;

public class Seco {

  private long idSeco;
  private long ean;
  private String descripcion;
  private double precio;
  private long cantidad;
  private long areaId;

  public Seco(long idSeco, long ean, String descripcion, double precio, long cantidad, long areaId) {
    this.idSeco = idSeco;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
    this.cantidad = cantidad;
    this.areaId = areaId;
  }

  public long getIdSeco() {
    return idSeco;
  }

  public void setIdSeco(int idSeco) {
    this.idSeco = idSeco;
  }


  public long getEan() {
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


  public long getCantidad() {
    return cantidad;
  }

  public void setCantidad(long cantidad) {
    this.cantidad = cantidad;
  }

  public long getAreaId() {
    return areaId;
  }

  public void setAreaId(long areaId) {
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
