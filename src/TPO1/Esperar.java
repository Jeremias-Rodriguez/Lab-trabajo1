/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author jerexio
 */
public class Esperar implements EstrategiaCoduccion {

    @Override
    public void conducir(Colectivo colectivo) {
        colectivo.setVelocidad(0);
    }
}
