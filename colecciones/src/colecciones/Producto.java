package colecciones;

import java.util.Objects;

/**
 * Clase Producto para representar un producto en el inventario
 * Contiene id, nombre y categoría
 */
public class Producto {
    private int id;
    private String nombre;
    private String categoria;

    /**
     * Constructor con parámetros
     * @param id identificador único del producto
     * @param nombre nombre del producto
     * @param categoria categoría a la que pertenece
     */
    public Producto(int id, String nombre, String categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    /**
     * Implementación de equals usando solo el id
     * Dos productos son iguales si tienen el mismo id
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    /**
     * Implementación de hashCode usando solo el id
     * Coherente con equals para uso en HashSet y HashMap
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Representación en String del producto
     */
    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}