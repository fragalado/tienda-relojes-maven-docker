/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dam.tiendarelojes.daos;

import com.dam.tiendarelojes.model.Usuario;

/**
 * Interfaz que define los metodos que daran servicio a Usuario
 * 
 * @author Alvaro Duarte
 */
public interface UsuarioDAO {

    /**
     * Metodo que guarda un usuario pasado por parámetros
     * 
     * @param usuario Usuario que se quiere guardar
     */
    public void guardarUsuario(Usuario usuario);

    /**
     * Método que obtiene un usuario por su dni
     * 
     * @param dni Dni del usuario
     * @return Devuelve el usuario encontrado o null si no se ha encontrado
     */
    public Usuario obtenerUsuarioPorDni(String dni);

    /**
     * Método que obtiene un usuario por su dni y la lista de sus productos
     * comprados
     * 
     * @param dni Dni del usuario
     * @return Devuelve el usuario encontrado o null si no se ha encontrado
     */
    public Usuario obtenerUsuarioPorDniConProductos(String dni);
}
