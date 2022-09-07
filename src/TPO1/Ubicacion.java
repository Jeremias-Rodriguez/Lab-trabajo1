/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * Esta clase tiene 2 principales funcionalidades
 * - Ser un indicador de donde esta un objeto actualmente en la ruta
 * - Indicar en que puntos del mapa se puede doblar
 * @author jerexio
 */
public class Ubicacion {
    private String nombreRuta;
    private int posX;
    private int posY;

    public Ubicacion(String nombreRuta, int posX, int posY) {
        this.nombreRuta = nombreRuta;
        this.posX = posX;
        this.posY = posY;
    }
    
    public String getNombreRuta() {
        return nombreRuta;
    }

    public synchronized void actualizarUbicacion(int x, int y){
        posX = x;
        posY = y;
    }

    public synchronized int getPosX() {
        return posX;
    }

    public synchronized int getPosY() {
        return posY;
    }
}
