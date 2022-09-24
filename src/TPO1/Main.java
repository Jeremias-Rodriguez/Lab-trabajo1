/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import Interfaz.Interfaz;
 
/**
 *
 * @author repetto.francisco
 */
public class Main {
    public static void main(String[] args) {
        int
            cantidadPiqueteros = 2,
            cantidadSindicatos = 4;
        
        Interfaz prog = new Interfaz();
        prog.iniciar(cantidadPiqueteros, cantidadSindicatos);
        
        
    }
}
