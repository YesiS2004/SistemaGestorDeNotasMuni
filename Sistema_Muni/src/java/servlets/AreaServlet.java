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
            case "nuevo":
                request.getRequestDispatcher("agregarArea.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Area areaEditar = areaDAO.buscarPorId(idEditar);
                request.setAttribute("area", areaEditar);
                request.getRequestDispatcher("editarArea.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                areaDAO.eliminar(idEliminar);
                response.sendRedirect("areas");
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

        switch (action) {
            case "agregar":
                String nombre = request.getParameter("nombre");
                int usuarioArea = Integer.parseInt(request.getParameter("usuarioArea"));

                Area nuevaArea = new Area();
                nuevaArea.setNombre(nombre);
                nuevaArea.setUsuario_Area(usuarioArea);

                areaDAO.agregar(nuevaArea);
                break;

            case "actualizar":
                int id = Integer.parseInt(request.getParameter("id"));
                String nombreEditado = request.getParameter("nombre");
                int usuarioEditado = Integer.parseInt(request.getParameter("usuarioArea"));

                Area areaActualizada = new Area(id, nombreEditado, usuarioEditado);
                areaDAO.actualizar(areaActualizada);
                break;
        }

        response.sendRedirect("areas");
    }
}
