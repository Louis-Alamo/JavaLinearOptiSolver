/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ventanas;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import metodo_grafico.Ventana;
import metodo_simplex.Tabla;
import herramientas.Datos;
import herramientas.ProcesadorDeFunciones;
import excepciones.FormatoRestriccionInvalidoException;

/**
 *
 * @author louis
 */
public class AgregarRestriccionesGUI extends javax.swing.JFrame {

    /**
     * Creates new form AgregarRestriccionesGUI
     */
    private int cantRestricciones;
    private String funcionZ;
    private List<String> restricciones = new ArrayList<>();
    private ProcesadorDeFunciones utilerias = new ProcesadorDeFunciones();
    private boolean bandera = false;

    public AgregarRestriccionesGUI(int cantRestricciones, String funcionz) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.continuar.setEnabled(false);
        this.cantRestricciones = cantRestricciones;
        this.funcionZ = funcionz;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        entrada = new javax.swing.JTextField();
        agregar = new javax.swing.JButton();
        continuar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        metodos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Metodo: ");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        continuar.setText("Continuar");
        continuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                continuarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Restriccion:");

        metodos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "Metodo grafico", "Metodo simplex" }));
        metodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                metodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(continuar)
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(metodos, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(metodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(entrada, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(continuar))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        try {
            if (utilerias.formatoFuncionValido(entrada.getText())) {

                restricciones.add(this.entrada.getText());
                if (comprobarCantidadRestricciones() && bandera == true) {

                    Datos.setFuncionZ(funcionZ);
                    Datos.setRestricciones(restricciones);
                    Datos.setCantidadRestricciones(cantRestricciones);

                    this.agregar.setEnabled(false);
                    this.continuar.setEnabled(true);
                }
                JOptionPane.showMessageDialog(null, "Agregado con exito");
                this.entrada.setText("");
                
            } else {
                throw new FormatoRestriccionInvalidoException("Exception: Formato de la reestriccion invalido");
               
            }
        }catch(FormatoRestriccionInvalidoException e){
            JOptionPane.showMessageDialog(null, "Ingrese una restriccion con el formato: 2x+y<=10");
        }


    }//GEN-LAST:event_agregarActionPerformed

    private void continuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_continuarActionPerformed
        if (this.metodos.getSelectedItem().toString() == "Metodo grafico") {
            Ventana ventana = new Ventana();
            this.dispose();

        } else if (this.metodos.getSelectedItem().toString() == "Metodo simplex") {
            Tabla tabla = new Tabla();
            this.dispose();
        }

    }//GEN-LAST:event_continuarActionPerformed

    private void metodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_metodosActionPerformed
        if (!"...".equals(this.metodos.getSelectedItem().toString())) {
            bandera = true;
        } else if (comprobarCantidadRestricciones() && entrada.getText().isEmpty()) {
            this.continuar.setEnabled(true);
        }
    }//GEN-LAST:event_metodosActionPerformed

    private boolean comprobarCantidadRestricciones() {

        return restricciones.size() == cantRestricciones;
    }
    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton continuar;
    private javax.swing.JTextField entrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> metodos;
    // End of variables declaration//GEN-END:variables
}