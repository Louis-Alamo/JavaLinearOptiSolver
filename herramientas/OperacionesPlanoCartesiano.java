package herramientas;
import java.util.List;
import java.util.List;
import metodo_grafico.Restriccion;

public class OperacionesPlanoCartesiano {

    public static Double[] encontrarInterseccion(String[] funcion_a, String[] funcion_b) {
        // Extraer los coeficientes a, b y c de ambas funciones
        Double a1 = Double.parseDouble(funcion_a[0]);
        Double b1 = Double.parseDouble(funcion_a[2]);
        Double c1 = Double.parseDouble(funcion_a[4]);

        Double a2 = Double.parseDouble(funcion_b[0]);
        Double b2 = Double.parseDouble(funcion_b[2]);
        Double c2 = Double.parseDouble(funcion_b[4]);

        // Calcular el determinante principal
        Double determinantePrincipal = (a1 * b2) - (a2 * b1);

        if (determinantePrincipal == 0) {
            // Las líneas son paralelas, no tienen intersección
            return null;
        }

        // Calcular los determinantes para las coordenadas x e y
        Double determinanteX = (c1 * b2) - (c2 * b1);
        Double determinanteY = (a1 * c2) - (a2 * c1);

        // Calcular las coordenadas de intersección
        Double x = determinanteX / determinantePrincipal;
        Double y = determinanteY / determinantePrincipal;

        Double[] interseccion = {x, y};
        return interseccion;
    }

    public static Double[] obtenerCordenadasFuncion(String forma_a, String forma_b, Double forma_ac, Double forma_bc, Double c) {
        Double cordenada_x = 0.0, cordenada_y = 0.0;
        //empezando con x4

        if (forma_ac == 1) {
            cordenada_x = c;

        } else if (forma_ac == -1) {
            cordenada_x = -1 * c;

        } else if (forma_ac != 1 && forma_ac > 1) {
            cordenada_x = c / forma_ac;

        } else if (forma_ac < 0 && forma_ac != 1) {
            cordenada_x = c / forma_ac;
        }

        //empezando con y
        if (forma_bc == 1) {
            cordenada_y = c;

        } else if (forma_bc == -1) {
            cordenada_y = -1 * c;

        } else if (forma_bc != 1 && forma_bc > 1) {
            cordenada_y = c / forma_bc;

        } else if (forma_bc < 0 && forma_bc != 1) {
            cordenada_y = c / forma_bc;
        }
        Double[] cordenadas = new Double[2];
        cordenadas[0] = cordenada_x;
        cordenadas[1] = cordenada_y;

        return cordenadas;
    }

    public static Double[] obtenerXYMinimos(List<Restriccion> listaRestricciones) {
        if (listaRestricciones.isEmpty()) {
            return null; // Devolver null si la lista está vacía
        }

        Double minX = listaRestricciones.get(0).getX();
        Double minY = listaRestricciones.get(0).getY();

        for (Restriccion restriccion : listaRestricciones) {
            if (restriccion.getX() < minX) {
                minX = restriccion.getX();
            }
            if (restriccion.getY() < minY) {
                minY = restriccion.getY();
            }
        }

        Double[] minimos = {minX, minY};
        return minimos;
    }

    public static String[] encontrarInterseccionMasCercana(List<String[]> intersecciones) {
        double distanciaMinima = Double.MAX_VALUE;
        String[] interseccionMasCercana = null;

        for (String[] interseccion : intersecciones) {
            double x = Double.parseDouble(interseccion[0]);
            double y = Double.parseDouble(interseccion[1]);
            double distancia = Math.sqrt(x * x + y * y);

            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                interseccionMasCercana = interseccion;
            }
        }

        return interseccionMasCercana;
    }
}
