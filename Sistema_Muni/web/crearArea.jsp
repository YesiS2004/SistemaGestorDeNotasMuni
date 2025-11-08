<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Crear Nueva Área</title>
    <link rel="stylesheet" href="estilos.css">
</head>
<body>

<div class="nav-btn-container">
    <a href="menuPrincipal.jsp" class="btn btn-logout">⬅ Volver al Menú</a>
</div>

<div class="container">
    <h1>Crear Nueva Área</h1>

    <form action="areas" method="post">
        <!-- Action del servlet -->
        <input type="hidden" name="action" value="agregar">

        <div class="form-group">
            <label>Nombre del Área:</label>
            <input type="text" name="nombre" required>
        </div>

        <div class="form-group">
            <label>Usuario Responsable (ID):</label>
            <input type="number" name="usuarioArea" required>
        </div>

        <button type="submit" class="btn btn-gradiente">Guardar Área</button>
    </form>
</div>

</body>
</html>
