/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.dto;

/**
 *
 * @author ruben
 */
public class ProductoDto {
 
    // Propiedades
    private int id;
    private String nombre;
    private String detalle;
    private String tipo;
    private String fotoBase64;
    private float precio;
    private boolean estaComprado = false;
    
    // Constructores

    public ProductoDto(int id, String nombre, String detalle, String tipo, String fotoBase64, float precio, boolean estaComprado) {
        this.id = id;
        this.nombre = nombre;
        this.detalle = detalle;
        this.tipo = tipo;
        this.fotoBase64 = fotoBase64;
        this.precio = precio;
        this.estaComprado = estaComprado;
    }
    
    public ProductoDto() {
    }
    
    // Getter y Setter

    public int getId(){
        return id;
    }
    
    public void setId(int id){
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    public boolean getEstaComprado() {
        return estaComprado;
    }

    public void setEstaComprado(boolean estaComprado) {
        this.estaComprado = estaComprado;
    }
    
    
    
}
