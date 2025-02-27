/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet para el Logout - Cerrar sesion
 * 
 * @author Alvaro Duarte
 */
@WebServlet(name = "LogoutServlet", urlPatterns = { "/LogoutServlet" })
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Cerramos la sesion
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate(); // Destruir la sesión
        }
        resp.sendRedirect("index.jsp?info=logout"); // Redirigir a la página de inicio
    }

}
