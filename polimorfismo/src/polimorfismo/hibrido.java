package polimorfismo;

public class hibrido extends vehiculo implements combustion, electricidad {
    private String tipoCombustible;
    private double nivelCombustible;
    private int nivelBateria;
    
    public hibrido(String marca, String modelo, double velocidad) {
        super(marca, modelo, velocidad);
        this.tipoCombustible = "Híbrido";
        this.nivelCombustible = 60.0; // Tanque al 60%
        this.nivelBateria = 70; // Batería al 70%
    }
    
    @Override
    public String tipo() {
        return "Híbrido";
    }
    
    @Override
    public void repostar() {
        nivelCombustible = 100.0;
        System.out.println("Híbrido repostado. Nivel de combustible: " + nivelCombustible + "%");
    }
    
    @Override
    public String tipoCombustible() {
        return tipoCombustible;
    }
    
    @Override
    public void cargar() {
        nivelBateria = 100;
        System.out.println("Híbrido cargado. Nivel de batería: " + nivelBateria + "%");
    }
    
    @Override
    public int nivelBateria() {
        return nivelBateria;
    }
    
    @Override
    public String describir() {
        return super.describir() + " - Tipo: " + tipo() + 
               " - Combustible: " + nivelCombustible + "%" + 
               " - Batería: " + nivelBateria + "%";
    }
}