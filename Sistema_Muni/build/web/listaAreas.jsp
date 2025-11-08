<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Area"%>
<%@page import="dao.AreaDAO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Listado de Áreas</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="nav-btn-container">
    <a href="menuPrincipal.jsp" class="btn btn-logout">⬅ Volver al Menú</a>
</div>

<div class="container">
    <h1>Gestión de Áreas</h1>
    <h2>Listado completo de todas las Áreas registradas</h2>

    <a href="areas?action=nuevo" class="btn btn-gradiente">Crear Nueva Área</a>

    <%
        AreaDAO areaDAO = new AreaDAO();
        List<Area> areas = areaDAO.listar();
    %>

    <%
        if (areas.isEmpty()) {
    %>
        <p style="margin-top:50px;">No hay áreas registradas en el sistema.</p>
    <%
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre del Área</th>
                    <th>Usuario Responsable (ID)</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Area a : areas) {
                %>
                <tr>
                    <td><%= a.getIdArea() %></td>
                    <td><%= a.getNombre() %></td>
                    <td><%= a.getUsuarioArea() %></td>
                    <td>
                        <a href="areas?action=editar&id=<%= a.getIdArea() %>" class="btn btn-gradiente" style="padding:5px 10px; margin:0;">Editar</a>
                        <a href="areas?action=eliminar&id=<%= a.getIdArea() %>" class="btn btn-logout" style="padding:5px 10px; margin:0;" onclick="return confirm('¿Seguro que desea eliminar esta Área?')">Eliminar</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    <%
        }
    %>

</div>
</body>
</html>
