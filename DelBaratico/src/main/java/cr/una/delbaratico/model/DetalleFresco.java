package main.java.cr.una.delbaratico.model;


public class DetalleFresco {

  private String idDetalleFresco;
  private double peso;
  private double subtotal;
  private String frescoId;
  private String facturaId;

  public DetalleFresco(String idDetalleFresco, double peso, double subtotal, String frescoId, String facturaId) {
    this.idDetalleFresco = idDetalleFresco;
    this.peso = peso;
    this.subtotal = subtotal;
    this.frescoId = frescoId;
    this.facturaId = facturaId;
  }

  public String getIdDetalleFresco() {
    return idDetalleFresco;
  }

  public void setIdDetalleFresco(String idDetalleFresco) {
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


  public String getFrescoId() {
    return frescoId;
  }

  public void setFrescoId(String frescoId) {
    this.frescoId = frescoId;
  }


  public String getFacturaId() {
    return facturaId;
  }

  public void setFacturaId(String facturaId) {
    this.facturaId = facturaId;
  }


}
