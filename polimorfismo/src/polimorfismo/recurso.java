package polimorfismo;

public class recurso implements AutoCloseable {
    private String nombreRecurso;
    
    public recurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
        System.out.println("Recurso " + nombreRecurso + " inicializado.");
    }
    
    public void usar() {
        System.out.println("Usando recurso: " + nombreRecurso);
    }
    
    @Override
    public void close() {
        System.out.println("Cerrando recurso: " + nombreRecurso + " - Limpieza completada.");
    }
}