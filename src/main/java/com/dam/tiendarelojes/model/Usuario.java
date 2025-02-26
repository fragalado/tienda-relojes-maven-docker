/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.List;

/**
 *
 * @author ruben
 */
@Entity
@Table(name="usuarios")
public class Usuario {
    
    // Propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 12)
    private String dni;
    @Column(nullable = false, length = 50)
    private String password;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Producto> productos;
    
    // Campo calculado: suma total de precios de los productos del usuario
    @Transient
    public float getTotalPrecioProductosComprados(){
        if (productos == null){
            return 0.0f;
        }
        return productos.stream()
                .map(Producto::getPrecio)
                .reduce(0.0f, Float::sum);
    }
    
    // Campo calculado: cantidad de productos
    @Transient
    public int getCantidadProductosComprados(){
        return (productos != null) ? productos.size() : 0;
    }
    
    // Constructores

    public Usuario(String dni, String password) {
        this.dni = dni;
        this.password = password;
    }
    
    public Usuario() {
    }
    
    // Getter y Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Producto> getProductos(){
        return productos;
    }
    
}
