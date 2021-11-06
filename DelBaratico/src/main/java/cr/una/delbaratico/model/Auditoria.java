package main.java.cr.una.delbaratico.model;


import java.sql.Date;

public class Auditoria {

  private int idAuditoria;
  private String usuario;
  private String tabla;
  private String transaccion;
  private java.sql.Date fecha;

  public Auditoria(int idAuditoria, String usuario, String tabla, String transaccion, Date fecha) {
    this.idAuditoria = idAuditoria;
    this.usuario = usuario;
    this.tabla = tabla;
    this.transaccion = transaccion;
    this.fecha = fecha;
  }

  public int getIdAuditoria() {
    return idAuditoria;
  }

  public void setIdAuditoria(int idAuditoria) {
    this.idAuditoria = idAuditoria;
  }


  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }


  public String getTabla() {
    return tabla;
  }

  public void setTabla(String tabla) {
    this.tabla = tabla;
  }


  public String getTransaccion() {
    return transaccion;
  }

  public void setTransaccion(String transaccion) {
    this.transaccion = transaccion;
  }


  public java.sql.Date getFecha() {
    return fecha;
  }

  public void setFecha(java.sql.Date fecha) {
    this.fecha = fecha;
  }

  @Override
  public String toString() {
    return "Auditoria{" +
            "idAuditoria='" + idAuditoria + '\'' +
            ", usuario='" + usuario + '\'' +
            ", tabla='" + tabla + '\'' +
            ", transaccion='" + transaccion + '\'' +
            ", fecha=" + fecha +
            '}';
  }
}
