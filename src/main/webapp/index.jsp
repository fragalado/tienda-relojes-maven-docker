<%-- 
    Document   : index
    Created on : 23 feb 2025, 20:08:01
    Author     : Alvaro Duarte
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setHeader("Expires", "0"); %>
<%
// Control de sesion
    HttpSession sesion = request.getSession(false);
    if (sesion.getAttribute("user") != null) {
        response.sendRedirect("PrincipalServlet");
        return;
    }

%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Login</title>        
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
    </head>
    <body>
        <header>
            <h1>Bienvenido a la Tienda de Relojes</h1>
            <p>Autor: Alvaro Duarte</p>
        </header>

        <main>
            <section id="login-section">
                <h2>Iniciar Sesión</h2>
                <form action="${pageContext.request.contextPath}/LoginServlet" method="post" onsubmit="return validarFormulario()">
                    <label for="id">ID de Usuario:</label>
                    <input type="text" id="id" name="dni" placeholder="Ej: 12345678A" required>
                    <span id="dni-error" class="error-msg"></span>

                    <label for="password">Contraseña:</label>
                    <input type="password" id="password" name="password" required>
                    <span id="password-error" class="error-msg"></span>

                    <!-- Mensaje Info -->
                    <%                        
                        if (request.getParameter("info") != null && request.getParameter("info").equals("logout")) {%>
                            <div class="alert alert-primary" role="alert">
                                ¡Se ha cerrado sesión correctamente!
                            </div><%
                                }
                    %>

                    <button type="submit">Ingresar</button>
                </form>
            </section>
        </main>

        <footer>
            <p>&copy; 2025 Tienda de Relojes</p>
        </footer>

        <!-- JS -->
        <script src="${pageContext.request.contextPath}/js/index.js"></script>

    </body>
</html>
