package com.example.AdrianPeiro.Modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;
    private String email;
    private String password;
    private String telefono;
    private int es_socio; // 0 o 1
    private String perfil;

    // Constructor por defecto (obligatorio para JPA)
    public Usuario() {}

    // Constructor con todos los campos
    public Usuario(int id, String nombre, String email, String password, String telefono, int es_socio, String perfil) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.telefono = telefono;
        this.es_socio = es_socio;
        this.perfil = perfil;
    }

    // Getters
    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getTelefono() { return telefono; }
    public int getEs_socio() { return es_socio; }
    public String getPerfil() { return perfil; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEs_socio(int es_socio) { this.es_socio = es_socio; }
    public void setPerfil(String perfil) { this.perfil = perfil; }
}
