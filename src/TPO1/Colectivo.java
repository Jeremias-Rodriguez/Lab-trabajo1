/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import Interfaz.Interfaz;


/**
 *
 * @author jerexio
 */
public class Colectivo extends Thread {
    private Ubicacion posActual;
    private int velocidad;
    private Mapa mapa;
    private Interfaz interfaz;

    public Colectivo(Ubicacion ubicacion, Mapa mapa) {
        this.posActual = ubicacion;
        this.mapa = mapa;
        this.velocidad = 0;
    }

    public void setInterfaz(Interfaz interfaz) {
        this.interfaz = interfaz;
    }
    
    public void setVelocidad(int velocidad){
        this.velocidad = velocidad;
    }
    
    public Ubicacion getUbicacion() {
        return posActual;
    }
    
    public boolean estaEnMovimiento(){
        return velocidad > 0;
    }
    
    public void ejecutarEstrategia(EstrategiaCoduccion estrategia){
        estrategia.conducir(this);
    }
    
    public void run(){
        boolean llegoADestino = false;
        
        while(!llegoADestino){
            if(velocidad > 0){
                desplazar();
                System.out.println(posActual.getPosY()*100);
                System.out.println(posActual.getPosX()*100);
                interfaz.moverColectivo(posActual.getPosY()*100, posActual.getPosX()*100);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.err.println("El colectivo pincho para siempre");
            }
            llegoADestino = mapa.esDestino(posActual.getPosX(), posActual.getPosY()+1);
        }
    }
    
    /**
     * Desplazamiento en orden
     * - Hacia adelante
     * - Hacia arriba
     * - Hacia abajo
     */
    private void desplazar(){
        int actualX = posActual.getPosX(),
            actualY = posActual.getPosY();
        
        if(velocidad > 0){
            if(mapa.desplazarColectivo(actualX, actualY, actualX, actualY+1)){
                posActual.actualizarUbicacion(actualX, actualY+1);
            }
            else{
                if(mapa.desplazarColectivoSiArbol(actualX, actualY, actualX+1, actualY+1)){
                    posActual.actualizarUbicacion(actualX+1, actualY);
                }
                else{
                    if(mapa.desplazarColectivoSiArbol(actualX, actualY, actualX-1, actualY+1)){
                        posActual.actualizarUbicacion(actualX-1, actualY);
                    }
                    else
                        velocidad = 0;
                }
            }
        }
    }
}
