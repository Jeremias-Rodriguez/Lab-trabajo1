/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * Esta clase representa al colectivo.
 * Es el objeto sobre el que se aplican las estrategias, es controlado por el
 * usuario. Y es manejado por la clase chofer
 * 
 * @author jerexio
 * @author repetto.francisco 
 */
public class Colectivo {
    private Ubicacion posActual;
    private int velocidad;

    public Colectivo(Ubicacion ubicacion, Mapa mapa) {
        this.posActual = ubicacion;
        this.velocidad = 0;
    }
    
    public synchronized void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    
    public Ubicacion getUbicacion() {
        return posActual;
    }
    
    public synchronized boolean estaEnMovimiento(){
        return velocidad > 0;
    }
    
    public void ejecutarEstrategia(EstrategiaCoduccion estrategia){
        estrategia.conducir(this);
    }
}
