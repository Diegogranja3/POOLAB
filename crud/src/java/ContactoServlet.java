package com.ejemplo.agenda.web;

import com.ejemplo.agenda.dao.ContactoDAO;
import com.ejemplo.agenda.model.Contacto;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/contactos")
public class ContactoServlet extends HttpServlet {
    
    private ContactoDAO contactoDAO;

    @Override
    public void init() throws ServletException {
        contactoDAO = new ContactoDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        try {
            if (action == null) {
                action = "listar";
            }
            
            switch (action) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;
                case "eliminar":
                    eliminarContacto(request, response);
                    break;
                default:
                    listarContactos(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        
        try {
            if ("guardar".equals(action)) {
                guardarContacto(request, response);
            } else if ("actualizar".equals(action)) {
                actualizarContacto(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Error en la base de datos", e);
        }
    }

    private void listarContactos(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Contacto> contactos = contactoDAO.listarTodos();
        request.setAttribute("contactos", contactos);
        request.getRequestDispatcher("/views/lista.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Contacto contacto = contactoDAO.obtenerPorId(id);
        request.setAttribute("contacto", contacto);
        request.getRequestDispatcher("/views/form.jsp").forward(request, response);
    }

    private void guardarContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Contacto contacto = construirContactoDesdeRequest(request);
        contactoDAO.insertar(contacto);
        response.sendRedirect(request.getContextPath() + "/contactos");
    }

    private void actualizarContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Contacto contacto = construirContactoDesdeRequest(request);
        contacto.setId(Integer.parseInt(request.getParameter("id")));
        contactoDAO.actualizar(contacto);
        response.sendRedirect(request.getContextPath() + "/contactos");
    }

    private void eliminarContacto(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        contactoDAO.eliminar(id);
        response.sendRedirect(request.getContextPath() + "/contactos");
    }

    private Contacto construirContactoDesdeRequest(HttpServletRequest request) {
        Contacto contacto = new Contacto();
        contacto.setNombre(request.getParameter("nombre"));
        contacto.setApellidoPaterno(request.getParameter("apellidoPaterno"));
        contacto.setApellidoMaterno(request.getParameter("apellidoMaterno"));
        contacto.setSexo(request.getParameter("sexo"));
        contacto.setTelefono(request.getParameter("telefono"));
        contacto.setDireccion(request.getParameter("direccion"));
        contacto.setTipoContacto(request.getParameter("tipoContacto"));
        return contacto;
    }
}