/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDateTime;

/**
 * Entidad Producto que representa la tabla productos de la base de datos
 * 
 * @author Alvaro Duarte
 */
@Entity
@Table(name = "productos")
public class Producto {

    // Propiedades
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false, length = 1000)
    private String detalle;
    @Column(nullable = false)
    private String rutaFoto;
    @Column(nullable = false)
    private float precio;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @Column(nullable = false)
    private String tipo;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    // Constructor

    public Producto(String nombre, String detalle, String rutaFoto, float precio, String tipo) {
        this.nombre = nombre;
        this.detalle = detalle;
        this.rutaFoto = rutaFoto;
        this.precio = precio;
        this.tipo = tipo;
    }

    public Producto() {
    }

    // Getter y Setter

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

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFoto() {
        return rutaFoto;
    }

    public void setFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
