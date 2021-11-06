package main.java.cr.una.delbaratico.model;

import java.sql.Date;

public class Factura {

  private int idFactura;
  private int numero;
  private java.sql.Date fecha;
  private double total;
  private String usuario;
  private Caja numCaja;

  public Factura(int idFactura, int numero, Date fecha, double total, String usuario, Caja numCaja) {
    this.idFactura = idFactura;
    this.numero = numero;
    this.fecha = fecha;
    this.total = total;
    this.usuario = usuario;
    this.numCaja = numCaja;
  }

  public int getIdFactura() {
    return idFactura;
  }

  public void setIdFactura(int idFactura) {
    this.idFactura = idFactura;
  }


  public int getNumero() {
    return numero;
  }

  public void setNumero(int numero) {
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


  public Caja getNumCaja() {
    return numCaja;
  }

  public void setNumCaja(Caja numCaja) {
    this.numCaja = numCaja;
  }

  @Override
  public String toString() {
    return "Factura{" +
            "idFactura='" + idFactura + '\'' +
            ", numero='" + numero + '\'' +
            ", fecha=" + fecha +
            ", total=" + total +
            ", usuario='" + usuario + '\'' +
            ", numCaja='" + numCaja + '\'' +
            '}';
  }
}
