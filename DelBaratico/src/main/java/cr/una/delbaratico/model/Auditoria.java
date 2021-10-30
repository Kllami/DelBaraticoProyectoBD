package main.java.cr.una.delbaratico.model;


import java.sql.Date;

public class Auditoria {

  private String idAuditoria;
  private String usuario;
  private String tabla;
  private String transaccion;
  private java.sql.Date fecha;

  public Auditoria(String idAuditoria, String usuario, String tabla, String transaccion, Date fecha) {
    this.idAuditoria = idAuditoria;
    this.usuario = usuario;
    this.tabla = tabla;
    this.transaccion = transaccion;
    this.fecha = fecha;
  }

  public String getIdAuditoria() {
    return idAuditoria;
  }

  public void setIdAuditoria(String idAuditoria) {
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

}
