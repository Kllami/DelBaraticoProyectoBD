package main.java.cr.una.delbaratico.model;


public class Area {

  private int idArea;
  private String descripcion;

  public Area(int idArea, String descripcion) {
    this.idArea = idArea;
    this.descripcion = descripcion;
  }

  public int getIdArea() {
    return idArea;
  }

  public void setIdArea(int idArea) {
    this.idArea = idArea;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  @Override
  public String toString() {
    return "Area{" +
            "idArea='" + idArea + '\'' +
            ", descripcion='" + descripcion + '\'' +
            '}';
  }
}
