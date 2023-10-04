/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_simplex;

/**
 *
 * @author louis
 */
public class OperacionesSimplex {

    public int encontrarPosicionMasNegativa(Double[][] matriz) {

        if (matriz[0][1] < matriz[0][2] && matriz[0][1] < 0) {
            return 1;
        } else if (matriz[0][2] < matriz[0][1] && matriz[0][2] < 0) {
            return 2;
        }
        return 0;

    }

    public boolean comprobarNegativos(Double[][] matriz) {

        if (matriz[0][1] < 0 || matriz[0][2] < 0) {
            return true;
        }
        return false;
    }

    public int encontrarPosicionVariableEntrada(int posicionColumna, Double matriz[][]) {
        Double valorMenor = Double.MAX_VALUE; // Inicializar con un valor grande
        int posicionFila = -1; // Inicializar en -1 para verificar si se encontró una fila válida

        for (int i = 1; i < matriz.length; i++) {
            if (matriz[i][posicionColumna] > 0) {
                double division = matriz[i][matriz[i].length - 1] / matriz[i][posicionColumna];
                if (division < valorMenor && division >= 0) {
                    valorMenor = division;
                    posicionFila = i;
                }
            }
        }

        return posicionFila;
    }


}
