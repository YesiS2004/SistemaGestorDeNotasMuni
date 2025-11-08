<%@page contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="modelo.Nota"%>
<%@page import="dao.NotaDAO"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Notas</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="nav-btn-container">
    <a href="<%=request.getContextPath()%>/menuPrincipal.jsp" class="btn btn-logout">⬅ Volver al Menú</a>
</div>

<div class="container">
    <h1>Gestión de Notas</h1>
    <h2>Listado completo de todas las Notas registradas</h2>
    
    <a href="notas?action=nuevo" class="btn btn-gradiente">Crear Nueva Nota</a>

    <%
        NotaDAO dao = new NotaDAO();
        List<Nota> lista = dao.listar();

        if(lista.isEmpty()) {
    %>
        <p style="margin-top: 50px;">No hay notas registradas en el sistema.</p>
    <%
        } else {
    %>
    
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>ID Persona</th>
                <th>Fecha Entrega</th>
                <th>Detalles</th>
                <th>Estado</th>
                <th>Archivo Adjunto</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
        <%
            for (Nota n : lista) {
        %>
        <tr>
            <td><%= n.getIdNota() %></td>
            <td><%= n.getIdPersona() %></td>
            <td><%= n.getFechaEntrega() %></td>
            <td><%= n.getDetalles() %></td>
            <td><%= n.getEstadoActual() %></td>
            
            <td>
                <%
                    if (n.getArchivoNota() != null) {
                %>
                    <a href="notas?action=descargar&id=<%=n.getIdNota()%>" class="btn btn-small btn-info text-dark">Descargar</a>
                <%
                    } else {
                %>
                    No
                <%
                    }
                %>
            </td>
            
            <td>
                <a href="notas?action=editar&id=<%=n.getIdNota()%>" class="btn btn-gradiente">Editar</a>
                <a href="notas?action=eliminar&id=<%=n.getIdNota()%>" class="btn btn-logout" onclick="return confirm('¿Seguro que desea eliminar esta Nota?')">Eliminar</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <%
        }
    %>
</div>
</body>
</html>