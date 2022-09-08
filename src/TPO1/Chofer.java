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
public class Chofer extends Thread {
    private Colectivo colectivo;
    private Mapa mapa;
    private Interfaz interfaz;

    public Chofer(Colectivo colectivo, Mapa mapa, Interfaz interfaz) {
        this.colectivo = colectivo;
        this.mapa = mapa;
        this.interfaz = interfaz;
    }
    
    public void run(){
        boolean llegoADestino = false;
        Ubicacion posActual = colectivo.getUbicacion();
        
        while(!llegoADestino){
            if(colectivo.estaEnMovimiento()){
                desplazar(posActual);
                interfaz.moverColectivo(posActual.getPosY()*50, posActual.getPosX()*100);
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
    private boolean desplazar(Ubicacion posActual){
        int actualX = posActual.getPosX(),
            actualY = posActual.getPosY();
        
        boolean seMovio = false;
        
        if(colectivo.estaEnMovimiento()){
            if(mapa.desplazarColectivo(actualX, actualY, actualX, actualY+1)){
                posActual.actualizarUbicacion(actualX, actualY+1);
                pararEnEsquina(actualX, actualY+1);
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
                        colectivo.setVelocidad(0);
                }
            }
            
            seMovio = true;
        }
        
        return seMovio;
    }
    
    public void pararEnEsquina(int posX, int posY){
        
        if(mapa.esEsquina(posX, posY)){
            colectivo.ejecutarEstrategia(new Esperar());
        }
        
    }
}
