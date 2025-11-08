<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelo.Area" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Área</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="nav-btn-container">
    <a href="areas?action=listar" class="btn btn-logout">⬅ Volver al Listado</a>
</div>

<div class="container">
    <h1>Editar Área</h1>

    <%
        Area area = (Area) request.getAttribute("area");
        if (area == null) {
    %>
        <p>No se encontró el área seleccionada.</p>
    <%
        } else {
    %>

    <form action="areas" method="post">
        <!-- Action del servlet -->
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="<%= area.getIdArea() %>">

        <div class="form-group">
            <label>Nombre del Área:</label>
            <input type="text" name="nombre" value="<%= area.getNombre() %>" required>
        </div>

        <div class="form-group">
            <label>Usuario Responsable (ID):</label>
            <input type="number" name="usuarioArea" value="<%= area.getUsuarioArea() %>" required>
        </div>

        <button type="submit" class="btn btn-gradiente">Actualizar Área</button>
    </form>

    <%
        }
    %>
</div>

</body>
</html>
