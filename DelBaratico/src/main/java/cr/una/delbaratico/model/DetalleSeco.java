package main.java.cr.una.delbaratico.model;


public class DetalleSeco {

  private int idDetalleSeco;
  private int cantidad;
  private double subtotal;
  private Seco secoId;
  private Factura facturaId;

  public DetalleSeco(int idDetalleSeco, int cantidad, double subtotal, Seco secoId, Factura facturaId) {
    this.idDetalleSeco = idDetalleSeco;
    this.cantidad = cantidad;
    this.subtotal = subtotal;
    this.secoId = secoId;
    this.facturaId = facturaId;
  }

  public int getIdDetalleSeco() {
    return idDetalleSeco;
  }

  public void setIdDetalleSeco(int idDetalleSeco) {
    this.idDetalleSeco = idDetalleSeco;
  }


  public int getCantidad() {
    return cantidad;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
  }


  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }


  public Seco getSecoId() {
    return secoId;
  }

  public void setSecoId(Seco secoId) {
    this.secoId = secoId;
  }


  public Factura getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(Factura facturaId) {
    this.facturaId = facturaId;
  }

  @Override
  public String toString() {
    return "DetalleSeco{" +
            "idDetalleSeco='" + idDetalleSeco + '\'' +
            ", cantidad='" + cantidad + '\'' +
            ", subtotal=" + subtotal +
            ", secoId='" + secoId + '\'' +
            ", facturaId='" + facturaId + '\'' +
            '}';
  }
}
