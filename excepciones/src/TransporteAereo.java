/**
 * Transporte Aéreo - Avión
 * Demuestra HERENCIA e IMPLEMENTACIÓN de interface
 */
public class TransporteAereo extends Transporte implements Operable {
    
    public TransporteAereo(String id, int capacidad) {
        super(id, capacidad);
    }
    
    @Override
    public String tipo() {
        return "Aéreo";
    }
    
    @Override
    public void mover() {
        System.out.println("Volando en el aire... ✈️");
    }
    
    @Override
    public void realizarMantenimiento() {
        System.out.println("Mantenimiento aéreo: inspección de turbinas y sistemas.");
    }
}