/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import com.dam.tiendarelojes.model.Producto;
import com.dam.tiendarelojes.daos.ProductoDAO;
import com.dam.tiendarelojes.daos.ProductoDAOImpl;
import com.dam.tiendarelojes.dto.ProductoDto;
import jakarta.servlet.RequestDispatcher;

import java.io.File;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;

/**
 * Servlet para Producto
 * 
 * @author Alvaro Duarte
 */
@WebServlet(name = "ProductoServlet", urlPatterns = { "/ProductoServlet" })
@MultipartConfig
public class ProductoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param req  servlet request
     * @param resp servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Obtenemos el id del producto a obtener de la URL
            int id = Integer.parseInt(req.getParameter("id").toString());

            // Obtenemos el producto por el id
            // Inicializamos ProductoDAOImpl
            ProductoDAO productoDao = new ProductoDAOImpl();
            ProductoDto productoDto = productoDao.obtenerProducto(id);

            if (productoDto != null) {
                // Asignamos el producto al request
                req.setAttribute("producto", productoDto);

                // Devolvemos la vista
                RequestDispatcher dispatcher = req.getRequestDispatcher("detalleProducto.jsp");
                dispatcher.forward(req, resp);
            } else {
                resp.sendRedirect("ErrorServlet");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            resp.sendRedirect("ErrorServlet");
        }
    }

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
            // Inicializamos ProductoDAOImpl
            ProductoDAO productoDao = new ProductoDAOImpl();

            // Obtenemos los datos del formulario
            String nombre = request.getParameter("nombre");
            String detalle = request.getParameter("detalle");
            float precio = Float.parseFloat(request.getParameter("precio"));
            String tipo = request.getParameter("tipo");
            Part filePart = request.getPart("foto");

            // Obtener el nombre original del archivo
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            // Definir la ruta donde se guardará la imagen
            String uploadPath = getServletContext().getRealPath("") + File.separator + "static";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Crea el directorio si no existe
            }

            // Ruta completa donde se guardará la imagen
            String filePath = uploadPath + File.separator + fileName;

            // Guardar el archivo en el directorio
            filePart.write(filePath);

            // Guardar solo la ruta relativa en la base de datos
            String imagePath = "static/" + fileName;

            // Realizamos comprobaciones
            // Guardamos el producto
            productoDao.guardarProducto(new Producto(nombre, detalle, imagePath, precio, tipo));
            // Redirigimos
            response.sendRedirect("PrincipalServlet");
        } catch (Exception e) {
            response.sendRedirect("ErrorServlet");
        }
    }

}
