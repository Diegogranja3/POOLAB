/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package banco;

/**
 *
 * @author Diego
 */
public class Banco {
    public static void main(String[] args) {
        // Crear una cuenta bancaria
        cuenta cuenta = new cuenta("123456", "Diego", 500);

        // Mostrar información inicial
        System.out.println("Cuenta creada: " + cuenta.getNumeroCuenta() +
                " | Titular: " + cuenta.getTitular() +
                " | Saldo: $" + cuenta.getSaldo());

        // Operaciones
        cuenta.depositar(200);   // saldo = 700
        cuenta.retirar(100);     // saldo = 600
        cuenta.retirar(1000);    // error: saldo insuficiente
        cuenta.depositar(-50);   // error: monto negativo
    }
}

