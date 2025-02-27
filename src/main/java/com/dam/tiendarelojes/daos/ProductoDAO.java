/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.dam.tiendarelojes.daos;

import com.dam.tiendarelojes.dto.ProductoDto;
import com.dam.tiendarelojes.model.Producto;
import java.util.List;

/**
 * Interfaz que define los metodos que daran servicio a Producto
 * 
 * @author Alvaro Duarte
 */
public interface ProductoDAO {

    /**
     * Metodo que guarda un producto pasado por parámetros en una base de datos
     * MySQL
     * 
     * @param producto Procuto a guardar
     */
    public void guardarProducto(Producto producto);

    /**
     * Metodo que obtiene un producto por su id
     * 
     * @param id Id del producto a obtener
     */
    public ProductoDto obtenerProducto(int id);

    /**
     * Metodo que obtiene todos los productos de la base de datos
     * 
     * @return Devuelve una lista con todos los productos o null si no existe
     *         ninguno
     */
    public List<ProductoDto> obtenerTodosLosProductos();

    /**
     * Metodo que obtiene todos los productos comprados por un usuario
     * 
     * @param dni DNI del usuario
     * @return Devuelve una lista con todos los productos o null si no existe
     *         ninguno
     */
    public List<ProductoDto> obtenerTodosLosProductosDeUnUsuarioPorDNI(int id);

}
