<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Agregar Área</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="container">
    <h1>Agregar Nueva Área</h1>

    <form action="areas" method="post">
        <input type="hidden" name="action" value="agregar">

        Nombre del Área:
        <input type="text" name="nombre" required>

        Usuario Área (ID):
        <input type="number" name="usuarioArea" required>

        <input type="submit" value="Guardar">
        <a href="areas" class="btn btn-gradiente">Cancelar</a>
    </form>
</div>

</body>
</html>
