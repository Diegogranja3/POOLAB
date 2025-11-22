package polimorfismo;

import java.util.Arrays;
import java.util.List;

public class Polimorfismo {
    public static void main(String[] args) {
        System.out.println("=== EJERCICIO DE POLIMORFISMO ===\n");
        
        // Crear instancias siguiendo el ejemplo proporcionado
        hibrido b = new hibrido("BYD", "Dolphin", 30.00);
        System.out.println("Tipo de b: " + b.tipo());
        b.acelerar();
        
        System.out.println("\n--- Creando vehículo polimórfico ---");
        vehiculo g = new hibrido("BYD", "Dolphin", 30.00);
        
        // Crear más vehículos para la flota
        auto auto = new auto("Toyota", "Corolla", 0.0);
        moto moto = new moto("Honda", "CBR600", 0.0);
        electrico electrico = new electrico("Tesla", "Model 3", 0.0);
        
        // Crear lista de vehículos (flota)
        List<vehiculo> flota = Arrays.asList(b, g, auto, moto, electrico);
        
        System.out.println("\n=== DEMOSTRANDO POLIMORFISMO EN LA FLOTA ===");
        
        for (vehiculo v : flota) {
            System.out.println("\n--- " + v.getClass().getSimpleName() + " ---");
            System.out.println("Descripción: " + v.describir());
            System.out.println("Tipo: " + v.tipo());
            
            // Acelerar usando sobrecarga de métodos
            v.acelerar(); // Sin parámetros
            v.acelerar(15.0); // Con parámetros
            v.frenar(5.0);
            
            // Demostrar casting y verificación de interfaces
            if (v instanceof combustion) {
                System.out.println("Es de combustión - Tipo: " + ((combustion) v).tipoCombustible());
                ((combustion) v).repostar();
            }
            
            if (v instanceof electricidad) {
                System.out.println("Es eléctrico - Batería: " + ((electricidad) v).nivelBateria() + "%");
                ((electricidad) v).cargar();
            }
        }
        
        System.out.println("\n=== DEMOSTRANDO AutoCloseable (RecursoLog) ===");
        
        try (recurso recurso = new recurso("Sistema de Navegación")) {
            recurso.usar();
            System.out.println("Operaciones completadas.");
        }
        
        System.out.println("\n=== RESUMEN DE CONCEPTOS DEMOSTRADOS ===");
        System.out.println("✓ Herencia: Todas las clases heredan de Vehiculo");
        System.out.println("✓ Clases abstractas: Vehiculo con método abstracto tipo()");
        System.out.println("✓ Interfaces: Combustion y Electricidad");
        System.out.println("✓ Implementación múltiple: Hibrido implementa ambas interfaces");
        System.out.println("✓ Sobrecarga: acelerar() y acelerar(double)");
        System.out.println("✓ Polimorfismo: Mismo comportamiento, diferentes implementaciones");
        System.out.println("✓ Casting y instanceof: Verificación de tipos en tiempo de ejecución");
        System.out.println("✓ AutoCloseable: Manejo automático de recursos");
    }
}