/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import java.io.IOException;

import com.dam.tiendarelojes.daos.ProductoDAO;
import com.dam.tiendarelojes.daos.ProductoDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet para Producto
 * 
 * @author Alvaro Duarte
 */
@WebServlet(name = "ComprarProductoServlet", urlPatterns = { "/ComprarProductoServlet" })
@MultipartConfig
public class ComprarProductoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Obtenemos el id del producto a comprar
            int idProducto = Integer.parseInt(request.getParameter("id").toString());
            // Obtenemos el id del usuario
            int idUsuario = Integer.parseInt(request.getSession().getAttribute("idUsu").toString());

            // Inicializamos ProductoDAOImpl
            ProductoDAO productoDao = new ProductoDAOImpl();

            // Compramos el producto
            productoDao.comprarProducto(idProducto, idUsuario);

            // Redirigimos
            response.sendRedirect("PrincipalServlet");
        } catch (Exception e) {
            response.sendRedirect("ErrorServlet");
        }
    }

}
