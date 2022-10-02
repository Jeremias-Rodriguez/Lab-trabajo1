/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

import Interfaz.Interfaz;

/**
 * Esta clase es un hilo, encargada de mover el colectivo a nivel logico y
 * a nivel grafico.
 * @author jerexio
 * @author repetto.francisco 
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
    
    /**
     * El trabajo del chofer es:
     * Mientras no llego a destino, si el colectivo esta en movimiento lo
     * desplaza a la siguiente posicion a nivel logico y grafico
     */
    public void run(){
        boolean llegoADestino = false;
        Ubicacion posActual = colectivo.getUbicacion();
        
        while(!llegoADestino){
            if(colectivo.estaEnMovimiento()){
                desplazar(posActual);
                interfaz.moverColectivo(posActual.getPosY(), posActual.getPosX());
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
    
    /**
     * Dada una posicion representada con X e Y, en el mapa
     * @param posX --> Fila de la matriz
     * @param posY --> Columna de la matriz
     * Verifica si esta en una esquina, y en caso de estar, se detiene
     */
    public void pararEnEsquina(int posX, int posY){
        
        if(mapa.esEsquina(posX, posY)){
            colectivo.ejecutarEstrategia(new Esperar());
        }
        
    }
}
