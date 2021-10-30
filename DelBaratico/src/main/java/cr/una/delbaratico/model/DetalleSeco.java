package main.java.cr.una.delbaratico.model;


public class DetalleSeco {

  private String idDetalleSeco;
  private String cantidad;
  private double subtotal;
  private String secoId;
  private String facturaId;

  public DetalleSeco(String idDetalleSeco, String cantidad, double subtotal, String secoId, String facturaId) {
    this.idDetalleSeco = idDetalleSeco;
    this.cantidad = cantidad;
    this.subtotal = subtotal;
    this.secoId = secoId;
    this.facturaId = facturaId;
  }

  public String getIdDetalleSeco() {
    return idDetalleSeco;
  }

  public void setIdDetalleSeco(String idDetalleSeco) {
    this.idDetalleSeco = idDetalleSeco;
  }


  public String getCantidad() {
    return cantidad;
  }

  public void setCantidad(String cantidad) {
    this.cantidad = cantidad;
  }


  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }


  public String getSecoId() {
    return secoId;
  }

  public void setSecoId(String secoId) {
    this.secoId = secoId;
  }


  public String getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(String facturaId) {
    this.facturaId = facturaId;
  }

}
