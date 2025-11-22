package concurrente;

import java.io.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clase que representa un hilo lector de archivos
 * Hereda de Thread para ejecutarse de forma concurrente
 */
class LectorArchivo extends Thread {
    private String nombreArchivo;
    private static AtomicInteger contadorLineasTotal = new AtomicInteger(0);
    
    public LectorArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    
    @Override
    public void run() {
        BufferedReader lector = null;
        try {
            // Crear el lector del archivo
            lector = new BufferedReader(new FileReader(nombreArchivo));
            String linea;
            
            // Leer linea por linea
            while ((linea = lector.readLine()) != null) {
                // Mostrar que hilo esta leyendo que linea
                System.out.println(Thread.currentThread().getName() + " leyendo: " + linea);
                
                // Incrementar contador total de líneas
                contadorLineasTotal.incrementAndGet();
                
                // Simular procesamiento (hace que la ejecución sea más visible)
                Thread.sleep(500);
            }
            
        } catch (FileNotFoundException e) {
            System.err.println("Error: Archivo no encontrado - " + nombreArchivo);
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Hilo interrumpido: " + e.getMessage());
        } finally {
            // IMPORTANTE: Siempre cerrar el lector
            try {
                if (lector != null) {
                    lector.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo: " + e.getMessage());
            }
        }
    }
    
    public static int getContadorLineasTotal() {
        return contadorLineasTotal.get();
    }
    
    public static void resetContador() {
        contadorLineasTotal.set(0);
    }
}

/**
 * Clase principal - DEBE TENER EL MISMO NOMBRE QUE EL ARCHIVO .java
 */
public class Concurrente {
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE LECTURA CONCURRENTE DE ARCHIVOS ===\n");
        
        // Paso 1: Crear los archivos de prueba
        crearArchivosSucursales();
        
        System.out.println("\n--- Iniciando lectura concurrente ---\n");
        
        // Paso 2: Crear los hilos lectores
        LectorArchivo hilo1 = new LectorArchivo("sucursal1.txt");
        LectorArchivo hilo2 = new LectorArchivo("sucursal2.txt");
        LectorArchivo hilo3 = new LectorArchivo("sucursal3.txt");
        
        // Paso 3: Iniciar los hilos (ejecutan en paralelo)
        hilo1.start();
        hilo2.start();
        hilo3.start();
        
        // Paso 4: Esperar a que todos los hilos terminen
        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            System.err.println("Error al esperar los hilos: " + e.getMessage());
        }
        
        // Paso 5: Mostrar estadisticas finales
        System.out.println("\n--- Lectura completada ---");
        System.out.println("Total de lineas leidas: " + LectorArchivo.getContadorLineasTotal());
    }
    
    /**
     * Método auxiliar para crear los archivos de prueba
     */
    private static void crearArchivosSucursales() {
        // Datos de cada sucursal
        String[][] sucursales = {
            {"Ana", "Luis", "Sofia"},
            {"Carlos", "Marta", "Pedro"},
            {"Laura", "Jorge", "Diego"}
        };
        
        String[] nombresArchivos = {"sucursal1.txt", "sucursal2.txt", "sucursal3.txt"};
        
        // Crear cada archivo
        for (int i = 0; i < sucursales.length; i++) {
            BufferedWriter escritor = null;
            try {
                escritor = new BufferedWriter(new FileWriter(nombresArchivos[i]));
                
                for (String nombre : sucursales[i]) {
                    escritor.write(nombre);
                    escritor.newLine();
                }
                
                System.out.println("✓ Archivo creado: " + nombresArchivos[i]);
                
            } catch (IOException e) {
                System.err.println("Error al crear archivo: " + e.getMessage());
            } finally {
                try {
                    if (escritor != null) {
                        escritor.close();
                    }
                } catch (IOException e) {
                    System.err.println("Error al cerrar escritor: " + e.getMessage());
                }
            }
        }
    }
}


// ============================================
// EJEMPLO ALTERNATIVO USANDO RUNNABLE
// ============================================

/**
 * Implementacion alternativa usando la interfaz Runnable
 * Mas flexible que heredar de Thread
 * 
 * Para usarlo:
 * 1. Crea un nuevo archivo llamado "EjemploRunnable.java"
 * 2. Copia las clases LectorArchivoRunnable y EjemploRunnable
 * 3. Ejecuta EjemploRunnable
 */
class LectorArchivoRunnable implements Runnable {
    private String nombreArchivo;
    private String nombreHilo;
    
    public LectorArchivoRunnable(String nombreArchivo, String nombreHilo) {
        this.nombreArchivo = nombreArchivo;
        this.nombreHilo = nombreHilo;
    }
    
    @Override
    public void run() {
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(nombreHilo + " leyendo: " + linea);
                Thread.sleep(500);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error en " + nombreHilo + ": " + e.getMessage());
        }
    }
}