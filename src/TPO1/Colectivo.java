/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 *
 * @author jerexio
 */
public class Colectivo {
    private Ubicacion ubicacion;
    private int velocidad;

    public Colectivo(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
        this.velocidad = 0;
    }
    
    public void parar(){
        velocidad = 0;
    }
    
    public void seguir(){
        velocidad = 1;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }
}
