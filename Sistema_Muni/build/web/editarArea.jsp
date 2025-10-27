<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="modelo.Area" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Área</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f8ff;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 40%;
            margin: 60px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 25px;
            text-align: center;
        }
        h2 {
            color: #4b7bec;
            margin-bottom: 25px;
        }
        label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
        }
        input[type="text"] {
            width: 80%;
            padding: 8px;
            border-radius: 5px;
            border: 1px solid #ccc;
        }
        .botones {
            margin-top: 25px;
        }
        .btn {
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 6px;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }
        .guardar {
            background-color: #4cd137;
        }
        .cancelar {
            background-color: #e84118;
        }
        .volver {
            display: inline-block;
            background-color: #6c63ff;
            margin-top: 15px;
        }
    </style>
</head>
<body>

<%
    Area area = (Area) request.getAttribute("area");
    if (area == null) {
%>
    <div class="container">
        <h2>No se encontró el área seleccionada</h2>
        <a href="areas" class="btn volver">Volver a la lista</a>
    </div>
<%
    } else {
%>

<div class="container">
    <h2>Editar Área</h2>
    <form action="areas" method="post">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="<%= area.getId() %>">

        <label for="nombre">Nombre del Área</label>
        <input type="text" id="nombre" name="nombre" value="<%= area.getNombre() %>" required>

        <div class="botones">
            <button type="submit" class="btn guardar">Guardar Cambios</button>
            <a href="areas" class="btn cancelar">Cancelar</a>
        </div>
    </form>
</div>

<% } %>

</body>
</html>
