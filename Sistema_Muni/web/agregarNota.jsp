<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Nota</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>
<div class="nav-btn-container">
    <a href="listaNotas.jsp" class="btn btn-logout">⬅ Volver al Listado de Notas</a>
</div>

<div class="container">
    <h1>Agregar Nueva Nota</h1>
    <form action="notas" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="agregar">

        <label for="idPersona">ID Persona:</label>
        <input type="number" name="idPersona" id="idPersona" required><br><br>

        <label for="fechaEntrega">Fecha de Entrega:</label>
        <input type="date" name="fechaEntrega" id="fechaEntrega" required><br><br>

        <label for="detalles">Detalles:</label>
        <textarea name="detalles" id="detalles" rows="4"></textarea><br><br>

        <label for="estadoActual">Estado Actual:</label>
        <input type="number" name="estadoActual" id="estadoActual" required><br><br>

        <label for="notaFile">Archivo Nota:</label>
        <input type="file" name="notaFile" id="notaFile"><br><br>

        <button type="submit" class="btn btn-gradiente">Agregar Nota</button>
    </form>
</div>
</body>
</html>
