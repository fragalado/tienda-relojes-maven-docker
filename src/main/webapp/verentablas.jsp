<%-- 
    Document   : verentablas
    Created on : 25 feb 2025, 13:05:45
    Author     : ruben
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="java.util.List, com.dam.tiendarelojes.dto.ProductoDto" %>
<% response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); %>
<% response.setHeader("Pragma", "no-cache"); %>
<% response.setHeader("Expires", "0"); %>
<%
    // Verificar si hay sesión activa
    HttpSession sesion = request.getSession(false);
    String usuario = (sesion != null) ? (String) sesion.getAttribute("user") : null;

    if (usuario == null) {
        response.sendRedirect("index.jsp"); // Redirigir a la página de inicio si no hay sesión
        return;
    }

    // Obtenemos la lista de productos
    List<ProductoDto> productos = (List<ProductoDto>) request.getAttribute("productos");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Ver en Tablas</title>
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
            <!-- Titulo -->
            <div class="col-12">
                <h1 class="text-center mb-4">Relojes Comprados</h1>
            </div>

            <!-- Tabla -->
            <div class="col-12 table-responsive">
                <table class="table table-dark table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Detalle</th>
                            <th scope="col">Precio</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">#</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">

                        <%
                            if (productos != null && !productos.isEmpty()) {
                                int contador = 1;
                                for (ProductoDto producto : productos) {
                        %>
                        <tr>
                            <th scope="row"><%=contador %></th>
                            <td><%=producto.getNombre()%></td>
                            <td>
                                <%
                                    String detalle = producto.getDetalle();
                                    if (detalle.length() > 150) {
                                        out.print(detalle.substring(0, 150) + "...");
                                    } else {
                                        out.print(detalle);
                                    }
                                %>
                            </td>
                            <td><%=producto.getPrecio()%>€</td>
                            <td>
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
                            </td>
                            <td>
                                <a class="btn btn-primary" href="<%="ProductoServlet?id=" + producto.getId() %>">Ver detalle</a>
                            </td>
                        </tr><% contador++;
                            }
                        } else {
                        %><tr>
                            <td class="text-center" colspan="6">No hay relojes comprados.</td>
                        </tr> <%
                            }%>
                    </tbody>
                </table>
            </div>
        </main>

        <!-- Footer -->
        <footer>
            <p>&copy; 2025 Tienda de Relojes</p>
        </footer>
    </body>
</html>
