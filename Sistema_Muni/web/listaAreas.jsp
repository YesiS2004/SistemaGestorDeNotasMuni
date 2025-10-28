<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Áreas</title>
        <link rel="stylesheet" href="estilos.css">
    </head>
    <body>

        <div class="nav-btn-container">
            <a href="MenuPrincipal.jsp" class="btn btn-logout">⬅ Volver al Menú</a>
        </div>

        <div class="container">
            <h1>Gestión de Áreas</h1>
            <h2>Listado completo de todas las Áreas registradas</h2>
            
            <a href="crearArea.jsp" class="btn btn-gradiente">Crear Nueva Área</a>

            <c:choose>
                <c:when test="${not empty listaAreas}">
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
                            <c:forEach var="area" items="${listaAreas}">
                                <tr>
                                    <td>${area.ID_Area}</td> 
                                    <td>${area.Nombre}</td>
                                    <td>${area.Usuario_Area}</td>
                                    <td>
                                        <a href="SvArea?accion=editar&id=${area.ID_Area}" class="btn btn-gradiente" style="padding: 5px 10px; margin: 0;">Editar</a>
                                        
                                        <a href="SvArea?accion=eliminar&id=${area.ID_Area}" class="btn btn-logout" style="padding: 5px 10px; margin: 0;" onclick="return confirm('¿Seguro que desea eliminar esta Área?')">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:otherwise>
                    <p style="margin-top: 50px;">No hay áreas registradas en el sistema.</p>
                </c:otherwise>
            </c:choose>

        </div>
    </body>
</html>