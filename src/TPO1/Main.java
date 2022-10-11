/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TPO1;

import Interfaz.Interfaz;
 
/**
 *
 * @author jerexio
 * @author repetto.francisco
 */
public class Main {
    public static void main(String[] args) {
        int cantidadPiqueteros = 10,
            cantidadSindicatos = 100,
            sizeMatrizFila = 7,
            sizeMatrizCol = 13,
            pixelesMoveX = 700/sizeMatrizFila,
            pixelesMoveY = 1300/sizeMatrizCol;
        
        Interfaz prog = new Interfaz();
        prog.iniciar(cantidadPiqueteros, cantidadSindicatos,
                sizeMatrizFila, sizeMatrizCol,
                pixelesMoveX, pixelesMoveY);
        
        
    }
}
