package metodo_grafico;

import java.awt.Color;

public class Restriccion {
    private Double X, Y;
    private Color color;
    private String[] partes_funcion;
    private static int num = 0; // Variable estática para contar instancias
    

    public Restriccion(Double[] cordenadas, String[] partes) {
        this.X = cordenadas[0];
        this.Y = cordenadas[1];
        this.color = Color.BLACK; // Color predeterminado
        this.partes_funcion = partes;
        num++; // Incrementamos el contador de instancias para que haci cada Linea tenga un numero
    }

    public Restriccion(Double[] cordenadas, String[] partes, Color color) {
        this(cordenadas, partes);
        this.color = color;
    }

    public Double getX() {
        return X;
    }

    public Double getY() {
        return Y;
    }

    public Color getColor() {
        return color;
    }

    public static int getNum() {
        return num;
    }

    public String[] getPartes_funcion() {
        return partes_funcion;
    }

    public void mostrar_datos() {
        System.out.println("Num Línea: " + num + ", X: " + X + ", Y: " + Y + ", Color: " + color);
        System.out.println("Función: " + partes_funcion[0] + partes_funcion[1] + partes_funcion[2] + partes_funcion[3] + "=" + partes_funcion[4]);
    }
}
