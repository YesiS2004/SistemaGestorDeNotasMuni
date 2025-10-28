package servlets;

import dao.NotaDAO;
import modelo.Nota;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.util.List;

@WebServlet("/notas")
@MultipartConfig(maxFileSize = 16177215) // Para subir archivos grandes
public class NotaServlet extends HttpServlet {

    private NotaDAO notaDAO = new NotaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                request.getRequestDispatcher("agregarNota.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Nota notaEditar = notaDAO.buscarPorId(idEditar);
                request.setAttribute("nota", notaEditar);
                request.getRequestDispatcher("editarNota.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                notaDAO.eliminar(idEliminar);
                response.sendRedirect("notas");
                break;

            case "listar":
            default:
                List<Nota> lista = notaDAO.listar();
                request.setAttribute("notas", lista);
                request.getRequestDispatcher("listaNotas.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        Date fechaEntrega = Date.valueOf(request.getParameter("fechaEntrega"));
        String detalles = request.getParameter("detalles");
        int estadoActual = Integer.parseInt(request.getParameter("estadoActual"));

        // Lectura del archivo (compatible con Java 8)
        Part archivo = request.getPart("notaArchivo");
        InputStream inputStream = archivo.getInputStream();
        byte[] notaBytes = null;
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            notaBytes = buffer.toByteArray();
        }

        switch (action) {
            case "agregar":
                Nota nuevaNota = new Nota();
                nuevaNota.setID_Persona(idPersona);
                nuevaNota.setFecha_Entrega(fechaEntrega);
                nuevaNota.setDetalles(detalles);
                nuevaNota.setEstado_Actual(estadoActual);
                nuevaNota.setNota(notaBytes);
                notaDAO.agregar(nuevaNota);
                break;

            case "actualizar":
                int idNota = Integer.parseInt(request.getParameter("id"));
                Nota notaActualizada = new Nota(idNota, idPersona, fechaEntrega, detalles, estadoActual, notaBytes);
                notaDAO.actualizar(notaActualizada);
                break;
        }

        response.sendRedirect("notas");
    }
}
