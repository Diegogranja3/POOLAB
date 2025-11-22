/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1;
import Calculador.Calculadora;
/**
 *
 * @author Diego
 */
public class Practica1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calculadora casio = new Calculadora();
        casio.division(1,0);
        System.out.println(casio.suma(1,2));
        System.out.println(casio.resta(1,3));
        System.out.println(casio.multiplicacion(1,4));
    }
    
}
