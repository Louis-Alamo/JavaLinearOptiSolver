/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_grafico;

import herramientas.OperacionesPlanoCartesiano;

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

/**
 * @author louis
 */
public class Plano extends JPanel {

    private List<Restriccion> listaRestricciones = new ArrayList<>();
    private Double x_mayor, y_mayor;//variables que se guardaran para cuando encuentre la manera de automatizar la escala
    private int cont = 0;
    private int escala = 40; //una unidad en el plano equivale a 20px en el panel
    private int divisor = 0;
    private int centro_x = 60, centro_y = 400;//temporal
    private OperacionesPlanoCartesiano operacionesPlano = new OperacionesPlanoCartesiano();
    private List<List<Double>> matriz_intersecciones = new ArrayList<>();
    private String[] interseccionCercana;
    private Graphics grafico;

    public Plano() {

        this.x_mayor = 0.0;
        this.y_mayor = 0.0;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        grafico = g;
        float grosorLinea = 3.0f;
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(grosorLinea));

        obtener_escala();
        dibujarEjes(g);
        dibujarCuadricula(g, g2d, grosorLinea);
        dibujarLineasFunciones(g, g2d, grosorLinea);
        dibujarAreaSoulucion(g);

    }

    private void dibujarEjes(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawLine(0, 400, 500, 400);
        g.drawLine(60, 0, 60, 500);
    }

    private void dibujarCuadricula(Graphics g, Graphics2D g2d, float grosorLinea) {
        cont = -1;
        for (int i = 40; i <= 500; i += escala) {

            grosorLinea = 1.0f;
            g2d.setStroke(new BasicStroke(grosorLinea));

            g.setColor(Color.BLACK);

            //g.drawLine(i, 400 - 5, i, 400 + 5);
            g.drawLine(60 - 5, i, 60 + 5, i);

            //g.setColor(Color.ORANGE);
            //String text = String.valueOf(cont * divisor);
            //g.drawString(text, i, 400 + 15);
            //System.out.println("");
            cont++;

        }

        cont = -1;
        for (int i = 0; i <= 500; i += escala) {
            grosorLinea = 1.0f;
            g2d.setStroke(new BasicStroke(grosorLinea));
            g.setColor(Color.BLACK);

            if (cont == 0) {
                i = 60;
                g.drawLine(i, 400 - 5, i, 400 + 5);
                //g.drawLine(60 - 5, i, 60 + 5, i);
                cont++;

            } else {

                g.drawLine(i, 400 - 5, i, 400 + 5);
                //g.drawLine(60 - 5, i, 60 + 5, i);

                g.setColor(Color.ORANGE);
                String text = String.valueOf(cont * divisor);
                g.drawString(text, i, 400 + 15);
                System.out.println("");
                cont++;
            }

        }

        cont = 1;
        for (int i = 380; i >= 0; i -= escala) {

            g.drawString(String.valueOf((cont++) * divisor), 60 - 20, i - 10);
        }

    }

    private void obtener_escala() {

        for (Restriccion obj : listaRestricciones) {
            if (x_mayor < obj.getX()) {
                x_mayor = obj.getX();
            }
            if (y_mayor < obj.getY()) {
                y_mayor = obj.getY();
            }
        }

        if ((x_mayor > 15 || y_mayor > 15) && (x_mayor < 99 && y_mayor < 19)) {
            this.divisor = 10;

        } else if (x_mayor >= 100 || y_mayor >= 100) {
            divisor = 100;

        } else {
            divisor = 1;

        }

        //System.out.println("X: " + this.x_mayor + "\nY: " + this.y_mayor);
    }

    private void dibujarLineasFunciones(Graphics g, Graphics2D g2d, float grosorLinea) {
        for (Restriccion obj : listaRestricciones) {
            int x = (int) ((obj.getX() / divisor) * escala);
            int y = (int) ((obj.getY() / divisor) * escala);

            g.setColor(Color.red);
            grosorLinea = 2.0f;
            g2d.setStroke(new BasicStroke(grosorLinea));

            g.drawLine(centro_x + x, centro_y, centro_x, centro_y - y);

        }
    }

    private void dibujarAreaSoulucion(Graphics g) {
        if (interseccionCercana != null) {
            Double[] xyminimos = operacionesPlano.obtenerXYMinimos(listaRestricciones);

            int xmin = (int) ((xyminimos[0] / divisor) * escala);
            int ymin = (int) ((xyminimos[1] / divisor) * escala);

            int xinterseccion = (int) ((Double.parseDouble(interseccionCercana[0]) / divisor) * escala);
            int yinterseccion = (int) ((Double.parseDouble(interseccionCercana[1]) / divisor) * escala);

            int[] puntosX = {centro_x, centro_x + xmin, xinterseccion + centro_x, centro_x};
            int[] puntosY = {centro_y, centro_y, centro_y - yinterseccion, centro_y - ymin};
            int numPuntos = 4;

            // Rellena la figura deforme
            g.setColor(Color.BLUE);
            g.fillPolygon(puntosX, puntosY, numPuntos);

            // Dibuja los bordes de la figura
            g.setColor(Color.BLACK);
            g.drawPolygon(puntosX, puntosY, numPuntos);
            System.out.println(xyminimos[0] + " , " + xyminimos[1]);
        } else {
            Double[] xyminimos = operacionesPlano.obtenerXYMinimos(listaRestricciones);

            int xmin = (int) ((xyminimos[0] / divisor) * escala);
            int ymin = (int) ((xyminimos[1] / divisor) * escala);

            int[] puntosX = {centro_x, centro_x + xmin, centro_x};
            int[] puntosY = {centro_y, centro_y, centro_y - ymin};
            int numPuntos = 3;
            
            g.setColor(Color.BLUE);
            g.fillPolygon(puntosX, puntosY, numPuntos);
            
                        g.setColor(Color.BLACK);
            g.drawPolygon(puntosX, puntosY, numPuntos);

        }

    }

    public void agregarArreglo(List<Restriccion> listaRestricciones) {
        this.listaRestricciones = listaRestricciones;
        this.obtener_escala();
        repaint();
    }

    public void setInterseccionCercana(String[] interseccionCercana) {
        this.interseccionCercana = interseccionCercana;
    }
}
