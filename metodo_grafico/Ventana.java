/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_grafico;


import herramientas.ProcesadorDeFunciones;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import herramientas.Datos;
import herramientas.Utilerias;
import herramientas.OperacionesPlanoCartesiano;
import java.awt.event.*;
import metodo_simplex.Tabla;

public class Ventana extends JFrame implements ActionListener{

    private Plano panel = new Plano();
    private JTable tablaIntersecciones, tablaRestricciones;
    private ProcesadorDeFunciones utilerias = new ProcesadorDeFunciones();
    private List<Restriccion> listaRestricciones = new ArrayList<>();
    private JPanel botonPanel;
    private JLabel interseccionesText, restriccionesText, solucionText, mostrarSolucionText, funcionzText, mostrarFuncionZText;
    private OperacionesPlanoCartesiano opera = new OperacionesPlanoCartesiano();
    private String[] interseccionSolucion = new String[2];
    private JButton mostrarSimplex;

    public Ventana() {
        setTitle("Graficador java");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(750, 500));
        setBounds(0, 0, 750, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setBackground(new Color(255, 255, 255));

        Border borde = BorderFactory.createLineBorder(Color.BLACK, 2);

        panel.setBorder(borde);

       
        setLayout(new BorderLayout());
        add(panel, BorderLayout.WEST);

      
        panel.setPreferredSize(new java.awt.Dimension(500, 500));
        panel.setBackground(new Color(255, 255, 255));
        
        botonPanel = new JPanel(new BorderLayout());
        botonPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.WEST);

        
        interseccionesText = new JLabel("Intersecciónes");
        interseccionesText.setBounds(50, 18, 150, 20);
        interseccionesText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(interseccionesText);

        botonPanel.setBackground(Color.white);

        restriccionesText = new JLabel("Restricciones");
        restriccionesText.setBounds(50, 169, 150, 20);
        restriccionesText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(restriccionesText);

        solucionText = new JLabel("Solucion");
        solucionText.setBounds(70, 381, 150, 20);
        solucionText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(solucionText);

        funcionzText = new JLabel("Funcion Z");
        funcionzText.setBounds(70, 294, 150, 20);
        funcionzText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(funcionzText);

        mostrarFuncionZText = new JLabel(Datos.getFuncionZ());
        mostrarFuncionZText.setBounds(60, 332, 100, 25);
        mostrarFuncionZText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(mostrarFuncionZText);
        
        mostrarSimplex = new JButton("tabla");
        mostrarSimplex.setBounds(160,332, 100, 20);
        mostrarSimplex.addActionListener(this);
        botonPanel.add(mostrarSimplex);

        solucionText = new JLabel("Solucion");
        solucionText.setBounds(70, 381, 100, 20);
        solucionText.setFont(new Font("Andale mono", 3, 16));
        botonPanel.add(solucionText);

        add(botonPanel);
        procesarFuncion();

        pack(); 
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == mostrarSimplex){
            Tabla mensajero = new Tabla();
        }
    }

    private void procesarFuncion() {
        int cont = 0;
        while (cont < Datos.getCantidadRestricciones()) {
            Restriccion linea = utilerias.procesarFuncionTotal(Datos.obtenerRestriccionSeparada(cont));
            listaRestricciones.add(linea);
            cont++;
        }
        crearTablas();

        panel.agregarArreglo(listaRestricciones);
        panel.setInterseccionCercana(interseccionSolucion);
    }

    private void crearTablas() {
        List<String[]> interseccionesUnicas = new ArrayList<>();
        List<Restriccion> restricciones = listaRestricciones; 
        double tolerancia = 0.0001; 

        for (Restriccion restriccion1 : restricciones) {
            for (Restriccion restriccion2 : restricciones) {
                if (restriccion1 != restriccion2) {
                    Double[] interseccion = OperacionesPlanoCartesiano.encontrarInterseccion(restriccion1.getPartes_funcion(), restriccion2.getPartes_funcion());
                    if (interseccion != null) {
                        
                        // Formatea las coordenadas de intersección con un solo decimal
                        String x = Utilerias.formatearValor(interseccion[0]);
                        String y = Utilerias.formatearValor(interseccion[1]);
                        String[] interseccionStr = {x, y};

                        // Comprobar si esta intersección ya existe (dentro de la tolerancia)
                        boolean esUnica = true;
                        for (String[] existente : interseccionesUnicas) {
                            double existenteX = Double.parseDouble(existente[0]);
                            double existenteY = Double.parseDouble(existente[1]);
                            double nuevoX = Double.parseDouble(x);
                            double nuevoY = Double.parseDouble(y);

                            if (Utilerias.sonCercanos(existenteX, nuevoX, tolerancia) && Utilerias.sonCercanos(existenteY, nuevoY, tolerancia)) {
                                esUnica = false;
                                break;
                            }
                        }

                        if (esUnica) {
                            interseccionesUnicas.add(interseccionStr);
                        }
                    }
                }
            }
        }

        String[] columnas = {"X", "Y"};

        DefaultTableModel model = new DefaultTableModel(interseccionesUnicas.toArray(new String[0][]), columnas);
        tablaIntersecciones = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tablaIntersecciones);
        scrollPane.setBounds(20, 48, 171, 105);
        botonPanel.add(scrollPane);

        interseccionSolucion = encontrarInterseccionMasCercana(interseccionesUnicas);
        //System.out.println(interseccionSolucion[0] + " , " + interseccionSolucion[1]);

        if (interseccionSolucion != null) {
            
            // Se encontró una intersección
            Double resultado = utilerias.resolverFuncionZ(interseccionSolucion);
            //Double resultado = utilerias.resolverFuncionZ(interseccionSolucion);
            mostrarSolucionText = new JLabel("( " + interseccionSolucion[0] + " , " + interseccionSolucion[1] + " )  Z=" + resultado);
            mostrarSolucionText.setBounds(40, 415, 200, 25);
            mostrarSolucionText.setFont(new Font("Andale mono", 3, 16));
            botonPanel.add(mostrarSolucionText);
        } else {
            // No se encontraron intersecciones
            mostrarSolucionText = new JLabel("");
            mostrarSolucionText.setBounds(40, 415, 200, 25);
            mostrarSolucionText.setFont(new Font("Andale mono", 3, 16));
            botonPanel.add(mostrarSolucionText);
            mostrarSolucionText.setText("No hay soluciones");
        }


        List<String> restriccionesString = Datos.getRestricciones();

// Verifica si la lista de restricciones no es nula y no está vacía
        if (restriccionesString != null && !restriccionesString.isEmpty()) {
            DefaultTableModel modelRestricciones = new DefaultTableModel();
            modelRestricciones.addColumn("Número");
            modelRestricciones.addColumn("Restricción");

            int numeroRestriccion = 1;

            for (String restriccion : restriccionesString) {
                String[] fila = {String.valueOf(numeroRestriccion), restriccion};
                modelRestricciones.addRow(fila);
                numeroRestriccion++;
            }

            // Crear la tabla de restricciones
            tablaRestricciones = new JTable(modelRestricciones);
            JScrollPane scrollPaneRestricciones = new JScrollPane(tablaRestricciones);
            scrollPaneRestricciones.setBounds(20, 203, 171, 75); // Ajusta las dimensiones según tus necesidades
            botonPanel.add(scrollPaneRestricciones);
            
        } 

    }

    private String[] encontrarInterseccionMasCercana(List<String[]> intersecciones) {
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
