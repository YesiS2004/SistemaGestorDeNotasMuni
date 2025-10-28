<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Nota</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="container">
    <h1>Agregar Nueva Nota</h1>

    <form action="notas" method="post" enctype="multipart/form-data">
        <input type="hidden" name="action" value="agregar">

        ID Persona:
        <input type="number" name="idPersona" required>

        Fecha de Entrega:
        <input type="date" name="fechaEntrega" required>

        Detalles:
        <textarea name="detalles"></textarea>

        Estado Actual:
        <input type="number" name="estadoActual" required>

        Archivo Nota:
        <input type="file" name="notaArchivo" required>

        <input type="submit" value="Guardar">
        <a href="notas" class="btn btn-gradiente">Cancelar</a>
    </form>
</div>

</body>
</html>
