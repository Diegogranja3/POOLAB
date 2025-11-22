package polimorfismo;

public class auto extends vehiculo implements combustion {
    private String tipoCombustible;
    private double nivelCombustible;
    
    public auto(String marca, String modelo, double velocidad) {
        super(marca, modelo, velocidad);
        this.tipoCombustible = "Gasolina";
        this.nivelCombustible = 50.0; // Tanque al 50%
    }
    
    @Override
    public String tipo() {
        return "Auto";
    }
    
    @Override
    public void repostar() {
        nivelCombustible = 100.0;
        System.out.println("Auto repostado. Nivel de combustible: " + nivelCombustible + "%");
    }
    
    @Override
    public String tipoCombustible() {
        return tipoCombustible;
    }
    
    @Override
    public String describir() {
        return super.describir() + " - Tipo: " + tipo() + " - Combustible: " + tipoCombustible;
    }
}