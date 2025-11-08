<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="modelo.Nota" %>
<%
    // Asegúrate de que el objeto 'nota' se haya pasado desde el Servlet
    Nota nota = (Nota) request.getAttribute("nota");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Nota</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="nav-btn-container">
    <a href="<%=request.getContextPath()%>/notas" class="btn btn-logout">⬅ Volver al Listado</a>
</div>

<div class="container">
    <h1>Editar Nota</h1>

    <div class="form-container">
        <form action="<%=request.getContextPath()%>/notas" method="post" enctype="multipart/form-data">
            
            <input type="hidden" name="action" value="editar">
            <input type="hidden" name="id" value="<%= nota.getIdNota() %>">

            <div class="form-group">
                <label for="idPersona">ID Persona:</label>
                <input type="number" id="idPersona" name="idPersona" value="<%= nota.getIdPersona() %>" required>
            </div>
            
            <div class="form-group">
                <label for="fechaEntrega">Fecha Entrega:</label>
                <input type="date" id="fechaEntrega" name="fechaEntrega" value="<%= nota.getFechaEntrega() %>" required>
            </div>
            
            <div class="form-group">
                <label for="detalles">Detalles:</label>
                <textarea id="detalles" name="detalles" rows="4" required><%= nota.getDetalles() %></textarea>
            </div>
            
            <div class="form-group">
                <label for="estadoActual">Estado:</label>
                <input type="text" id="estadoActual" name="estadoActual" value="<%= nota.getEstadoActual() %>" required>
            </div>
            
            <div class="form-group">
                <label for="notaFile">Archivo Nota (Opcional):</label>
                <input type="file" name="notaFile" id="notaFile">
                <small class="hint-text">Selecciona un archivo si deseas reemplazar el adjunto actual.</small>
            </div>
            <button type="submit" class="btn btn-gradiente btn-block">Guardar Cambios</button>
        </form>
    </div>
</div>
</body>
</html>