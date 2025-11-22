package polimorfismo;

public abstract class vehiculo {
    private double velocidad;
    protected String marca;
    protected String modelo;
    
    public vehiculo(String marca, String modelo, double velocidad) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = velocidad;
    }
    
    // Método abstracto que debe ser implementado por las clases hijas
    public abstract String tipo();
    
    public String describir() {
        return "Vehículo: " + marca + " " + modelo + " - Velocidad: " + velocidad + " km/h";
    }
    
    // Sobrecarga de métodos acelerar
    public void acelerar() {
        velocidad += 10;
        System.out.println("Acelerando... Nueva velocidad: " + velocidad + " km/h");
    }
    
    public void acelerar(double incremento) {
        velocidad += incremento;
        System.out.println("Acelerando " + incremento + " km/h... Nueva velocidad: " + velocidad + " km/h");
    }
    
    public void frenar(double decremento) {
        velocidad = Math.max(0, velocidad - decremento);
        System.out.println("Frenando " + decremento + " km/h... Nueva velocidad: " + velocidad + " km/h");
    }
    
    // Getters
    public double getVelocidad() {
        return velocidad;
    }
    
    public String getMarca() {
        return marca;
    }
    
    public String getModelo() {
        return modelo;
    }
}