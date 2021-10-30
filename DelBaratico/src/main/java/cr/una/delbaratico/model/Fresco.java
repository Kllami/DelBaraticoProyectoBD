package main.java.cr.una.delbaratico.model;


public class Fresco {

  private String idFresco;
  private String plu;
  private double peso;
  private String ean;
  private String descripcion;
  private double precio;

  public Fresco(String idFresco, String plu, double peso, String ean, String descripcion, double precio) {
    this.idFresco = idFresco;
    this.plu = plu;
    this.peso = peso;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
  }

  public String getIdFresco() {
    return idFresco;
  }

  public void setIdFresco(String idFresco) {
    this.idFresco = idFresco;
  }


  public String getPlu() {
    return plu;
  }

  public void setPlu(String plu) {
    this.plu = plu;
  }


  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
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

}
