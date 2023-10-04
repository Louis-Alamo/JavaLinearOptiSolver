package metodo_simplex;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InterfazSimplex extends JFrame {

    private JTable tablaMatriz;
    private DefaultTableModel tableModel;
    private JLabel labelX, labelY, labelZ;

    public InterfazSimplex(Double[][] matrizFinal, double valorX, double valorY) {
        setTitle("Resultados Método Simplex");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 200); 
        setLocationRelativeTo(null);

        tablaMatriz = new JTable();
        tableModel = new DefaultTableModel();

        // Configurar la tabla con la matriz final
        tableModel.setRowCount(matrizFinal.length);
        tableModel.setColumnCount(matrizFinal[0].length + 1);

        // Agregamos "Z" en la primera fila de la primera columna
        tableModel.setValueAt("Z", 0, 0);

        // Llenamos las demás filas de la primera columna con "S1", "S2", etc.
        for (int i = 1; i < matrizFinal.length; i++) {
            if (i == valorX) {
                tableModel.setValueAt("X", i, 0);
            } else if (i == valorY) {
                tableModel.setValueAt("Y", i, 0);
            } else {
                tableModel.setValueAt("S" + i, i, 0);
            }
        }

        for (int i = 0; i < matrizFinal.length; i++) {
            for (int j = 0; j < matrizFinal[0].length; j++) {
                tableModel.setValueAt(matrizFinal[i][j], i, j + 1); // Comenzamos desde la segunda columna

            }
        }

        tablaMatriz.setModel(tableModel);

        // Cambiar el encabezado de las columnas
        String[] columnNames = new String[matrizFinal[0].length + 1];
        columnNames[0] = ""; // La primera columna no tiene título
        for (int i = 1; i < columnNames.length; i++) {
            if (i == 1) {
                columnNames[i] = "Z";
            } else if (i == 2) {
                columnNames[i] = "X";
            } else if (i == 3) {
                columnNames[i] = "Y";
            } else if (i == columnNames.length - 1) {
                columnNames[i] = "Resultado";
            } else {
                columnNames[i] = "S" + (i - 3);
            }
        }
        tableModel.setColumnIdentifiers(columnNames);


        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BorderLayout());
        panelSuperior.add(new JScrollPane(tablaMatriz), BorderLayout.CENTER);

        JPanel panelAbajo = new JPanel();
        panelAbajo.setBackground(Color.WHITE);
        panelSuperior.setBackground(Color.WHITE);

        if (valorX == 0) {
            labelX = new JLabel("X: No entro");
            labelX.setFont(new Font("Andale mono", 3, 16));
            panelAbajo.add(labelX);
        } else {
            labelX = new JLabel("X: " + matrizFinal[(int) valorX][matrizFinal[0].length - 1]);
            labelX.setFont(new Font("Andale mono", 3, 16));
            panelAbajo.add(labelX);
        }

        if (valorY == 0) {
            labelY = new JLabel("Y: No entro");
            labelY.setFont(new Font("Andale mono", 3, 16));
            panelAbajo.add(labelY);
        } else {
            labelY = new JLabel("Y: " + matrizFinal[(int) valorY][matrizFinal[0].length - 1]);
            labelY.setFont(new Font("Andale mono", 3, 16));
            panelAbajo.add(labelY);
        }

        labelZ = new JLabel("Z: " + matrizFinal[0][matrizFinal[0].length - 1]);
        labelZ.setFont(new Font("Andale mono", 3, 16));
        panelAbajo.add(labelZ);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(panelSuperior, BorderLayout.CENTER);
        contentPane.add(panelAbajo, BorderLayout.SOUTH);

        setContentPane(contentPane);
    }

}
