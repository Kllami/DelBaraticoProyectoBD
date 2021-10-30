package main.java.cr.una.delbaratico.model;


public class Area {

  private String idArea;
  private String descripcion;

  public Area(String idArea, String descripcion) {
    this.idArea = idArea;
    this.descripcion = descripcion;
  }

  public String getIdArea() {
    return idArea;
  }

  public void setIdArea(String idArea) {
    this.idArea = idArea;
  }


  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

}
