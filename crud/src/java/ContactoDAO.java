package com.ejemplo.agenda.dao;

import com.ejemplo.agenda.model.Contacto;
import com.ejemplo.agenda.util.DB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDAO {

    // Listar todos los contactos
    public List<Contacto> listarTodos() throws SQLException {
        List<Contacto> contactos = new ArrayList<>();
        String sql = "SELECT * FROM contactos ORDER BY nombre, apellido_paterno";
        
        try (Connection conn = DB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                contactos.add(mapearContacto(rs));
            }
        }
        return contactos;
    }

    // Obtener contacto por ID
    public Contacto obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM contactos WHERE id = ?";
        
        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return mapearContacto(rs);
                }
            }
        }
        return null;
    }

    // Insertar nuevo contacto
    public boolean insertar(Contacto contacto) throws SQLException {
        String sql = "INSERT INTO contactos (nombre, apellido_paterno, apellido_materno, " +
                     "sexo, telefono, direccion, tipo_contacto) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            setParametrosContacto(pstmt, contacto);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Actualizar contacto existente
    public boolean actualizar(Contacto contacto) throws SQLException {
        String sql = "UPDATE contactos SET nombre=?, apellido_paterno=?, apellido_materno=?, " +
                     "sexo=?, telefono=?, direccion=?, tipo_contacto=? WHERE id=?";
        
        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            setParametrosContacto(pstmt, contacto);
            pstmt.setInt(8, contacto.getId());
            return pstmt.executeUpdate() > 0;
        }
    }

    // Eliminar contacto
    public boolean eliminar(Integer id) throws SQLException {
        String sql = "DELETE FROM contactos WHERE id = ?";
        
        try (Connection conn = DB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        }
    }

    // Método auxiliar para mapear ResultSet a Contacto
    private Contacto mapearContacto(ResultSet rs) throws SQLException {
        Contacto contacto = new Contacto();
        contacto.setId(rs.getInt("id"));
        contacto.setNombre(rs.getString("nombre"));
        contacto.setApellidoPaterno(rs.getString("apellido_paterno"));
        contacto.setApellidoMaterno(rs.getString("apellido_materno"));
        contacto.setSexo(rs.getString("sexo"));
        contacto.setTelefono(rs.getString("telefono"));
        contacto.setDireccion(rs.getString("direccion"));
        contacto.setTipoContacto(rs.getString("tipo_contacto"));
        return contacto;
    }

    // Método auxiliar para establecer parámetros del PreparedStatement
    private void setParametrosContacto(PreparedStatement pstmt, Contacto contacto) 
            throws SQLException {
        pstmt.setString(1, contacto.getNombre());
        pstmt.setString(2, contacto.getApellidoPaterno());
        pstmt.setString(3, contacto.getApellidoMaterno());
        pstmt.setString(4, contacto.getSexo());
        pstmt.setString(5, contacto.getTelefono());
        pstmt.setString(6, contacto.getDireccion());
        pstmt.setString(7, contacto.getTipoContacto());
    }
}