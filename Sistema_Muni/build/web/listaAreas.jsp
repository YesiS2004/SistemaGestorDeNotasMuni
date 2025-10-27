<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="modelo.Area" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Áreas</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f5f8ff;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: 40px auto;
            background: white;
            border-radius: 10px;
            box-shadow: 0px 2px 10px rgba(0,0,0,0.1);
            padding: 20px;
        }
        h2 {
            text-align: center;
            color: #4b7bec;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }
        th, td {
            text-align: center;
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #4b7bec;
            color: white;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a.btn {
            text-decoration: none;
            padding: 6px 12px;
            border-radius: 5px;
            color: white;
            font-size: 14px;
        }
        .btn-editar {
            background-color: #4cd137;
        }
        .btn-eliminar {
            background-color: #e84118;
        }
        .btn-agregar {
            display: inline-block;
            background-color: #0097e6;
            margin-bottom: 15px;
        }
        .top-menu {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .top-menu a {
            background-color: #6c63ff;
            color: white;
            text-decoration: none;
            padding: 8px 15px;
            border-radius: 6px;
        }
    </style>
</head>
<body>

<div class="container">
    <div class="top-menu">
        <a href="menu.jsp">Menú Principal</a>
        <h2>Lista de Áreas</h2>
        <a href="registrarArea.jsp" class="btn-agregar">+ Nueva Área</a>
    </div>

    <table>
        <tr>
            <th>Número</th>
            <th>Nombre del Área</th>
            <th>Acciones</th>
        </tr>
        <%
            List<Area> lista = (List<Area>) request.getAttribute("areas");
            if (lista != null && !lista.isEmpty()) {
                for (Area area : lista) {
        %>
        <tr>
            <td><%= area.getId() %></td>
            <td><%= area.getNombre() %></td>
            <td>
                <a href="areas?action=editar&id=<%= area.getId() %>" class="btn btn-editar">Editar</a>
                <a href="areas?action=eliminar&id=<%= area.getId() %>" 
                   class="btn btn-eliminar"
                   onclick="return confirm('¿Seguro que deseas eliminar esta área?');">Eliminar</a>
            </td>
        </tr>
        <% 
                }
            } else { 
        %>
        <tr>
            <td colspan="3">No hay áreas registradas.</td>
        </tr>
        <% } %>
    </table>
</div>

</body>
</html>
