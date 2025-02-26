<%-- 
    Document   : detalleProducto
    Created on : 26 feb 2025, 12:28:24
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List, com.dam.tiendarelojes.dto.ProductoDto" %>
<%
    // Verificar si hay sesión activa
    HttpSession sesion = request.getSession(false);
    String usuario = (sesion != null) ? (String) sesion.getAttribute("user") : null;

    if (usuario == null) {
        response.sendRedirect("index.jsp"); // Redirigir a la página de inicio si no hay sesión
        return;
    }

    // Obtener el producto de la request
    ProductoDto producto = (ProductoDto) request.getAttribute("producto");

    if (producto == null) {
        response.sendRedirect("PrincipalServlet"); // Si no hay producto, volver a la principal
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Detalle Producto</title>
        <!-- Bootstrap imports -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
        <!-- CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css">
    </head>
    <body>
        <!-- Navbar -->
        <header>
            <nav class="navbar navbar-expand-lg bg-body-tertiary" style="border-bottom: 1px solid black; margin-bottom: 20px">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Tienda de Relojes</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="PrincipalServlet">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="VerBoxServlet">Ver en Box</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="VerTablaServlet">Ver en Tablas</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="LogoutServlet">Logout</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>

        <!-- Main -->
        <main class="row" style="padding: 10px 70px">
            <!-- Boton volver pagina anterior -->
            <div class="mb-3">
                <a href="javascript:history.back()" class="btn btn-secondary">← Volver</a>
            </div>

            <!-- Detalle del Producto -->
            <div class="row mt-4">
                <!-- Imagen del producto -->
                <div class="col-md-6 text-center">
                    <img src="<%=producto.getRutaFoto()%>" 
                         alt="<%=producto.getNombre()%>" 
                         class="img-fluid rounded" 
                         style="max-width: 100%; height: auto;">
                </div>

                <!-- Información del producto -->
                <div class="col-md-6">
                    <h2><%=producto.getNombre()%></h2>
                    <p>
                        <strong>Tipo:</strong>
                        <%
                            switch (producto.getTipo()) {
                                case "reloj-pulsera":
                                    out.print("Reloj de pulsera");
                                    break;
                                case "reloj-digital":
                                    out.print("Reloj digital");
                                    break;
                                case "reloj-bolsillo":
                                    out.print("Reloj de bolsillo");
                                    break;
                            }
                        %>
                    </p>
                    <p><strong>Descripción:</strong> <%=producto.getDetalle()%></p>
                    <p><strong>Precio:</strong> <span class="text-success">$<%=producto.getPrecio()%></span></p>

                    <!-- Botón para comprar -->
                    <!--<a href="#" class="btn btn-primary">Comprar</a>-->
                </div>
            </div>
        </main>

        <!-- Footer -->
        <footer>
            <p>&copy; 2025 Tienda de Relojes</p>
        </footer>
    </body>
</html>
