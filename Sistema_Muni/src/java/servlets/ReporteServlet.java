package servlets;

import dao.ReporteDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

@WebServlet("/ReporteServlet")
public class ReporteServlet extends HttpServlet {

    private final ReporteDAO reporteDAO = new ReporteDAO();
    
    // Helper para obtener el nombre del mes
    private String getNombreMes(int numeroMes) {
        String[] nombres = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        if (numeroMes >= 1 && numeroMes <= 12) {
            return nombres[numeroMes - 1];
        }
        return "Desconocido";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        JSONObject responseData = new JSONObject();
        
        try {
            // --- 1. Obtener Datos Reales del DAO ---
            int totalNotas = reporteDAO.totalNotas();
            int totalDerivaciones = reporteDAO.totalDerivaciones();
            int totalNotasRecibidas = reporteDAO.totalNotasRecibidas();
            List<Map<String, Object>> notasPorEstado = reporteDAO.notasPorEstado();
            List<Map<String, Object>> notasMensuales = reporteDAO.notasEntregadasMensual();

            // --- 2. Mapear Métricas simples  ---
            responseData.put("clientesNuevos", totalNotas); 
            responseData.put("serviciosCompletados", totalDerivaciones); 
            responseData.put("notasRecibidas", totalNotasRecibidas); 

            // --- 3. Mapear datos para el Gráfico de torta  ---
            JSONArray cantidadesEstado = new JSONArray();
            JSONArray estados = new JSONArray();
            
            for (Map<String, Object> fila : notasPorEstado) {
                estados.put(fila.get("estado")); 
                cantidadesEstado.put(fila.get("cantidad")); 
            }

            responseData.put("tiposServicios", estados);
            responseData.put("distribucionServicios", cantidadesEstado);
            
            // 4. Mapear datos para el Gráfico de Barras ---
            JSONArray cantidadesMensual = new JSONArray();
            JSONArray meses = new JSONArray();
            
            for (Map<String, Object> fila : notasMensuales) {
                meses.put(getNombreMes((int)fila.get("mes"))); 
                cantidadesMensual.put(fila.get("cantidad"));
            }

            responseData.put("cantidadNotasMensual", cantidadesMensual);
            responseData.put("meses", meses);
            
            // --- 5. Enviar respuesta JSON ---
            response.getWriter().write(responseData.toString());
            
        } catch (Exception e) {
            System.err.println("Error al generar JSON de reportes: " + e.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(new JSONObject().put("error", "Error interno al procesar los reportes: " + e.getMessage()).toString());
        }
    }
}