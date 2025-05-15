package com.ejemplo.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CategoriaSecundaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String categoriaPrincipalId;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoriaPrincipalId() {
        return categoriaPrincipalId;
    }

    public void setCategoriaPrincipalId(String categoriaPrincipalId) {
        this.categoriaPrincipalId = categoriaPrincipalId;
    }
}
