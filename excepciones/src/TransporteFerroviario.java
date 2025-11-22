/**
 * Transporte Ferroviario - Tren
 * NUEVA clase agregada para demostrar extensibilidad del sistema
 */
public class TransporteFerroviario extends Transporte implements Operable {
    
    public TransporteFerroviario(String id, int capacidad) {
        super(id, capacidad);
    }
    
    @Override
    public String tipo() {
        return "Ferroviario";
    }
    
    @Override
    public void mover() {
        System.out.println("Desplazándose sobre rieles... 🚂");
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println("Mantenimiento ferroviario: revisión de vías y sistemas eléctricos.");
    }
}