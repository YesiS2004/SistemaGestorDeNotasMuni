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

@WebServlet("/Reportes") 
public class ReporteController extends HttpServlet {

    private final ReporteDAO reporteDAO = new ReporteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            // --- 1. Obtener Datos ---
            
            // 1. Notas por Área
            List<Map<String, Object>> notasPorArea = reporteDAO.notasPorArea();
            request.setAttribute("notasPorArea", notasPorArea);
            
            // 2. Notas por Secretaría
            List<Map<String, Object>> notasPorSecretaria = reporteDAO.notasPorSecretaria();
            request.setAttribute("notasPorSecretaria", notasPorSecretaria);
            
            // --- Redirigir a la vista JSP ---
            request.getRequestDispatcher("reportes.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.err.println("Error en ReporteController: " + e.getMessage());
            request.setAttribute("error", "Error al cargar las tablas de reportes.");
            request.getRequestDispatcher("menuPrincipal.jsp").forward(request, response);
        }
    }
}