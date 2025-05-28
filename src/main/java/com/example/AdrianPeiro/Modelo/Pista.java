package com.example.AdrianPeiro.Modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pistas")
public class Pista {

    @Id
    private int id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private String precioHora;
    private String imagen;
    private String estado;

    public Pista() {
    }

    public Pista(int id, String nombre, String tipo, String descripcion, String precioHora, String imagen, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precioHora = precioHora;
        this.imagen = imagen;
        this.estado = estado;
    }

    // Getters y setters...

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public String getPrecioHora() {
        return precioHora;
    }
    public void setPrecioHora(String precioHora) {
        this.precioHora = precioHora;
    }
    public String getImagen() {
        return imagen;
    }
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
