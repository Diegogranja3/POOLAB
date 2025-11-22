package colecciones;

import java.util.*;

/**
 * Laboratorio de Colecciones en Java
 * Demuestra el uso de ArrayList, LinkedList, HashSet y HashMap
 */
public class LaboratorioColecciones {

    public static void main(String[] args) {
        
        // ========== A. ArrayList ==========
        System.out.println("== ArrayList: Exhibición ==");
        ArrayList<Producto> listaExhibicion = new ArrayList<>();
        
        // Agregar productos a la lista
        listaExhibicion.add(new Producto(1, "Teclado", "Perifericos"));
        listaExhibicion.add(new Producto(2, "Mouse", "Perifericos"));
        listaExhibicion.add(new Producto(3, "Monitor", "Pantallas"));
        listaExhibicion.add(new Producto(4, "Cable HDMI", "Oferta"));
        
        // Recorrer con for-each
        for (Producto p : listaExhibicion) {
            System.out.println(p);
        }
        System.out.println();
        
        
        // ========== B. LinkedList ==========
        System.out.println("== LinkedList: Reabastecer ==");
        LinkedList<Producto> colaReabastecer = new LinkedList<>();
        
        // Agregar productos usando addFirst y addLast
        colaReabastecer.addLast(new Producto(5, "Laptop", "Computo"));
        colaReabastecer.addFirst(new Producto(6, "Webcam", "Perifericos"));
        
        // Recorrer con for-each
        for (Producto p : colaReabastecer) {
            System.out.println(p);
        }
        System.out.println();
        
        
        // ========== C. HashSet ==========
        System.out.println("== HashSet: Categorías únicas ==");
        HashSet<String> categorias = new HashSet<>();
        
        // Insertar categorías de todos los productos (algunas repetidas)
        for (Producto p : listaExhibicion) {
            categorias.add(p.getCategoria());
        }
        for (Producto p : colaReabastecer) {
            categorias.add(p.getCategoria());
        }
        
        // Imprimir el set (orden no garantizado)
        System.out.println(categorias);
        System.out.println();
        
        
        // ========== D. HashMap ==========
        System.out.println("== HashMap: Consulta por id ==");
        HashMap<Integer, Producto> mapaPorId = new HashMap<>();
        
        // Insertar todos los productos usando id como clave
        for (Producto p : listaExhibicion) {
            mapaPorId.put(p.getId(), p);
        }
        for (Producto p : colaReabastecer) {
            mapaPorId.put(p.getId(), p);
        }
        
        // Consultar un producto por id
        int idBuscado = 3;
        Producto encontrado = mapaPorId.get(idBuscado);
        System.out.println("id=" + idBuscado + " -> " + encontrado);
        System.out.println();
        
        // Recorrer el entrySet con for-each
        System.out.println("Recorriendo HashMap (entrySet):");
        for (Map.Entry<Integer, Producto> entry : mapaPorId.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();
        
        
        // ========== E. Iterator (eliminación segura) ==========
        System.out.println("== Iterator: eliminar categoría 'Oferta' en listaExhibicion ==");
        Iterator<Producto> iterator = listaExhibicion.iterator();
        
        while (iterator.hasNext()) {
            Producto p = iterator.next();
            if (p.getCategoria().equals("Oferta")) {
                iterator.remove(); // Eliminación segura con iterator
            }
        }
        
        // Volver a imprimir la lista para confirmar eliminación
        System.out.println("Después de eliminar:");
        for (Producto p : listaExhibicion) {
            System.out.println(p);
        }
    }
}