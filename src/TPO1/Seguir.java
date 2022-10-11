/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * La clase Seguir es una implementacion de la interfaz EstrategiaConduccion.
 * Su funcion es implementar la estrategia para avanzar con el colectivo
 * 
 * @author jerexio
 * @author repetto.francisco
 */
public class Seguir implements EstrategiaCoduccion {

    @Override
    public void conducir(Colectivo colectivo) {
        colectivo.setVelocidad(1);
    }
    
}
