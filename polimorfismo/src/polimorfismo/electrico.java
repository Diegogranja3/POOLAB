package polimorfismo;

public class electrico extends vehiculo implements electricidad {
    private int nivelBateria;
    
    public electrico(String marca, String modelo, double velocidad) {
        super(marca, modelo, velocidad);
        this.nivelBateria = 80; // Batería al 80%
    }
    
    @Override
    public String tipo() {
        return "Auto Eléctrico";
    }
    
    @Override
    public void cargar() {
        nivelBateria = 100;
        System.out.println("Auto eléctrico cargado. Nivel de batería: " + nivelBateria + "%");
    }
    
    @Override
    public int nivelBateria() {
        return nivelBateria;
    }
    
    @Override
    public String describir() {
        return super.describir() + " - Tipo: " + tipo() + " - Batería: " + nivelBateria + "%";
    }
}