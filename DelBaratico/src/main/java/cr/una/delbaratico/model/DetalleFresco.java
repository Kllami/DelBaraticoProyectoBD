package main.java.cr.una.delbaratico.model;

public class DetalleFresco {

  private int idDetalleFresco;
  private double peso;
  private double subtotal;
  private Fresco frescoId;
  private Factura facturaId;

  public DetalleFresco(int idDetalleFresco, double peso, double subtotal, Fresco frescoId, Factura facturaId) {
    this.idDetalleFresco = idDetalleFresco;
    this.peso = peso;
    this.subtotal = subtotal;
    this.frescoId = frescoId;
    this.facturaId = facturaId;
  }

  public int getIdDetalleFresco() {
    return idDetalleFresco;
  }

  public void setIdDetalleFresco(int idDetalleFresco) {
    this.idDetalleFresco = idDetalleFresco;
  }


  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }


  public double getSubtotal() {
    return subtotal;
  }

  public void setSubtotal(double subtotal) {
    this.subtotal = subtotal;
  }


  public Fresco getFrescoId() {
    return frescoId;
  }

  public void setFrescoId(Fresco frescoId) {
    this.frescoId = frescoId;
  }


  public Factura getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(Factura facturaId) {
    this.facturaId = facturaId;
  }


  @Override
  public String toString() {
    return "DetalleFresco{" +
            "idDetalleFresco='" + idDetalleFresco + '\'' +
            ", peso=" + peso +
            ", subtotal=" + subtotal +
            ", frescoId='" + frescoId + '\'' +
            ", facturaId='" + facturaId + '\'' +
            '}';
  }
}
