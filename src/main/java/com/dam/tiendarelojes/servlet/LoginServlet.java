/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import com.dam.tiendarelojes.daos.UsuarioDAOImpl;
import com.dam.tiendarelojes.daos.UsuarioDAO;
import com.dam.tiendarelojes.model.Usuario;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author ruben
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // Inicializamos UsuarioDAOImpl
            UsuarioDAO usuarioDao = new UsuarioDAOImpl();

            // Obtenemos los datos del formulario
            String dni = request.getParameter("dni");
            String password = request.getParameter("password");

            // Comprobamos si el usuario existe
            // Si existe redirigimos a la vista principal
            Usuario usuarioObtenido = usuarioDao.obtenerUsuarioPorDni(dni);
            if (usuarioObtenido != null && usuarioObtenido.getPassword().equals(password)) {
                HttpSession session = request.getSession(false);
                session.setAttribute("user", dni);
                session.setAttribute("idUsu", usuarioObtenido.getId());
                response.sendRedirect("PrincipalServlet");
            } else {
                response.sendRedirect("validacionErronea.jsp");
            }
        } catch (Exception e) {
            response.sendRedirect("ErrorServlet");
        }
        
    }
    
}
