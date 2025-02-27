/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import com.dam.tiendarelojes.daos.ProductoDAO;
import com.dam.tiendarelojes.daos.ProductoDAOImpl;
import com.dam.tiendarelojes.daos.UsuarioDAO;
import com.dam.tiendarelojes.daos.UsuarioDAOImpl;
import com.dam.tiendarelojes.dto.ProductoDto;
import com.dam.tiendarelojes.model.Usuario;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Servlet para Principal
 * 
 * @author Alvaro Duarte
 */
@WebServlet(name = "PrincipalServlet", urlPatterns = { "/PrincipalServlet" })
public class PrincipalServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // Control de sesion
            HttpSession session = request.getSession(false);
            if (session.getAttribute("user") == null) {
                response.sendRedirect("index.jsp");
                return;
            }

            // Inicializamos ProductoDAOImpl
            ProductoDAO productoDao = new ProductoDAOImpl();

            // Obtenemos todos los productos
            List<ProductoDto> productosDto = productoDao.obtenerTodosLosProductos();

            // Guardamos la lista de productos en el request
            request.setAttribute("productos", productosDto);

            // Obtenemos el usuario por el dni para poder mostrar campo calculado en vista
            UsuarioDAO usuarioDao = new UsuarioDAOImpl();
            Usuario usuario = usuarioDao.obtenerUsuarioPorDniConProductos(session.getAttribute("user").toString());

            request.setAttribute("totalPrecioProductos", usuario.getTotalPrecioProductosComprados());
            request.setAttribute("totalProductos", usuario.getCantidadProductosComprados());

            // Devolvemos la vista
            RequestDispatcher dispatcher = request.getRequestDispatcher("principal.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("ErrorServlet");
        }
    }

}
