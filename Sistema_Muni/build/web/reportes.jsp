<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panel de Reportes</title>
    <link rel="stylesheet" href="estilos.css"> 
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        /* (Mantén tus estilos CSS aquí) */
        .report-container { display: flex; justify-content: space-around; margin: 30px 0; gap: 20px; flex-wrap: wrap; }
        .report-card { 
            background: #e3f2fd; border-left: 5px solid #1e88e5; padding: 20px; 
            border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.1); flex-basis: calc(33% - 20px); 
            min-width: 200px; text-align: center;
        }
        .report-card h3 { color: #1e88e5; margin-top: 0; }
        .report-value { font-size: 2.5em; font-weight: bold; color: #333; }
        .chart-section { display: flex; flex-wrap: wrap; justify-content: center; gap: 40px; margin: 50px 0; }
        .chart-box { max-width: 450px; width: 100%; padding: 20px; background: #fff; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.05); }
        .table-section { margin-top: 50px; }
        .table-section h3 { border-bottom: 2px solid #ddd; padding-bottom: 10px; margin-bottom: 20px; }
        .report-table { width: 100%; border-collapse: collapse; margin-bottom: 30px; }
        .report-table th, .report-table td { border: 1px solid #ddd; padding: 12px; text-align: left; }
        .report-table th { background-color: #f2f2f2; color: #333; }
        
        @media (max-width: 768px) {
            .report-card { flex-basis: 100%; }
            .chart-box { max-width: 100%; }
        }
    </style>
</head>
<body>
    <div class="nav-btn-container">
        <a href="<%=request.getContextPath()%>/menuPrincipal.jsp" class="btn btn-logout">⬅ Volver al Menú</a>
    </div>

    <div class="container">
        <h1>Panel de Reportes</h1>
        <h2>Estadísticas del Sistema Municipal</h2>

        <div class="report-container" id="indicadores">
            <div class="report-card">
                <h3>Notas Registradas (Clientes Nuevos)</h3>
                <div class="report-value" id="clientesNuevos">...</div>
            </div>
            <div class="report-card">
                <h3>Derivaciones Realizadas (Trámites Completados)</h3>
                <div class="report-value" id="serviciosCompletados">...</div>
            </div>
            <div class="report-card">
                <h3>Notas Recibidas (Mesa de Entrada)</h3>
                <div class="report-value" id="notasRecibidas">...</div>
            </div>
        </div>

        <div class="chart-section">
            <div class="chart-box">
                <canvas id="ventasChart"></canvas>
            </div>
            <div class="chart-box">
                <canvas id="serviciosChart"></canvas>
            </div>
        </div>
        
        <div class="table-section">
            <h3>Notas por Área de Derivación</h3>
            <table class="report-table">
                <thead>
                    <tr><th>Área</th><th>Cantidad de Notas Derivadas</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${notasPorArea}">
                        <tr>
                            <td>${item.area}</td> 
                            <td>${item.cantidad}</td> 
                        </tr>
                    </c:forEach>
                    <c:if test="${empty notasPorArea}"><tr><td colspan="2">No hay datos de derivación por área.</td></tr></c:if>
                </tbody>
            </table>

            <h3>Notas por Secretaría</h3>
            <table class="report-table">
                <thead>
                    <tr><th>ID Secretaría</th><th>Cantidad de Notas Derivadas</th></tr>
                </thead>
                <tbody>
                    <c:forEach var="item" items="${notasPorSecretaria}">
                        <tr>
                            <td>${item.secretaria}</td> 
                            <td>${item.cantidad}</td> 
                        </tr>
                    </c:forEach>
                    <c:if test="${empty notasPorSecretaria}"><tr><td colspan="2">No hay datos de derivación por secretaría.</td></tr></c:if>
                </tbody>
            </table>
        </div>
    </div>

    <script>
    // El JS llama al Servlet de datos JSON
    fetch("ReporteServlet")
        .then(response => {
            if (!response.ok) {
                throw new Error('Error de red: No se pudo conectar al ReporteServlet.');
            }
            return response.json();
        })
        .then(data => {
            // === 1. Indicadores ===
            document.getElementById("clientesNuevos").innerText = data.clientesNuevos;
            document.getElementById("serviciosCompletados").innerText = data.serviciosCompletados;
            document.getElementById("notasRecibidas").innerText = data.notasRecibidas;

            // --- 2. GRÁFICO 1: Conteo Mensual de Notas (REAL) ---
            const ctx1 = document.getElementById("ventasChart");
            new Chart(ctx1, {
                type: "bar",
                data: {
                    labels: data.meses,
                    datasets: [{
                        label: "Notas Entregadas por Mes",
                        data: data.cantidadNotasMensual,
                        backgroundColor: "#1e88e5"
                    }]
                },
                options: {
                    responsive: true,
                    animation: { duration: 1200, easing: "easeOutBounce" },
                    plugins: { 
                        title: { 
                            display: true, 
                            text: 'Conteo de Notas Entregadas por Mes (Año Actual)' 
                        } 
                    },
                    scales: {
                        y: { beginAtZero: true, ticks: { precision: 0 } },
                        x: { ticks: { color: "#333" } }
                    }
                }
            });

            // --- 3. GRÁFICO 2: Distribución de notas por Estado (REAL) ---
            const ctx2 = document.getElementById("serviciosChart");
            new Chart(ctx2, {
                type: "pie",
                data: {
                    labels: data.tiposServicios,
                    datasets: [{
                        label: "Notas por Estado",
                        data: data.distribucionServicios,
                        backgroundColor: [
                            "#42a5f5", "#66bb6a", "#ffa726", "#ab47bc", "#ef5350", "#26a69a"
                        ],
                        hoverOffset: 4
                    }]
                },
                options: {
                    responsive: true,
                    animation: { duration: 1000, easing: "easeOutCubic" },
                    plugins: { title: { display: true, text: 'Distribución de Notas por Estado' } }
                }
            });
        })
        .catch(error => {
            console.error("Error al cargar los datos de ReporteServlet:", error);
            // Mensaje de error visible si falla el fetch
            document.getElementById("indicadores").innerHTML = '<p style="color: red; text-align: center;">Error al cargar los reportes. Verifique la conexión a la base de datos.</p>';
        });
    </script>
</body>
</html>