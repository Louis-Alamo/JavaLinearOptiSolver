/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_simplex;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import herramientas.Datos;
import herramientas.ProcesadorDeFunciones;

/**
 *
 * @author louis
 */
public class Tabla {

    private List<String> restricciones;
    private String funcionZ;
    private List<Double[]> tabla = new ArrayList<>();
    private List<Double[][]> iteracionesMatriz = new ArrayList<>();
    private String variableX, VariableY;
    private Double[][] matriz;
    private int cont = 0;
    private Double[] arregloFunciones;
    private int filas, columnas;
    private OperacionesSimplex simplex = new OperacionesSimplex();
    private int[] posicionesEntrada = new int[2];

    private ProcesadorDeFunciones utilerias = new ProcesadorDeFunciones();

    public Tabla() {

        this.restricciones = Datos.getRestricciones();
        this.filas = 1 + restricciones.size();
        this.columnas = 4 + restricciones.size();
        this.funcionZ = Datos.getFuncionZ();
        matriz = new Double[filas][columnas];
        arregloFunciones = new Double[restricciones.size() + 1];
        matriz[0][0] = 1.0;
        procesarFuncionZ();
        InterfazSimplex interfaz = new InterfazSimplex(matriz, posicionesEntrada[0], posicionesEntrada[1]);
        interfaz.setVisible(true);
    }

    private void procesarFuncionZ() {
        String[] funcionZprocesada = utilerias.procesarFuncionZ(funcionZ);
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                matriz[i][j] = 0.0;
            }
        }

        matriz[0][0] = 1.0;
        matriz[0][1] = -1 * (Double.valueOf(funcionZprocesada[0]));
        matriz[0][2] = -1 * (Double.valueOf(funcionZprocesada[2]));

        System.out.println();

        procesarRestricciones(1, 3);

    }

    private void procesarRestricciones(int contadorFilas, int contadorVariablesHolgura) {

        if (contadorFilas == filas) {
            mostrarMatriz();
            inicioAlgoritmoSimplex(matriz);
        } else {
            String restriccion = restricciones.get(contadorFilas - 1);
            String[] restriccionPartes = utilerias.procesarFuncionTotalString(restriccion);

            matriz[contadorFilas][1] = Double.valueOf(restriccionPartes[0]);
            matriz[contadorFilas][2] = Double.valueOf(restriccionPartes[2]);
            matriz[contadorFilas][columnas - 1] = Double.valueOf(restriccionPartes[4]);
            matriz[contadorFilas][contadorVariablesHolgura] = 1.0;
            System.out.println(restriccion);
            procesarRestricciones(contadorFilas + 1, contadorVariablesHolgura + 1);
        }

    }

    private void mostrarMatriz() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {

                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("");
    }

    private void inicioAlgoritmoSimplex(Double[][] matriz) {
        if (simplex.comprobarNegativos(matriz)) {
            int posicionColumna = simplex.encontrarPosicionMasNegativa(matriz);

            int posicionFila = simplex.encontrarPosicionVariableEntrada(posicionColumna, matriz);

            
            Double elementoPivote = matriz[posicionFila][posicionColumna];
            
            //inicioAlgoritmoSimplex(matriz);

            //matriz = simplex.procesarFilaPivote(posicionFila, matriz, elementoPivote);

            //matriz = simplex.procesarTablaCero(posicionFila, posicionColumna, matriz);
            matriz = procesarTablaSimplex(matriz, posicionFila, posicionColumna);

            
            iteracionesMatriz.add(copiarMatriz(matriz));

            agregarEntrada(posicionFila, posicionColumna);
            mostrarMatriz();
            inicioAlgoritmoSimplex(matriz);

        } else {
            
        }

    }

    private Double[][] copiarMatriz(Double[][] matrizOriginal) {
        int filas = matrizOriginal.length;
        int columnas = matrizOriginal[0].length;
        Double[][] copia = new Double[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                copia[i][j] = matrizOriginal[i][j];
            }
        }

        return copia;
    }

    private void agregarEntrada(int posicionEntrada, int posicion) {
        if (posicion == 1) {
            posicionesEntrada[0] = posicionEntrada;
        } else {
            posicionesEntrada[1] = posicionEntrada;
        }
    }

    public Double[][] procesarTablaSimplex(Double[][] matriz, int filaPivote, int columnaPivote) {
        int filas = matriz.length;
        int columnas = matriz[0].length;

        //Dividir la fila pivote por el elemento pivote
        double elementoPivote = matriz[filaPivote][columnaPivote];
        for (int j = 0; j < columnas; j++) {
            matriz[filaPivote][j] /= elementoPivote;
        }

        //Actualizar las demás filas
        for (int i = 0; i < filas; i++) {
            if (i != filaPivote) { // No estamos en la fila pivote
                double factor = matriz[i][columnaPivote];
                for (int j = 0; j < columnas; j++) {
                    matriz[i][j] -= factor * matriz[filaPivote][j];
                }
            }
        }

        return matriz;
    }

}
