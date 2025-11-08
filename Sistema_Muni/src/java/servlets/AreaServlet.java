package servlets;

import dao.AreaDAO;
import modelo.Area;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/areas")
public class AreaServlet extends HttpServlet {

    private final AreaDAO areaDAO = new AreaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "listar":
                    List<Area> listaAreas = areaDAO.listar();
                    request.setAttribute("areas", listaAreas);
                    request.getRequestDispatcher("listaAreas.jsp").forward(request, response);
                    break;

                case "nuevo":
                    request.getRequestDispatcher("crearArea.jsp").forward(request, response);
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
                    response.sendRedirect("areas?action=listar");
                    break;

                default:
                    response.sendRedirect("areas?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error en AreaServlet: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "listar";
        }

        try {
            switch (action) {
                case "agregar":
                    String nombre = request.getParameter("nombre");
                    int usuarioArea = Integer.parseInt(request.getParameter("usuarioArea"));
                    Area nuevaArea = new Area();
                    nuevaArea.setNombre(nombre);
                    nuevaArea.setUsuarioArea(usuarioArea);
                    areaDAO.agregar(nuevaArea);
                    response.sendRedirect("areas?action=listar");
                    break;

                case "actualizar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    String nombreAct = request.getParameter("nombre");
                    int usuarioAct = Integer.parseInt(request.getParameter("usuarioArea"));
                    Area areaAct = new Area();
                    areaAct.setIdArea(id);
                    areaAct.setNombre(nombreAct);
                    areaAct.setUsuarioArea(usuarioAct);
                    areaDAO.actualizar(areaAct);
                    response.sendRedirect("areas?action=listar");
                    break;

                default:
                    response.sendRedirect("areas?action=listar");
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error en AreaServlet POST: " + e.getMessage());
        }
    }
}
