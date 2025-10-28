<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, modelo.Nota" %>
<html>
<head>
    <title>Listado de Notas</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
    <h2>Listado de Notas</h2>
    <a href="crearArea.jsp" class="btn btn-gradiente">Agregar Nueva Nota</a>
    <br><br>

    <table border="1" cellpadding="5">
        <tr>
            <th>ID</th>
            <th>ID Persona</th>
            <th>Fecha Entrega</th>
            <th>Detalles</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        <%
            List<Nota> notas = (List<Nota>) request.getAttribute("notas");
            if (notas != null) {
                for (Nota n : notas) {
        %>
        <tr>
            <td><%= n.getID_Nota() %></td>
            <td><%= n.getID_Persona() %></td>
            <td><%= n.getFecha_Entrega() %></td>
            <td><%= n.getDetalles() %></td>
            <td><%= n.getEstado_Actual() %></td>
            <td>
                <a href="notas?action=editar&id=<%= n.getID_Nota() %>">Editar</a> |
                <a href="notas?action=eliminar&id=<%= n.getID_Nota() %>"
                   onclick="return confirm('¿Seguro que desea eliminar esta nota?')">Eliminar</a>
            </td>
        </tr>
        <%      }
            }
        %>
    </table>
</body>
</html>
