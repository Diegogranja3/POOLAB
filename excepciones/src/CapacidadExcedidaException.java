/**
 * Excepción personalizada para manejar casos donde
 * se intenta transportar más pasajeros que la capacidad permitida
 */
public class CapacidadExcedidaException extends Exception {
    
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
    
    public CapacidadExcedidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}