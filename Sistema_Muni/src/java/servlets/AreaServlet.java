package servlets;

import dao.AreaDAO;
import modelo.Area;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/areas")
public class AreaServlet extends HttpServlet {

    private AreaDAO areaDAO = new AreaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // --- Control de sesión ---
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Area areaEditar = areaDAO.obtenerPorId(idEditar);
                request.setAttribute("area", areaEditar);
                request.getRequestDispatcher("editarArea.jsp").forward(request, response);
                break;

            case "listar":
            default:
                List<Area> lista = areaDAO.listar();
                request.setAttribute("areas", lista);
                request.getRequestDispatcher("listaAreas.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("actualizar".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            Area area = new Area(id, nombre);
            areaDAO.actualizar(area);
        }

        response.sendRedirect("areas");
    }
}
