package main.java.cr.una.delbaratico.model;


public class Fresco {

  private int idFresco;
  private int plu;
  private double peso;
  private Long ean;
  private String descripcion;
  private double precio;

  public Fresco(int idFresco, int plu, double peso, Long ean, String descripcion, double precio) {
    this.idFresco = idFresco;
    this.plu = plu;
    this.peso = peso;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
  }

  public int getIdFresco() {
    return idFresco;
  }

  public void setIdFresco(int idFresco) {
    this.idFresco = idFresco;
  }


  public int getPlu() {
    return plu;
  }

  public void setPlu(int plu) {
    this.plu = plu;
  }


  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
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

  @Override
  public String toString() {
    return "Fresco{" +
            "idFresco='" + idFresco + '\'' +
            ", plu='" + plu + '\'' +
            ", peso=" + peso +
            ", ean='" + ean + '\'' +
            ", descripcion='" + descripcion + '\'' +
            ", precio=" + precio +
            '}';
  }
}
