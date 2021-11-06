package main.java.cr.una.delbaratico.model;


public class Caja {

  private int idCaja;
  private String usuario;

  public Caja(int idCaja, String usuario) {
    this.idCaja = idCaja;
    this.usuario = usuario;
  }

  public int getIdCaja() {
    return idCaja;
  }

  public void setIdCaja(int idCaja) {
    this.idCaja = idCaja;
  }


  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  @Override
  public String toString() {
    return "Caja{" +
            "idCaja='" + idCaja + '\'' +
            ", usuario='" + usuario + '\'' +
            '}';
  }
}
