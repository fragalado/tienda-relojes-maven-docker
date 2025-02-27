/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dam.tiendarelojes.servlet;

import com.dam.tiendarelojes.daos.UsuarioDAOImpl;
import com.dam.tiendarelojes.daos.UsuarioDAO;
import com.dam.tiendarelojes.model.Producto;
import com.dam.tiendarelojes.model.Usuario;
import com.dam.tiendarelojes.util.HibernateUtil;

import java.io.IOException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet para el Login
 * 
 * @author Alvaro Duarte
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @SuppressWarnings("deprecation")
    @Override
    public void init() throws ServletException {
        // Inicializamos datos en la tabla usuario y productos
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            // Comprobamos si las tablas usuarios y productos no estan vacias
            if (Integer.parseInt(session.createQuery("select count(*) from Usuario").uniqueResult().toString()) > 0
                    || Integer.parseInt(
                            session.createQuery("select count(*) from Producto").uniqueResult().toString()) > 0) {
                System.out.println("Existen datos de usuarios y/o productos en la base de datos");
                return;
            } else {
                transaction = session.beginTransaction();

                // Creamos los usuarios
                Usuario usuario1 = new Usuario("53965130T", "123456");
                Usuario usuario2 = new Usuario("53965131D", "123456");

                // Creamos los productos
                Producto producto1 = new Producto("Rolex Datejust",
                        "Lanzado en 1945, el Datejust fue el primer reloj de pulsera cronómetro, automático y hermético en indicar la fecha en una ventana en la esfera situada en la posición de las 3 h. Esta combinación única fue más tarde optimizada en 1953 con el efecto lupa de la lente Cyclops en la visualización. Con estas invenciones, el Datejust encarnó la búsqueda de la excelencia del fundador de Rolex, Hans Wilsdorf, que creía que el progreso relojero debía fomentar el progreso humano. En un periodo de grandes cambios, nuestra relación con el tiempo está cambiando. La vida moderna está menos ligada a las estaciones mientras nos movemos al ritmo del paso de los días. El Datejust ofrece comodidad así como legibilidad y gestión diaria del tiempo. Útil y práctico, pronto se impuso como un instrumento valioso para el usuario moderno, independiente y activo.",
                        "static/rolex-datejust.avif",
                        9600, "reloj-pulsera");
                Producto producto2 = new Producto("Rolex Day-date",
                        "El Day‑Date se diseñó originalmente para ser un instrumento valioso de uso diario. Gracias a una proeza relojera sin precedentes en aquella época, ofrecía una solución para aquellos que necesitaban una maestría absoluta de su calendario personal: el Day‑Date fue el primer reloj de pulsera cronómetro, automático y hermético en indicar, además de la fecha, el día de la semana con todas las letras. Ambos cambian de forma simultánea a medianoche. Este cambio revolucionario continúa fascinando casi setenta años más tarde.",
                        "static/rolex-day-date.png",
                        9600, "reloj-pulsera");
                Producto producto3 = new Producto("Rolex GMT Master II",
                        "El GMT‑Master II, reconocible a simple vista, es el primer modelo de Rolex que introdujo un disco de bisel Cerachrom en cerámica de alta tecnología. Este disco monobloque bicolor con una escala graduada 24 horas es prácticamente imposible de rayar y extremadamente duro. Ha sido desarrollado y manufacturado por Rolex en una amplia gama de combinaciones de colores.",
                        "static/rolex-gmt-master-ii.avif",
                        9600, "reloj-pulsera");
                Producto producto4 = new Producto("Rolex Submariner",
                        "En su lanzamiento en 1953, el Rolex Submariner era el primer reloj de pulsera de buceo hermético hasta 100 metros. Esta profundidad se aumentó a 200 metros al año siguiente y, después, a 300 metros en 1989. En cuanto al Submariner Date, garantizaba una hermeticidad de hasta 200 metros en su lanzamiento en 1969, y de hasta 300 metros desde 1979. Estas mejoras sucesivas demuestran los esfuerzos inquebrantables de Rolex por superar los límites de la hermeticidad: una búsqueda que empezó con el desarrollo de la caja Oyster en los años 20.",
                        "static/rolex-submariner.avif",
                        9600, "reloj-pulsera");

                producto1.setUsuario(usuario1);
                producto2.setUsuario(usuario1);

                // Guardamos los usuarios y los productos en la base de datos
                session.save(usuario1);
                session.save(usuario2);
                session.save(producto1);
                session.save(producto2);
                session.save(producto3);
                session.save(producto4);

                transaction.commit();
            }
        } catch (Exception e) {
            System.out.println("Ha entrado en exception de init de la base de datos LoginServlet");
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
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
