/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TPO1;

/**
 * Esta clase tiene la funcionalidad de ser un indicador de donde esta un objeto
 * actualmente en la ruta.
 * Algunos de sus metodos estan sincronizados ya que puede ocurrir
 * que el chofer y el usuario entren en conflicto al momento de doblar
 * @author jerexio
 * @author repetto.francisco
 */
public class Ubicacion {
    private String nombreRuta;
    private int posX;
    private int posY;
    
    public Ubicacion(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }
    
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
