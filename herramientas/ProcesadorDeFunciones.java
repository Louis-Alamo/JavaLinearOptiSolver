/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

import javax.swing.JOptionPane;
import metodo_grafico.Restriccion;

/**
 * @author louis
 */
public class ProcesadorDeFunciones {

    public String obtenerSignoFuncion(String cadena) {
        int signoIndex = cadena.indexOf('+', 1);

        if (signoIndex == -1) {
            signoIndex = cadena.indexOf('-', 1);
        }
        return (signoIndex != -1) ? cadena.substring(signoIndex, signoIndex + 1) : "";
    }

    public boolean partesFuncionValido(String[] cadena) {

        return cadena.length == 2;
    }

    public String[] dividirFuncion(String cadena, String separador) {
        String[] partes = new String[2];

        int indiceCuchillo = cadena.indexOf(separador);

        if (indiceCuchillo != -1) {
            partes[0] = cadena.substring(0, indiceCuchillo);
            partes[1] = cadena.substring(indiceCuchillo + separador.length());
        } else {
            partes[0] = "";
            partes[1] = "";
        }

        return partes;
    }

    public String[] separarConstantes(String parte, char variableBusqueda) {
        String forma = "";
        String formaC = "";

        // Algoritmo para variable de búsqueda (variableBusqueda)
        if (parte.length() == 1) {
            forma = parte;
            formaC = "1";
        } else {
            int startIndex = (parte.charAt(0) == '-') ? 1 : 0;
            int endIndex = parte.indexOf(variableBusqueda, startIndex);

            if (endIndex != -1) {
                forma = parte.substring(endIndex, endIndex + 1);
                formaC = parte.substring(startIndex, endIndex);
            }
        }

        String[] formas = {forma, formaC};
        return formas;
    }

    public Restriccion procesarFuncionTotal(String funcion) {
        try {

            if (funcion != null && !funcion.isEmpty()) { // Verificar si no esta vacio o nulo
                String[] partes = funcion.split("<=");

                if (partesFuncionValido(partes)) {
                    String parte0 = partes[0].trim();
                    double c = Double.parseDouble(partes[1].trim());

                    String signo = obtenerSignoFuncion(parte0);

                    partes = dividirFuncion(parte0, signo.trim());

                    String[] parte_x = separarConstantes(partes[0], 'x');
                    String[] parte_y = separarConstantes(partes[1], 'y');

                    Double[] cordenadas = OperacionesPlanoCartesiano.obtenerCordenadasFuncion(parte_x[0], parte_y[0], Double.parseDouble(parte_x[1]), Double.parseDouble(parte_y[1]), c);

                    Double x = cordenadas[0];
                    Double y = cordenadas[1];

                    String[] funcion_partes = {String.valueOf(parte_x[1]), parte_x[0], String.valueOf(parte_y[1]), parte_y[0], String.valueOf(c)};

                    // Crear un objeto de tipo Resttriccion con las coordenadas obtenidas
                    return new Restriccion(cordenadas, funcion_partes);

                   
                } else {
                    System.out.println("Error: Formato de función incorrecto.");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Ingrese un número válido para 'c'.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error: La función debe estar en formato 'f(x) = ax + by + c'.");
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex.getMessage());
        }
        return null;
    }

    public String[] procesarFuncionTotalString(String funcion) {
        try {

            if (funcion != null && !funcion.isEmpty()) { // Verificar si no esta vacio
                String[] partes = funcion.split("<=");

                if (partesFuncionValido(partes)) {
                    String parte0 = partes[0].trim();
                    double c = Double.parseDouble(partes[1].trim());

                    String signo = obtenerSignoFuncion(parte0);

                    partes = dividirFuncion(parte0, signo.trim());

                    String[] parte_x = separarConstantes(partes[0], 'x');
                    String[] parte_y = separarConstantes(partes[1], 'y');

                    Double[] cordenadas = OperacionesPlanoCartesiano.obtenerCordenadasFuncion(parte_x[0], parte_y[0], Double.parseDouble(parte_x[1]), Double.parseDouble(parte_y[1]), c);

                    Double x = cordenadas[0];
                    Double y = cordenadas[1];

                    String[] funcion_partes = {String.valueOf(parte_x[1]), parte_x[0], String.valueOf(parte_y[1]), parte_y[0], String.valueOf(c)};

                    
                    return funcion_partes;

                    
                } else {
                    System.out.println("Error: Formato de función incorrecto.");
                }
            }
        } catch (NumberFormatException ex) {
            System.out.println("Error: Ingrese un número válido para 'c'.");
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println("Error: La función debe estar en formato 'f(x) = ax + by + c'.");
        } catch (Exception ex) {
            System.out.println("Error desconocido: " + ex.getMessage());
        }
        return null;
    }

    public String[] procesarFuncionZ(String funcionZ) {
        String[] funcionProcesada = new String[3];

        String[] funcionZPartes = funcionZ.split("=");

        String signo = obtenerSignoFuncion(funcionZPartes[1]);

        funcionZPartes = dividirFuncion(funcionZPartes[1], signo.trim());

        String[] parte_x = separarConstantes(funcionZPartes[0], 'x');
        String[] parte_y = separarConstantes(funcionZPartes[1], 'y');

        funcionProcesada[0] = parte_x[1];
        funcionProcesada[1] = signo;
        funcionProcesada[2] = parte_y[1];

        return funcionProcesada;

    }

    public Double resolverFuncionZ(String[] interseccionSolucion) {
        String funcionZ = Datos.getFuncionZ();
        String[] funcionz = procesarFuncionZ(funcionZ);
        if (interseccionSolucion != null) {

            Double resultado = 0.0;
            if (funcionz[1].equals("+")) {
                resultado = (Double.parseDouble(funcionz[0]) * Double.parseDouble(interseccionSolucion[0])) + (Double.parseDouble(funcionz[2]) * Double.parseDouble(interseccionSolucion[1]));
                return resultado;
            } else {
                resultado = (Double.parseDouble(funcionz[0]) * Double.parseDouble(interseccionSolucion[0])) - (Double.parseDouble(funcionz[1]) * Double.parseDouble(interseccionSolucion[1]));
                return resultado;
            }
        }
        return 0.0;
    }

    public boolean FormatoFuncionZValido(String funcionZ) {

        if (!funcionZ.isEmpty() && funcionZ != null) {
            String[] partes = funcionZ.split("=");
            if (partesFuncionValido(partes)) {

                if (Utilerias.cantidadRepetidasCaracter('z', funcionZ) == 1
                        && Utilerias.cantidadRepetidasCaracter('x', funcionZ) == 1
                        && Utilerias.cantidadRepetidasCaracter('y', funcionZ) == 1) {

                    if (Utilerias.contieneCaracteresNoPermitidos(funcionZ)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean formatoFuncionValido(String funcion) {

        if (!funcion.isEmpty() && funcion != null) {
            String[] partes = funcion.split("<=");
            if (partesFuncionValido(partes)) {

                if (Utilerias.cantidadRepetidasCaracter('x', funcion) == 1
                        && Utilerias.cantidadRepetidasCaracter('y', funcion) == 1) {

                    if (Utilerias.contieneCaracteresNoPermitidos(funcion)) {
                        return true;
                    }
                }
            }
        }
        return false;

    }
}
