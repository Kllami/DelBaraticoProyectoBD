package main.java.cr.una.delbaratico.model;

public class Fresco {

  private long idFresco;
  private long plu;
  private double peso;
  private long ean;
  private String descripcion;
  private double precio;

  public Fresco(long idFresco, long plu, double peso, long ean, String descripcion, double precio) {
    this.idFresco = idFresco;
    this.plu = plu;
    this.peso = peso;
    this.ean = ean;
    this.descripcion = descripcion;
    this.precio = precio;
  }

  public long getIdFresco() {
    return idFresco;
  }

  public void setIdFresco(long idFresco) {
    this.idFresco = idFresco;
  }


  public long getPlu() {
    return plu;
  }

  public void setPlu(long plu) {
    this.plu = plu;
  }

  public double getPeso() {
    return peso;
  }

  public void setPeso(double peso) {
    this.peso = peso;
  }


  public long getEan() {
    return ean;
  }

  public void setEan(long ean) {
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
