package main.java.cr.una.delbaratico.model;


import java.sql.Date;

public class Factura {

  private String idFactura;
  private String numero;
  private java.sql.Date fecha;
  private double total;
  private String usuario;
  private String numCaja;

  public Factura(String idFactura, String numero, Date fecha, double total, String usuario, String numCaja) {
    this.idFactura = idFactura;
    this.numero = numero;
    this.fecha = fecha;
    this.total = total;
    this.usuario = usuario;
    this.numCaja = numCaja;
  }

  public String getIdFactura() {
    return idFactura;
  }

  public void setIdFactura(String idFactura) {
    this.idFactura = idFactura;
  }


  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }


  public java.sql.Date getFecha() {
    return fecha;
  }

  public void setFecha(java.sql.Date fecha) {
    this.fecha = fecha;
  }


  public double getTotal() {
    return total;
  }

  public void setTotal(double total) {
    this.total = total;
  }


  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }


  public String getNumCaja() {
    return numCaja;
  }

  public void setNumCaja(String numCaja) {
    this.numCaja = numCaja;
  }

}
