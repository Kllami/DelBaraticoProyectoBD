package main.java.cr.una.delbaratico.model;

public class Usuario {
    private String nombre;
    private String rol;
    private String password;

    public Usuario(String nombre, String rol, String password) {
        this.nombre = nombre;
        this.rol = rol;
        this.password = password;
    }

    public String getRol() {
        return rol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "rol='" + rol + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
