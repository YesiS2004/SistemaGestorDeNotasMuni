<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.Nota" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Nota</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<%
    Nota nota = (Nota) request.getAttribute("nota");
%>

<div class="container">
    <h1>Editar Nota</h1>

    <form action="notas" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="actualizar">
        <input type="hidden" name="id" value="<%= nota.getID_Nota() %>">

        ID Persona:
        <input type="number" name="idPersona" value="<%= nota.getID_Persona() %>" required>

        Fecha de Entrega:
        <input type="date" name="fechaEntrega" value="<%= nota.getFecha_Entrega() %>" required>

        Detalles:
        <textarea name="detalles"><%= nota.getDetalles() %></textarea>

        Estado Actual:
        <input type="number" name="estadoActual" value="<%= nota.getEstado_Actual() %>" required>

        Nueva Nota (Archivo):
        <input type="file" name="notaArchivo">

        <input type="submit" value="Actualizar">
        <a href="notas" class="btn btn-gradiente">Cancelar</a>
    </form>
</div>

</body>
</html>
