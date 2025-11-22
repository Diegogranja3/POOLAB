import java.io.*;
import java.util.*;

public class GestorArchivos {
    private static final String CARPETA = "datos";
    private static final String ARCHIVO_PRODUCTOS = "productos.txt";
    private static final String ARCHIVO_CATEGORIAS = "categorias.txt";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            // 1. Crear carpeta si no existe
            crearCarpeta();

            // 2 y 3. Agregar productos desde teclado
            agregarProductos();

            // 5 y 6. Leer y mostrar productos
            leerArchivo();

            // 7. Listar archivos en la carpeta
            listarArchivos();

            // Retos opcionales - Menu interactivo
            menuOpciones();
        } catch (Exception e) {
            System.out.println("Error general en la aplicacion: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * 1. Crear carpeta 'datos' si no existe
     */
    private static void crearCarpeta() {
        File carpeta = new File(CARPETA);
        if (carpeta.exists()) {
            System.out.println("Carpeta 'datos' ya existe.");
        } else {
            if (carpeta.mkdir()) {
                System.out.println("Carpeta 'datos' creada exitosamente.");
            } else {
                System.out.println("Error al crear la carpeta.");
                System.exit(1);
            }
        }
    }

    /**
     * 3 y 4. Agregar productos y guardarlos en archivo
     */
    private static void agregarProductos() {
        System.out.println("\n=== Registro de productos ===");
        
        int cantidad = leerEnteroPositivo("Cuantos productos quieres agregar?: ", 1, 100);

        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);

        // try-with-resources para cerrar automaticamente
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo, true))) {
            for (int i = 1; i <= cantidad; i++) {
                System.out.println("\nProducto #" + i);
                
                // Validacion de ID unico
                int id = leerIdUnico(archivo);
                
                // Validacion de nombre no vacio
                String nombre = leerTextoNoVacio("Nombre: ");
                
                // Validacion de categoria
                String categoria = leerTextoNoVacio("Categoria: ");

                // Escribir en formato CSV
                writer.write(id + "," + nombre + "," + categoria);
                writer.newLine();
            }
            System.out.println("\nProductos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar productos: " + e.getMessage());
        }
    }

    /**
     * Valida y lee un ID unico que no exista en el archivo
     */
    private static int leerIdUnico(File archivo) {
        Set<Integer> idsExistentes = obtenerIdsExistentes(archivo);
        int id;
        
        while (true) {
            id = leerEnteroPositivo("ID: ", 1, 99999);
            
            if (idsExistentes.contains(id)) {
                System.out.println("  El ID " + id + " ya existe. Ingresa otro ID.");
            } else {
                break;
            }
        }
        
        return id;
    }

    /**
     * Obtiene todos los IDs existentes en el archivo
     */
    private static Set<Integer> obtenerIdsExistentes(File archivo) {
        Set<Integer> ids = new HashSet<>();
        
        if (!archivo.exists()) {
            return ids;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 1) {
                    try {
                        ids.add(Integer.parseInt(datos[0].trim()));
                    } catch (NumberFormatException e) {
                        // Ignorar lineas con formato incorrecto
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Advertencia: No se pudieron leer IDs existentes.");
        }
        
        return ids;
    }

    /**
     * Lee un entero positivo con validacion
     */
    private static int leerEnteroPositivo(String mensaje, int min, int max) {
        int numero;
        while (true) {
            try {
                System.out.print(mensaje);
                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("  El campo no puede estar vacio. Intenta de nuevo.");
                    continue;
                }
                
                numero = Integer.parseInt(input);
                
                if (numero < min || numero > max) {
                    System.out.println("  Debe estar entre " + min + " y " + max + ". Intenta de nuevo.");
                    continue;
                }
                
                break;
            } catch (NumberFormatException e) {
                System.out.println("  Debe ser un numero valido. Intenta de nuevo.");
            }
        }
        return numero;
    }

    /**
     * Lee texto no vacio con validacion
     */
    private static String leerTextoNoVacio(String mensaje) {
        String texto;
        while (true) {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            
            if (texto.isEmpty()) {
                System.out.println("  El campo no puede estar vacio. Intenta de nuevo.");
                continue;
            }
            
            if (texto.length() > 100) {
                System.out.println("  Maximo 100 caracteres. Intenta de nuevo.");
                continue;
            }
            
            if (texto.contains(",")) {
                System.out.println("  No puede contener comas (,). Intenta de nuevo.");
                continue;
            }
            
            break;
        }
        return texto;
    }

    /**
     * 5. Leer archivo completo
     */
    private static void leerArchivo() {
        System.out.println("\n=== Lectura del archivo ===");
        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);

        if (!archivo.exists()) {
            System.out.println("  El archivo no existe todavia.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int contador = 0;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
                contador++;
            }
            
            if (contador == 0) {
                System.out.println("  El archivo esta vacio.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    /**
     * 7. Listar archivos en la carpeta 'datos'
     */
    private static void listarArchivos() {
        System.out.println("\n=== Archivos en la carpeta 'datos' ===");
        File carpeta = new File(CARPETA);
        File[] archivos = carpeta.listFiles();

        if (archivos != null && archivos.length > 0) {
            for (File archivo : archivos) {
                if (archivo.isFile()) {
                    System.out.println("  " + archivo.getName() + 
                                     " (" + archivo.length() + " bytes)");
                }
            }
        } else {
            System.out.println("  La carpeta esta vacia.");
        }
    }

    /**
     * RETO 1: Buscar producto por nombre
     */
    private static void buscarProductoPorNombre(String nombreBuscado) {
        System.out.println("\n=== Busqueda de producto ===");
        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!archivo.exists()) {
            System.out.println("  El archivo no existe.");
            return;
        }
        
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.toLowerCase().contains(nombreBuscado.toLowerCase())) {
                    System.out.println("  Encontrado: " + linea);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("  No se encontro ningun producto con ese nombre.");
            }
        } catch (IOException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }
    }

    /**
     * RETO 3: Crear archivo de categorias unicas
     */
    private static void crearArchivoCategorias() {
        File archivoProductos = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!archivoProductos.exists()) {
            System.out.println("\n  El archivo de productos no existe.");
            return;
        }
        
        File archivoCategorias = new File(CARPETA + File.separator + ARCHIVO_CATEGORIAS);
        Set<String> categoriasUnicas = new HashSet<>();

        // Leer categorias del archivo de productos
        try (BufferedReader reader = new BufferedReader(new FileReader(archivoProductos))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3) {
                    categoriasUnicas.add(datos[2].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer productos: " + e.getMessage());
            return;
        }

        if (categoriasUnicas.isEmpty()) {
            System.out.println("\n  No hay categorias para guardar.");
            return;
        }

        // Escribir categorias unicas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoCategorias))) {
            System.out.println("\n=== Categorias unicas ===");
            List<String> categoriasOrdenadas = new ArrayList<>(categoriasUnicas);
            Collections.sort(categoriasOrdenadas);
            
            for (String categoria : categoriasOrdenadas) {
                writer.write(categoria);
                writer.newLine();
                System.out.println("  " + categoria);
            }
            System.out.println("\nArchivo 'categorias.txt' creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al crear archivo de categorias: " + e.getMessage());
        }
    }

    /**
     * PREGUNTA 8: Contar productos (lineas del archivo)
     */
    private static int contarProductos() {
        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!archivo.exists()) {
            return 0;
        }
        
        int contador = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            while (reader.readLine() != null) {
                contador++;
            }
        } catch (IOException e) {
            System.out.println("Error al contar productos: " + e.getMessage());
        }

        return contador;
    }

    /**
     * PREGUNTA 6: Leer productos de una categoria especifica
     */
    private static void leerPorCategoria(String categoriaFiltro) {
        System.out.println("\n=== Productos de categoria: " + categoriaFiltro + " ===");
        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!archivo.exists()) {
            System.out.println("  El archivo no existe.");
            return;
        }
        
        boolean encontrado = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 3 && datos[2].trim().equalsIgnoreCase(categoriaFiltro)) {
                    System.out.println("  " + linea);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                System.out.println("  No hay productos en esa categoria.");
            }
        } catch (IOException e) {
            System.out.println("Error al leer archivo: " + e.getMessage());
        }
    }

    /**
     * PREGUNTA 10: Crear respaldo del archivo
     */
    private static void crearRespaldo() {
        File origen = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!origen.exists()) {
            System.out.println("\n  El archivo de productos no existe.");
            return;
        }
        
        File destino = new File(CARPETA + File.separator + "productos_backup.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(origen));
             BufferedWriter writer = new BufferedWriter(new FileWriter(destino))) {
            
            String linea;
            while ((linea = reader.readLine()) != null) {
                writer.write(linea);
                writer.newLine();
            }
            System.out.println("\nRespaldo creado: productos_backup.txt");
        } catch (IOException e) {
            System.out.println("Error al crear respaldo: " + e.getMessage());
        }
    }

    /**
     * RETO 4: Borrar archivo con confirmacion
     */
    private static void borrarArchivo() {
        File archivo = new File(CARPETA + File.separator + ARCHIVO_PRODUCTOS);
        
        if (!archivo.exists()) {
            System.out.println("\n  El archivo no existe.");
            return;
        }
        
        System.out.print("\nEstas seguro de eliminar productos.txt? (S/N): ");
        String confirmacion = scanner.nextLine().trim();

        if (confirmacion.equalsIgnoreCase("S") || confirmacion.equalsIgnoreCase("SI")) {
            if (archivo.delete()) {
                System.out.println("Archivo eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el archivo.");
            }
        } else {
            System.out.println("Operacion cancelada.");
        }
    }

    /**
     * Menu de opciones adicionales
     */
    private static void menuOpciones() {
        while (true) {
            try {
                System.out.println("\n=== MENU DE OPCIONES ===");
                System.out.println("1. Buscar producto por nombre");
                System.out.println("2. Listar productos por categoria");
                System.out.println("3. Contar total de productos");
                System.out.println("4. Generar archivo de categorias unicas");
                System.out.println("5. Crear respaldo del archivo");
                System.out.println("6. Borrar archivo de productos");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opcion: ");

                String input = scanner.nextLine().trim();
                
                if (input.isEmpty()) {
                    System.out.println("  Debes ingresar una opcion.");
                    continue;
                }

                int opcion;
                try {
                    opcion = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    System.out.println("  Opcion no valida. Ingresa un numero.");
                    continue;
                }

                switch (opcion) {
                    case 1:
                        String nombre = leerTextoNoVacio("Ingresa el nombre a buscar: ");
                        buscarProductoPorNombre(nombre);
                        break;
                    case 2:
                        String categoria = leerTextoNoVacio("Ingresa la categoria: ");
                        leerPorCategoria(categoria);
                        break;
                    case 3:
                        int total = contarProductos();
                        System.out.println("\nTotal de productos: " + total);
                        break;
                    case 4:
                        crearArchivoCategorias();
                        break;
                    case 5:
                        crearRespaldo();
                        break;
                    case 6:
                        borrarArchivo();
                        break;
                    case 0:
                        System.out.println("\nHasta pronto!");
                        return;
                    default:
                        System.out.println("  Opcion no valida. Selecciona entre 0 y 6.");
                }
            } catch (Exception e) {
                System.out.println("Error en el menu: " + e.getMessage());
            }
        }
    }
}