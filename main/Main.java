package main;


import javax.swing.SwingUtilities;
import ventanas.PedirDatosGUI;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author louis
 */
public class Main {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PedirDatosGUI ventana = new PedirDatosGUI();
            ventana.setVisible(true);
        });
    }
}
