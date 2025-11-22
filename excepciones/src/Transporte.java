/**
 * Clase abstracta base que representa un transporte genérico
 * Demuestra encapsulamiento con atributos privados
 */
public abstract class Transporte {
    // Atributos privados - ENCAPSULAMIENTO
    private String id;
    private int capacidad;
    
    // Constructor
    public Transporte(String id, int capacidad) throws IllegalArgumentException {
        this.id = id;
        setCapacidad(capacidad); // Usa setter para validación
    }
    
    // Getters y Setters con validación
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public int getCapacidad() {
        return capacidad;
    }
    
    public void setCapacidad(int capacidad) throws IllegalArgumentException {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0");
        }
        if (capacidad > 500) {
            throw new IllegalArgumentException(
                "La capacidad no puede exceder 500 pasajeros por razones de seguridad"
            );
        }
        this.capacidad = capacidad;
    }
    
    // Métodos abstractos - deben ser implementados por subclases
    public abstract String tipo();
    public abstract void mover();
    
    // Método concreto que maneja la excepción personalizada
    public void transportar(int pasajeros) throws CapacidadExcedidaException {
        if (pasajeros > capacidad) {
            throw new CapacidadExcedidaException(
                "Pasajeros (" + pasajeros + ") exceden capacidad (" + 
                capacidad + ") en " + tipo()
            );
        }
        System.out.println("Transportando " + pasajeros + " pasajeros en " + 
                          tipo() + " [" + id + "]");
    }
    
    @Override
    public String toString() {
        return tipo() + " [" + id + "] - Capacidad: " + capacidad;
    }
}