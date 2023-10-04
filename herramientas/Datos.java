/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package herramientas;

import java.util.List;

/**
 *
 * @author louis
 */
public class Datos {
    private static int cantidadRestricciones;
    private static String funcionZ;
    private static List<String> restricciones;
    
    public Datos(List<String> restricciones, String funcionZ){
        this.funcionZ = funcionZ;
        this.restricciones = restricciones;
        this.cantidadRestricciones = restricciones.size();
        
    }

    public static int getCantidadRestricciones() {
        return cantidadRestricciones;
    }

    public static void setCantidadRestricciones(int cantidadRestricciones) {
        Datos.cantidadRestricciones = cantidadRestricciones;
    }

    public static void setFuncionZ(String funcionZ) {
        Datos.funcionZ = funcionZ;
    }

    public static void setRestricciones(List<String> restricciones) {
        Datos.restricciones = restricciones;
    }

    public static String getFuncionZ() {
        return funcionZ;
    }

    public static List<String> getRestricciones() {
        return restricciones;
    }
    
    public static String obtenerRestriccionSeparada(int indice){
        
        return Datos.restricciones.get(indice);
    }
    
    
}
