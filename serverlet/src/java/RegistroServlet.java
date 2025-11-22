/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Diego
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {
    
    // Configuración de la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/registros_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = ""; // XAMPP por defecto no tiene contraseña

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        
        boolean registroExitoso = false;
        String mensajeError = "";
        
        // Intentar guardar en la base de datos
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer conexión
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            // Preparar la consulta SQL
            String sql = "INSERT INTO usuarios (nombre, apellido, email, telefono) VALUES (?, ?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, apellido);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            
            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();
            
            if (filasAfectadas > 0) {
                registroExitoso = true;
            }
            
        } catch (ClassNotFoundException e) {
            mensajeError = "Error: Driver MySQL no encontrado. " + e.getMessage();
            e.printStackTrace();
        } catch (SQLException e) {
            mensajeError = "Error de base de datos: " + e.getMessage();
            e.printStackTrace();
        } finally {
            // Cerrar conexiones
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Generar la respuesta HTML
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Resultado del Registro</title>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); margin: 0; padding: 20px; min-height: 100vh; display: flex; justify-content: center; align-items: center; }");
            out.println(".container { background-color: white; padding: 40px; border-radius: 15px; box-shadow: 0 10px 30px rgba(0,0,0,0.3); max-width: 600px; width: 100%; }");
            out.println("h1 { text-align: center; margin-bottom: 30px; }");
            out.println(".exito { color: #4CAF50; }");
            out.println(".error { color: #f44336; }");
            out.println(".info-box { background-color: #f9f9f9; padding: 20px; border-radius: 8px; margin: 20px 0; }");
            out.println(".dato { margin: 12px 0; padding: 12px; background-color: white; border-left: 4px solid #667eea; border-radius: 4px; }");
            out.println(".label { font-weight: bold; color: #333; display: inline-block; min-width: 100px; }");
            out.println(".value { color: #555; }");
            out.println(".mensaje-error { background-color: #ffebee; border-left: 4px solid #f44336; padding: 15px; margin: 15px 0; border-radius: 4px; }");
            out.println(".mensaje-exito { background-color: #e8f5e9; border-left: 4px solid #4CAF50; padding: 15px; margin: 15px 0; border-radius: 4px; text-align: center; }");
            out.println(".btn-volver { display: inline-block; margin-top: 20px; padding: 12px 30px; background-color: #667eea; color: white; text-decoration: none; border-radius: 25px; text-align: center; transition: all 0.3s; }");
            out.println(".btn-volver:hover { background-color: #764ba2; transform: translateY(-2px); box-shadow: 0 5px 15px rgba(0,0,0,0.2); }");
            out.println(".center { text-align: center; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='container'>");
            
            if (registroExitoso) {
                out.println("<h1 class='exito'>✓ Registro Exitoso</h1>");
                out.println("<div class='mensaje-exito'>");
                out.println("<strong>¡Los datos han sido guardados correctamente en la base de datos MySQL!</strong>");
                out.println("</div>");
                
                out.println("<div class='info-box'>");
                out.println("<h3>Datos Registrados:</h3>");
                
                out.println("<div class='dato'>");
                out.println("<span class='label'>Nombre:</span>");
                out.println("<span class='value'>" + nombre + "</span>");
                out.println("</div>");
                
                out.println("<div class='dato'>");
                out.println("<span class='label'>Apellido:</span>");
                out.println("<span class='value'>" + apellido + "</span>");
                out.println("</div>");
                
                out.println("<div class='dato'>");
                out.println("<span class='label'>Email:</span>");
                out.println("<span class='value'>" + email + "</span>");
                out.println("</div>");
                
                out.println("<div class='dato'>");
                out.println("<span class='label'>Teléfono:</span>");
                out.println("<span class='value'>" + telefono + "</span>");
                out.println("</div>");
                
                out.println("</div>");
                
            } else {
                out.println("<h1 class='error'>✗ Error en el Registro</h1>");
                out.println("<div class='mensaje-error'>");
                out.println("<strong>No se pudo guardar el registro en la base de datos.</strong><br><br>");
                out.println("<strong>Detalles del error:</strong><br>");
                out.println(mensajeError);
                out.println("</div>");
                out.println("<p><strong>Verifica:</strong></p>");
                out.println("<ul>");
                out.println("<li>Que MySQL esté corriendo en XAMPP</li>");
                out.println("<li>Que la base de datos 'registros_db' exista</li>");
                out.println("<li>Que el conector MySQL (JAR) esté agregado al proyecto</li>");
                out.println("</ul>");
            }
            
            out.println("<div class='center'>");
            out.println("<a href='index.html' class='btn-volver'>← Volver al Formulario</a>");
            out.println("</div>");
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet para procesar y guardar datos de registro en MySQL";
    }
}