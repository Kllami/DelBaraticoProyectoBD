package main.java.cr.una.delbaratico.model;


public class Caja {

  private String idCaja;
  private String usuario;

  public Caja(String idCaja, String usuario) {
    this.idCaja = idCaja;
    this.usuario = usuario;
  }

  public String getIdCaja() {
    return idCaja;
  }

  public void setIdCaja(String idCaja) {
    this.idCaja = idCaja;
  }


  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

}
