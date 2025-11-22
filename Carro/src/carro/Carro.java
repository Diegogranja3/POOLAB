/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package carro;

/**
 *
 * @author Diego
 */
public class Carro {

    String marca;
    String modelo;
    int velocidad;
    boolean motorEncendido;
    

    public Carro(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
        this.velocidad = 0;
        this.motorEncendido = false;
        System.out.println("Carro creado: " + marca + " " + modelo);
    }
    

    public void iniciarMotor() {
        motorEncendido = true;
        System.out.println("Motor encendido");
    }
    
 
    public void darVuelta() {
        System.out.println("El carro está dando vuelta");
    }
    

    public void acelerar() {
        velocidad = velocidad + 20;
        System.out.println("Velocidad: " + velocidad + " km/h");
    }
    

    public void mostrarInfo() {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Velocidad: " + velocidad);
        System.out.println("Motor encendido: " + motorEncendido);
    }

    public static void main(String[] args) {

        Carro miCarro = new Carro("Toyota", "Corolla");
        
        miCarro.iniciarMotor();
        miCarro.acelerar();
        miCarro.darVuelta();
        miCarro.acelerar();
        miCarro.mostrarInfo();
        

        System.out.println("\nCreando más carros...");
        Carro carro1 = new Carro("Honda", "Civic");
        Carro carro2 = new Carro("Ford", "Focus");
       
        System.gc(); 
    }
}
