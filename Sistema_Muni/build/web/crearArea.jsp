<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Nueva Área</title>
        <link rel="stylesheet" href="css/estilos.css">
    </head>
    <body>

        <div class="nav-btn-container">
            <a href="menuPrincipal.jsp" class="btn btn-menu-principal">MENÚ PRINCIPAL</a>
        </div>

        <div class="container">
            <h1>Crear nueva Área</h1>

            <div class="form-container">
                <form action="#" method="post">
                    <div class="form-group">
                        <label for="nombreArea">NOMBRE DEL ÁREA</label>
                        <input type="text" id="nombreArea" name="nombreArea">
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-gradiente">REGISTRAR</button>
                        <a href="menuPrincipal.jsp" class="btn btn-gradiente">CANCELAR</a>
                    </div>
                </form>
            </div>
        </div>

    </body>
</html>
