/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import com.dam.tiendarelojes.daos.ProductoDAO;
import com.dam.tiendarelojes.daos.ProductoDAOImpl;
import com.dam.tiendarelojes.dto.ProductoDto;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author ruben
 */
@WebServlet(name = "VerTablaServlet", urlPatterns = {"/VerTablaServlet"})
public class VerTablaServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Control de sesion
        HttpSession session = request.getSession(false);
        if (session.getAttribute("user") == null){
            response.sendRedirect("index.jsp?error");
            return;
        }
        
        // Inicializamos ProductoDAOImpl
        ProductoDAO productoDao = new ProductoDAOImpl();

        // Obtenemos todos los productos comprados por el usuario
        List<ProductoDto> productosDto = productoDao.obtenerTodosLosProductosDeUnUsuarioPorDNI(Integer.parseInt(session.getAttribute("idUsu").toString()));

        // Guardamos la lista de productos en el request
        request.setAttribute("productos", productosDto);

        // Devolvemos la vista
        RequestDispatcher dispatcher = request.getRequestDispatcher("verentablas.jsp");
        dispatcher.forward(request, response);
    }

}
