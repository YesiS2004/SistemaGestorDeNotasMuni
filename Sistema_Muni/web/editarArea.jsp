<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Area" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Área</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<%
    Area area = (Area) request.getAttribute("area");
%>

<div class="container">
    <h1>Editar Área</h1>

    <form action="areas" method="post">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="<%= area.getID_Area() %>">

        Nombre del Área:
        <input type="text" name="nombre" value="<%= area.getNombre() %>" required>

        Usuario Área (ID):
        <input type="number" name="usuarioArea" value="<%= area.getUsuario_Area() %>" required>

        <input type="submit" value="Actualizar">
        <a href="areas" class="btn btn-gradiente">Cancelar</a>
    </form>
</div>

</body>
</html>
