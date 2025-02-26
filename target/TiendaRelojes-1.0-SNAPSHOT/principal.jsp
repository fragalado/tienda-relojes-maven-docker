<%-- 
    Document   : principal
    Created on : 24 feb 2025, 12:02:41
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

    // Obtenemos la lista de productos
    List<ProductoDto> productos = (List<ProductoDto>) request.getAttribute("productos");
    
    // Obtenemos el total de productos comprados
    int totalProductos = Integer.parseInt(request.getAttribute("totalProductos").toString());
    // Obtenemos el precio total de los productos comprados
    float totalPrecioProductos = Float.parseFloat(request.getAttribute("totalPrecioProductos").toString());
    
%>
<!DOCTYPE html>
<html lang="es">    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Principal</title>
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
        <div class="row" style="padding: 10px 70px">
            <div class="col-12">
                <!-- Boton filtrar relojes -->
                
                
                <!-- Boton campo calculado -->
                <button type="button" class="btn btn-primary m-2" data-bs-toggle="modal" data-bs-target="#mostrarInformacion">
                    Información perfil
                </button>

                <!-- Boton agregar producto -->
                <button type="button" class="btn btn-primary m-2" style="float: right" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                    Agregar producto
                </button>

                <!-- Modal para mostrar informacion perfil -->
                <div class="modal fade" id="mostrarInformacion" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="mostrarInformacion" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="mostrarInformacion">Información perfil</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <!-- Total productos comprados -->
                                <h4 class="text-center">Total Productos Comprados:</h4>
                                <p class="text-center" style="font-size: 1.3em"><%=totalProductos %></p>
                                <!-- Total precio productos comprados -->
                                <h4 class="text-center">Total Precio Productos Comprados:</h4>
                                <p class="text-center" style="font-size: 1.3em"><%=totalPrecioProductos %>€</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Termina modal mostrar informacion perfil -->

                <!-- Modal para agregar producto -->
                <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="staticBackdropLabel">Agregar producto</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <!-- Formulario -->
                            <form action="ProductoServlet" method="post" enctype="multipart/form-data">
                                <div class="modal-body">
                                    <!-- Nombre -->
                                    <div class="mb-3">
                                        <label for="nombre" class="form-label">Nombre:</label>
                                        <input type="text" name="nombre" class="form-control border border-black" id="nombre" placeholder="Ej: Rolex Submariner" required>
                                    </div>
                                    <!-- Detalle -->
                                    <div class="mb-3">
                                        <label for="detalle" class="form-label">Detalle:</label>
                                        <input type="text" name="detalle" class="form-control border border-black" id="detalle" placeholder="Ej: El reloj Submariner es uno de los primeros..." required>
                                    </div>
                                    <!-- Precio -->
                                    <div class="mb-3">
                                        <label for="precio" class="form-label">Precio:</label>
                                        <input type="number" name="precio" class="form-control border border-black" id="precio" placeholder="Ej: 10.0" required>
                                    </div>
                                    <!-- Tipo -->
                                    <div class="mb-3">
                                        <label for="tipo" class="form-label">Tipo:</label>
                                        <select class="form-select border border-black" name="tipo" id="tipo" required>
                                            <option selected>-- Seleciona un tipo --</option>
                                            <option value="reloj-digital">Reloj digital</option>
                                            <option value="reloj-pulsera">Reloj de pulsera</option>
                                            <option value="reloj-bolsillo">Reloj de bolsillo</option>
                                        </select>
                                    </div>
                                    <!-- Foto -->
                                    <div class="mb-3">
                                        <label for="formFile" class="form-label">Foto:</label>
                                        <input class="form-control border border-black" name="foto" type="file" id="formFile" required>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    <button type="submit" class="btn btn-primary">Enviar</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- Termina modal agregar producto -->

            </div>

            <div class="col-12">
                <!-- Mostramos productos -->
                <div class="row">
                    <%
                        if (productos != null) {
                            for (ProductoDto producto : productos) {
                    %><div class="col-xl-3 col-lg-4 col-md-6 col-12 mt-3"><div class="card" style="width: 18rem;">
                            <img class="card-img-top" style="width: 100%; height: 350px; object-fit: cover;" src="data:image/jpeg;base64,<%=producto.getFotoBase64()%>" alt="Imagen de <%=producto.getNombre()%>"/>
                            <div class="card-body">
                                <h5 class="card-title text-center"><%=producto.getNombre()%></h5>
                                <p class="card-text">
                                    <%
                                        String detalle = producto.getDetalle();
                                        if (detalle.length() > 150) {
                                            out.print(detalle.substring(0, 150) + "...");
                                        } else {
                                            out.print(detalle);
                                        }
                                    %>
                                </p>
                                <div class="text-center">
                                    <a href="<%="ProductoServlet?id=" + producto.getId() %>" class="btn btn-secondary">Ver detalle</a>
                                </div>

                            </div>
                        </div></div><%
                            }
                        } else {
                        %> <div class="col-12"><p style="text-align: center; margin-top: 20px">No existe ningún producto</p></div> <%
                            }
                    %>
                </div>
            </div>
        </div>


        <!-- Footer -->
        <footer>
            <p>&copy; 2025 Tienda de Relojes</p>
        </footer>
    </body>
</html>
