package servlets;

import dao.NotaDAO;
import modelo.Nota;
import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/notas")
@MultipartConfig(maxFileSize = 16177215) 
public class NotaServlet extends HttpServlet {

    private final NotaDAO notaDAO = new NotaDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                List<Nota> lista = notaDAO.listar();
                request.setAttribute("notas", lista);
                request.getRequestDispatcher("listaNotas.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Nota nota = notaDAO.buscarPorId(idEditar);
                request.setAttribute("nota", nota);
                request.getRequestDispatcher("editarNota.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                notaDAO.eliminar(idEliminar);
                response.sendRedirect("notas?action=listar");
                break;

            case "nuevo":
                request.getRequestDispatcher("agregarNota.jsp").forward(request, response);
                break;
                
            case "descargar": 
                int idDescargar = Integer.parseInt(request.getParameter("id"));
                Nota notaDescarga = notaDAO.buscarPorId(idDescargar);
                
                if (notaDescarga != null && notaDescarga.getArchivoNota() != null) {
                    byte[] archivoBytes = notaDescarga.getArchivoNota();
                    
                    response.setContentType("application/octet-stream"); 
                    
                    response.setHeader("Content-Disposition", "attachment; filename=\"nota_" + idDescargar + "_adjunto.bin\""); 
                    response.setContentLength(archivoBytes.length);

                    response.getOutputStream().write(archivoBytes);
                    response.getOutputStream().flush();
                } else {
                    response.setStatus(HttpServletResponse.SC_NOT_FOUND); 
                    response.getWriter().println("El archivo no existe o no pudo ser encontrado para la nota ID: " + idDescargar);
                }
                break;


            default:
                response.sendRedirect("notas?action=listar");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "agregar":
                procesarFormulario(request, response, false);
                break;

            case "editar": 
                procesarFormulario(request, response, true);
                break;

            default:
                response.sendRedirect("notas?action=listar");
        }
    }

    private void procesarFormulario(HttpServletRequest request, HttpServletResponse response, boolean esEdicion)
            throws IOException, ServletException {

        // Parámetros básicos
        int idPersona = Integer.parseInt(request.getParameter("idPersona"));
        Date fechaEntrega = Date.valueOf(request.getParameter("fechaEntrega"));
        String detalles = request.getParameter("detalles");
        int estadoActual = Integer.parseInt(request.getParameter("estadoActual"));

        // Leer archivo
        Part filePart = request.getPart("notaFile");
        byte[] archivoBytes = null;

        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream inputStream = filePart.getInputStream();
                 ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

                byte[] tmp = new byte[4096];
                int nRead;
                while ((nRead = inputStream.read(tmp)) != -1) {
                    buffer.write(tmp, 0, nRead);
                }
                archivoBytes = buffer.toByteArray();
            } catch (IOException ex) {
                 request.getSession().setAttribute("errorMsg", "Error leyendo el archivo: " + ex.getMessage());
                 response.sendRedirect("notas?action=listar");
                 return;
            }
        }

        Nota n = new Nota();
        n.setIdPersona(idPersona);
        n.setFechaEntrega(fechaEntrega);
        n.setDetalles(detalles);
        n.setEstadoActual(estadoActual);

        if (esEdicion) {
            // Si es edición, preservamos el archivo anterior cuando no se sube uno nuevo
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Si el usuario SUBIÓ un nuevo archivo
            if (archivoBytes != null) {
                n.setArchivoNota(archivoBytes);
            } else {
                // Si NO SUBIÓ un nuevo archivo, buscamos el archivo existente
                Nota existente = notaDAO.buscarPorId(id); 
                n.setArchivoNota(existente != null ? existente.getArchivoNota() : null);
            }
            n.setIdNota(id);
            notaDAO.actualizar(n);
        } else {
            // Si es nueva, puede venir sin archivo 
            n.setArchivoNota(archivoBytes);
            notaDAO.agregar(n);
        }

        response.sendRedirect("notas?action=listar");
    }
}