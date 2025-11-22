import java.util.ArrayList;

/**
 * Clase principal que demuestra:
 * - Polimorfismo con ArrayList<Transporte>
 * - Manejo de excepciones personalizadas
 * - Uso de interface común
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=".repeat(70));
        System.out.println("       SISTEMA DE GESTIÓN DE TRANSPORTES");
        System.out.println("=".repeat(70));
        
        // ArrayList polimórfico - puede contener cualquier tipo de Transporte
        ArrayList<Transporte> flota = new ArrayList<>();
        
        try {
            // Crear transportes
            flota.add(new TransporteMaritimo("BAR-01", 80));
            flota.add(new TransporteTerrestre("BUS-42", 50));
            flota.add(new TransporteAereo("AV-777", 150));
            flota.add(new TransporteFerroviario("TREN-9", 200)); // NUEVO
            
            System.out.println("\n📋 Flota registrada:");
            for (Transporte t : flota) {
                System.out.println("  • " + t);
            }
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("PRUEBAS DE TRANSPORTE Y MANTENIMIENTO");
            System.out.println("=".repeat(70));
            
            // Probar cada transporte - POLIMORFISMO en acción
            int[] pasajeros = {60, 60, 60, 180}; // Algunos exceden capacidad
            
            for (int i = 0; i < flota.size(); i++) {
                Transporte t = flota.get(i);
                System.out.println("\n" + "-".repeat(70));
                
                // Mover (método abstracto implementado)
                t.mover();
                
                // Intentar transportar - MANEJO DE EXCEPCIONES
                try {
                    t.transportar(pasajeros[i]);
                } catch (CapacidadExcedidaException e) {
                    System.out.println("❌ ERROR: " + e.getMessage());
                }
                
                // Mantenimiento (método de interface)
                if (t instanceof Operable) {
                    ((Operable) t).realizarMantenimiento();
                }
            }
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("PRUEBA DE VALIDACIÓN DE CAPACIDAD");
            System.out.println("=".repeat(70));
            
            // Demostrar validación en setCapacidad
            try {
                System.out.println("\n🧪 Intentando crear transporte con capacidad 0...");
                TransporteAereo avioncito = new TransporteAereo("AV-000", 0);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Rechazado: " + e.getMessage());
            }
            
            try {
                System.out.println("\n🧪 Intentando crear transporte con capacidad 600...");
                TransporteMaritimo superbarco = new TransporteMaritimo("BAR-999", 600);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Rechazado: " + e.getMessage());
            }
            
            System.out.println("\n✅ Las validaciones funcionan correctamente!");
            
        } catch (Exception e) {
            System.err.println("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("       FIN DEL SISTEMA");
        System.out.println("=".repeat(70));
    }
}