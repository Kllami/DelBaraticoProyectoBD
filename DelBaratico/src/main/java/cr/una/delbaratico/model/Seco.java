package main.java.cr.una.delbaratico.model;


public class Seco {

  private int idSeco;
  private Long ean;
  private String descripcion;
  private double precio;
  private int cantidad;
  private Area areaId;

  public Seco(int idSeco, Long ean, String descripcion, double precio, int cantidad, Area areaId) {
    this.idSeco = idSeco;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
    this.cantidad = cantidad;
    this.areaId = areaId;
  }

  public int getIdSeco() {
    return idSeco;
  }

  public void setIdSeco(int idSeco) {
    this.idSeco = idSeco;
  }


  public Long getEan() {
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


  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }

  public Area getAreaId() {
    return areaId;
  }

  public void setAreaId(Area areaId) {
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
