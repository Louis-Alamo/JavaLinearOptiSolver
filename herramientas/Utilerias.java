/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

/**
 *
 * @author louis
 */
public class Utilerias {

    public static double recortarDecimales(double numero, int cantidadDecimales) {
        if (cantidadDecimales < 0) {
            throw new IllegalArgumentException("La cantidad de decimales debe ser un número no negativo.");
        }

        double factor = Math.pow(10, cantidadDecimales);
        return Math.floor(numero * factor) / factor;
    }

    public static boolean sonCercanos(double a, double b, double tolerancia) {
        return Math.abs(a - b) <= tolerancia;
    }

    public static String formatearValor(double valor) {
        if (Math.abs(valor) < 1e-5) {
            return "0.0";
        }
        return String.format("%.1f", valor);
    }

    public static int cantidadRepetidasCaracter(char caracter, String cadena) {
        int contador = 0;

        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == caracter) {
                contador++;
            }
        }
        return contador;
    }

    public static boolean contieneCaracteresNoPermitidos(String cadena) {
        String patron = "^[xy0-9\\+\\-\\=]*$";
        return !cadena.matches(patron);
    }
}
