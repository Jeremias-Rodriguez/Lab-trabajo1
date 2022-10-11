/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package TPO1;

/**
 * La interfaz EstrategiaConduccion, es parte de un aplicacion del patron
 * Strategy.
 * Implementada en las clases Doblar, Seguir y Esperar
 * 
 * @author jerexio
 * @author repetto.francisco
 */
public interface EstrategiaCoduccion {
    public void conducir(Colectivo colectivo);
}
